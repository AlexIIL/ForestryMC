package forestry.greenhouse.tiles;

import java.io.IOException;

import forestry.api.climate.ImmutableClimateState;
import forestry.api.multiblock.IGreenhouseComponent;
import forestry.core.network.PacketBufferForestry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileGreenhouseClimateControl extends TileGreenhouse implements IGreenhouseComponent.ClimateControl {

	private ImmutableClimateState climateControl;

	public TileGreenhouseClimateControl() {
		super();
		climateControl = ImmutableClimateState.MAX;
	}

	@Override
	protected void decodeDescriptionPacket(NBTTagCompound packetData) {
		super.decodeDescriptionPacket(packetData);
		climateControl = new ImmutableClimateState(packetData);
	}

	@Override
	protected void encodeDescriptionPacket(NBTTagCompound packetData) {
		super.encodeDescriptionPacket(packetData);
		packetData.setFloat("Temperature", climateControl.getTemperature());
		packetData.setFloat("Humidity", climateControl.getHumidity());
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		climateControl = new ImmutableClimateState(data);
		super.readFromNBT(data);
	}


	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		data.setFloat("Temperature", climateControl.getTemperature());
		data.setFloat("Humidity", climateControl.getHumidity());
		return super.writeToNBT(data);
	}

	@Override
	public void writeGuiData(PacketBufferForestry data) {
		super.writeGuiData(data);
		data.writeFloat(climateControl.getTemperature());
		data.writeFloat(climateControl.getHumidity());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void readGuiData(PacketBufferForestry data) throws IOException {
		super.readGuiData(data);
		climateControl = new ImmutableClimateState(data.readFloat(), data.readFloat());
	}

	@Override
	public ImmutableClimateState getControlClimate() {
		return climateControl;
	}

	@Override
	public void setControlClimate(ImmutableClimateState climateControl) {
		this.climateControl = climateControl;
	}

}
