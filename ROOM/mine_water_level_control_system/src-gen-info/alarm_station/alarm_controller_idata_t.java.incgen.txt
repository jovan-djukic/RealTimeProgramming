package alarm_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;



public class alarm_controller_idata_t implements Serializable {

	private static final long serialVersionUID = -1181588199L;


	/*--------------------- attributes ---------------------*/
	public  device_t alarm;

	/* --------------------- attribute setters and getters */
	public void setAlarm(device_t alarm) {
		 this.alarm = alarm;
	}
	public device_t getAlarm() {
		return this.alarm;
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
	public alarm_controller_idata_t(device_t alarm) {
		super();

		this.alarm = alarm;

		/* user defined constructor body */
	}

	// deep copy
	public alarm_controller_idata_t deepCopy() {
		alarm_controller_idata_t copy = new alarm_controller_idata_t();
		copy.alarm = alarm;
		return copy;
	}
};
