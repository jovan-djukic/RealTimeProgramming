package pump_station;

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


public class water_flow_sensor_controller_t extends periodic_task_t {


	//--------------------- ports
	protected switch_protocol_tPort alarm_port = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_alarm_port = 4;

	/*--------------------- attributes ---------------------*/
	public  device_t pump;
	public  water_flow_sensor_t water_flow_sensor;
	public  int number_of_activations;
	public  int pump_state;
	public  int current_activation;

	/*--------------------- operations ---------------------*/
	public  boolean is_water_flow_sensor_value_expected() {
		if ( this.pump.state == device_state_t.ON )	{
			return this.water_flow_sensor.value != 0;
		} else {
			return this.water_flow_sensor.value == 0;
		}
	}


	//--------------------- construction
	public water_flow_sensor_controller_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("water_flow_sensor_controller_t");

		// initialize attributes
		this.setPump(null);
		this.setWater_flow_sensor(null);
		this.setNumber_of_activations(0);
		this.setPump_state(0);
		this.setCurrent_activation(0);

		// own ports
		alarm_port = new switch_protocol_tPort(this, "alarm_port", IFITEM_alarm_port);

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}
	public void setWater_flow_sensor(water_flow_sensor_t water_flow_sensor) {
		 this.water_flow_sensor = water_flow_sensor;
	}
	public water_flow_sensor_t getWater_flow_sensor() {
		return this.water_flow_sensor;
	}
	public void setNumber_of_activations(int number_of_activations) {
		 this.number_of_activations = number_of_activations;
	}
	public int getNumber_of_activations() {
		return this.number_of_activations;
	}
	public void setPump_state(int pump_state) {
		 this.pump_state = pump_state;
	}
	public int getPump_state() {
		return this.pump_state;
	}
	public void setCurrent_activation(int current_activation) {
		 this.current_activation = current_activation;
	}
	public int getCurrent_activation() {
		return this.current_activation;
	}


	//--------------------- port getters
	public switch_protocol_tPort getAlarm_port (){
		return this.alarm_port;
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
	public static final int STATE_checking = 4;
	public static final int STATE_alarm = 5;
	public static final int STATE_MAX = 6;
	
	/* transition chains */
	public static final int CHAIN_TRANS_state_normal_transition_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_state_normal_transition = 1;
	public static final int CHAIN_TRANS_pump_state_change_or_water_flow_sensor_error_FROM_sleeping_TO_checking_BY_timeouttimer_access_point = 2;
	public static final int CHAIN_TRANS_check_succeeded_FROM_checking_TO_sleeping_BY_timeouttimer_access_point = 3;
	public static final int CHAIN_TRANS_check_failed_within_limit_FROM_checking_TO_checking_BY_timeouttimer_access_point_check_failed_within_limit = 4;
	public static final int CHAIN_TRANS_check_failed_limit_exceeded_FROM_checking_TO_alarm_BY_timeouttimer_access_point = 5;
	public static final int CHAIN_TRANS_check_failed_alarm_turned_on_FROM_alarm_TO_alarm_BY_timeouttimer_access_point_check_failed_alarm_turned_on = 6;
	public static final int CHAIN_TRANS_check_succeeded_alarm_turned_on_FROM_alarm_TO_checking_BY_timeouttimer_access_point = 7;
	public static final int CHAIN_TRANS_INITIAL_TO__waiting_for_imessage = 8;
	public static final int CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport = 9;
	
	/* triggers */
	public static final int POLLING = 0;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"waiting_for_imessage",
		"sleeping",
		"checking",
		"alarm"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	protected void entry_checking() {
		super.info ( this.getName ( ), "Sleeping in checking state");
		super.test_port.checking ( );
		this.timer_access_point.startTimeout ( super.period );
	}
	protected void entry_alarm() {
		super.info ( this.getName ( ), "Sleeping in alarm state");
		super.test_port.alarm ( );
		this.timer_access_point.startTimeout ( super.period );
	}
	
	/* Action Codes */
	protected void action_TRANS_state_normal_transition_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_state_normal_transition(InterfaceItemBase ifitem) {
	    super.info ( this.getName ( ), "State normal" );
	}
	protected void action_TRANS_pump_state_change_or_water_flow_sensor_error_FROM_sleeping_TO_checking_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;					
	    super.info ( this.getName ( ), "Pump state changed or sensor broken" );
	}
	protected void action_TRANS_check_succeeded_FROM_checking_TO_sleeping_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    this.pump_state = this.pump.state;
	    super.info ( this.getName ( ), "Pump in previous state or read expected water flow sensor value" );
	}
	protected void action_TRANS_check_failed_within_limit_FROM_checking_TO_checking_BY_timeouttimer_access_point_check_failed_within_limit(InterfaceItemBase ifitem) {
	    this.current_activation++;	
	    super.info ( this.getName ( ), "Read unexpected water flow sensor value, current activation is " + this.current_activation + ", within limit" );
	}
	protected void action_TRANS_check_failed_limit_exceeded_FROM_checking_TO_alarm_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    this.alarm_port.turn_on ( );
	    super.info ( this.getName ( ), "Read unexpected water flow sensor value, limit breached, turning on alarm" );
	}
	protected void action_TRANS_check_failed_alarm_turned_on_FROM_alarm_TO_alarm_BY_timeouttimer_access_point_check_failed_alarm_turned_on(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    super.info ( this.getName ( ), "Read unexpected water flow sensor value, leaving alarm on" );
	}
	protected void action_TRANS_check_succeeded_alarm_turned_on_FROM_alarm_TO_checking_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    this.alarm_port.turn_off ( );
	    super.info ( this.getName ( ), "Pump state changed or read expected water flow sensor value, turning off alarm" );
	}
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport(InterfaceItemBase ifitem, periodic_task_idata_t data) {
	    this.period = data.period;
	    super.info ( this.getName ( ), "Initialization message received" );
	    
	    this.pump = ( ( water_flow_sensor_controller_idata_t ) data ).pump;
	    this.water_flow_sensor = ( ( water_flow_sensor_controller_idata_t ) data ).water_flow_sensor;
	    this.number_of_activations = ( ( water_flow_sensor_controller_idata_t ) data ).number_of_activations;
	    
	    this.pump_state = this.pump.state;
	    this.current_activation = 0;
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
				case STATE_checking:
					this.history[STATE_TOP] = STATE_checking;
					current__et = STATE_TOP;
					break;
				case STATE_alarm:
					this.history[STATE_TOP] = STATE_alarm;
					current__et = STATE_TOP;
					break;
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
			case water_flow_sensor_controller_t.CHAIN_TRANS_state_normal_transition_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_state_normal_transition:
			{
				action_TRANS_state_normal_transition_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_state_normal_transition(ifitem);
				return STATE_sleeping;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_pump_state_change_or_water_flow_sensor_error_FROM_sleeping_TO_checking_BY_timeouttimer_access_point:
			{
				action_TRANS_pump_state_change_or_water_flow_sensor_error_FROM_sleeping_TO_checking_BY_timeouttimer_access_point(ifitem);
				return STATE_checking;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_check_succeeded_FROM_checking_TO_sleeping_BY_timeouttimer_access_point:
			{
				action_TRANS_check_succeeded_FROM_checking_TO_sleeping_BY_timeouttimer_access_point(ifitem);
				return STATE_sleeping;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_within_limit_FROM_checking_TO_checking_BY_timeouttimer_access_point_check_failed_within_limit:
			{
				action_TRANS_check_failed_within_limit_FROM_checking_TO_checking_BY_timeouttimer_access_point_check_failed_within_limit(ifitem);
				return STATE_checking;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_limit_exceeded_FROM_checking_TO_alarm_BY_timeouttimer_access_point:
			{
				action_TRANS_check_failed_limit_exceeded_FROM_checking_TO_alarm_BY_timeouttimer_access_point(ifitem);
				return STATE_alarm;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_alarm_turned_on_FROM_alarm_TO_alarm_BY_timeouttimer_access_point_check_failed_alarm_turned_on:
			{
				action_TRANS_check_failed_alarm_turned_on_FROM_alarm_TO_alarm_BY_timeouttimer_access_point_check_failed_alarm_turned_on(ifitem);
				return STATE_alarm;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_check_succeeded_alarm_turned_on_FROM_alarm_TO_checking_BY_timeouttimer_access_point:
			{
				action_TRANS_check_succeeded_alarm_turned_on_FROM_alarm_TO_checking_BY_timeouttimer_access_point(ifitem);
				return STATE_checking;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case water_flow_sensor_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport:
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
				case STATE_checking:
					if (!(skip_entry__et)) entry_checking();
					/* in leaf state: return state id */
					return STATE_checking;
				case STATE_alarm:
					if (!(skip_entry__et)) entry_alarm();
					/* in leaf state: return state id */
					return STATE_alarm;
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
		int chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
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
			    case STATE_checking:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (this.is_water_flow_sensor_value_expected ( ) || this.pump_state == this.pump.state
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_succeeded_FROM_checking_TO_sleeping_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (!this.is_water_flow_sensor_value_expected ( ) && this.current_activation < this.number_of_activations
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_within_limit_FROM_checking_TO_checking_BY_timeouttimer_access_point_check_failed_within_limit;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (!this.is_water_flow_sensor_value_expected ( ) && this.current_activation == this.number_of_activations
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_limit_exceeded_FROM_checking_TO_alarm_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_alarm:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (this.pump_state != this.pump.state || !this.is_water_flow_sensor_value_expected ( )
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_alarm_turned_on_FROM_alarm_TO_alarm_BY_timeouttimer_access_point_check_failed_alarm_turned_on;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (this.pump_state == this.pump.state || this.is_water_flow_sensor_value_expected ( )
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_succeeded_alarm_turned_on_FROM_alarm_TO_checking_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_waiting_for_imessage:
			        switch(trigger__et) {
			                case TRIG_iport__initialize:
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport;
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
			                    if (this.pump_state == this.pump.state && this.is_water_flow_sensor_value_expected ( )
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_state_normal_transition_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_state_normal_transition;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (this.pump_state != this.pump.state || !this.is_water_flow_sensor_value_expected ( )	
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_pump_state_change_or_water_flow_sensor_error_FROM_sleeping_TO_checking_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
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
