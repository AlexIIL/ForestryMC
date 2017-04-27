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
package forestry.greenhouse;

public class GreenhouseClimateSource//<P extends TileGreenhouseClimatiser> extends ClimateSource<P> 
{
/*
	private static final int COOLDOWN = 10;
	private int currentCooldown;
	
	public GreenhouseClimateSource(int ticksForChange) {
		super(ticksForChange);
	}

	@Override
	public boolean changeClimate(int tickCount, IClimateContainer region) {
		if (provider == null) {
			return false;
		}
		if(currentCooldown > 0){
			currentCooldown--;
			return false;
		}
		IClimatiserDefinition definition = provider.getDefinition();
		IMultiblockLogic logic = provider.getMultiblockLogic();
		IMultiblockController controller = logic.getController();
		Iterable<BlockPos> positionsInRange = provider.getPositionsInRange();

		boolean hasChange = false;
		boolean isActive = false;
		if (logic.isConnected() && controller.isAssembled() && controller instanceof IGreenhouseControllerInternal && positionsInRange.iterator().hasNext()) {
			IGreenhouseControllerInternal greenhouseInternal = (IGreenhouseControllerInternal) controller;
			IClimateInfo climateInfo = getClimateControl(greenhouseInternal);
			float controlTemp = climateInfo.getTemperature();
			float controlHum = climateInfo.getHumidity();
			isActive = greenhouseInternal.canWork() && provider.canWork();
			EnumClimatiserType type = definition.getType();
			EnumClimatiserMode mode = definition.getMode();
			float maxChange = definition.getChange();
			if (isActive) {
				if (canChange(type, EnumClimatiserType.TEMPERATURE)) {
					if (region.getAverageTemperature() < controlTemp) {
						if (!canChange(mode, EnumClimatiserMode.POSITIVE)) {
							isActive = false;
						}
					} else if (region.getAverageTemperature() > controlTemp) {
						if (!canChange(mode, EnumClimatiserMode.NEGATIVE)) {
							isActive = false;
						}
					}
				}
				if (canChange(type, EnumClimatiserType.HUMIDITY)) {
					if (region.getAverageTemperature() < controlHum) {
						if (!canChange(mode, EnumClimatiserMode.POSITIVE)) {
							isActive = false;
						}
					} else if (region.getAverageTemperature() > controlHum) {
						if (!canChange(mode, EnumClimatiserMode.NEGATIVE)) {
							isActive = false;
						}
					}
				}
			}

			if (isActive) {
				for (BlockPos pos : positionsInRange) {
					IClimatePosition position = region.getPosition(pos);
					if (position != null) {
						double distance = pos.distanceSq(provider.getCoordinates());
						double range = definition.getRange();
						float change = maxChange;
						if (distance > 0) {
							change = (float) (maxChange / distance);
						}
						if (canChange(type, EnumClimatiserType.TEMPERATURE)) {
							if (position.getTemperature() < controlTemp) {
								if (canChange(mode, EnumClimatiserMode.POSITIVE)) {
									position.addTemperature(Math.min(change, controlTemp - position.getTemperature()));
									hasChange = true;
								}
							} else if (position.getTemperature() > controlTemp) {
								if (canChange(mode, EnumClimatiserMode.NEGATIVE)) {
									position.addTemperature(-Math.min(position.getTemperature() - controlTemp, change));
									hasChange = true;
								}
							}
						}
						if (canChange(type, EnumClimatiserType.HUMIDITY)) {
							if (position.getHumidity() < controlHum) {
								if (canChange(mode, EnumClimatiserMode.POSITIVE)) {
									position.addHumidity(Math.min(change, controlHum - position.getHumidity()));
									hasChange = true;
								}
							} else if (position.getHumidity() > controlHum) {
								if (canChange(mode, EnumClimatiserMode.NEGATIVE)) {
									position.addHumidity(-Math.min(position.getHumidity() - controlHum, change));
									hasChange = true;
								}
							}
						}
					}
				}
			}
		}
		if(!hasChange){
			currentCooldown = COOLDOWN;
			if(isActive){
				isActive = false;
			}
		}
		if (provider.isActive() != isActive) {
			provider.setActive(isActive);
		}
		return hasChange;
	}

	protected boolean canChange(EnumClimatiserType climatiserType, EnumClimatiserType type) {
		return type == climatiserType || climatiserType == EnumClimatiserType.BOTH;
	}

	protected boolean canChange(EnumClimatiserMode climatiserMode, EnumClimatiserMode mode) {
		return mode == climatiserMode || climatiserMode == EnumClimatiserMode.BOTH;
	}

	protected IClimateInfo getClimateControl(IGreenhouseControllerInternal greenhouseInternal) {
		return greenhouseInternal.getControlClimate();
	}*/

}
