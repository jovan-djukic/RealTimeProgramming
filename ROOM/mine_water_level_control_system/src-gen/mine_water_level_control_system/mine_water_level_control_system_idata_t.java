package mine_water_level_control_system;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;
import logger.*;



public class mine_water_level_control_system_idata_t implements Serializable {

	private static final long serialVersionUID = -2127082103L;


	/*--------------------- attributes ---------------------*/
	public  int water_level;
	public  int water_level_sensors_low_water_level_threshold;
	public  int water_level_sensors_high_water_level_threshold;
	public  device_t alarm;
	public  device_t pump;
	public  gas_sensor_t co_sensor;
	public  int co_sensor_controller_deadline_in_ms;
	public  int co_sensor_controller_period_in_ms;
	public  int co_sensor_controller_threshold;
	public  int co_sensor_controller_error_count_threshold;
	public  gas_sensor_t o_sensor;
	public  int o_sensor_controller_deadline_in_ms;
	public  int o_sensor_controller_period_in_ms;
	public  int o_sensor_controller_threshold;
	public  int o_sensor_controller_error_count_threshold;
	public  gas_sensor_t ch4_sensor;
	public  int ch4_sensor_controller_deadline_in_ms;
	public  int ch4_sensor_controller_period_in_ms;
	public  int ch4_sensor_controller_threshold;
	public  int ch4_sensor_controller_error_count_threshold;
	public  int water_level_sensors_controller_deadline_in_ms;
	public  sensor_t water_flow_sensor;
	public  int water_flow_sensor_controller_deadline_in_ms;
	public  int water_flow_sensor_controller_period_in_ms;
	public  int water_flow_sensor_controller_number_of_activations;

