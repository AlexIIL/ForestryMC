/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

import java.util.Collection;

/**
 * @since 5.3.4
 *
 */
public interface IClimateSourceContainer {

	/**
	 * Add a climate source to this container.
	 */
	void addClimateSource(IClimateSource source);

	/**
	 * Remove a climate source from this container.
	 */
	void removeClimateSource(IClimateSource source);
	
	/**
	 * @return All climate sources of this container.
	 */
	Collection<IClimateSource> getClimateSources();
	
	/**
	 * Called if a climate source is added or if a climate source got an important change.
	 */
	void updateClimateSources();
	
}
