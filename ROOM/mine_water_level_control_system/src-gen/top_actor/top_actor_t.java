package top_actor;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import alarm_station.*;
import devices.*;
import logger.*;
import room.basic.service.timing.*;
import room.basic.service.timing.PTimer.*;
import alarm_station.alarm_controller_iprotocol_t.*;
import devices.switch_protocol_t.*;

/*--------------------- begin user code ---------------------*/
class Constants {
	public static final int TEST_PERIOD_IN_MS = 50;
	public static final int NUMBER_OF_CONTROLLERS = 4;
	public static final int CH4_CONTROLLER = 0;
	public static final int CO_CONTROLLER = 1;
	public static final int O_CONTROLLER = 2;
	public static final int WATER_FLOW_CONTROLLER = 3;
}

/*--------------------- end user code ---------------------*/


public class top_actor_t extends ActorClassBase {


	//--------------------- ports
	protected alarm_controller_iprotocol_tConjPort alarm_controler_iport = null;
	protected switch_protocol_tPort ch4_alarm_switch_port = null;
	protected switch_protocol_tPort co_alarm_switch_port = null;
	protected switch_protocol_tPort o_alarm_switch_port = null;
	protected switch_protocol_tPort water_flow_alarm_switch_port = null;

	//--------------------- saps
	protected PTimerConjPort timer_port = null;

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_alarm_controler_iport = 1;
	public static final int IFITEM_ch4_alarm_switch_port = 2;
	public static final int IFITEM_co_alarm_switch_port = 3;
	public static final int IFITEM_o_alarm_switch_port = 4;
	public static final int IFITEM_water_flow_alarm_switch_port = 5;
	public static final int IFITEM_timer_port = 6;

	/*--------------------- attributes ---------------------*/
	public  int expected_state;
	public  boolean turn_on_message_sent[];
	public  int alarm_level;
	public  device_t alarm;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public top_actor_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("top_actor_t");

		// initialize attributes
		this.setExpected_state(0);
		{
			boolean[] array = new boolean[4];
			for (int i=0;i<4;i++){
				array[i] = false;
			}
			this.setTurn_on_message_sent(array);
		}
		this.setAlarm_level(0);
		this.setAlarm(new device_t());

		// own ports
		alarm_controler_iport = new alarm_controller_iprotocol_tConjPort(this, "alarm_controler_iport", IFITEM_alarm_controler_iport);
		ch4_alarm_switch_port = new switch_protocol_tPort(this, "ch4_alarm_switch_port", IFITEM_ch4_alarm_switch_port);
		co_alarm_switch_port = new switch_protocol_tPort(this, "co_alarm_switch_port", IFITEM_co_alarm_switch_port);
		o_alarm_switch_port = new switch_protocol_tPort(this, "o_alarm_switch_port", IFITEM_o_alarm_switch_port);
		water_flow_alarm_switch_port = new switch_protocol_tPort(this, "water_flow_alarm_switch_port", IFITEM_water_flow_alarm_switch_port);

		// own saps
		timer_port = new PTimerConjPort(this, "timer_port", IFITEM_timer_port, 0);

