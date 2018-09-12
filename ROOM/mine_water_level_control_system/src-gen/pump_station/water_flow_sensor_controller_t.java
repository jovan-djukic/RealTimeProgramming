package pump_station;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import alarm_station.*;
import deadline_task.*;
import devices.*;
import logger.*;
import periodic_task.*;
import room.basic.service.timing.*;
import test.*;
import room.basic.service.timing.PTimer.*;
import deadline_task.deadline_task_iprotocol_t.*;
import test.test_protocol_t.*;



public class water_flow_sensor_controller_t extends periodic_task_t {


	//--------------------- ports

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs

	/*--------------------- attributes ---------------------*/
	public  device_t pump;
	public  sensor_t water_flow_sensor;
	public  int number_of_activations;
	public  int pump_state;
	public  int current_activation;
	public  alarm_controller_t alarm_controller;

	/*--------------------- operations ---------------------*/
	public  boolean is_water_flow_sensor_value_expected() {
		int current_pump_state = this.pump.get_state (
			super.logger,
			super.getName ( )
		);
		
		int water_flow_sensor_value = this.water_flow_sensor.get_value (
			super.logger,
			super.getName ( )
		);
		
		if ( current_pump_state == device_state_t.ON )	{
			return ( water_flow_sensor_value != 0 );
		} else {
			return ( water_flow_sensor_value == 0 );
		}
	}
	public  boolean has_pump_state_changed() {
		int current_pump_state = this.pump.get_state (
			super.logger,
			super.getName ( )
		);
		
		if ( current_pump_state == this.pump_state ) {
			return false;
		} else {
			return true;
		}
	}
	public  void entry_action() {
		this.water_flow_sensor.start_conversion (
			super.logger,
			super.getName ( )
		);
		
		super.entry_action ( );
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
		this.setAlarm_controller(new alarm_controller_t());

		// own ports

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
	public void setWater_flow_sensor(sensor_t water_flow_sensor) {
		 this.water_flow_sensor = water_flow_sensor;
	}
	public sensor_t getWater_flow_sensor() {
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
	public void setAlarm_controller(alarm_controller_t alarm_controller) {
		 this.alarm_controller = alarm_controller;
	}
	public alarm_controller_t getAlarm_controller() {
		return this.alarm_controller;
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
		this.entry_action ( );
		
		super.logger.info (
			super.getName ( ), 
			"Sleeping in checking state"
		);
		
		super.test_port.checking ( );
		
		this.timer_access_point.startTimeout (
			super.period
		);
	}
	protected void exit_checking() {
		this.exit_action ( );
	}
	protected void entry_alarm() {
		this.entry_action ( );
		
		super.logger.info (
			this.getName ( ),
			"Sleeping in alarm state"
		);
		
		super.test_port.alarm ( );
		
		this.timer_access_point.startTimeout (
			super.period
		);
	}
	protected void exit_alarm() {
		this.exit_action ( );
	}
	
	/* Action Codes */
	protected void action_TRANS_state_normal_transition_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_state_normal_transition(InterfaceItemBase ifitem) {
	    super.logger.info ( 
	    	super.getName ( ),
	    	"State normal"
	    );
	}
	protected void action_TRANS_pump_state_change_or_water_flow_sensor_error_FROM_sleeping_TO_checking_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;					
	    
	    super.logger.info ( 
	    	super.getName ( ),
	    	"Pump state changed or sensor error"
	    );
	}
	protected void action_TRANS_check_succeeded_FROM_checking_TO_sleeping_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    this.pump_state 		= this.pump.state;
	    
	    super.logger.info ( 
	    	super.getName ( ), 
	    	"Pump in previous state or read expected water flow sensor value"
	    );
	}
	protected void action_TRANS_check_failed_within_limit_FROM_checking_TO_checking_BY_timeouttimer_access_point_check_failed_within_limit(InterfaceItemBase ifitem) {
	    this.current_activation++;	
	    
	    super.logger.info ( 
	    	this.getName ( ), 
	    	"Read unexpected water flow sensor value, current activation is " + this.current_activation + ", within limit"
	    );
	}
	protected void action_TRANS_check_failed_limit_exceeded_FROM_checking_TO_alarm_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    
	    super.logger.info ( 
	    	super.getName ( ), 
	    	"Read unexpected water flow sensor value, limit breached, turning on alarm"
	    );
	    
