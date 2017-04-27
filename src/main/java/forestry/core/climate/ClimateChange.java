package forestry.core.climate;

import forestry.api.climate.ClimateState;
import forestry.api.climate.IClimateState;
import forestry.api.climate.ImmutableClimateState;
import net.minecraft.util.math.MathHelper;

public class ClimateChange implements IClimateState{
	
	protected float temperature;
	protected float humidity;
	
	protected ClimateChange(float temperature, float humidity) {
		addTemperature(temperature);
		addHumidity(humidity);
	}
	
	@Override
	public float getTemperature() {
		return temperature;
	}
	
	@Override
	public float getHumidity() {
		return humidity;
	}
	
	public void setTemperature(float temperature) {
		this.temperature = 0;
		addTemperature(temperature);
	}
	
	public void setHumidity(float humidity) {
		this.humidity = 0;
		addHumidity(humidity);
	}
	
	public void add(ClimateState state){
		addTemperature(state.getTemperature());
		addHumidity(state.getHumidity());
	}
	
	public void remove(ClimateState state){
		addTemperature(-state.getTemperature());
		addHumidity(-state.getHumidity());
	}
	
	public void addTemperature(float temperature){
		this.temperature = MathHelper.clamp(this.temperature + temperature, -2.0F, 2.0F);
	}
	
	public void addHumidity(float humidity){
		this.humidity = MathHelper.clamp(this.humidity + humidity, -2.0F, 2.0F);
	}

	@Override
	public ImmutableClimateState toImmutable() {
		return new ImmutableClimateState(temperature, humidity);
	}

	@Override
	public ClimateState toMutable() {
		return new ClimateState(temperature, humidity);
	}
	
}
