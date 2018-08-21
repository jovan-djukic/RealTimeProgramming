package logger;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;


/*--------------------- begin user code ---------------------*/
import java.util.logging.*;
import java.io.IOException;

/*--------------------- end user code ---------------------*/


public class logger_t extends ActorClassBase {


	//--------------------- ports

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs

	/*--------------------- attributes ---------------------*/
	public  java.util.logging.Logger logger;

	/*--------------------- operations ---------------------*/
	public  void info(String actor_name, String message) {
		this.logger.info ( "[ " + actor_name + " ] " + message );
	}
	public  void severe(String actor_name, String message) {
		this.logger.severe ( "[ " + actor_name + " ] " + message );
	}


	//--------------------- construction
	public logger_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("logger_t");

		// initialize attributes
		this.setLogger(null);

		// own ports

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */
		this.logger = Logger.getLogger( logger_t.class.getName ( ) );
		Logger root_logger = Logger.getLogger( "" );
		Handler handlers[] = root_logger.getHandlers ( );
		for ( int i = 0; i < handlers.length; i++ )	{
			if ( handlers[i] instanceof ConsoleHandler ) {
				root_logger.removeHandler ( handlers[i] );
			}
		}
		
		try {
			FileHandler file_handler = new FileHandler( "log.txt" );
			file_handler.setFormatter ( new SimpleFormatter ( ) );
			root_logger.addHandler( file_handler );
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/* --------------------- attribute setters and getters */
	public void setLogger(java.util.logging.Logger logger) {
		 this.logger = logger;
	}
	public java.util.logging.Logger getLogger() {
		return this.logger;
	}


	//--------------------- port getters

	//--------------------- lifecycle functions
	public void stop(){
		super.stop();
	}

	public void destroy(){
		/* user defined destructor body */
		DebuggingService.getInstance().addMessageActorDestroy(this);
		super.destroy();
	}

	//--------------------- no state machine
	public void receiveEvent(InterfaceItemBase ifitem, int evt, Object data) {
		handleSystemEvent(ifitem, evt, data);
	}

	public void executeInitTransition() {}

};
