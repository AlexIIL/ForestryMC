/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

import javax.annotation.Nullable;

/**
 * A climate source is used to change the climate in a region.
 */
public interface IClimateSource {
	
	@Nullable
	IClimateSourceProvider getProvider();
	
	float getChange();

	float getRange();

	EnumClimatiserMode getMode();

	EnumClimatiserType getType();

	boolean canWork(IClimateContainer container);
	
	void onWork(IClimateContainer container);

}
