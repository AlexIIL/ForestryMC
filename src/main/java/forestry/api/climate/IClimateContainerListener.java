package forestry.api.climate;

/**
 * 
 * @since 5.3.4
 *
 */
public interface IClimateContainerListener {

	/**
	 * Test if the container is closed.
	 *
	 * @return true to let the container slowly set his climate state back to the default climate state.
	 */
	boolean isClosed(IClimateContainer container);
	
}
