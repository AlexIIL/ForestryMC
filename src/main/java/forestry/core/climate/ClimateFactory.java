package forestry.core.climate;

import forestry.api.climate.IClimateContainer;
import forestry.api.climate.IClimateFactory;
import forestry.api.climate.IClimatedRegion;

public class ClimateFactory implements IClimateFactory{

	@Override
	public IClimateContainer createContainer(IClimatedRegion climatedRegion) {
		return new ClimateContainer(climatedRegion);
	}

}
