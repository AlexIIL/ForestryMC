/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

import java.util.Collection;

import forestry.api.core.INbtReadable;
import forestry.api.core.INbtWritable;

/**
 * @since 5.3.4
 */
public interface IClimateContainer extends INbtReadable, INbtWritable, IClimateSourceContainer {
	
	/**
	 * @return The parent of this container.
	 */
	IClimatedRegion getParent();
	
	/**
	 * @return The current climate state.
	 */
	IClimateState getState();
	
	ImmutableClimateState getTargetedState();
	
	void setTargetedState(ImmutableClimateState state);
	
	/**
	 * @return The dimension id of the dimension in that the container exists.
	 */
	int getDimensionID();
	
	/**
	 * Update the climate in a region.
	 */
	void updateClimate(int ticks);
	
	/**
	 * Add a listener to this container.
	 */
	void addListaner(IClimateContainerListener listener);
	
	/**
	 * Remove a listener to this container.
	 */
	void removeListener(IClimateContainerListener listener);
	
	/**
	 * All listeners of this container.
	 */
	Collection<IClimateContainerListener> getListeners();

}