	/* --------------------- attribute setters and getters */
	public void setWater_level(int water_level) {
		 this.water_level = water_level;
	}
	public int getWater_level() {
		return this.water_level;
	}
	public void setWater_level_sensors_low_water_level_threshold(int water_level_sensors_low_water_level_threshold) {
		 this.water_level_sensors_low_water_level_threshold = water_level_sensors_low_water_level_threshold;
	}
	public int getWater_level_sensors_low_water_level_threshold() {
		return this.water_level_sensors_low_water_level_threshold;
	}
	public void setWater_level_sensors_high_water_level_threshold(int water_level_sensors_high_water_level_threshold) {
		 this.water_level_sensors_high_water_level_threshold = water_level_sensors_high_water_level_threshold;
	}
	public int getWater_level_sensors_high_water_level_threshold() {
		return this.water_level_sensors_high_water_level_threshold;
	}
	public void setAlarm(device_t alarm) {
		 this.alarm = alarm;
	}
	public device_t getAlarm() {
		return this.alarm;
	}
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}
	public void setCo_sensor(gas_sensor_t co_sensor) {
		 this.co_sensor = co_sensor;
	}
	public gas_sensor_t getCo_sensor() {
		return this.co_sensor;
	}
	public void setCo_sensor_controller_deadline_in_ms(int co_sensor_controller_deadline_in_ms) {
		 this.co_sensor_controller_deadline_in_ms = co_sensor_controller_deadline_in_ms;
	}
	public int getCo_sensor_controller_deadline_in_ms() {
		return this.co_sensor_controller_deadline_in_ms;
	}
	public void setCo_sensor_controller_period_in_ms(int co_sensor_controller_period_in_ms) {
		 this.co_sensor_controller_period_in_ms = co_sensor_controller_period_in_ms;
	}
	public int getCo_sensor_controller_period_in_ms() {
		return this.co_sensor_controller_period_in_ms;
	}
	public void setCo_sensor_controller_threshold(int co_sensor_controller_threshold) {
		 this.co_sensor_controller_threshold = co_sensor_controller_threshold;
	}
	public int getCo_sensor_controller_threshold() {
		return this.co_sensor_controller_threshold;
	}
	public void setCo_sensor_controller_error_count_threshold(int co_sensor_controller_error_count_threshold) {
		 this.co_sensor_controller_error_count_threshold = co_sensor_controller_error_count_threshold;
	}
	public int getCo_sensor_controller_error_count_threshold() {
		return this.co_sensor_controller_error_count_threshold;
	}
	public void setO_sensor(gas_sensor_t o_sensor) {
		 this.o_sensor = o_sensor;
	}
	public gas_sensor_t getO_sensor() {
		return this.o_sensor;
	}
	public void setO_sensor_controller_deadline_in_ms(int o_sensor_controller_deadline_in_ms) {
		 this.o_sensor_controller_deadline_in_ms = o_sensor_controller_deadline_in_ms;
	}
	public int getO_sensor_controller_deadline_in_ms() {
		return this.o_sensor_controller_deadline_in_ms;
	}
	public void setO_sensor_controller_period_in_ms(int o_sensor_controller_period_in_ms) {
		 this.o_sensor_controller_period_in_ms = o_sensor_controller_period_in_ms;
	}
	public int getO_sensor_controller_period_in_ms() {
		return this.o_sensor_controller_period_in_ms;
	}
	public void setO_sensor_controller_threshold(int o_sensor_controller_threshold) {
		 this.o_sensor_controller_threshold = o_sensor_controller_threshold;
	}
	public int getO_sensor_controller_threshold() {
		return this.o_sensor_controller_threshold;
	}
	public void setO_sensor_controller_error_count_threshold(int o_sensor_controller_error_count_threshold) {
		 this.o_sensor_controller_error_count_threshold = o_sensor_controller_error_count_threshold;
	}
	public int getO_sensor_controller_error_count_threshold() {
		return this.o_sensor_controller_error_count_threshold;
	}
	public void setCh4_sensor(gas_sensor_t ch4_sensor) {
		 this.ch4_sensor = ch4_sensor;
	}
	public gas_sensor_t getCh4_sensor() {
		return this.ch4_sensor;
	}
	public void setCh4_sensor_controller_deadline_in_ms(int ch4_sensor_controller_deadline_in_ms) {
		 this.ch4_sensor_controller_deadline_in_ms = ch4_sensor_controller_deadline_in_ms;
	}
	public int getCh4_sensor_controller_deadline_in_ms() {
		return this.ch4_sensor_controller_deadline_in_ms;
	}
	public void setCh4_sensor_controller_period_in_ms(int ch4_sensor_controller_period_in_ms) {
		 this.ch4_sensor_controller_period_in_ms = ch4_sensor_controller_period_in_ms;
	}
	public int getCh4_sensor_controller_period_in_ms() {
		return this.ch4_sensor_controller_period_in_ms;
	}
	public void setCh4_sensor_controller_threshold(int ch4_sensor_controller_threshold) {
		 this.ch4_sensor_controller_threshold = ch4_sensor_controller_threshold;
	}
	public int getCh4_sensor_controller_threshold() {
		return this.ch4_sensor_controller_threshold;
	}
	public void setCh4_sensor_controller_error_count_threshold(int ch4_sensor_controller_error_count_threshold) {
		 this.ch4_sensor_controller_error_count_threshold = ch4_sensor_controller_error_count_threshold;
	}
	public int getCh4_sensor_controller_error_count_threshold() {
		return this.ch4_sensor_controller_error_count_threshold;
	}
	public void setWater_level_sensors_controller_deadline_in_ms(int water_level_sensors_controller_deadline_in_ms) {
		 this.water_level_sensors_controller_deadline_in_ms = water_level_sensors_controller_deadline_in_ms;
	}
	public int getWater_level_sensors_controller_deadline_in_ms() {
		return this.water_level_sensors_controller_deadline_in_ms;
	}
	public void setWater_flow_sensor(sensor_t water_flow_sensor) {
		 this.water_flow_sensor = water_flow_sensor;
	}
	public sensor_t getWater_flow_sensor() {
		return this.water_flow_sensor;
	}
	public void setWater_flow_sensor_controller_deadline_in_ms(int water_flow_sensor_controller_deadline_in_ms) {
		 this.water_flow_sensor_controller_deadline_in_ms = water_flow_sensor_controller_deadline_in_ms;
	}
	public int getWater_flow_sensor_controller_deadline_in_ms() {
		return this.water_flow_sensor_controller_deadline_in_ms;
	}
	public void setWater_flow_sensor_controller_period_in_ms(int water_flow_sensor_controller_period_in_ms) {
		 this.water_flow_sensor_controller_period_in_ms = water_flow_sensor_controller_period_in_ms;
	}
	public int getWater_flow_sensor_controller_period_in_ms() {
		return this.water_flow_sensor_controller_period_in_ms;
	}
	public void setWater_flow_sensor_controller_number_of_activations(int water_flow_sensor_controller_number_of_activations) {
		 this.water_flow_sensor_controller_number_of_activations = water_flow_sensor_controller_number_of_activations;
	}
	public int getWater_flow_sensor_controller_number_of_activations() {
		return this.water_flow_sensor_controller_number_of_activations;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public mine_water_level_control_system_idata_t() {
		super();

		// initialize attributes
		this.setAlarm(null);
		this.setPump(null);
		this.setCo_sensor(null);
		this.setO_sensor(null);
		this.setCh4_sensor(null);
		this.setWater_flow_sensor(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public mine_water_level_control_system_idata_t(int water_level, int water_level_sensors_low_water_level_threshold, int water_level_sensors_high_water_level_threshold, device_t alarm, device_t pump, gas_sensor_t co_sensor, int co_sensor_controller_deadline_in_ms, int co_sensor_controller_period_in_ms, int co_sensor_controller_threshold, int co_sensor_controller_error_count_threshold, gas_sensor_t o_sensor, int o_sensor_controller_deadline_in_ms, int o_sensor_controller_period_in_ms, int o_sensor_controller_threshold, int o_sensor_controller_error_count_threshold, gas_sensor_t ch4_sensor, int ch4_sensor_controller_deadline_in_ms, int ch4_sensor_controller_period_in_ms, int ch4_sensor_controller_threshold, int ch4_sensor_controller_error_count_threshold, int water_level_sensors_controller_deadline_in_ms, sensor_t water_flow_sensor, int water_flow_sensor_controller_deadline_in_ms, int water_flow_sensor_controller_period_in_ms, int water_flow_sensor_controller_number_of_activations) {
		super();

		this.water_level = water_level;
		this.water_level_sensors_low_water_level_threshold = water_level_sensors_low_water_level_threshold;
		this.water_level_sensors_high_water_level_threshold = water_level_sensors_high_water_level_threshold;
		this.alarm = alarm;
		this.pump = pump;
		this.co_sensor = co_sensor;
		this.co_sensor_controller_deadline_in_ms = co_sensor_controller_deadline_in_ms;
		this.co_sensor_controller_period_in_ms = co_sensor_controller_period_in_ms;
		this.co_sensor_controller_threshold = co_sensor_controller_threshold;
		this.co_sensor_controller_error_count_threshold = co_sensor_controller_error_count_threshold;
		this.o_sensor = o_sensor;
		this.o_sensor_controller_deadline_in_ms = o_sensor_controller_deadline_in_ms;
		this.o_sensor_controller_period_in_ms = o_sensor_controller_period_in_ms;
		this.o_sensor_controller_threshold = o_sensor_controller_threshold;
		this.o_sensor_controller_error_count_threshold = o_sensor_controller_error_count_threshold;
		this.ch4_sensor = ch4_sensor;
		this.ch4_sensor_controller_deadline_in_ms = ch4_sensor_controller_deadline_in_ms;
		this.ch4_sensor_controller_period_in_ms = ch4_sensor_controller_period_in_ms;
		this.ch4_sensor_controller_threshold = ch4_sensor_controller_threshold;
		this.ch4_sensor_controller_error_count_threshold = ch4_sensor_controller_error_count_threshold;
		this.water_level_sensors_controller_deadline_in_ms = water_level_sensors_controller_deadline_in_ms;
		this.water_flow_sensor = water_flow_sensor;
		this.water_flow_sensor_controller_deadline_in_ms = water_flow_sensor_controller_deadline_in_ms;
		this.water_flow_sensor_controller_period_in_ms = water_flow_sensor_controller_period_in_ms;
		this.water_flow_sensor_controller_number_of_activations = water_flow_sensor_controller_number_of_activations;

		/* user defined constructor body */
	}

	// deep copy
	public mine_water_level_control_system_idata_t deepCopy() {
		mine_water_level_control_system_idata_t copy = new mine_water_level_control_system_idata_t();
		copy.water_level = water_level;
		copy.water_level_sensors_low_water_level_threshold = water_level_sensors_low_water_level_threshold;
		copy.water_level_sensors_high_water_level_threshold = water_level_sensors_high_water_level_threshold;
		copy.alarm = alarm;
		copy.pump = pump;
		copy.co_sensor = co_sensor;
		copy.co_sensor_controller_deadline_in_ms = co_sensor_controller_deadline_in_ms;
		copy.co_sensor_controller_period_in_ms = co_sensor_controller_period_in_ms;
		copy.co_sensor_controller_threshold = co_sensor_controller_threshold;
		copy.co_sensor_controller_error_count_threshold = co_sensor_controller_error_count_threshold;
		copy.o_sensor = o_sensor;
		copy.o_sensor_controller_deadline_in_ms = o_sensor_controller_deadline_in_ms;
		copy.o_sensor_controller_period_in_ms = o_sensor_controller_period_in_ms;
		copy.o_sensor_controller_threshold = o_sensor_controller_threshold;
		copy.o_sensor_controller_error_count_threshold = o_sensor_controller_error_count_threshold;
		copy.ch4_sensor = ch4_sensor;
		copy.ch4_sensor_controller_deadline_in_ms = ch4_sensor_controller_deadline_in_ms;
		copy.ch4_sensor_controller_period_in_ms = ch4_sensor_controller_period_in_ms;
		copy.ch4_sensor_controller_threshold = ch4_sensor_controller_threshold;
		copy.ch4_sensor_controller_error_count_threshold = ch4_sensor_controller_error_count_threshold;
		copy.water_level_sensors_controller_deadline_in_ms = water_level_sensors_controller_deadline_in_ms;
		copy.water_flow_sensor = water_flow_sensor;
		copy.water_flow_sensor_controller_deadline_in_ms = water_flow_sensor_controller_deadline_in_ms;
		copy.water_flow_sensor_controller_period_in_ms = water_flow_sensor_controller_period_in_ms;
		copy.water_flow_sensor_controller_number_of_activations = water_flow_sensor_controller_number_of_activations;
		return copy;
	}
};
