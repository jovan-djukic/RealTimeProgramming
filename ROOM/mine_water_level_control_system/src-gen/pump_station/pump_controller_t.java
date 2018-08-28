package pump_station;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import devices.*;
import logger.*;
import devices.methane_protocol_t.*;
import pump_station.pump_controller_iprotocol_t.*;
import devices.switch_protocol_t.*;

/*--------------------- begin user code ---------------------*/
import java.util.logging.*;
import java.io.IOException;

/*--------------------- end user code ---------------------*/


public class pump_controller_t extends logger_t {


	//--------------------- ports
	protected pump_controller_iprotocol_tPort iport = null;
	protected switch_protocol_tConjPort water_level_detectors_controller_port = null;
	protected switch_protocol_tConjPort user_port = null;
	protected methane_protocol_tConjPort methane_port = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_iport = 1;
	public static final int IFITEM_water_level_detectors_controller_port = 2;
	public static final int IFITEM_user_port = 3;
	public static final int IFITEM_methane_port = 4;

	/*--------------------- attributes ---------------------*/
	public  device_t pump;
	public  int pump_state;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public pump_controller_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("pump_controller_t");

		// initialize attributes
		this.setPump(null);
		this.setPump_state(0);

		// own ports
		iport = new pump_controller_iprotocol_tPort(this, "iport", IFITEM_iport);
		water_level_detectors_controller_port = new switch_protocol_tConjPort(this, "water_level_detectors_controller_port", IFITEM_water_level_detectors_controller_port);
		user_port = new switch_protocol_tConjPort(this, "user_port", IFITEM_user_port);
		methane_port = new methane_protocol_tConjPort(this, "methane_port", IFITEM_methane_port);

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
	public void setPump_state(int pump_state) {
		 this.pump_state = pump_state;
	}
	public int getPump_state() {
		return this.pump_state;
	}


