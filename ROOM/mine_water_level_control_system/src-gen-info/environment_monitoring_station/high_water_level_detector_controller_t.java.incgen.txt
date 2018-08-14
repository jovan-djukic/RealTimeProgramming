package environment_monitoring_station;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import devices.*;
import room.basic.service.timing.*;
import room.basic.service.timing.PTimer.*;
import environment_monitoring_station.environmonet_statition_actor_base_iprotocol_t.*;
import devices.switch_protocol_t.*;



public class high_water_level_detector_controller_t extends water_level_detector_controller_base_t {


	//--------------------- ports

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs

	/*--------------------- attributes ---------------------*/

	/*--------------------- operations ---------------------*/
	public  void query_action() {
		if ( super.water_level_detector.value == 1 ) {
			super.pump_switch_port.turn_on ( );
		}
	}


	//--------------------- construction
	public high_water_level_detector_controller_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("high_water_level_detector_controller_t");

		// initialize attributes

		// own ports

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */


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


};
