package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class water_level_sensors_interrupt_data_t implements Serializable {

	private static final long serialVersionUID = 748736737L;


	/*--------------------- attributes ---------------------*/
	public  boolean low_water_level_threshold_breached;
	public  boolean high_water_level_threshold_breached;

	/* --------------------- attribute setters and getters */
	public void setLow_water_level_threshold_breached(boolean low_water_level_threshold_breached) {
		 this.low_water_level_threshold_breached = low_water_level_threshold_breached;
	}
	public boolean getLow_water_level_threshold_breached() {
		return this.low_water_level_threshold_breached;
	}
	public void setHigh_water_level_threshold_breached(boolean high_water_level_threshold_breached) {
		 this.high_water_level_threshold_breached = high_water_level_threshold_breached;
	}
	public boolean getHigh_water_level_threshold_breached() {
		return this.high_water_level_threshold_breached;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_level_sensors_interrupt_data_t() {
		super();

		// initialize attributes

		/* user defined constructor body */
	}

	// constructor using fields
	public water_level_sensors_interrupt_data_t(boolean low_water_level_threshold_breached, boolean high_water_level_threshold_breached) {
		super();

		this.low_water_level_threshold_breached = low_water_level_threshold_breached;
		this.high_water_level_threshold_breached = high_water_level_threshold_breached;

		/* user defined constructor body */
	}

	// deep copy
	public water_level_sensors_interrupt_data_t deepCopy() {
		water_level_sensors_interrupt_data_t copy = new water_level_sensors_interrupt_data_t();
		copy.low_water_level_threshold_breached = low_water_level_threshold_breached;
		copy.high_water_level_threshold_breached = high_water_level_threshold_breached;
		return copy;
	}
};
