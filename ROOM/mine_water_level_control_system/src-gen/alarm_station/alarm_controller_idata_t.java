package alarm_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;
import logger.*;



public class alarm_controller_idata_t implements Serializable {

	private static final long serialVersionUID = -1181588199L;


	/*--------------------- attributes ---------------------*/
	public  device_t alarm;
	public  int number_of_alarm_activators;

	/* --------------------- attribute setters and getters */
	public void setAlarm(device_t alarm) {
		 this.alarm = alarm;
	}
	public device_t getAlarm() {
		return this.alarm;
	}
	public void setNumber_of_alarm_activators(int number_of_alarm_activators) {
		 this.number_of_alarm_activators = number_of_alarm_activators;
	}
	public int getNumber_of_alarm_activators() {
		return this.number_of_alarm_activators;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public alarm_controller_idata_t() {
		super();

		// initialize attributes
		this.setAlarm(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public alarm_controller_idata_t(device_t alarm, int number_of_alarm_activators) {
		super();

		this.alarm = alarm;
		this.number_of_alarm_activators = number_of_alarm_activators;

		/* user defined constructor body */
	}

	// deep copy
	public alarm_controller_idata_t deepCopy() {
		alarm_controller_idata_t copy = new alarm_controller_idata_t();
		copy.alarm = alarm;
		copy.number_of_alarm_activators = number_of_alarm_activators;
		return copy;
	}
};
