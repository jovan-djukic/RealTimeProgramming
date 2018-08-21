package test;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import devices.*;
import environment_monitoring_station.*;
import logger.*;
import room.basic.service.timing.*;
import room.basic.service.timing.PTimer.*;
import environment_monitoring_station.environmonet_statition_actor_base_iprotocol_t.*;
import devices.switch_protocol_t.*;

/*--------------------- begin user code ---------------------*/
class Constants {
	public static final int HIGH_WATER_LEVEL_DETECTOR_CONTROLLER_PERIOD_IN_MS = 500;
	public static final int TEST_PERIOD_IN_MS = 750;
	public static final int WATER_LEVEL_DETECTOR_IVALUE = 0;
}

/*--------------------- end user code ---------------------*/


public class top_actor_t extends ActorClassBase {


	//--------------------- ports
	protected environmonet_statition_actor_base_iprotocol_tConjPort high_water_level_detector_iport = null;
	protected switch_protocol_tConjPort pump_switch_port = null;

	//--------------------- saps
	protected PTimerConjPort timer_port = null;

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_high_water_level_detector_iport = 1;
	public static final int IFITEM_pump_switch_port = 2;
	public static final int IFITEM_timer_port = 3;

	/*--------------------- attributes ---------------------*/
	public  water_level_detector_t water_level_detector;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public top_actor_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("top_actor_t");

		// initialize attributes
		this.setWater_level_detector(new water_level_detector_t());

		// own ports
		high_water_level_detector_iport = new environmonet_statition_actor_base_iprotocol_tConjPort(this, "high_water_level_detector_iport", IFITEM_high_water_level_detector_iport);
		pump_switch_port = new switch_protocol_tConjPort(this, "pump_switch_port", IFITEM_pump_switch_port);

		// own saps
		timer_port = new PTimerConjPort(this, "timer_port", IFITEM_timer_port, 0);

		// own service implementations

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "high_water_level_detector_controller");
		new high_water_level_detector_controller_t(this, "high_water_level_detector_controller");

		// wiring
		InterfaceItemBase.connect(this, "high_water_level_detector_controller/iport", "high_water_level_detector_iport");
		InterfaceItemBase.connect(this, "high_water_level_detector_controller/pump_switch_port", "pump_switch_port");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setWater_level_detector(water_level_detector_t water_level_detector) {
		 this.water_level_detector = water_level_detector;
	}
	public water_level_detector_t getWater_level_detector() {
		return this.water_level_detector;
	}


	//--------------------- port getters
	public environmonet_statition_actor_base_iprotocol_tConjPort getHigh_water_level_detector_iport (){
		return this.high_water_level_detector_iport;
	}
	public switch_protocol_tConjPort getPump_switch_port (){
		return this.pump_switch_port;
	}
	public PTimerConjPort getTimer_port (){
		return this.timer_port;
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
	public static final int CHAIN_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_switch_port_turn_off_message_received = 2;
	public static final int CHAIN_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_switch_port_turn_on_message_received = 3;
	public static final int CHAIN_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received = 4;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_pump_switch_port__turn_on = IFITEM_pump_switch_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_pump_switch_port__turn_off = IFITEM_pump_switch_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	public static final int TRIG_timer_port__timeout = IFITEM_timer_port + EVT_SHIFT*PTimer.OUT_timeout;
	public static final int TRIG_timer_port__internalTimer = IFITEM_timer_port + EVT_SHIFT*PTimer.OUT_internalTimer;
	public static final int TRIG_timer_port__internalTimeout = IFITEM_timer_port + EVT_SHIFT*PTimer.OUT_internalTimeout;
	
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
	    this.water_level_detector.value = Constants.WATER_LEVEL_DETECTOR_IVALUE;
	    
	    this.high_water_level_detector_iport.initialize ( 
	    	new water_level_detector_controller_base_idata_t ( 
	    		Constants.HIGH_WATER_LEVEL_DETECTOR_CONTROLLER_PERIOD_IN_MS,
	    		this.water_level_detector
	    	)
	    );
	    
	    this.timer_port.startTimeout ( Constants.TEST_PERIOD_IN_MS );
	}
	protected void action_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_switch_port_turn_off_message_received(InterfaceItemBase ifitem) {
	    System.err.println ( "TURN OFF MESSAGE RECEIVED UNEXPECTEDLY " );
	}
	protected void action_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_switch_port_turn_on_message_received(InterfaceItemBase ifitem) {
	    if ( this.water_level_detector.value != 1 ) {
	    	System.err.println ( "TURN ON MESSAGE RECEIVED UNEXPECTEDLY " );
	    } else {
	    	System.err.println ( "TURN ON MESSAGE RECEIVED EXPECTEDLY " );
	    }
	}
	protected void action_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received(InterfaceItemBase ifitem) {
	    if ( Math.random ( ) > 0.5 ) {
	    	this.water_level_detector.value = 1 - this.water_level_detector.value;	
	    }
	    this.timer_port.startTimeout ( Constants.TEST_PERIOD_IN_MS );
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
			case top_actor_t.CHAIN_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_switch_port_turn_off_message_received:
			{
				action_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_switch_port_turn_off_message_received(ifitem);
				return STATE_testing;
			}
			case top_actor_t.CHAIN_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_switch_port_turn_on_message_received:
			{
				action_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_switch_port_turn_on_message_received(ifitem);
				return STATE_testing;
			}
			case top_actor_t.CHAIN_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received:
			{
				action_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received(ifitem);
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
			                case TRIG_pump_switch_port__turn_off:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_turn_off_message_received_FROM_testing_TO_testing_BY_turn_offpump_switch_port_turn_off_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_pump_switch_port__turn_on:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_turn_on_message_received_FROM_testing_TO_testing_BY_turn_onpump_switch_port_turn_on_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_timer_port__timeout:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received;
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
