package pump_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;
import logger.*;



public class pump_controller_t implements Serializable {

	private static final long serialVersionUID = 622646347L;


	/*--------------------- attributes ---------------------*/
	public  device_t pump;
	public  int pump_state;
	public  int pump_controller_state;

	/* --------------------- attribute setters and getters */
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}
	public void setPump_state(int pump_state) {
		 this.pump_state = pump_state;
	}
	public int getPump_state() {
		return this.pump_state;
	}
	public void setPump_controller_state(int pump_controller_state) {
		 this.pump_controller_state = pump_controller_state;
	}
	public int getPump_controller_state() {
		return this.pump_controller_state;
	}

	/*--------------------- operations ---------------------*/
	public  void initialize(logger_t logger, String actor_name, pump_controller_idata_t data) {
		logger.info ( 
			actor_name,
			"Initializing pump controller"
		);
		
		this.pump = data.pump;	
		this.pump.set_state (
			logger,
			actor_name,
			device_state_t.OFF
		);
		
		this.pump_state 		   = device_state_t.OFF;
		this.pump_controller_state = pump_controller_state_t.PUMP_TURNED_OFF;
	}
	public  void turn_on(logger_t logger, String actor_name) {
		synchronized ( this ) { 
			switch ( this.pump_controller_state ) {
				case pump_controller_state_t.PUMP_TURNED_OFF : {
					logger.info ( 
						actor_name,
						"Initial turn on, turning on pump"
					);
		
					this.pump.set_state (
						logger,
						actor_name,
						device_state_t.ON
					);
		
					this.pump_controller_state = pump_controller_state_t.PUMP_TURNED_ON;
					
					break;
				}
				
				case pump_controller_state_t.PUMP_TURNED_ON : {
					logger.info ( 
						actor_name,
						"Following turn on, pump already on"
					);
		
					break;
				}
				
				case pump_controller_state_t.METHANE_THRESHOLD_BREACHED : {
					logger.info ( 
						actor_name,
						"Turn on while methane threshold breached, saving request"
					);
		
					this.pump_state = device_state_t.ON;
		
					break;
				}
			}
		}
	}
	public  void turn_off(logger_t logger, String actor_name) {
		synchronized ( this ) { 
			switch ( this.pump_controller_state ) {
				case pump_controller_state_t.PUMP_TURNED_OFF : {
					logger.info ( 
						actor_name,
						"Following turn off, pump already off"
					);
		
					break;
				}
				
				case pump_controller_state_t.PUMP_TURNED_ON : {
					logger.info ( 
						actor_name,
						"Initial turn off, turning off pump"
					);
		
					this.pump.set_state (
						logger,
						actor_name,
						device_state_t.OFF
					);
		
					this.pump_controller_state = pump_controller_state_t.PUMP_TURNED_OFF;
		
					break;
				}
				
				case pump_controller_state_t.METHANE_THRESHOLD_BREACHED : {
					logger.info ( 
						actor_name,
						"Turn off while methane threshold breached, saving request"
					);
		
					this.pump_state = device_state_t.OFF;
		
					break;
				}
			}
		}
	}
	public  void methane_threshold_breached(logger_t logger, String actor_name) {
		synchronized ( this ) { 
			if ( this.pump_controller_state != pump_controller_state_t.METHANE_THRESHOLD_BREACHED ) {
				logger.info ( 
					actor_name,
					"Methane threshold breached, saving pump state and turning off pump"
				);
		
			
				this.pump_state = this.pump.get_state (
					logger,
					actor_name
				);	
		
				this.pump.set_state (
					logger,
					actor_name,
					device_state_t.OFF
				);
		
				this.pump_controller_state = pump_controller_state_t.METHANE_THRESHOLD_BREACHED;
			}
		}
	}
	public  void methane_state_normal(logger_t logger, String actor_name) {
		synchronized ( this ) { 
			if ( this.pump_controller_state == pump_controller_state_t.METHANE_THRESHOLD_BREACHED ) {
				logger.info ( 
					actor_name,
					"Methane state normal, restoring pump state"
				);
		
				this.pump.set_state (
					logger,
					actor_name,
					this.pump_state
				);
		
				if ( this.pump_state == device_state_t.ON ) {
					this.pump_controller_state = pump_controller_state_t.PUMP_TURNED_ON;
				} else {
					this.pump_controller_state = pump_controller_state_t.PUMP_TURNED_OFF;
				}
			}
		}
	}

	// default constructor
	public pump_controller_t() {
		super();

		// initialize attributes
		this.setPump(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public pump_controller_t(device_t pump, int pump_state, int pump_controller_state) {
		super();

		this.pump = pump;
		this.pump_state = pump_state;
		this.pump_controller_state = pump_controller_state;

		/* user defined constructor body */
	}

	// deep copy
	public pump_controller_t deepCopy() {
		pump_controller_t copy = new pump_controller_t();
		copy.pump = pump;
		copy.pump_state = pump_state;
		copy.pump_controller_state = pump_controller_state;
		return copy;
	}
};