	    this.alarm_controller.turn_on (
	    	super.logger,
	    	super.getName ( )
	    );
	}
	protected void action_TRANS_check_failed_alarm_turned_on_FROM_alarm_TO_alarm_BY_timeouttimer_access_point_check_failed_alarm_turned_on(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    
	    super.logger.info ( 
	    	super.getName ( ), 
	    	"Read unexpected water flow sensor value, leaving alarm on"
	    );
	}
	protected void action_TRANS_check_succeeded_alarm_turned_on_FROM_alarm_TO_checking_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    this.current_activation = 0;	
	    
	    super.logger.info ( 
	    	super.getName ( ), 
	    	"Pump state changed or read expected water flow sensor value, turning off alarm"
	    );
	    
	    this.alarm_controller.turn_off (
	    	super.logger,
	    	super.getName ( )
	    );
	}
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport(InterfaceItemBase ifitem, deadline_task_idata_t data) {
	    this.deadline = data.deadline;
	    this.logger.info (
	    	this.getName ( ),
	    	"Initialization message received"
	    );
	    
	    this.period =  ( ( periodic_task_idata_t ) data ).period;
	    
	    this.first_activation = true;
	    
	    this.pump 				   = ( ( water_flow_sensor_controller_idata_t ) data ).pump;
	    this.water_flow_sensor 	   = ( ( water_flow_sensor_controller_idata_t ) data ).water_flow_sensor;
	    this.number_of_activations = ( ( water_flow_sensor_controller_idata_t ) data ).number_of_activations;
	    this.alarm_controller 	   = ( ( water_flow_sensor_controller_idata_t ) data ).alarm_controller;
	    
	    this.pump_state	= this.pump.get_state ( 
	    	super.logger,
	    	super.getName ( )
	    );
	    
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
					exit_checking();
					this.history[STATE_TOP] = STATE_checking;
					current__et = STATE_TOP;
					break;
				case STATE_alarm:
					exit_alarm();
					this.history[STATE_TOP] = STATE_alarm;
					current__et = STATE_TOP;
					break;
				case STATE_waiting_for_imessage:
					this.history[STATE_TOP] = STATE_waiting_for_imessage;
					current__et = STATE_TOP;
					break;
				case STATE_sleeping:
					exit_sleeping();
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
				deadline_task_idata_t data = (deadline_task_idata_t) generic_data__et;
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
			                    if (this.is_water_flow_sensor_value_expected ( ) == true
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_succeeded_FROM_checking_TO_sleeping_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (( this.is_water_flow_sensor_value_expected ( ) == false ) && 
			                    ( this.current_activation < this.number_of_activations )
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_within_limit_FROM_checking_TO_checking_BY_timeouttimer_access_point_check_failed_within_limit;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (( this.is_water_flow_sensor_value_expected ( ) == false ) && 
			                    ( this.current_activation == this.number_of_activations )
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
			                    if (this.is_water_flow_sensor_value_expected ( ) == false
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_check_failed_alarm_turned_on_FROM_alarm_TO_alarm_BY_timeouttimer_access_point_check_failed_alarm_turned_on;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (this.is_water_flow_sensor_value_expected ( ) == true
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
			                    if (( this.has_pump_state_changed ( ) == false ) &&
			                    ( this.is_water_flow_sensor_value_expected ( ) == true )
			                    )
			                    {
			                        chain__et = water_flow_sensor_controller_t.CHAIN_TRANS_state_normal_transition_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_state_normal_transition;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (( this.has_pump_state_changed ( ) == true ) || 
			                    ( this.is_water_flow_sensor_value_expected ( ) == false )
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
