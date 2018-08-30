package test;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import devices.*;
import environment_monitoring_station.*;
import logger.*;
import periodic_task.*;
import room.basic.service.timing.*;
import test.*;
import room.basic.service.timing.PTimer.*;
import periodic_task.periodic_task_iprotocol_t.*;
import devices.switch_protocol_t.*;
import test.test_protocol_t.*;

/*--------------------- begin user code ---------------------*/
class Constants {
	public static final int HIGH_WATER_LEVEL_SENSOR_CONTROLLER_PERIOD_IN_MS = 500;
	public static final int TEST_PERIOD_IN_MS = 750;
	public static final int LOW_WATER_LEVEL_SENSOR_IVALUE = 0;
	public static final int HIGH_WATER_LEVEL_SENSOR_IVALUE = 0;
}

/*--------------------- end user code ---------------------*/


public class top_actor_t extends ActorClassBase {


	//--------------------- ports
	protected periodic_task_iprotocol_tConjPort water_level_sensors_controller_iport = null;
	protected switch_protocol_tConjPort pump_port = null;
	protected test_protocol_tConjPort test_port = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_water_level_sensors_controller_iport = 1;
	public static final int IFITEM_pump_port = 2;
	public static final int IFITEM_test_port = 3;

	/*--------------------- attributes ---------------------*/
	public  boolean pump_turn_off_expected;
	public  boolean pump_turn_on_expected;
	public  water_level_sensor_t low_water_level_sensor;
	public  water_level_sensor_t high_water_level_sensor;

	/*--------------------- operations ---------------------*/
	public  void process(int low_water_level_sensor_value, int high_water_level_sensor_value) {
		this.pump_turn_off_expected = false;
		this.pump_turn_on_expected = false;
		
		if ( low_water_level_sensor_value != 0 ) {
			this.pump_turn_off_expected = true;
		} else if ( high_water_level_sensor_value != 0 ) {
			this.pump_turn_on_expected = true;	
		}
	}
	public  void update() {
		double random = Math.random ( );
		
		this.low_water_level_sensor.value = 0;
		this.high_water_level_sensor.value = 0;
		
		if ( random > 0.7 ) {
			this.high_water_level_sensor.value = 1;
		} else if ( random < 0.3 ) {
			this.low_water_level_sensor.value = 1;
		}
		
		this.process(
			this.low_water_level_sensor.value,
			this.high_water_level_sensor.value
		);
	}


	//--------------------- construction
	public top_actor_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("top_actor_t");

		// initialize attributes
		this.setPump_turn_off_expected(false);
		this.setPump_turn_on_expected(false);
		this.setLow_water_level_sensor(new water_level_sensor_t());
		this.setHigh_water_level_sensor(new water_level_sensor_t());

		// own ports
		water_level_sensors_controller_iport = new periodic_task_iprotocol_tConjPort(this, "water_level_sensors_controller_iport", IFITEM_water_level_sensors_controller_iport);
		pump_port = new switch_protocol_tConjPort(this, "pump_port", IFITEM_pump_port);
		test_port = new test_protocol_tConjPort(this, "test_port", IFITEM_test_port);

		// own saps

