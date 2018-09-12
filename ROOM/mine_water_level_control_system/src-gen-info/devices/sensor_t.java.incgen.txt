package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import logger.*;



public class sensor_t implements Serializable {

	private static final long serialVersionUID = 702208268L;


	/*--------------------- attributes ---------------------*/
	public  int value;
	public  long conversion_time;
	public  String sensor_name;
	public  long last_conversion_start_time;

	/* --------------------- attribute setters and getters */
	public void setValue(int value) {
		 this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	public void setConversion_time(long conversion_time) {
		 this.conversion_time = conversion_time;
	}
	public long getConversion_time() {
		return this.conversion_time;
	}
	public void setSensor_name(String sensor_name) {
		 this.sensor_name = sensor_name;
	}
	public String getSensor_name() {
		return this.sensor_name;
	}
	public void setLast_conversion_start_time(long last_conversion_start_time) {
		 this.last_conversion_start_time = last_conversion_start_time;
	}
	public long getLast_conversion_start_time() {
		return this.last_conversion_start_time;
	}

	/*--------------------- operations ---------------------*/
	public  void initialize_without_read_error_occurred(logger_t logger, String actor_name, int value, long conversion_time, String sensor_name) {
		synchronized ( this ) {
			logger.info (
				actor_name,
				"Initializing sensor  " + sensor_name + "\n" + 
				"\t value 		   		   => " + value + "\n" + 
				"\t conversion_time ( ms ) => " + conversion_time + "\n" 
			);
		
			this.value 			 			= value;
			this.conversion_time 			= conversion_time;
			this.sensor_name  				= sensor_name;
			this.last_conversion_start_time = 0;
		}
	}
	public  void start_conversion(logger_t logger, String actor_name) {
		synchronized ( this ) { 
			logger.info (
				actor_name,
				"Starting conversion for sensor " + this.sensor_name 
			);
		
			this.last_conversion_start_time = System.currentTimeMillis ( );
		}
	}
	public  boolean check_conversion_time(logger_t logger, String actor_name) {
		long elapsed_time = System.currentTimeMillis ( ) - this.last_conversion_start_time;
		
		if ( elapsed_time >= conversion_time ) {
			logger.info (
				actor_name,
				"Conversion successful for sensor " + this.sensor_name 
			);
		
			return true;
		} else {
			logger.info (
				actor_name,
				"Conversion unsuccessful for sensor " + this.sensor_name 
			);
			
			System.exit ( 1 );
			return false;
		}
	}
	public  int get_value(logger_t logger, String actor_name) {
		int return_value = ~0;
		
		synchronized ( this ) {
			boolean conversion_successful = this.check_conversion_time (
				logger,
				actor_name	
			);	
		
			if ( conversion_successful == true ) {	
				logger.info (
					actor_name,
					"Getting value for sensor " + this.sensor_name + " which is " + this.value
				);
		
				return_value = this.value;
			}
		}
		
		return return_value; 
	}
	public  void set_value(logger_t logger, String actor_name, int value) {
		synchronized ( this ) {
			logger.info (
				actor_name,
				"Setting value for sensor " + this.sensor_name + " to " + value
			);
			
			this.value = value;
		}
	}

	// default constructor
	public sensor_t() {
		super();

		// initialize attributes
		this.setSensor_name("");

		/* user defined constructor body */
	}

	// constructor using fields
	public sensor_t(int value, long conversion_time, String sensor_name, long last_conversion_start_time) {
		super();

		this.value = value;
		this.conversion_time = conversion_time;
		this.sensor_name = sensor_name;
		this.last_conversion_start_time = last_conversion_start_time;

		/* user defined constructor body */
	}

	// deep copy
	public sensor_t deepCopy() {
		sensor_t copy = new sensor_t();
		copy.value = value;
		copy.conversion_time = conversion_time;
		copy.sensor_name = sensor_name;
		copy.last_conversion_start_time = last_conversion_start_time;
		return copy;
	}
};
