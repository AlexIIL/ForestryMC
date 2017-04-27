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

import forestry.api.climate.EnumClimatiserMode;
import forestry.api.climate.EnumClimatiserType;
import forestry.api.climate.IClimateContainer;
import forestry.api.climate.IClimateSource;

public class ClimateSource implements IClimateSource {
	
	protected final float change;
	protected final EnumClimatiserMode mode;
	protected final float range;
	protected final EnumClimatiserType type;

	public ClimateSource(float change, EnumClimatiserMode mode, float range, EnumClimatiserType type) {
		this.change = change;
		this.mode = mode;
		this.range = range;
		this.type = type;
	}

	@Override
	public float getChange() {
		return change;
	}

	@Override
	public float getRange() {
		return range;
	}

	@Override
	public EnumClimatiserType getType() {
		return type;
	}

	@Override
	public EnumClimatiserMode getMode() {
		return mode;
	}

	@Override
	public boolean canWork(IClimateContainer container) {
		return true;
	}

}