		// own service implementations

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "alarm_controller");
		new alarm_controller_t(this, "alarm_controller");

		// wiring
		InterfaceItemBase.connect(this, "alarm_controller/iport", "alarm_controler_iport");
		InterfaceItemBase.connect(this, "alarm_controller/ch4_controller_port", "ch4_alarm_switch_port");
		InterfaceItemBase.connect(this, "alarm_controller/co_controller_port", "co_alarm_switch_port");
		InterfaceItemBase.connect(this, "alarm_controller/o_controller_port", "o_alarm_switch_port");
		InterfaceItemBase.connect(this, "alarm_controller/water_flow_controller_port", "water_flow_alarm_switch_port");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setExpected_state(int expected_state) {
		 this.expected_state = expected_state;
	}
	public int getExpected_state() {
		return this.expected_state;
	}
	public void setTurn_on_message_sent(boolean[] turn_on_message_sent) {
		 this.turn_on_message_sent = turn_on_message_sent;
	}
	public boolean[] getTurn_on_message_sent() {
		return this.turn_on_message_sent;
	}
	public void setAlarm_level(int alarm_level) {
		 this.alarm_level = alarm_level;
	}
	public int getAlarm_level() {
		return this.alarm_level;
	}
	public void setAlarm(device_t alarm) {
		 this.alarm = alarm;
	}
	public device_t getAlarm() {
		return this.alarm;
	}


	//--------------------- port getters
	public alarm_controller_iprotocol_tConjPort getAlarm_controler_iport (){
		return this.alarm_controler_iport;
	}
	public switch_protocol_tPort getCh4_alarm_switch_port (){
		return this.ch4_alarm_switch_port;
	}
	public switch_protocol_tPort getCo_alarm_switch_port (){
		return this.co_alarm_switch_port;
	}
	public switch_protocol_tPort getO_alarm_switch_port (){
		return this.o_alarm_switch_port;
	}
	public switch_protocol_tPort getWater_flow_alarm_switch_port (){
		return this.water_flow_alarm_switch_port;
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
	public static final int CHAIN_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received = 2;
	
	/* triggers */
	public static final int POLLING = 0;
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
	    this.expected_state = device_state_t.OFF;
	    for ( int i = 0; i < Constants.NUMBER_OF_CONTROLLERS; i++ ) {
	    	this.turn_on_message_sent[i] = false;
	    }
	    this.alarm_level = 0;
	    
	    this.alarm_controler_iport.initialize ( 
	    	new alarm_controller_idata_t ( 
	    		this.alarm
	    	)
	    );
	    
	    this.timer_port.startTimeout ( Constants.TEST_PERIOD_IN_MS );
	}
	protected void action_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received(InterfaceItemBase ifitem) {
	    String alarm_state_string = this.alarm.state == device_state_t.ON ? "ON" : "OFF";
	    String expected_state_string = this.expected_state == device_state_t.ON ? "ON" : "OFF";
	    if ( this.expected_state == this.alarm.state ) {
	    	System.err.println ( "Expected alarm state " + expected_state_string + "/" + alarm_state_string );
	    } else {
	    	System.err.println ( "Unexpected alarm state " + expected_state_string + "/" + alarm_state_string );
	    }
	    
	    int controller_index = (int) ( Math.random ( ) * Constants.NUMBER_OF_CONTROLLERS );
	    
	    this.turn_on_message_sent[ controller_index ] = !this.turn_on_message_sent[ controller_index ];
	    
	    switch ( controller_index ) {
	    	case Constants.CH4_CONTROLLER : {
	    		if ( this.turn_on_message_sent[ controller_index ] == true ) {
	    			this.ch4_alarm_switch_port.turn_on ( );
	    		} else {
	    			this.ch4_alarm_switch_port.turn_off ( );
	    		}
	    	}
	    
	    	case Constants.CO_CONTROLLER : {
	    		if ( this.turn_on_message_sent[ controller_index ] == true ) {
	    			this.co_alarm_switch_port.turn_on ( );
	    		} else {
	    			this.co_alarm_switch_port.turn_off ( );
	    		}
	    	}
	    
	    	case Constants.O_CONTROLLER : {
	    		if ( this.turn_on_message_sent[ controller_index ] == true ) {
	    			this.o_alarm_switch_port.turn_on ( );
	    		} else {
	    			this.o_alarm_switch_port.turn_off ( );
	    		}
	    	}
	    
	    	case Constants.WATER_FLOW_CONTROLLER : {
	    		if ( this.turn_on_message_sent[ controller_index ] == true ) {
	    			this.water_flow_alarm_switch_port.turn_on ( );
	    		} else {
	    			this.water_flow_alarm_switch_port.turn_off ( );
	    		}
	    	}
	    }
	    
	    if ( this.turn_on_message_sent[ controller_index ] == true ) {
	    	this.alarm_level++;
	    	if ( this.alarm_level == 1 ) {
	    		this.expected_state = device_state_t.ON;
	    	} 
	    } else {
	    	this.alarm_level--;
	    	if ( this.alarm_level == 0 ) {
	    		this.expected_state = device_state_t.OFF;
	    	} 
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
