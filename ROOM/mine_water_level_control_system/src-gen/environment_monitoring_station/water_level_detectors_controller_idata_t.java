package environment_monitoring_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;



public class water_level_detectors_controller_idata_t extends environment_station_actor_base_idata_t implements Serializable {

	private static final long serialVersionUID = -180942557L;


	/*--------------------- attributes ---------------------*/
	public  water_level_detector_t low_water_level_detector;
	public  water_level_detector_t high_water_level_detector;

	/* --------------------- attribute setters and getters */
	public void setLow_water_level_detector(water_level_detector_t low_water_level_detector) {
		 this.low_water_level_detector = low_water_level_detector;
	}
	public water_level_detector_t getLow_water_level_detector() {
		return this.low_water_level_detector;
	}
	public void setHigh_water_level_detector(water_level_detector_t high_water_level_detector) {
		 this.high_water_level_detector = high_water_level_detector;
	}
	public water_level_detector_t getHigh_water_level_detector() {
		return this.high_water_level_detector;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_level_detectors_controller_idata_t() {
		super();

		// initialize attributes
		this.setLow_water_level_detector(null);
		this.setHigh_water_level_detector(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public water_level_detectors_controller_idata_t(int period, water_level_detector_t low_water_level_detector, water_level_detector_t high_water_level_detector) {
		super(period);

		this.low_water_level_detector = low_water_level_detector;
		this.high_water_level_detector = high_water_level_detector;

		/* user defined constructor body */
	}

	// deep copy
	public water_level_detectors_controller_idata_t deepCopy() {
		water_level_detectors_controller_idata_t copy = new water_level_detectors_controller_idata_t();
		copy.period = period;
		copy.low_water_level_detector = low_water_level_detector;
		copy.high_water_level_detector = high_water_level_detector;
		return copy;
	}
};
