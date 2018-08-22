package top_actor;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import devices.*;
import logger.*;
import pump_station.*;
import room.basic.service.timing.*;
import room.basic.service.timing.PTimer.*;
import devices.methane_protocol_t.*;
import pump_station.pump_controller_iprotocol_t.*;
import devices.switch_protocol_t.*;

/*--------------------- begin user code ---------------------*/
class Constants {
	public static final int TEST_PERIOD_IN_MS = 100;
	public static final int NUMBER_OF_CONTROLLERS = 3;
	public static final int WATER_DETECTORS_CONTROLLER_CONTROLLER = 0;
	public static final int USER = 1;
	public static final int METHANE_CONTROLLER = 2;
}

/*--------------------- end user code ---------------------*/


public class top_actor_t extends ActorClassBase {


	//--------------------- ports
	protected pump_controller_iprotocol_tConjPort pump_controler_iport = null;
	protected switch_protocol_tPort water_detectors_pump_switch_port = null;
	protected switch_protocol_tPort user_switch_port = null;
	protected methane_protocol_tPort methane_port = null;

	//--------------------- saps
	protected PTimerConjPort timer_port = null;

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_pump_controler_iport = 1;
	public static final int IFITEM_water_detectors_pump_switch_port = 2;
	public static final int IFITEM_user_switch_port = 3;
	public static final int IFITEM_methane_port = 4;
	public static final int IFITEM_timer_port = 5;

	/*--------------------- attributes ---------------------*/
	public  int expected_state;
	public  int pump_state;
	public  boolean turn_on_message_sent[];
	public  boolean methane_threshold_breached;
	public  device_t pump;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public top_actor_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("top_actor_t");

		// initialize attributes
		this.setExpected_state(0);
		this.setPump_state(0);
		{
			boolean[] array = new boolean[2];
			for (int i=0;i<2;i++){
				array[i] = false;
			}
			this.setTurn_on_message_sent(array);
		}
		this.setMethane_threshold_breached(false);
		this.setPump(new device_t());

		// own ports
		pump_controler_iport = new pump_controller_iprotocol_tConjPort(this, "pump_controler_iport", IFITEM_pump_controler_iport);
		water_detectors_pump_switch_port = new switch_protocol_tPort(this, "water_detectors_pump_switch_port", IFITEM_water_detectors_pump_switch_port);
		user_switch_port = new switch_protocol_tPort(this, "user_switch_port", IFITEM_user_switch_port);
		methane_port = new methane_protocol_tPort(this, "methane_port", IFITEM_methane_port);

		// own saps
		timer_port = new PTimerConjPort(this, "timer_port", IFITEM_timer_port, 0);

