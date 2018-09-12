package environment_monitoring_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import deadline_task.*;
import devices.*;
import logger.*;
import pump_station.*;



public class water_level_sensors_controller_idata_t extends deadline_task_idata_t implements Serializable {

	private static final long serialVersionUID = 409194007L;


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
	public water_level_sensors_controller_idata_t() {
		super();

		// initialize attributes
		this.setPump_controller(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public water_level_sensors_controller_idata_t(int deadline, pump_controller_t pump_controller) {
		super(deadline);

		this.pump_controller = pump_controller;

		/* user defined constructor body */
	}

	// deep copy
	public water_level_sensors_controller_idata_t deepCopy() {
		water_level_sensors_controller_idata_t copy = new water_level_sensors_controller_idata_t();
		copy.deadline = deadline;
		copy.pump_controller = pump_controller;
		return copy;
	}
};
