package pump_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import alarm_station.*;
import deadline_task.*;
import devices.*;
import logger.*;
import periodic_task.*;



public class water_flow_sensor_controller_idata_t extends periodic_task_idata_t implements Serializable {

	private static final long serialVersionUID = 1673794046L;


	/*--------------------- attributes ---------------------*/
	public  device_t pump;
	public  sensor_t water_flow_sensor;
	public  int number_of_activations;
	public  alarm_controller_t alarm_controller;

	/* --------------------- attribute setters and getters */
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}
	public void setWater_flow_sensor(sensor_t water_flow_sensor) {
		 this.water_flow_sensor = water_flow_sensor;
	}
	public sensor_t getWater_flow_sensor() {
		return this.water_flow_sensor;
	}
	public void setNumber_of_activations(int number_of_activations) {
		 this.number_of_activations = number_of_activations;
	}
	public int getNumber_of_activations() {
		return this.number_of_activations;
	}
	public void setAlarm_controller(alarm_controller_t alarm_controller) {
		 this.alarm_controller = alarm_controller;
	}
	public alarm_controller_t getAlarm_controller() {
		return this.alarm_controller;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_flow_sensor_controller_idata_t() {
		super();

		// initialize attributes
		this.setPump(null);
		this.setWater_flow_sensor(null);
		this.setAlarm_controller(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public water_flow_sensor_controller_idata_t(int deadline, int period, device_t pump, sensor_t water_flow_sensor, int number_of_activations, alarm_controller_t alarm_controller) {
		super(deadline, period);

		this.pump = pump;
		this.water_flow_sensor = water_flow_sensor;
		this.number_of_activations = number_of_activations;
		this.alarm_controller = alarm_controller;

		/* user defined constructor body */
	}

	// deep copy
	public water_flow_sensor_controller_idata_t deepCopy() {
		water_flow_sensor_controller_idata_t copy = new water_flow_sensor_controller_idata_t();
		copy.deadline = deadline;
		copy.period = period;
		copy.pump = pump;
		copy.water_flow_sensor = water_flow_sensor;
		copy.number_of_activations = number_of_activations;
		copy.alarm_controller = alarm_controller;
		return copy;
	}
};
