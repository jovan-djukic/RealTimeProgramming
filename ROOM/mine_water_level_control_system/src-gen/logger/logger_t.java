package logger;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;


/*--------------------- begin user code ---------------------*/
import java.util.logging.*;
import java.io.IOException;

/*--------------------- end user code ---------------------*/


public class logger_t implements Serializable {

	private static final long serialVersionUID = -1799208171L;


	/*--------------------- attributes ---------------------*/
	public  java.util.logging.Logger logger;

	/* --------------------- attribute setters and getters */
	public void setLogger(java.util.logging.Logger logger) {
		 this.logger = logger;
	}
	public java.util.logging.Logger getLogger() {
		return this.logger;
	}

	/*--------------------- operations ---------------------*/
	public  void info(String actor_name, String message) {
		this.logger.info (
			"[ " + actor_name + " ] " + message
		);
	}
	public  void severe(String actor_name, String message) {
		this.logger.severe (
			"[ " + actor_name + " ] " + message
		);
	}

	// default constructor
	public logger_t() {
		super();

		// initialize attributes
		this.setLogger(null);

		/* user defined constructor body */
		this.logger = Logger.getLogger ( 
			logger_t.class.getName ( )
		);
		
		Logger root_logger = Logger.getLogger( "" );
		Handler handlers[] = root_logger.getHandlers ( );
		
		for ( int i = 0; i < handlers.length; i++ )	{
			if ( handlers[i] instanceof ConsoleHandler ) {
				root_logger.removeHandler (
					handlers[i]
				);
			}
		}
		
		try {
			FileHandler file_handler = new FileHandler (
				"log.txt"
			);
		
			file_handler.setFormatter (
				new SimpleFormatter ( )
			);
		
			root_logger.addHandler (
				file_handler
			);
		} catch ( SecurityException e ) {
			e.printStackTrace ( );
		} catch ( IOException e ) {
			e.printStackTrace ( );
		}
	}

	// constructor using fields
	public logger_t(java.util.logging.Logger logger) {
		super();

		this.logger = logger;

		/* user defined constructor body */
		this.logger = Logger.getLogger ( 
			logger_t.class.getName ( )
		);
		
		Logger root_logger = Logger.getLogger( "" );
		Handler handlers[] = root_logger.getHandlers ( );
		
		for ( int i = 0; i < handlers.length; i++ )	{
			if ( handlers[i] instanceof ConsoleHandler ) {
				root_logger.removeHandler (
					handlers[i]
				);
			}
		}
		
		try {
			FileHandler file_handler = new FileHandler (
				"log.txt"
			);
		
			file_handler.setFormatter (
				new SimpleFormatter ( )
			);
		
			root_logger.addHandler (
				file_handler
			);
		} catch ( SecurityException e ) {
			e.printStackTrace ( );
		} catch ( IOException e ) {
			e.printStackTrace ( );
		}
	}

	// deep copy
	public logger_t deepCopy() {
		logger_t copy = new logger_t();
		copy.logger = logger;
		return copy;
	}
};
