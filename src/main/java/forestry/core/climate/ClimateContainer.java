/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core.climate;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import forestry.api.climate.ClimateState;
import forestry.api.climate.ImmutableClimateState;
import forestry.api.climate.EnumClimatiserMode;
import forestry.api.climate.EnumClimatiserType;
import forestry.api.climate.IClimateContainer;
import forestry.api.climate.IClimateContainerListener;
import forestry.api.climate.IClimateSource;
import forestry.api.climate.IClimateSourceProvider;
import forestry.api.climate.IClimatedRegion;
import forestry.core.config.Config;
import forestry.core.network.IStreamable;
import forestry.core.network.PacketBufferForestry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

public class ClimateContainer implements IClimateContainer, IStreamable {
	public static final float CLIMATE_CHANGE = 0.01F;
	
	protected final IClimatedRegion parent;
	protected final ClimateContainerListeners listeners;
	protected final Set<IClimateSource> sources;
	private int delay;
	protected ClimateState state;
	protected ImmutableClimateState targetedState;
	protected ImmutableClimateState range;
	protected ClimateChange change;
	
	/**
	 * Creates an empty region.
	 */
	public ClimateContainer(IClimatedRegion parent) {
		this.parent = parent;
		this.listeners = new ClimateContainerListeners();
		this.sources = new HashSet<>();
		this.delay = 20;
	}


	public ClimateContainer(IClimatedRegion parent, NBTTagCompound nbtTag) {
		this(parent);
		readFromNBT(nbtTag);
	}
	
	@Override
	public IClimatedRegion getParent() {
		return parent;
	}

	@Override
	public void updateClimate(int ticks) {
		if (ticks % getTickDelay() == 0) {
			if(!listeners.isClosed(this)) {
				returnClimateToDefault();
			}else{
				state.add(change);
			}
		}
	}
	
	protected void returnClimateToDefault(){
		ImmutableClimateState defaultState = parent.getDefaultClimate();
		float infoTemp = defaultState.getTemperature();
		float infoHumid = defaultState.getHumidity();
		float posTemp = state.getTemperature();
		float posHumid = state.getHumidity();
		if (posTemp != infoTemp) {
			if (posTemp > infoTemp) {
				state.addTemperature(-Math.min(CLIMATE_CHANGE, posTemp - infoTemp));
			} else {
				state.addTemperature(Math.min(CLIMATE_CHANGE, infoTemp - posTemp));
			}
		}
		if (posHumid != infoHumid) {
			if (posHumid > infoHumid) {
				state.addHumidity(-Math.min(CLIMATE_CHANGE, posHumid - infoHumid));
			} else {
				state.addHumidity(Math.min(CLIMATE_CHANGE, infoHumid - posHumid));
			}
		}
	}
	
	private void addHumidity(float value){
		float humidity = state.getHumidity();
		float targetedHumidity = targetedState.getHumidity();
		if (humidity != targetedHumidity) {
			if (humidity > targetedHumidity) {
				value=Math.min(value, humidity - targetedHumidity);
			} else {
				value=Math.min(value, targetedHumidity - humidity);
			}
			state.addHumidity(value);
		}
	}
	