	//--------------------- port getters
	public pump_controller_iprotocol_tPort getIport (){
		return this.iport;
	}
	public switch_protocol_tConjPort getWater_level_detectors_controller_port (){
		return this.water_level_detectors_controller_port;
	}
	public switch_protocol_tConjPort getUser_port (){
		return this.user_port;
	}
	public methane_protocol_tConjPort getMethane_port (){
		return this.methane_port;
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
	public static final int STATE_waiting_for_imessage = 2;
	public static final int STATE_pump_turned_off = 3;
	public static final int STATE_pump_turned_on = 4;
	public static final int STATE_methane_threshold_breached = 5;
	public static final int STATE_MAX = 6;
	
	/* transition chains */
	public static final int CHAIN_TRANS_INITIAL_TO__waiting_for_imessage = 1;
	public static final int CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_pump_turned_off_BY_initializeiport = 2;
	public static final int CHAIN_TRANS_turn_on_message_received_FROM_pump_turned_off_TO_pump_turned_on_BY_turn_onwater_level_detectors_controller_portturn_onuser_port = 3;
	public static final int CHAIN_TRANS_turn_off_message_received_FROM_pump_turned_on_TO_pump_turned_off_BY_turn_offwater_level_detectors_controller_portturn_offuser_port = 4;
	public static final int CHAIN_TRANS_methane_threshold_breached_received_while_pump_off_FROM_pump_turned_off_TO_methane_threshold_breached_BY_threshold_breachedmethane_port = 5;
	public static final int CHAIN_TRANS_methane_threshold_breached_received_while_pump_on_FROM_pump_turned_on_TO_methane_threshold_breached_BY_threshold_breachedmethane_port = 6;
	public static final int CHAIN_TRANS_state_normal_received_pump_was_turned_on_FROM_methane_threshold_breached_TO_pump_turned_on_BY_state_normalmethane_port = 7;
	public static final int CHAIN_TRANS_state_normal_received_pump_was_turned_off_FROM_methane_threshold_breached_TO_pump_turned_off_BY_state_normalmethane_port = 8;
	public static final int CHAIN_TRANS_turn_on_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_onwater_level_detectors_controller_portturn_onuser_port_turn_on_message_received_while_methane_threshold_breached = 9;
	public static final int CHAIN_TRANS_turn_off_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_offwater_level_detectors_controller_portturn_offuser_port_turn_off_message_received_while_methane_threshold_breached = 10;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_iport__initialize = IFITEM_iport + EVT_SHIFT*pump_controller_iprotocol_t.IN_initialize;
	public static final int TRIG_water_level_detectors_controller_port__turn_on = IFITEM_water_level_detectors_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_water_level_detectors_controller_port__turn_off = IFITEM_water_level_detectors_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	public static final int TRIG_user_port__turn_on = IFITEM_user_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_user_port__turn_off = IFITEM_user_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	public static final int TRIG_methane_port__threshold_breached = IFITEM_methane_port + EVT_SHIFT*methane_protocol_t.OUT_threshold_breached;
	public static final int TRIG_methane_port__state_normal = IFITEM_methane_port + EVT_SHIFT*methane_protocol_t.OUT_state_normal;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"waiting_for_imessage",
		"pump_turned_off",
		"pump_turned_on",
		"methane_threshold_breached"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	protected void entry_pump_turned_off() {
		this.pump.state = device_state_t.OFF;	
	}
	protected void entry_pump_turned_on() {
		this.pump.state = device_state_t.ON;	
	}
	
	/* Action Codes */
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_pump_turned_off_BY_initializeiport(InterfaceItemBase ifitem, pump_controller_idata_t data) {
	    this.pump = data.pump;	
	}
	protected void action_TRANS_turn_on_message_received_FROM_pump_turned_off_TO_pump_turned_on_BY_turn_onwater_level_detectors_controller_portturn_onuser_port(InterfaceItemBase ifitem) {
	    super.info ( super.getName ( ), "Turn on message, turning on pump" );
	}
	protected void action_TRANS_turn_off_message_received_FROM_pump_turned_on_TO_pump_turned_off_BY_turn_offwater_level_detectors_controller_portturn_offuser_port(InterfaceItemBase ifitem) {
	    super.info ( super.getName ( ), "Turn off message, turning on pump" );
	}
	protected void action_TRANS_methane_threshold_breached_received_while_pump_off_FROM_pump_turned_off_TO_methane_threshold_breached_BY_threshold_breachedmethane_port(InterfaceItemBase ifitem) {
	    this.pump_state = this.pump.state;	
	    this.pump.state = device_state_t.OFF;
	    super.info ( super.getName ( ), "Methane threshold breached while pump turned off" );
	}
	protected void action_TRANS_methane_threshold_breached_received_while_pump_on_FROM_pump_turned_on_TO_methane_threshold_breached_BY_threshold_breachedmethane_port(InterfaceItemBase ifitem) {
	    this.pump_state = this.pump.state;	
	    this.pump.state = device_state_t.OFF;
	    super.info ( super.getName ( ), "Methane threshold breached while pump turned on, turning off pump" );
	}
	protected void action_TRANS_state_normal_received_pump_was_turned_on_FROM_methane_threshold_breached_TO_pump_turned_on_BY_state_normalmethane_port(InterfaceItemBase ifitem) {
	    super.info ( super.getName ( ), "Methane state normal received, turning on pump" );
	}
	protected void action_TRANS_state_normal_received_pump_was_turned_off_FROM_methane_threshold_breached_TO_pump_turned_off_BY_state_normalmethane_port(InterfaceItemBase ifitem) {
	    super.info ( super.getName ( ), "Methane state normal received" );
	}
	protected void action_TRANS_turn_on_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_onwater_level_detectors_controller_portturn_onuser_port_turn_on_message_received_while_methane_threshold_breached(InterfaceItemBase ifitem) {
	    this.pump_state = device_state_t.ON;	
	    super.info ( super.getName ( ), "Turn on message received while methane threshold breached, will turn on when methane state is normal" );
	}
	protected void action_TRANS_turn_off_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_offwater_level_detectors_controller_portturn_offuser_port_turn_off_message_received_while_methane_threshold_breached(InterfaceItemBase ifitem) {
	    this.pump_state = device_state_t.OFF;	
	    super.info ( super.getName ( ), "Turn off message received while methane threshold breached" );
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
				case STATE_pump_turned_off:
					this.history[STATE_TOP] = STATE_pump_turned_off;
					current__et = STATE_TOP;
					break;
				case STATE_pump_turned_on:
					this.history[STATE_TOP] = STATE_pump_turned_on;
					current__et = STATE_TOP;
					break;
				case STATE_methane_threshold_breached:
					this.history[STATE_TOP] = STATE_methane_threshold_breached;
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
			case pump_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case pump_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_pump_turned_off_BY_initializeiport:
			{
				pump_controller_idata_t data = (pump_controller_idata_t) generic_data__et;
				action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_pump_turned_off_BY_initializeiport(ifitem, data);
				return STATE_pump_turned_off;
			}
			case pump_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_pump_turned_off_TO_pump_turned_on_BY_turn_onwater_level_detectors_controller_portturn_onuser_port:
			{
				action_TRANS_turn_on_message_received_FROM_pump_turned_off_TO_pump_turned_on_BY_turn_onwater_level_detectors_controller_portturn_onuser_port(ifitem);
				return STATE_pump_turned_on;
			}
			case pump_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_pump_turned_on_TO_pump_turned_off_BY_turn_offwater_level_detectors_controller_portturn_offuser_port:
			{
				action_TRANS_turn_off_message_received_FROM_pump_turned_on_TO_pump_turned_off_BY_turn_offwater_level_detectors_controller_portturn_offuser_port(ifitem);
				return STATE_pump_turned_off;
			}
			case pump_controller_t.CHAIN_TRANS_methane_threshold_breached_received_while_pump_off_FROM_pump_turned_off_TO_methane_threshold_breached_BY_threshold_breachedmethane_port:
			{
				action_TRANS_methane_threshold_breached_received_while_pump_off_FROM_pump_turned_off_TO_methane_threshold_breached_BY_threshold_breachedmethane_port(ifitem);
				return STATE_methane_threshold_breached;
			}
			case pump_controller_t.CHAIN_TRANS_methane_threshold_breached_received_while_pump_on_FROM_pump_turned_on_TO_methane_threshold_breached_BY_threshold_breachedmethane_port:
			{
				action_TRANS_methane_threshold_breached_received_while_pump_on_FROM_pump_turned_on_TO_methane_threshold_breached_BY_threshold_breachedmethane_port(ifitem);
				return STATE_methane_threshold_breached;
			}
			case pump_controller_t.CHAIN_TRANS_state_normal_received_pump_was_turned_on_FROM_methane_threshold_breached_TO_pump_turned_on_BY_state_normalmethane_port:
			{
				action_TRANS_state_normal_received_pump_was_turned_on_FROM_methane_threshold_breached_TO_pump_turned_on_BY_state_normalmethane_port(ifitem);
				return STATE_pump_turned_on;
			}
			case pump_controller_t.CHAIN_TRANS_state_normal_received_pump_was_turned_off_FROM_methane_threshold_breached_TO_pump_turned_off_BY_state_normalmethane_port:
			{
				action_TRANS_state_normal_received_pump_was_turned_off_FROM_methane_threshold_breached_TO_pump_turned_off_BY_state_normalmethane_port(ifitem);
				return STATE_pump_turned_off;
			}
			case pump_controller_t.CHAIN_TRANS_turn_on_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_onwater_level_detectors_controller_portturn_onuser_port_turn_on_message_received_while_methane_threshold_breached:
			{
				action_TRANS_turn_on_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_onwater_level_detectors_controller_portturn_onuser_port_turn_on_message_received_while_methane_threshold_breached(ifitem);
				return STATE_methane_threshold_breached;
			}
			case pump_controller_t.CHAIN_TRANS_turn_off_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_offwater_level_detectors_controller_portturn_offuser_port_turn_off_message_received_while_methane_threshold_breached:
			{
				action_TRANS_turn_off_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_offwater_level_detectors_controller_portturn_offuser_port_turn_off_message_received_while_methane_threshold_breached(ifitem);
				return STATE_methane_threshold_breached;
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
				case STATE_pump_turned_off:
					if (!(skip_entry__et)) entry_pump_turned_off();
					/* in leaf state: return state id */
					return STATE_pump_turned_off;
				case STATE_pump_turned_on:
					if (!(skip_entry__et)) entry_pump_turned_on();
					/* in leaf state: return state id */
					return STATE_pump_turned_on;
				case STATE_methane_threshold_breached:
					/* in leaf state: return state id */
					return STATE_methane_threshold_breached;
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
		int chain__et = pump_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
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
			                        chain__et = pump_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_pump_turned_off_BY_initializeiport;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_pump_turned_off:
			        switch(trigger__et) {
			                case TRIG_water_level_detectors_controller_port__turn_on:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_pump_turned_off_TO_pump_turned_on_BY_turn_onwater_level_detectors_controller_portturn_onuser_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_user_port__turn_on:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_pump_turned_off_TO_pump_turned_on_BY_turn_onwater_level_detectors_controller_portturn_onuser_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_methane_port__threshold_breached:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_methane_threshold_breached_received_while_pump_off_FROM_pump_turned_off_TO_methane_threshold_breached_BY_threshold_breachedmethane_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_pump_turned_on:
			        switch(trigger__et) {
			                case TRIG_water_level_detectors_controller_port__turn_off:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_pump_turned_on_TO_pump_turned_off_BY_turn_offwater_level_detectors_controller_portturn_offuser_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_user_port__turn_off:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_pump_turned_on_TO_pump_turned_off_BY_turn_offwater_level_detectors_controller_portturn_offuser_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_methane_port__threshold_breached:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_methane_threshold_breached_received_while_pump_on_FROM_pump_turned_on_TO_methane_threshold_breached_BY_threshold_breachedmethane_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_methane_threshold_breached:
			        switch(trigger__et) {
			                case TRIG_methane_port__state_normal:
			                    { 
			                    if (this.pump_state == device_state_t.ON
			                    )
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_state_normal_received_pump_was_turned_on_FROM_methane_threshold_breached_TO_pump_turned_on_BY_state_normalmethane_port;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (this.pump_state == device_state_t.OFF
			                    )
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_state_normal_received_pump_was_turned_off_FROM_methane_threshold_breached_TO_pump_turned_off_BY_state_normalmethane_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                case TRIG_water_level_detectors_controller_port__turn_on:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_on_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_onwater_level_detectors_controller_portturn_onuser_port_turn_on_message_received_while_methane_threshold_breached;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_user_port__turn_on:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_on_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_onwater_level_detectors_controller_portturn_onuser_port_turn_on_message_received_while_methane_threshold_breached;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_water_level_detectors_controller_port__turn_off:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_off_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_offwater_level_detectors_controller_portturn_offuser_port_turn_off_message_received_while_methane_threshold_breached;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_user_port__turn_off:
			                    {
			                        chain__et = pump_controller_t.CHAIN_TRANS_turn_off_message_received_while_methane_threshold_breached_FROM_methane_threshold_breached_TO_methane_threshold_breached_BY_turn_offwater_level_detectors_controller_portturn_offuser_port_turn_off_message_received_while_methane_threshold_breached;
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
