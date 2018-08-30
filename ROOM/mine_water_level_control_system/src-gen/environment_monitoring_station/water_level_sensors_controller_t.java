package environment_monitoring_station;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import devices.*;
import logger.*;
import periodic_task.*;
import room.basic.service.timing.*;
import test.*;
import room.basic.service.timing.PTimer.*;
import periodic_task.periodic_task_iprotocol_t.*;
import devices.switch_protocol_t.*;
import test.test_protocol_t.*;

/*--------------------- begin user code ---------------------*/
import java.util.logging.*;
import java.io.IOException;

/*--------------------- end user code ---------------------*/


public class water_level_sensors_controller_t extends sensor_controller_t {


	//--------------------- ports
	protected switch_protocol_tPort pump_port = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_pump_port = 4;

	/*--------------------- attributes ---------------------*/
	public  boolean pump_turned_on;
	public  water_level_sensor_t low_water_level_sensor;
	public  water_level_sensor_t high_water_level_sensor;

	/*--------------------- operations ---------------------*/
	public  void query_action() {
		if ( this.low_water_level_sensor.value != 0 )	{
			if ( this.pump_turned_on == true ) {
				this.pump_port.turn_off ( );
				this.pump_turned_on = false;
				super.info ( this.getName ( ), "Water under low threshold, turning off pump" );
			}
		} else if ( this.high_water_level_sensor.value != 0 ) {
			if ( this.pump_turned_on == false ) {
				this.pump_port.turn_on ( );
				this.pump_turned_on = true;
				super.info ( this.getName ( ), "Water over high threshold, turning on pump" );
			}
		}
	}


	//--------------------- construction
	public water_level_sensors_controller_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("water_level_sensors_controller_t");

		// initialize attributes
		this.setPump_turned_on(false);
		this.setLow_water_level_sensor(null);
		this.setHigh_water_level_sensor(null);

		// own ports
		pump_port = new switch_protocol_tPort(this, "pump_port", IFITEM_pump_port);

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setPump_turned_on(boolean pump_turned_on) {
		 this.pump_turned_on = pump_turned_on;
	}
	public boolean getPump_turned_on() {
		return this.pump_turned_on;
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
	public switch_protocol_tPort getPump_port (){
		return this.pump_port;
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
	public static final int STATE_MAX = 4;
	
	/* transition chains */
	public static final int CHAIN_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received = 1;
	public static final int CHAIN_TRANS_INITIAL_TO__waiting_for_imessage = 2;
	public static final int CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport = 3;
	
	/* triggers */
	public static final int POLLING = 0;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"waiting_for_imessage",
		"sleeping"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	
	/* Action Codes */
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport(InterfaceItemBase ifitem, periodic_task_idata_t data) {
	    this.period = data.period;
	    super.info ( this.getName ( ), "Initialization message received" );
	    
	    this.low_water_level_sensor = ( ( water_level_sensors_controller_idata_t ) data ).low_water_level_sensor;
	    this.high_water_level_sensor = ( ( water_level_sensors_controller_idata_t ) data ).high_water_level_sensor;
	    this.pump_turned_on = false;
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
				case STATE_waiting_for_imessage:
					this.history[STATE_TOP] = STATE_waiting_for_imessage;
					current__et = STATE_TOP;
					break;
				case STATE_sleeping:
					this.history[STATE_TOP] = STATE_sleeping;
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
			case water_level_sensors_controller_t.CHAIN_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received:
			{
				action_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received(ifitem);
				return STATE_sleeping;
			}
			case water_level_sensors_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case water_level_sensors_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport:
			{
				periodic_task_idata_t data = (periodic_task_idata_t) generic_data__et;
				action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport(ifitem, data);
				return STATE_sleeping;
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
		boolean skip_entry__et = false;
		if (state__et >= STATE_MAX) {
			state__et =  (state__et - STATE_MAX);
			skip_entry__et = true;
		}
		while (true) {
			switch (state__et) {
				case STATE_waiting_for_imessage:
					/* in leaf state: return state id */
					return STATE_waiting_for_imessage;
				case STATE_sleeping:
					if (!(skip_entry__et)) entry_sleeping();
					/* in leaf state: return state id */
					return STATE_sleeping;
				case STATE_TOP:
					state__et = this.history[STATE_TOP];
					break;
				default:
					/* should not occur */
					break;
			}
			skip_entry__et = false;
		}
		/* return NO_STATE; // required by CDT but detected as unreachable by JDT because of while (true) */
	}
	
	public void executeInitTransition() {
		int chain__et = water_level_sensors_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
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
			    case STATE_waiting_for_imessage:
			        switch(trigger__et) {
			                case TRIG_iport__initialize:
			                    {
			                        chain__et = water_level_sensors_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_sleeping:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    {
			                        chain__et = water_level_sensors_controller_t.CHAIN_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received;
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
