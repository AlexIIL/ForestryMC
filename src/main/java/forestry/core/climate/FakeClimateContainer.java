package forestry.core.climate;

import java.util.Collection;
import java.util.Collections;

import forestry.api.climate.IClimateContainer;
import forestry.api.climate.IClimateContainerListener;
import forestry.api.climate.IClimateSource;
import forestry.api.climate.IClimateState;
import forestry.api.climate.IClimatedRegion;
import forestry.api.climate.ImmutableClimateState;
import net.minecraft.nbt.NBTTagCompound;

public class FakeClimateContainer implements IClimateContainer{

	public static final FakeClimateContainer INSTANCE = new FakeClimateContainer();
	
	private FakeClimateContainer() {
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	public void addClimateSource(IClimateSource source) {
	}

	@Override
	public void removeClimateSource(IClimateSource source) {
	}

	@Override
	public Collection<IClimateSource> getClimateSources() {
		return Collections.emptyList();
	}

	@Override
	public IClimateState getState() {
		return ImmutableClimateState.PLAIN;
	}

	@Override
	public int getDimensionID() {
		return 0;
	}

	@Override
	public void updateClimate(int ticks) {
		
	}

	@Override
	public int getTickDelay() {
		return 0;
	}

	@Override
	public void addListaner(IClimateContainerListener listener) {
		
	}

	@Override
	public void removeListener(IClimateContainerListener listener) {
		
	}

	@Override
	public Collection<IClimateContainerListener> getListeners() {
		return Collections.emptyList();
	}

	@Override
	public IClimatedRegion getParent() {
		return null;
	}
}
