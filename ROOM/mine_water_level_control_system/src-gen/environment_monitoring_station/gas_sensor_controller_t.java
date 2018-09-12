package environment_monitoring_station;

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



public class gas_sensor_controller_t extends periodic_task_t {


	//--------------------- ports

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs

	/*--------------------- attributes ---------------------*/
	public  boolean detect_above_threshold;
	public  int threshold;
	public  gas_sensor_t gas_sensor;
	public  int error_count_threshold;
	public  int error_count;
	public  boolean alarm_turned_on;
	public  alarm_controller_t alarm_controller;

	/*--------------------- operations ---------------------*/
	public  void addittional_query_action() {
	}
	public  void query_action() {
		boolean read_error_occurred = this.gas_sensor.get_read_error_occurred (
			super.logger,
			super.getName ( )
		);
		
		if ( read_error_occurred == true ) {
			this.error_count++;
			if ( this.error_count > this.error_count_threshold ) {
				if ( this.alarm_turned_on == false ) {
					super.logger.info (
						super.getName ( ),
						"Error received, count threshold breached, turning on alarm"
					);
		
					this.alarm_controller.turn_on (
						super.logger,
						super.getName ( )
					);
		
					this.alarm_turned_on = true;	
				}
			} else {
				super.logger.info (
					super.getName ( ), 
					"Error received, count normal"
				);
			}
		} else {
			this.error_count = 0;
		
			int gas_sensor_value = this.gas_sensor.get_value (
				super.logger,
				super.getName ( )
			);
		
			if ( this.detect_above_threshold == true && gas_sensor_value > this.threshold ) {
				if ( this.alarm_turned_on == false ) {
					super.logger.info (
						super.getName ( ),
						"Threshold breached, turning on alarm"
					);
		
					this.alarm_controller.turn_on (
						super.logger,
						super.getName ( )
					);
		
					this.alarm_turned_on = true;	
				}
			} else if ( this.detect_above_threshold == false && gas_sensor_value < this.threshold ) {
				if ( this.alarm_turned_on == false ) {
					super.logger.info (
						super.getName ( ), 
						"Threshold breached, turning on alarm"
					);
		
					this.alarm_controller.turn_on (
						super.logger,
						super.getName ( )
					);
		
					this.alarm_turned_on = true;	
				}
			} else {
				if ( this.alarm_turned_on == true ) {
					super.logger.info ( 
						super.getName ( ), 
						"Threshold stabilizied, turning off alarm"
					);
		
					this.alarm_controller.turn_off (
						super.logger,
						super.getName ( )
					);
					this.alarm_turned_on = false;	
				}
			}
		}
		
		this.addittional_query_action ( );
	}
	public  void entry_action() {
		this.gas_sensor.start_conversion (
			super.logger,
			super.getName ( )
		);
		
		super.entry_action ( );
	}


	//--------------------- construction
	public gas_sensor_controller_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("gas_sensor_controller_t");

		// initialize attributes
		this.setDetect_above_threshold(false);
		this.setThreshold(0);
		this.setGas_sensor(null);
		this.setError_count_threshold(0);
		this.setError_count(0);
		this.setAlarm_turned_on(false);
		this.setAlarm_controller(null);

		// own ports

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setDetect_above_threshold(boolean detect_above_threshold) {
		 this.detect_above_threshold = detect_above_threshold;
	}
	public boolean getDetect_above_threshold() {
		return this.detect_above_threshold;
	}
	public void setThreshold(int threshold) {
		 this.threshold = threshold;
	}
	public int getThreshold() {
		return this.threshold;
	}
	public void setGas_sensor(gas_sensor_t gas_sensor) {
		 this.gas_sensor = gas_sensor;
	}
	public gas_sensor_t getGas_sensor() {
		return this.gas_sensor;
	}
	public void setError_count_threshold(int error_count_threshold) {
		 this.error_count_threshold = error_count_threshold;
	}
	public int getError_count_threshold() {
		return this.error_count_threshold;
	}
	public void setError_count(int error_count) {
		 this.error_count = error_count;
	}
	public int getError_count() {
		return this.error_count;
	}
	public void setAlarm_turned_on(boolean alarm_turned_on) {
		 this.alarm_turned_on = alarm_turned_on;
	}
	public boolean getAlarm_turned_on() {
		return this.alarm_turned_on;
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
	protected void action_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received(InterfaceItemBase ifitem) {
	    super.logger.info (
	    	this.getName ( ),
	    	"Query action begin"
	    );
	    
	    this.query_action ( );
	    
	    super.logger.info (
	    	this.getName ( ),
	    	"Query action end"
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
	    
	    this.detect_above_threshold = ( ( gas_sensor_controller_idata_t ) data ).detect_above_threshold;
	    this.threshold  		    = ( ( gas_sensor_controller_idata_t ) data ).threshold;
	    this.gas_sensor 		    = ( ( gas_sensor_controller_idata_t ) data ).gas_sensor;
	    this.error_count_threshold  = ( ( gas_sensor_controller_idata_t ) data ).error_count_threshold;
	    this.alarm_controller 	    = ( ( gas_sensor_controller_idata_t ) data ).alarm_controller;
	    this.error_count 	  	    = 0;
	    this.alarm_turned_on  	    = false;
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
			case gas_sensor_controller_t.CHAIN_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received:
			{
				action_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received(ifitem);
				return STATE_sleeping;
			}
			case gas_sensor_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case gas_sensor_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport:
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
		int chain__et = gas_sensor_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
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
			                        chain__et = gas_sensor_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport;
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
			                        chain__et = gas_sensor_controller_t.CHAIN_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received;
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
