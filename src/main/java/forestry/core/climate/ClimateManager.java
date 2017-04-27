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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import forestry.api.climate.IClimateManager;
import forestry.api.climate.IClimateProvider;
import forestry.api.climate.IClimateSourceProvider;
import forestry.api.climate.IClimateState;
import forestry.api.climate.ImmutableClimateState;
import forestry.api.core.ILocatable;
import forestry.api.climate.IClimateContainer;
import forestry.core.DefaultClimateProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ClimateManager implements IClimateManager {

	private final Map<Integer, List<IClimateContainer>> containers;
	private final Map<Biome, ImmutableClimateState> biomeStates = new HashMap<>();
	private final Object regionsMutex;

	public ClimateManager() {
		containers = new HashMap<>();
		regionsMutex = new Object();
	}

	@Override
	public void addContainer(IClimateContainer container) {
		synchronized (regionsMutex) {
			Integer dimensionId = container.getDimensionID();
			List<IClimateContainer> containers = getDimensionContainers(dimensionId);
			if (!containers.contains(container)) {
				containers.add(container);
			}
		}
	}

	@Override
	public void removeContainer(IClimateContainer container) {
		synchronized (regionsMutex) {
			Integer dimensionId = container.getDimensionID();
			List<IClimateContainer> containers = getDimensionContainers(dimensionId);
			containers.remove(container);
		}
	}

	@Override
	public void removeSource(IClimateSourceProvider sourceProvider) {
		synchronized (regionsMutex) {
			IClimateContainer container = getContainer(sourceProvider);
			if (container != null) {
				container.removeClimateSource(sourceProvider.getClimateSource());
			}
		}
	}

	@Override
	public void addSource(IClimateSourceProvider sourceProvider) {
		synchronized (regionsMutex) {
			IClimateContainer container = getContainer(sourceProvider);
			if (container != null) {
				container.addClimateSource(sourceProvider.getClimateSource());
			}
		}
	}
	
	@Override
	public ImmutableClimateState getBiomeState(World world, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		return getBiomeState(biome);
	}
	
	private ImmutableClimateState getBiomeState(Biome biome) {
		if (!biomeStates.containsKey(biome)) {
			biomeStates.put(biome, new ImmutableClimateState(biome.getTemperature(), biome.getRainfall()));
		}
		return biomeStates.get(biome);
	}
	
	@Override
	public IClimateState getClimateState(World world, BlockPos pos) {
		IClimateContainer container = getContainer(world, pos);

		if (container != null) {
			return container.getState();
		}
		return getBiomeState(world, pos);
	}

	@Override
	public Map<Integer, List<IClimateContainer>> getContainers() {
		return containers;
	}

	@Override
	public IClimateContainer getContainer(World world, BlockPos pos) {
		List<IClimateContainer> containers = getWorldContainers(world);
		for (IClimateContainer container : containers) {
			if (container.getParent().containsPosition(pos)) {
				return container;
			}
		}
		return null;
	}
	
	private List<IClimateContainer> getDimensionContainers(int dimensionId){
		List<IClimateContainer> dimensionContainers = containers.get(dimensionId);
		if (dimensionContainers.isEmpty()) {
			containers.put(dimensionId, dimensionContainers = new ArrayList<>());
		}
		return dimensionContainers;
	}
	
	private List<IClimateContainer> getWorldContainers(World world){
		return getDimensionContainers(world.provider.getDimension());
	}
	
	private IClimateContainer getContainer(ILocatable locatable){
		return getContainer(locatable.getWorldObj(), locatable.getCoordinates());
	}
	
	@Override
	public void onUnloadDimension(int dimension) {
		containers.remove(dimension);
	}

	@Override
	public IClimateProvider getDefaultClimate(World world, BlockPos pos) {
		return new DefaultClimateProvider(world, pos);
	}

}
