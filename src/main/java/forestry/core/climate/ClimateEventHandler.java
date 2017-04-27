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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import forestry.api.climate.IClimateContainer;
import forestry.api.core.ForestryAPI;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClimateEventHandler {

	private final Map<Integer, Integer> serverTicks;

	public ClimateEventHandler() {
		serverTicks = new HashMap<>();
	}
	
	@SubscribeEvent()
	public void onWorldUnload(WorldEvent.Unload unloadWorldEvent) {
		World world = unloadWorldEvent.getWorld();
		int dimension = world.provider.getDimension();
		ForestryAPI.climateManager.onUnloadDimension(dimension);
		serverTicks.remove(dimension);
	}

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		World world = event.world;
		if (event.phase == TickEvent.Phase.END) {
			MinecraftServer server = world.getMinecraftServer();
			if (server != null) {
				server.addScheduledTask(() -> {
					int dimensionId = world.provider.getDimension();
					if (!serverTicks.containsKey(dimensionId)) {
						serverTicks.put(dimensionId, 1);
					}
					int ticks = serverTicks.get(world);
					Map<Integer, List<IClimateContainer>> regions = ForestryAPI.climateManager.getContainers();
					if (regions.containsKey(dimensionId)) {
						for (IClimateContainer region : regions.get(dimensionId)) {
							region.updateClimate(ticks);
						}
					}
					serverTicks.put(dimensionId, ticks + 1);
				});
			}
		}
	}

}
