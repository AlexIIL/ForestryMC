/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.multiblock;

import java.util.Set;

import forestry.api.climate.IClimateContainer;
import forestry.api.climate.IClimateControlProvider;
import forestry.api.core.ICamouflageHandler;
import forestry.api.greenhouse.IGreenhouseHousing;
import forestry.api.greenhouse.IInternalBlock;

public interface IGreenhouseController extends IMultiblockController, IGreenhouseHousing, ICamouflageHandler, IClimateControlProvider {
	
	/**
	 * @return All internal blocks of the greenhouse.
	 */
	Set<IInternalBlock> getInternalBlocks();

	/**
	 * spawns a butterfly in a greenhouse
	 * @return true if it has spawned it, and false if it has not
	 */
	boolean spawnButterfly(IGreenhouseComponent.Nursery nursery);
	
	IClimateContainer getClimateContainer();

}
