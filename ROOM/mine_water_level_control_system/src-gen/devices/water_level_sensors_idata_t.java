package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class water_level_sensors_idata_t implements Serializable {

	private static final long serialVersionUID = 1424453026L;


	/*--------------------- attributes ---------------------*/
	public  int low_water_level_threshold;
	public  int high_water_level_threshold;
	public  int water_level;

	/* --------------------- attribute setters and getters */
	public void setLow_water_level_threshold(int low_water_level_threshold) {
		 this.low_water_level_threshold = low_water_level_threshold;
	}
	public int getLow_water_level_threshold() {
		return this.low_water_level_threshold;
	}
	public void setHigh_water_level_threshold(int high_water_level_threshold) {
		 this.high_water_level_threshold = high_water_level_threshold;
	}
	public int getHigh_water_level_threshold() {
		return this.high_water_level_threshold;
	}
	public void setWater_level(int water_level) {
		 this.water_level = water_level;
	}
	public int getWater_level() {
		return this.water_level;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_level_sensors_idata_t() {
		super();

		// initialize attributes

		/* user defined constructor body */
	}

	// constructor using fields
	public water_level_sensors_idata_t(int low_water_level_threshold, int high_water_level_threshold, int water_level) {
		super();

		this.low_water_level_threshold = low_water_level_threshold;
		this.high_water_level_threshold = high_water_level_threshold;
		this.water_level = water_level;

		/* user defined constructor body */
	}

	// deep copy
	public water_level_sensors_idata_t deepCopy() {
		water_level_sensors_idata_t copy = new water_level_sensors_idata_t();
		copy.low_water_level_threshold = low_water_level_threshold;
		copy.high_water_level_threshold = high_water_level_threshold;
		copy.water_level = water_level;
		return copy;
	}
};