		// own service implementations

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "water_level_sensors_controller");
		new water_level_sensors_controller_t(this, "water_level_sensors_controller");

		// wiring
		InterfaceItemBase.connect(this, "water_level_sensors_controller/iport", "water_level_sensors_controller_iport");
		InterfaceItemBase.connect(this, "water_level_sensors_controller/pump_port", "pump_port");
		InterfaceItemBase.connect(this, "water_level_sensors_controller/test_port", "test_port");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setPump_turn_off_expected(boolean pump_turn_off_expected) {
		 this.pump_turn_off_expected = pump_turn_off_expected;
	}
	public boolean getPump_turn_off_expected() {
		return this.pump_turn_off_expected;
	}
	public void setPump_turn_on_expected(boolean pump_turn_on_expected) {
		 this.pump_turn_on_expected = pump_turn_on_expected;
	}
	public boolean getPump_turn_on_expected() {
		return this.pump_turn_on_expected;
	}
	public void setLow_water_level_sensor(water_level_sensor_t low_water_level_sensor) {
		 this.low_water_level_sensor = low_water_level_sensor;
	}
	public water_level_sensor_t getLow_water_level_sensor() {
		return this.low_water_level_sensor;
	}
	public void setHigh_water_level_sensor(water_level_sensor_t high_water_level_sensor) {
		 this.high_water_level_sensor = high_water_level_sensor;
	}
	public water_level_sensor_t getHigh_water_level_sensor() {
		return this.high_water_level_sensor;
	}


	//--------------------- port getters
	public periodic_task_iprotocol_tConjPort getWater_level_sensors_controller_iport (){
		return this.water_level_sensors_controller_iport;
	}
	public switch_protocol_tConjPort getPump_port (){
		return this.pump_port;
	}
	public test_protocol_tConjPort getTest_port (){
		return this.test_port;
	}

	//--------------------- lifecycle functions
	public void stop(){
		super.stop();
	}

	public void destroy(){
		/* user defined destructor body */
		DebuggingService.getInstance().addMessageActorDestroy(this);
		super.destroy();
	}

	/* state IDs */
	public static final int STATE_testing = 2;
	public static final int STATE_MAX = 3;
	
	/* transition chains */
	public static final int CHAIN_TRANS_INITIAL_TO__testing = 1;
	public static final int CHAIN_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_port_turn_off_message_received = 2;
	public static final int CHAIN_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_port_turn_on_message_received = 3;
	public static final int CHAIN_TRANS_activated_message_received_FROM_testing_TO_testing_BY_activatedtest_port_activated_message_received = 4;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_pump_port__turn_on = IFITEM_pump_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_pump_port__turn_off = IFITEM_pump_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	public static final int TRIG_test_port__activated = IFITEM_test_port + EVT_SHIFT*test_protocol_t.OUT_activated;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"testing"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	
	/* Action Codes */
	protected void action_TRANS_INITIAL_TO__testing() {
	    this.low_water_level_sensor.value = Constants.LOW_WATER_LEVEL_SENSOR_IVALUE;
	    this.high_water_level_sensor.value = Constants.HIGH_WATER_LEVEL_SENSOR_IVALUE;
	    
	    this.process(
	    	this.low_water_level_sensor.value,
	    	this.high_water_level_sensor.value
	    );
	    
	    this.water_level_sensors_controller_iport.initialize ( 
	    	new water_level_sensors_controller_idata_t ( 
	    		Constants.HIGH_WATER_LEVEL_SENSOR_CONTROLLER_PERIOD_IN_MS,
	    		this.low_water_level_sensor,
	    		this.high_water_level_sensor
	    	)
	    );
	}
	protected void action_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_port_turn_off_message_received(InterfaceItemBase ifitem) {
	    if ( this.pump_turn_off_expected == true ) {
	    	System.out.println ( "TURN OFF MESSAGE RECEIVED EXPECTEDLY " );
	    } else {
	    	System.err.println ( "TURN OFF MESSAGE RECEIVED UNEXPECTEDLY " );
	    }
	}
	protected void action_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_port_turn_on_message_received(InterfaceItemBase ifitem) {
	    if ( this.pump_turn_on_expected == true ) {
	    	System.out.println ( "TURN ON MESSAGE RECEIVED EXPECTEDLY " );
	    } else {
	    	System.err.println ( "TURN OF MESSAGE RECEIVED UNEXPECTEDLY " );
	    }
	}
	protected void action_TRANS_activated_message_received_FROM_testing_TO_testing_BY_activatedtest_port_activated_message_received(InterfaceItemBase ifitem) {
	    this.update ( );	
	}
	
	/* State Switch Methods */
	/**
	 * calls exit codes while exiting from the current state to one of its
	 * parent states while remembering the history
	 * @param current__et - the current state
	 * @param to - the final parent state
	 */
	private void exitTo(int current__et, int to) {
		while (current__et!=to) {
			switch (current__et) {
				case STATE_testing:
					this.history[STATE_TOP] = STATE_testing;
					current__et = STATE_TOP;
					break;
				default:
					/* should not occur */
					break;
			}
		}
	}
	
	/**
	 * calls action, entry and exit codes along a transition chain. The generic data are cast to typed data
	 * matching the trigger of this chain. The ID of the final state is returned
	 * @param chain__et - the chain ID
	 * @param generic_data__et - the generic data pointer
	 * @return the +/- ID of the final state either with a positive sign, that indicates to execute the state's entry code, or a negative sign vice versa
	 */
	private int executeTransitionChain(int chain__et, InterfaceItemBase ifitem, Object generic_data__et) {
		switch (chain__et) {
			case top_actor_t.CHAIN_TRANS_INITIAL_TO__testing:
			{
				action_TRANS_INITIAL_TO__testing();
				return STATE_testing;
			}
			case top_actor_t.CHAIN_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_port_turn_off_message_received:
			{
				action_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_port_turn_off_message_received(ifitem);
				return STATE_testing;
			}
			case top_actor_t.CHAIN_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_port_turn_on_message_received:
			{
				action_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_port_turn_on_message_received(ifitem);
				return STATE_testing;
			}
			case top_actor_t.CHAIN_TRANS_activated_message_received_FROM_testing_TO_testing_BY_activatedtest_port_activated_message_received:
			{
				action_TRANS_activated_message_received_FROM_testing_TO_testing_BY_activatedtest_port_activated_message_received(ifitem);
				return STATE_testing;
			}
				default:
					/* should not occur */
					break;
		}
		return NO_STATE;
	}
	
	/**
	 * calls entry codes while entering a state's history. The ID of the final leaf state is returned
	 * @param state__et - the state which is entered
	 * @return - the ID of the final leaf state
	 */
	private int enterHistory(int state__et) {
		if (state__et >= STATE_MAX) {
			state__et =  (state__et - STATE_MAX);
		}
		while (true) {
			switch (state__et) {
				case STATE_testing:
					/* in leaf state: return state id */
					return STATE_testing;
				case STATE_TOP:
					state__et = this.history[STATE_TOP];
					break;
				default:
					/* should not occur */
					break;
			}
		}
		/* return NO_STATE; // required by CDT but detected as unreachable by JDT because of while (true) */
	}
	
	public void executeInitTransition() {
		int chain__et = top_actor_t.CHAIN_TRANS_INITIAL_TO__testing;
		int next__et = executeTransitionChain(chain__et, null, null);
		next__et = enterHistory(next__et);
		setState(next__et);
	}
	
	/* receiveEvent contains the main implementation of the FSM */
	public void receiveEventInternal(InterfaceItemBase ifitem, int localId, int evt, Object generic_data__et) {
		int trigger__et = localId + EVT_SHIFT*evt;
		int chain__et = NOT_CAUGHT;
		int catching_state__et = NO_STATE;
	
		if (!handleSystemEvent(ifitem, evt, generic_data__et)) {
			switch (getState()) {
			    case STATE_testing:
			        switch(trigger__et) {
			                case TRIG_pump_port__turn_off:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_port_turn_off_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_pump_port__turn_on:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_port_turn_on_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_test_port__activated:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_activated_message_received_FROM_testing_TO_testing_BY_activatedtest_port_activated_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    default:
			        /* should not occur */
			        break;
			}
		}
		if (chain__et != NOT_CAUGHT) {
			exitTo(getState(), catching_state__et);
			{
				int next__et = executeTransitionChain(chain__et, ifitem, generic_data__et);
				next__et = enterHistory(next__et);
				setState(next__et);
			}
		}
	}
	public void receiveEvent(InterfaceItemBase ifitem, int evt, Object generic_data__et) {
		int localId = (ifitem==null)? 0 : ifitem.getLocalId();
		receiveEventInternal(ifitem, localId, evt, generic_data__et);
	}

};