	private void addTemperature(float value){
		float temperature = state.getTemperature();
		float targetedTemperature = targetedState.getTemperature();
		if (temperature != targetedTemperature) {
			if (temperature > targetedTemperature) {
				value=Math.min(value, temperature - targetedTemperature);
			} else {
				value=Math.min(value, targetedTemperature - temperature);
			}
			state.addTemperature(value);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		state.writeToNBT(nbt);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		state.readFromNBT(nbt);
	}
	
	@Override
	public void setTargetedState(ImmutableClimateState state) {
		this.targetedState = state;
	}
	
	@Override
	public ImmutableClimateState getTargetedState() {
		return targetedState;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	/**
	 * @return The ticks between updates.
	 */
	public int getTickDelay() {
		return delay;
	}
	
	@Override
	public int getDimensionID() {
		return parent.getDimensionID();
	}
	
	@Override
	public void updateClimateSources() {
		claculateRangeAndChange();
	}

	@Override
	public synchronized void addClimateSource(IClimateSource source) {
		if (!sources.contains(source)) {
			sources.add(source);
			IClimateSourceProvider provider = source.getProvider();
			if(provider != null) {
				provider.setContainer(this);
			}
			updateClimateSources();
		}
	}

	@Override
	public synchronized void removeClimateSource(IClimateSource source) {
		if (sources.contains(source)) {
			sources.remove(source);
			IClimateSourceProvider provider = source.getProvider();
			if(provider != null) {
				provider.setContainer(null);
			}
			updateClimateSources();
		}
	}

	@Override
	public Collection<IClimateSource> getClimateSources() {
		return sources;
	}
	
	public ImmutableClimateState getRange() {
		if(range == null){
			claculateRangeAndChange();
		}
		return range;
	}
	
	public ClimateChange getClimateChange() {
		if(change == null){
			claculateRangeAndChange();
		}
		return change;
	}
	
	private void claculateRangeAndChange(){
		int size = parent.getSize();
		int sources = this.sources.size();
		float humidityRange = 0.0F;
		float temperatureRange = 0.0F;
		float humidityChange = 0.0F;
		float temperatureChange = 0.0F;
		for(IClimateSource source : this.sources){
			float range = source.getRange();
			float change = source.getChange();
			EnumClimatiserMode mode = source.getMode();
			EnumClimatiserType type = source.getType();
			if(type.canChangeHumidity()){
				humidityRange+=range;
				if(mode.isNegative()){
					humidityChange -= change;
				}else{
					humidityChange += change;
				}
			}
			if(type.canChangeTemperature()){
				temperatureRange+= range;
				if(mode.isNegative()){
					temperatureChange -= change;
				}else{
					temperatureChange += change;
				}
			}
		}
		float sizeModifier = size / Config.climateSourceRange;
		humidityRange/=sizeModifier;
		temperatureRange/=sizeModifier;
		humidityChange/=sizeModifier;
		temperatureChange/=sizeModifier;
		humidityChange = MathHelper.clamp(humidityChange, -0.1F, 0.1F);
		temperatureChange = MathHelper.clamp(temperatureChange, -0.1F, 0.1F);
		range = new ImmutableClimateState(temperatureRange, humidityRange);
		change = new ClimateChange(temperatureChange / sources, humidityChange / sources);
	}

	@Override
	public void writeData(PacketBufferForestry data) {
		data.writeFloat(state.getTemperature());
		data.writeFloat(state.getHumidity());
	}

	@Override
	public void readData(PacketBufferForestry data) throws IOException {
		state.setTemperature(data.readFloat());
		state.setHumidity(data.readFloat());
	}
	
	@Override
	public ClimateState getState() {
		return state;
	}
	
	@Override
	public void addListaner(IClimateContainerListener listener) {
		listeners.addListaner(listener);
	}
	
	@Override
	public void removeListener(IClimateContainerListener listener) {
		listeners.removeListener(listener);
	}
	
	@Override
	public Collection<IClimateContainerListener> getListeners() {
		return listeners.getListeners();
	}
	
	private static final class ClimateContainerListeners implements IClimateContainerListener{
		private List<IClimateContainerListener> listeners = new LinkedList<>();

		@Override
		public boolean isClosed(IClimateContainer container) {
			for(IClimateContainerListener listener : listeners){
				if(!listener.isClosed(container)){
					return false;
				}
			}
			return true;
		}
		
		public void addListaner(IClimateContainerListener listener) {
			listeners.add(listener);
		}
		
		public void removeListener(IClimateContainerListener listener) {
			listeners.remove(listener);
		}
		
		public Collection<IClimateContainerListener> getListeners() {
			return listeners;
		}
		
	}

}
