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
package forestry.greenhouse.tiles;

public class TileGreenhouseHeater extends TileGreenhouseClimatiser {

	private static final IClimatiserDefinition DEFINITION = new ClimatiserDefinition(0.015F, EnumClimatiserMode.POSITIVE, 5F, EnumClimatiserType.TEMPERATURE);

	public TileGreenhouseHeater() {
		super(DEFINITION);
	}

}
