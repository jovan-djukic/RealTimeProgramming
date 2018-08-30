package pump_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;
import periodic_task.*;



public class water_flow_sensor_controller_idata_t extends periodic_task_idata_t implements Serializable {

	private static final long serialVersionUID = 1673794046L;


	/*--------------------- attributes ---------------------*/
	public  device_t pump;
	public  water_flow_sensor_t water_flow_sensor;
	public  int number_of_activations;

	/* --------------------- attribute setters and getters */
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}
	public void setWater_flow_sensor(water_flow_sensor_t water_flow_sensor) {
		 this.water_flow_sensor = water_flow_sensor;
	}
	public water_flow_sensor_t getWater_flow_sensor() {
		return this.water_flow_sensor;
	}
	public void setNumber_of_activations(int number_of_activations) {
		 this.number_of_activations = number_of_activations;
	}
	public int getNumber_of_activations() {
		return this.number_of_activations;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_flow_sensor_controller_idata_t() {
		super();

		// initialize attributes
		this.setPump(null);
		this.setWater_flow_sensor(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public water_flow_sensor_controller_idata_t(int period, device_t pump, water_flow_sensor_t water_flow_sensor, int number_of_activations) {
		super(period);

		this.pump = pump;
		this.water_flow_sensor = water_flow_sensor;
		this.number_of_activations = number_of_activations;

		/* user defined constructor body */
	}

	// deep copy
	public water_flow_sensor_controller_idata_t deepCopy() {
		water_flow_sensor_controller_idata_t copy = new water_flow_sensor_controller_idata_t();
		copy.period = period;
		copy.pump = pump;
		copy.water_flow_sensor = water_flow_sensor;
		copy.number_of_activations = number_of_activations;
		return copy;
	}
};