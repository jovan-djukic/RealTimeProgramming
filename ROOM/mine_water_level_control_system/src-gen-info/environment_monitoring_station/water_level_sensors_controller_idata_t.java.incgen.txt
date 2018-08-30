package environment_monitoring_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;
import periodic_task.*;



public class water_level_sensors_controller_idata_t extends periodic_task_idata_t implements Serializable {

	private static final long serialVersionUID = 409194007L;


	/*--------------------- attributes ---------------------*/
	public  water_level_sensor_t low_water_level_sensor;
	public  water_level_sensor_t high_water_level_sensor;

	/* --------------------- attribute setters and getters */
	public void setLow_water_level_sensor(water_level_sensor_t low_water_level_sensor) {
		 this.low_water_level_sensor = low_water_level_sensor;
	}
	public water_level_sensor_t getLow_water_level_sensor() {
		return this.low_water_level_sensor;
	}
	public void setHigh_water_level_sensor(water_level_sensor_t high_water_level_sensor) {
		 this.high_water_level_sensor = high_water_level_sensor;
	}
	public water_level_sensor_t getHigh_water_level_sensor() {
		return this.high_water_level_sensor;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_level_sensors_controller_idata_t() {
		super();

		// initialize attributes
		this.setLow_water_level_sensor(null);
		this.setHigh_water_level_sensor(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public water_level_sensors_controller_idata_t(int period, water_level_sensor_t low_water_level_sensor, water_level_sensor_t high_water_level_sensor) {
		super(period);

		this.low_water_level_sensor = low_water_level_sensor;
		this.high_water_level_sensor = high_water_level_sensor;

		/* user defined constructor body */
	}

	// deep copy
	public water_level_sensors_controller_idata_t deepCopy() {
		water_level_sensors_controller_idata_t copy = new water_level_sensors_controller_idata_t();
		copy.period = period;
		copy.low_water_level_sensor = low_water_level_sensor;
		copy.high_water_level_sensor = high_water_level_sensor;
		return copy;
	}
};
