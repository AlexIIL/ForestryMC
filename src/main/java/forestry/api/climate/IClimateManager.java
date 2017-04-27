/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

import javax.annotation.Nullable;

import forestry.api.climate.ImmutableClimateState;
import java.util.List;
import java.util.Map;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IClimateManager {
	
	/**
	 * @return The current state of a container on this position if one exists.
	 * @since 5.3.4
	 */
	@Nullable
	IClimateContainer getContainer(World world, BlockPos pos);
	
	/**
	 * Gets the current state of a container on this position or create one with the datas from the biome.
	 * @since 5.3.4
	 */
	IClimateState getClimateState(World world, BlockPos pos);
	
	/**
	 * Add a container to the manager.
	 * @since 5.3.4
	 */
	void addContainer(IClimateContainer container);
	
	/**
	 * Remove a container from the manager.
	 * @since 5.3.4
	 */
	void removeContainer(IClimateContainer container);
	
	/**
	 * Creates a climate state with the help of the biome on this position.
	 */
	ImmutableClimateState getBiomeState(World world, BlockPos pos);

	/**
	 * @return Create a climate provider.
	 */
	IClimateProvider getDefaultClimate(World world, BlockPos pos);

	/**
	 * Add a climate source to a container on this position if one exists.
	 */
	void addSource(IClimateSourceProvider source);

	/**
	 * Remove a climate source from a container on this position if one exists.
	 */
	void removeSource(IClimateSourceProvider source);

	/**
	 * Remove all containers that exist in this dimension.
	 * @since 5.3.4
	 */
	void onUnloadDimension(int dimension);

	/**
	 * @return A map with all added containers.
	 * @since 5.3.4
	 */
	Map<Integer, List<IClimateContainer>> getContainers();

}
