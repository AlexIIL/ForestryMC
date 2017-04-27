/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

import forestry.api.core.INbtReadable;
import forestry.api.core.INbtWritable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import static forestry.api.climate.ImmutableClimateState.MIN;
import static forestry.api.climate.ImmutableClimateState.MAX;

/**
 * @since 5.3.4
 */
public class ClimateState implements IClimateState, INbtReadable, INbtWritable{
	
	private static final String TEMPERATURE_NBT_KEY = "TEMP";
	private static final String HUMIDITY_NBT_KEY = "HUMID";
	
	protected float temperature;
	protected float humidity;
	
	public ClimateState(float temperature, float humidity) {
		addTemperature(temperature);
		addHumidity(humidity);
	}
	
	public ClimateState(NBTTagCompound compound) {
		readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setFloat(TEMPERATURE_NBT_KEY, temperature);
		compound.setFloat(HUMIDITY_NBT_KEY, humidity);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.temperature = compound.getFloat(TEMPERATURE_NBT_KEY);
		this.humidity = compound.getFloat(HUMIDITY_NBT_KEY);
	}
	
	public void setTemperature(float temperature) {
		this.temperature = MathHelper.clamp(temperature, MIN.temperature, MAX.temperature);
	}
	
	public void setHumidity(float humidity) {
		this.humidity = MathHelper.clamp(humidity, MIN.humidity, MAX.humidity);
	}
	
	public void addTemperature(float temperature){
		setTemperature(this.temperature + temperature);
	}
	
	public void addHumidity(float humidity){
		setHumidity(this.humidity + humidity);
	}
	
	public void add(IClimateState state){
		addTemperature(state.getTemperature());
		addHumidity(state.getHumidity());
	}
	
	public void remove(IClimateState state){
		addTemperature(-state.getTemperature());
		addHumidity(-state.getHumidity());
	}
	
	@Override
	public float getTemperature() {
		return temperature;
	}
	
	@Override
	public float getHumidity() {
		return humidity;
	}
	
	@Override
	public ImmutableClimateState toImmutable(){
		return new ImmutableClimateState(temperature, humidity);
	}
	
	@Override
	public ClimateState toMutable(){
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ClimateState)){
			return false;
		}
		ClimateState otherState = (ClimateState) obj;
		return otherState.temperature == temperature && otherState.humidity == humidity;
	}
	
	@Override
	public int hashCode() {
		return Float.hashCode(temperature) + Float.hashCode(humidity);
	}

}
