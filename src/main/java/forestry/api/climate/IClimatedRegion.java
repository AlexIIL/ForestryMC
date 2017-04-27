package forestry.api.climate;

import net.minecraft.util.math.BlockPos;

public interface IClimatedRegion {

	/**
	 * @return The climate container of this region.
	 */
	IClimateContainer getContainer();
	
	/**
	 * @return The size of the region in blocks.
	 */
	int getSize();
	
	/**
	 * @return true if the position is contained by this region.
	 */
	boolean containsPosition(BlockPos pos);
	
	/**
	 * @return The default climate state. It is calculated out of all biomedata that this region contains.
	 */
	ImmutableClimateState getDefaultClimate();
	
	int getDimensionID();
	
}
