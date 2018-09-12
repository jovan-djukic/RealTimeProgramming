package environment_monitoring_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import alarm_station.*;
import deadline_task.*;
import devices.*;
import logger.*;
import periodic_task.*;
import pump_station.*;



public class methane_sensor_controller_idata_t extends gas_sensor_controller_idata_t implements Serializable {

	private static final long serialVersionUID = 939250382L;


	/*--------------------- attributes ---------------------*/
	public  pump_controller_t pump_controller;

	/* --------------------- attribute setters and getters */
	public void setPump_controller(pump_controller_t pump_controller) {
		 this.pump_controller = pump_controller;
	}
	public pump_controller_t getPump_controller() {
		return this.pump_controller;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public methane_sensor_controller_idata_t() {
		super();

		// initialize attributes
		this.setPump_controller(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public methane_sensor_controller_idata_t(int deadline, int period, boolean detect_above_threshold, int threshold, gas_sensor_t gas_sensor, int error_count_threshold, alarm_controller_t alarm_controller, pump_controller_t pump_controller) {
		super(deadline, period, detect_above_threshold, threshold, gas_sensor, error_count_threshold, alarm_controller);

		this.pump_controller = pump_controller;

		/* user defined constructor body */
	}

	// deep copy
	public methane_sensor_controller_idata_t deepCopy() {
		methane_sensor_controller_idata_t copy = new methane_sensor_controller_idata_t();
		copy.deadline = deadline;
		copy.period = period;
		copy.detect_above_threshold = detect_above_threshold;
		copy.threshold = threshold;
		copy.gas_sensor = gas_sensor;
		copy.error_count_threshold = error_count_threshold;
		copy.alarm_controller = alarm_controller;
		copy.pump_controller = pump_controller;
		return copy;
	}
};
