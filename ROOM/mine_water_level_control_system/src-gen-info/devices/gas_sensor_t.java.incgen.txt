package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import logger.*;



public class gas_sensor_t extends sensor_t implements Serializable {

	private static final long serialVersionUID = 691136818L;


	/*--------------------- attributes ---------------------*/
	public  boolean read_error_occurred;

	/* --------------------- attribute setters and getters */
	public void setRead_error_occurred(boolean read_error_occurred) {
		 this.read_error_occurred = read_error_occurred;
	}
	public boolean getRead_error_occurred() {
		return this.read_error_occurred;
	}

	/*--------------------- operations ---------------------*/
	public  void initialize(logger_t logger, String actor_name, int value, boolean read_error_occurred, long conversion_time, String sensor_name) {
		synchronized ( this ) {
			super.initialize_without_read_error_occurred (
				logger,
				actor_name,
				value,
				conversion_time,
				sensor_name
			);
		
			logger.info (
				actor_name,
				"Initializing sensor  " + super.sensor_name + "\n" + 
				"\t read_error_occurred => " + read_error_occurred + "\n" 
			);
		
			this.read_error_occurred = read_error_occurred;
		}
	}
	public  boolean get_read_error_occurred(logger_t logger, String actor_name) {
		boolean return_read_error_occurred = true;
		
		synchronized ( this ) {
			boolean conversion_successful = this.check_conversion_time (
				logger,
				actor_name	
			);	
		
			if ( conversion_successful == true ) {	
				logger.info (
					actor_name,
					"Getting read error occurred for sensor " + this.sensor_name + " which is " + this.read_error_occurred
				);
		
				return_read_error_occurred = this.read_error_occurred;
			}
		}
		
		return return_read_error_occurred; 
	}
	public  void set_read_error_occurred(logger_t logger, String actor_name, boolean read_error_occurred) {
		synchronized ( this ) {
			logger.info (
				actor_name,
				"Setting read error occurred for sensor " + this.sensor_name + " to " + read_error_occurred 
			);
			
			this.read_error_occurred = read_error_occurred; 
		}
	}

	// default constructor
	public gas_sensor_t() {
		super();

		// initialize attributes

		/* user defined constructor body */
	}

	// constructor using fields
	public gas_sensor_t(int value, long conversion_time, String sensor_name, long last_conversion_start_time, boolean read_error_occurred) {
		super(value, conversion_time, sensor_name, last_conversion_start_time);

		this.read_error_occurred = read_error_occurred;

		/* user defined constructor body */
	}

	// deep copy
	public gas_sensor_t deepCopy() {
		gas_sensor_t copy = new gas_sensor_t();
		copy.value = value;
		copy.conversion_time = conversion_time;
		copy.sensor_name = sensor_name;
		copy.last_conversion_start_time = last_conversion_start_time;
		copy.read_error_occurred = read_error_occurred;
		return copy;
	}
};
