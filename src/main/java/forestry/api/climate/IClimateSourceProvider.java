/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

import javax.annotation.Nullable;

import forestry.api.core.ILocatable;

public interface IClimateSourceProvider extends ILocatable {

	/**
	 * The climate source of this provider;
	 */
	IClimateSource getClimateSource();
	
	@Nullable
	IClimateContainer getContainer();
	
	void setContainer(@Nullable IClimateContainer container);
}
