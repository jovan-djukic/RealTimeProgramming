package alarm_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;
import logger.*;



public class alarm_controller_t implements Serializable {

	private static final long serialVersionUID = 443002757L;


	/*--------------------- attributes ---------------------*/
	public  device_t alarm;
	public  int alarm_level;
	public  int number_of_alarm_activators;

	/* --------------------- attribute setters and getters */
	public void setAlarm(device_t alarm) {
		 this.alarm = alarm;
	}
	public device_t getAlarm() {
		return this.alarm;
	}
	public void setAlarm_level(int alarm_level) {
		 this.alarm_level = alarm_level;
	}
	public int getAlarm_level() {
		return this.alarm_level;
	}
	public void setNumber_of_alarm_activators(int number_of_alarm_activators) {
		 this.number_of_alarm_activators = number_of_alarm_activators;
	}
	public int getNumber_of_alarm_activators() {
		return this.number_of_alarm_activators;
	}

	/*--------------------- operations ---------------------*/
	public  void initialize(logger_t logger, String actor_name, alarm_controller_idata_t data) {
		logger.info (
			actor_name,
			"Initializing alarm controller"
		);
		
		this.alarm_level 				= 0;
		this.alarm 		 				= data.alarm;
		this.number_of_alarm_activators = data.number_of_alarm_activators;
		
		this.alarm.set_state (
			logger,
			actor_name,
			device_state_t.OFF
		);
	}
	public  void turn_on(logger_t logger, String actor_name) {
		synchronized ( this ) { 
			if ( this.alarm_level < this.number_of_alarm_activators ) {
				this.alarm_level++;
			}
		
			if ( this.alarm_level == 1 ) {
				logger.info (
					actor_name,
					"Initial turn on message recevied, turning on alarm"
				);
		
				this.alarm.set_state (
					logger,
					actor_name,
					device_state_t.ON
				);
			}
		}
	}
	public  void turn_off(logger_t logger, String actor_name) {
		if ( this.alarm_level > 0 ) {
			this.alarm_level--;
		}
		
		if ( this.alarm_level == 0 ) {
			logger.info (
				actor_name,
				"Last turn off message recevied, turning off alarm"
			);
			
			this.alarm.set_state (
				logger,
				actor_name,
				device_state_t.OFF
			);
		}
	}

	// default constructor
	public alarm_controller_t() {
		super();

		// initialize attributes
		this.setAlarm(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public alarm_controller_t(device_t alarm, int alarm_level, int number_of_alarm_activators) {
		super();

		this.alarm = alarm;
		this.alarm_level = alarm_level;
		this.number_of_alarm_activators = number_of_alarm_activators;

		/* user defined constructor body */
	}

	// deep copy
	public alarm_controller_t deepCopy() {
		alarm_controller_t copy = new alarm_controller_t();
		copy.alarm = alarm;
		copy.alarm_level = alarm_level;
		copy.number_of_alarm_activators = number_of_alarm_activators;
		return copy;
	}
};
