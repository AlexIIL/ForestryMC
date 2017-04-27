package forestry.api.climate;

public interface IClimateState {
	
	float getTemperature();
	
	float getHumidity();
	
	ImmutableClimateState toImmutable();
	
	ClimateState toMutable();
	
}
