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
import pump_station.*;
import room.basic.service.timing.*;
import test.*;
import room.basic.service.timing.PTimer.*;
import deadline_task.deadline_task_iprotocol_t.*;
import test.test_protocol_t.*;



public class methane_sensor_controller_t extends gas_sensor_controller_t {


	//--------------------- ports

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs

	/*--------------------- attributes ---------------------*/
	public  pump_controller_t pump_controller;
	public  boolean threshold_breached;

	/*--------------------- operations ---------------------*/
	public  void addittional_query_action() {
		boolean read_error_occurred = super.gas_sensor.get_read_error_occurred (
			super.logger,
			super.getName ( )
		);
		
		if ( read_error_occurred == false ) {
			int value = super.gas_sensor.get_value (
				super.logger,
				super.getName ( )
			);
		
			if ( value > super.threshold ) {
				if ( this.threshold_breached == false ) {
					super.logger.info (
						super.getName ( ),
						"Threshold breached, sending message to pump controller"
					);
		
					this.pump_controller.methane_threshold_breached (
						super.logger,
						super.getName ( )
					);
		
					this.threshold_breached = true;
				}
			} else {
				if ( this.threshold_breached == true ) {
					super.logger.info (
						super.getName ( ), 
						"State normal, sending message to pump controller"
					);
		
					this.pump_controller.methane_state_normal (
						super.logger,
						super.getName ( )
					);
		
					this.threshold_breached = false;
				}
			}
		}
	}


	//--------------------- construction
	public methane_sensor_controller_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("methane_sensor_controller_t");

		// initialize attributes
		this.setPump_controller(null);
		this.setThreshold_breached(false);

		// own ports

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setPump_controller(pump_controller_t pump_controller) {
		 this.pump_controller = pump_controller;
	}
	public pump_controller_t getPump_controller() {
		return this.pump_controller;
	}
	public void setThreshold_breached(boolean threshold_breached) {
		 this.threshold_breached = threshold_breached;
	}
	public boolean getThreshold_breached() {
		return this.threshold_breached;
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
	    
	    super.detect_above_threshold = true;
	    
	    this.pump_controller    = ( ( methane_sensor_controller_idata_t ) data ).pump_controller;
	    this.threshold_breached = false;
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
			case methane_sensor_controller_t.CHAIN_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received:
			{
				action_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received(ifitem);
				return STATE_sleeping;
			}
			case methane_sensor_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case methane_sensor_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport:
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
		int chain__et = methane_sensor_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
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
			                        chain__et = methane_sensor_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_sleeping_BY_initializeiport;
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
			                        chain__et = methane_sensor_controller_t.CHAIN_TRANS_timeout_received_FROM_sleeping_TO_sleeping_BY_timeouttimer_access_point_timeout_received;
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