		// own service implementations

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "pump_controller");
		new pump_controller_t(this, "pump_controller");

		// wiring
		InterfaceItemBase.connect(this, "pump_controller/iport", "pump_controler_iport");
		InterfaceItemBase.connect(this, "pump_controller/water_level_detectors_controller_port", "water_detectors_pump_switch_port");
		InterfaceItemBase.connect(this, "pump_controller/user_port", "user_switch_port");
		InterfaceItemBase.connect(this, "pump_controller/methane_port", "methane_port");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setExpected_state(int expected_state) {
		 this.expected_state = expected_state;
	}
	public int getExpected_state() {
		return this.expected_state;
	}
	public void setPump_state(int pump_state) {
		 this.pump_state = pump_state;
	}
	public int getPump_state() {
		return this.pump_state;
	}
	public void setTurn_on_message_sent(boolean[] turn_on_message_sent) {
		 this.turn_on_message_sent = turn_on_message_sent;
	}
	public boolean[] getTurn_on_message_sent() {
		return this.turn_on_message_sent;
	}
	public void setMethane_threshold_breached(boolean methane_threshold_breached) {
		 this.methane_threshold_breached = methane_threshold_breached;
	}
	public boolean getMethane_threshold_breached() {
		return this.methane_threshold_breached;
	}
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}


	//--------------------- port getters
	public pump_controller_iprotocol_tConjPort getPump_controler_iport (){
		return this.pump_controler_iport;
	}
	public switch_protocol_tPort getWater_detectors_pump_switch_port (){
		return this.water_detectors_pump_switch_port;
	}
	public switch_protocol_tPort getUser_switch_port (){
		return this.user_switch_port;
	}
	public methane_protocol_tPort getMethane_port (){
		return this.methane_port;
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
	    this.pump_state = device_state_t.OFF;
	    for ( int i = 0; i < ( Constants.NUMBER_OF_CONTROLLERS - 1 ); i++ ) {
	    	this.turn_on_message_sent[i] = false;
	    }
	    this.methane_threshold_breached = false;
	    this.pump.state = device_state_t.OFF;
	    
	    this.pump_controler_iport.initialize ( 
	    	new pump_controller_idata_t ( 
	    		this.pump
	    	)
	    );
	    
	    this.timer_port.startTimeout ( Constants.TEST_PERIOD_IN_MS );
	}
	protected void action_TRANS_timeout_message_received_FROM_testing_TO_testing_BY_timeouttimer_port_timeout_message_received(InterfaceItemBase ifitem) {
	    String pump_state_string = this.pump.state == device_state_t.ON ? "ON" : "OFF";
	    String expected_state_string = this.expected_state == device_state_t.ON ? "ON" : "OFF";
	    if ( this.expected_state == this.pump.state ) {
	    	System.err.println ( "Expected pump state " + expected_state_string + "/" + pump_state_string );
	    } else {
	    	System.err.println ( "Unexpected pump state " + expected_state_string + "/" + pump_state_string );
	    	System.exit(1);
	    }
	    
	    int action = (int) ( Math.random ( ) * Constants.NUMBER_OF_CONTROLLERS );
	    
	    if ( action < ( Constants.NUMBER_OF_CONTROLLERS - 1 ) ) {
	    	this.turn_on_message_sent[ action ] = !this.turn_on_message_sent[ action ];
	    } else {
	    	this.methane_threshold_breached = !this.methane_threshold_breached;
	    }
	    
	    switch ( action ) {
	    	case Constants.WATER_DETECTORS_CONTROLLER_CONTROLLER : {
	    		if ( this.turn_on_message_sent[ action ] == true ) {
	    			this.water_detectors_pump_switch_port.turn_on ( );
	    			System.err.println(0);
	    		} else {
	    			this.water_detectors_pump_switch_port.turn_off ( );
	    			System.err.println(1);
	    		}
	    
	    		break;
	    	}
	    
	    	case Constants.USER : {
	    		if ( this.turn_on_message_sent[ action ] == true ) {
	    			this.user_switch_port.turn_on ( );
	    			System.err.println(2);
	    		} else {
	    			this.user_switch_port.turn_off ( );
	    			System.err.println(3);
	    		}
	    
	    		break;
	    	}
	    
	    	case Constants.METHANE_CONTROLLER : {
	    		if ( this.methane_threshold_breached == true ) {
	    			this.methane_port.threshold_breached ( );
	    			System.err.println(4);
	    		} else {
	    			this.methane_port.state_normal ( );
	    			System.err.println(5);
	    		}
	    
	    		break;
	    	}
	    }
	    
	    if ( action < ( Constants.NUMBER_OF_CONTROLLERS - 1 ) ) {
	    	if ( this.methane_threshold_breached == false ) {
	    		if ( this.turn_on_message_sent[ action ] == true ) {
	    			this.expected_state = device_state_t.ON;
	    		} else {
	    			this.expected_state = device_state_t.OFF;
	    		}
	    	} else {
	    		if ( this.turn_on_message_sent[ action ] == true ) {
	    			this.pump_state = device_state_t.ON;
	    		} else {
	    			this.pump_state = device_state_t.OFF;
	    		}
	    	}
	    } else {
	    	if ( this.methane_threshold_breached == true ) {
	    		this.pump_state = this.pump.state;
	    		this.expected_state = device_state_t.OFF;
	    	} else {
	    		this.expected_state = this.pump_state;
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
