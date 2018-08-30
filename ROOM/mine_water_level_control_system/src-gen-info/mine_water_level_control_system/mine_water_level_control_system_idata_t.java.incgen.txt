package mine_water_level_control_system;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import alarm_station.*;
import devices.*;
import environment_monitoring_station.*;
import periodic_task.*;
import pump_station.*;



public class mine_water_level_control_system_idata_t implements Serializable {

	private static final long serialVersionUID = -2127082103L;


	/*--------------------- attributes ---------------------*/
	public  gas_sensor_controller_idata_t co_sensor_controller_idata;
	public  gas_sensor_controller_idata_t o_sensor_controller_idata;
	public  gas_sensor_controller_idata_t ch4_sensor_controller_idata;
	public  water_level_sensors_controller_idata_t water_level_detectors_sensor_controller_idata;
	public  water_flow_sensor_controller_idata_t water_flow_sensor_controller_idata;
	public  alarm_controller_idata_t alarm_controller_idata;
	public  pump_controller_idata_t pump_controller_idata;

	/* --------------------- attribute setters and getters */
	public void setCo_sensor_controller_idata(gas_sensor_controller_idata_t co_sensor_controller_idata) {
		 this.co_sensor_controller_idata = co_sensor_controller_idata;
	}
	public gas_sensor_controller_idata_t getCo_sensor_controller_idata() {
		return this.co_sensor_controller_idata;
	}
	public void setO_sensor_controller_idata(gas_sensor_controller_idata_t o_sensor_controller_idata) {
		 this.o_sensor_controller_idata = o_sensor_controller_idata;
	}
	public gas_sensor_controller_idata_t getO_sensor_controller_idata() {
		return this.o_sensor_controller_idata;
	}
	public void setCh4_sensor_controller_idata(gas_sensor_controller_idata_t ch4_sensor_controller_idata) {
		 this.ch4_sensor_controller_idata = ch4_sensor_controller_idata;
	}
	public gas_sensor_controller_idata_t getCh4_sensor_controller_idata() {
		return this.ch4_sensor_controller_idata;
	}
	public void setWater_level_detectors_sensor_controller_idata(water_level_sensors_controller_idata_t water_level_detectors_sensor_controller_idata) {
		 this.water_level_detectors_sensor_controller_idata = water_level_detectors_sensor_controller_idata;
	}
	public water_level_sensors_controller_idata_t getWater_level_detectors_sensor_controller_idata() {
		return this.water_level_detectors_sensor_controller_idata;
	}
	public void setWater_flow_sensor_controller_idata(water_flow_sensor_controller_idata_t water_flow_sensor_controller_idata) {
		 this.water_flow_sensor_controller_idata = water_flow_sensor_controller_idata;
	}
	public water_flow_sensor_controller_idata_t getWater_flow_sensor_controller_idata() {
		return this.water_flow_sensor_controller_idata;
	}
	public void setAlarm_controller_idata(alarm_controller_idata_t alarm_controller_idata) {
		 this.alarm_controller_idata = alarm_controller_idata;
	}
	public alarm_controller_idata_t getAlarm_controller_idata() {
		return this.alarm_controller_idata;
	}
	public void setPump_controller_idata(pump_controller_idata_t pump_controller_idata) {
		 this.pump_controller_idata = pump_controller_idata;
	}
	public pump_controller_idata_t getPump_controller_idata() {
		return this.pump_controller_idata;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public mine_water_level_control_system_idata_t() {
		super();

		// initialize attributes
		this.setCo_sensor_controller_idata(new gas_sensor_controller_idata_t());
		this.setO_sensor_controller_idata(new gas_sensor_controller_idata_t());
		this.setCh4_sensor_controller_idata(new gas_sensor_controller_idata_t());
		this.setWater_level_detectors_sensor_controller_idata(new water_level_sensors_controller_idata_t());
		this.setWater_flow_sensor_controller_idata(new water_flow_sensor_controller_idata_t());
		this.setAlarm_controller_idata(new alarm_controller_idata_t());
		this.setPump_controller_idata(new pump_controller_idata_t());

		/* user defined constructor body */
	}

	// constructor using fields
	public mine_water_level_control_system_idata_t(gas_sensor_controller_idata_t co_sensor_controller_idata, gas_sensor_controller_idata_t o_sensor_controller_idata, gas_sensor_controller_idata_t ch4_sensor_controller_idata, water_level_sensors_controller_idata_t water_level_detectors_sensor_controller_idata, water_flow_sensor_controller_idata_t water_flow_sensor_controller_idata, alarm_controller_idata_t alarm_controller_idata, pump_controller_idata_t pump_controller_idata) {
		super();

		this.co_sensor_controller_idata = co_sensor_controller_idata;
		this.o_sensor_controller_idata = o_sensor_controller_idata;
		this.ch4_sensor_controller_idata = ch4_sensor_controller_idata;
		this.water_level_detectors_sensor_controller_idata = water_level_detectors_sensor_controller_idata;
		this.water_flow_sensor_controller_idata = water_flow_sensor_controller_idata;
		this.alarm_controller_idata = alarm_controller_idata;
		this.pump_controller_idata = pump_controller_idata;

		/* user defined constructor body */
	}

	// deep copy
	public mine_water_level_control_system_idata_t deepCopy() {
		mine_water_level_control_system_idata_t copy = new mine_water_level_control_system_idata_t();
		if (co_sensor_controller_idata!=null) {
			copy.co_sensor_controller_idata = co_sensor_controller_idata.deepCopy();
		}
		if (o_sensor_controller_idata!=null) {
			copy.o_sensor_controller_idata = o_sensor_controller_idata.deepCopy();
		}
		if (ch4_sensor_controller_idata!=null) {
			copy.ch4_sensor_controller_idata = ch4_sensor_controller_idata.deepCopy();
		}
		if (water_level_detectors_sensor_controller_idata!=null) {
			copy.water_level_detectors_sensor_controller_idata = water_level_detectors_sensor_controller_idata.deepCopy();
		}
		if (water_flow_sensor_controller_idata!=null) {
			copy.water_flow_sensor_controller_idata = water_flow_sensor_controller_idata.deepCopy();
		}
		if (alarm_controller_idata!=null) {
			copy.alarm_controller_idata = alarm_controller_idata.deepCopy();
		}
		if (pump_controller_idata!=null) {
			copy.pump_controller_idata = pump_controller_idata.deepCopy();
		}
		return copy;
	}
};
