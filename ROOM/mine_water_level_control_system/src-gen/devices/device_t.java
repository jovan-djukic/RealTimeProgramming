package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import logger.*;



public class device_t implements Serializable {

	private static final long serialVersionUID = 549285032L;


	/*--------------------- attributes ---------------------*/
	public  int state;
	public  String device_name;

	/* --------------------- attribute setters and getters */
	public void setState(int state) {
		 this.state = state;
	}
	public int getState() {
		return this.state;
	}
	public void setDevice_name(String device_name) {
		 this.device_name = device_name;
	}
	public String getDevice_name() {
		return this.device_name;
	}

	/*--------------------- operations ---------------------*/
	public  void initialize(logger_t logger, String actor_name, String device_name, int state) {
		synchronized ( this ) {
			String state_name = state == device_state_t.ON ? "ON" : "OFF";
			logger.info (
				actor_name,
				"Initializing device " + device_name + " with state " + state_name
			);
		
			this.device_name = device_name;
			this.state 		 = state;	
		}
	}
	public  void set_state(logger_t logger, String actor_name, int state) {
		synchronized ( this ) {
			String state_name = state == device_state_t.ON ? "ON" : "OFF";
			logger.info (
				actor_name,
				"Setting state for device " + device_name + " to " + state_name
			);
		
			this.state = state;	
		}
	}
	public  int get_state(logger_t logger, String actor_name) {
		int return_state = device_state_t.OFF;
		
		synchronized ( this ) {
			String state_name = this.state == device_state_t.ON ? "ON" : "OFF";
			logger.info (
				actor_name,
				"Getting state for device " + device_name + " which is " + state_name
			);
		
			return_state = this.state;
		}
		
		return return_state;
	}

	// default constructor
	public device_t() {
		super();

		// initialize attributes
		this.setDevice_name("");

		/* user defined constructor body */
	}

	// constructor using fields
	public device_t(int state, String device_name) {
		super();

		this.state = state;
		this.device_name = device_name;

		/* user defined constructor body */
	}

	// deep copy
	public device_t deepCopy() {
		device_t copy = new device_t();
		copy.state = state;
		copy.device_name = device_name;
		return copy;
	}
};
