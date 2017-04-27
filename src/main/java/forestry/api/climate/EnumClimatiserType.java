/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

public enum EnumClimatiserType {

	TEMPERATURE{
		
		@Override
		public boolean canChangeHumidity() {
			return false;
		}
		
		@Override
		public boolean canChangeTemperature() {
			return true;
		}
	}, HUMIDITY{
		
		@Override
		public boolean canChangeHumidity() {
			return true;
		}
		
		@Override
		public boolean canChangeTemperature() {
			return false;
		}
	}, BOTH{
		
		@Override
		public boolean canChangeHumidity() {
			return true;
		}
		
		@Override
		public boolean canChangeTemperature() {
			return true;
		}
	};
	
	public abstract boolean canChangeTemperature();
	public abstract boolean canChangeHumidity();

}
