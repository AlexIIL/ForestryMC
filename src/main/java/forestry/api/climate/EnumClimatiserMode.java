/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.climate;

public enum EnumClimatiserMode {

	POSITIVE{
		@Override
		public boolean isNegative() {
			return false;
		}
		
		@Override
		public boolean isPositive() {
			return true;
		}
	}, NEGATIVE{
		@Override
		public boolean isNegative() {
			return true;
		}
		
		@Override
		public boolean isPositive() {
			return false;
		}
	}, BOTH{
		@Override
		public boolean isNegative() {
			return true;
		}
		
		@Override
		public boolean isPositive() {
			return true;
		}
	};
	
	public abstract boolean isNegative();
	
	public abstract boolean isPositive();

}
