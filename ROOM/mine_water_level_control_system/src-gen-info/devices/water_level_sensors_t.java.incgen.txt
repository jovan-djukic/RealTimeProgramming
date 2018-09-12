package devices;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import logger.*;
import devices.water_level_sensors_interrupt_protocol_t.*;
import devices.water_level_sensors_iprotocol_t.*;
import devices.water_level_sensors_update_protocol_t.*;



public class water_level_sensors_t extends ActorClassBase {


	//--------------------- ports
	protected water_level_sensors_iprotocol_tPort iport = null;
	protected water_level_sensors_update_protocol_tPort update_port = null;
	protected water_level_sensors_interrupt_protocol_tPort interrupt_port = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_iport = 1;
	public static final int IFITEM_update_port = 2;
	public static final int IFITEM_interrupt_port = 3;

	/*--------------------- attributes ---------------------*/
	public  logger_t logger;
	public  int low_water_level_threshold;
	public  int high_water_level_threshold;
	public  boolean low_water_level_threshold_breached;
	public  boolean high_water_level_threshold_breached;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public water_level_sensors_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("water_level_sensors_t");

		// initialize attributes
		this.setLogger(new logger_t());
		this.setLow_water_level_threshold(0);
		this.setHigh_water_level_threshold(0);
		this.setLow_water_level_threshold_breached(false);
		this.setHigh_water_level_threshold_breached(false);

		// own ports
		iport = new water_level_sensors_iprotocol_tPort(this, "iport", IFITEM_iport);
		update_port = new water_level_sensors_update_protocol_tPort(this, "update_port", IFITEM_update_port);
		interrupt_port = new water_level_sensors_interrupt_protocol_tPort(this, "interrupt_port", IFITEM_interrupt_port);

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setLogger(logger_t logger) {
		 this.logger = logger;
	}
	public logger_t getLogger() {
		return this.logger;
	}
	public void setLow_water_level_threshold(int low_water_level_threshold) {
		 this.low_water_level_threshold = low_water_level_threshold;
	}
	public int getLow_water_level_threshold() {
		return this.low_water_level_threshold;
	}
	public void setHigh_water_level_threshold(int high_water_level_threshold) {
		 this.high_water_level_threshold = high_water_level_threshold;
	}
	public int getHigh_water_level_threshold() {
		return this.high_water_level_threshold;
	}
	public void setLow_water_level_threshold_breached(boolean low_water_level_threshold_breached) {
		 this.low_water_level_threshold_breached = low_water_level_threshold_breached;
	}
	public boolean getLow_water_level_threshold_breached() {
		return this.low_water_level_threshold_breached;
	}
	public void setHigh_water_level_threshold_breached(boolean high_water_level_threshold_breached) {
		 this.high_water_level_threshold_breached = high_water_level_threshold_breached;
	}
	public boolean getHigh_water_level_threshold_breached() {
		return this.high_water_level_threshold_breached;
	}


	//--------------------- port getters
	public water_level_sensors_iprotocol_tPort getIport (){
		return this.iport;
	}
	public water_level_sensors_update_protocol_tPort getUpdate_port (){
		return this.update_port;
	}
	public water_level_sensors_interrupt_protocol_tPort getInterrupt_port (){
		return this.interrupt_port;
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
	public static final int STATE_waiting_for_update = 3;
	public static final int STATE_MAX = 4;
	
	/* transition chains */
	public static final int CHAIN_TRANS_INITIAL_TO__waiting_for_imessage = 1;
	public static final int CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_update_BY_initializeiport = 2;
	public static final int CHAIN_TRANS_update_received_FROM_waiting_for_update_TO_waiting_for_update_BY_updateupdate_port_update_received = 3;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_iport__initialize = IFITEM_iport + EVT_SHIFT*water_level_sensors_iprotocol_t.IN_initialize;
	public static final int TRIG_update_port__update = IFITEM_update_port + EVT_SHIFT*water_level_sensors_update_protocol_t.IN_update;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"waiting_for_imessage",
		"waiting_for_update"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	
	/* Action Codes */
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_update_BY_initializeiport(InterfaceItemBase ifitem, water_level_sensors_idata_t data) {
	    this.logger.info (
	    	super.getName ( ),
	    	"Initializing water level sensors"
	    );
	    
	    this.low_water_level_threshold  = data.low_water_level_threshold;
	    this.high_water_level_threshold = data.high_water_level_threshold;
	    
	    boolean threshold_breached = false;
	    if ( data.water_level < this.low_water_level_threshold ) {
	    	this.low_water_level_threshold_breached  = true;
	    	threshold_breached						 = true;
	    } else {
	    	this.low_water_level_threshold_breached  = false;
	    }
	    
	    if ( data.water_level > this.high_water_level_threshold ) {
	    	this.high_water_level_threshold_breached = true;
	    	threshold_breached						 = true;
	    } else {
	    	this.high_water_level_threshold_breached = false;
	    }
	    
	    if ( threshold_breached == true ) {
	    	this.interrupt_port.interrupt (
	    		new water_level_sensors_interrupt_data_t (
	    			this.low_water_level_threshold_breached,
	    			this.high_water_level_threshold_breached
	    		)
	    	);
	    }
	}
	protected void action_TRANS_update_received_FROM_waiting_for_update_TO_waiting_for_update_BY_updateupdate_port_update_received(InterfaceItemBase ifitem, int water_level) {
	    this.logger.info (
	    	super.getName ( ),
	    	"Updating water level sensors"
	    );
	    
	    boolean value_changed = false;
	    
	    boolean new_low_water_level_threshold_breached  = false;
	    if ( water_level < this.low_water_level_threshold ) {
	    	new_low_water_level_threshold_breached  = true;
	    } else {
	    	new_low_water_level_threshold_breached  = false;
	    }
	    
	    if ( new_low_water_level_threshold_breached != this.low_water_level_threshold_breached ) {
	    	this.low_water_level_threshold_breached = new_low_water_level_threshold_breached;
	    	value_changed = true;
	    }
	    
	    boolean new_high_water_level_threshold_breached  = false;
	    if ( water_level > this.high_water_level_threshold ) {
	    	new_high_water_level_threshold_breached  = true;
	    } else {
	    	new_high_water_level_threshold_breached  = false;
	    }
	    
	    if ( new_high_water_level_threshold_breached != this.high_water_level_threshold_breached ) {
	    	this.high_water_level_threshold_breached = new_high_water_level_threshold_breached;
	    	value_changed = true;
	    }
	    
	    if ( value_changed == true ) {
	    	this.interrupt_port.interrupt (
	    		new water_level_sensors_interrupt_data_t (
	    			this.low_water_level_threshold_breached,
	    			this.high_water_level_threshold_breached
	    		)
	    	);
	    }
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
				case STATE_waiting_for_update:
					this.history[STATE_TOP] = STATE_waiting_for_update;
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
			case water_level_sensors_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case water_level_sensors_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_update_BY_initializeiport:
			{
				water_level_sensors_idata_t data = (water_level_sensors_idata_t) generic_data__et;
				action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_update_BY_initializeiport(ifitem, data);
				return STATE_waiting_for_update;
			}
			case water_level_sensors_t.CHAIN_TRANS_update_received_FROM_waiting_for_update_TO_waiting_for_update_BY_updateupdate_port_update_received:
			{
				int water_level = (Integer) generic_data__et;
				action_TRANS_update_received_FROM_waiting_for_update_TO_waiting_for_update_BY_updateupdate_port_update_received(ifitem, water_level);
				return STATE_waiting_for_update;
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
				case STATE_waiting_for_imessage:
					/* in leaf state: return state id */
					return STATE_waiting_for_imessage;
				case STATE_waiting_for_update:
					/* in leaf state: return state id */
					return STATE_waiting_for_update;
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
		int chain__et = water_level_sensors_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
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
			                        chain__et = water_level_sensors_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_update_BY_initializeiport;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_waiting_for_update:
			        switch(trigger__et) {
			                case TRIG_update_port__update:
			                    {
			                        chain__et = water_level_sensors_t.CHAIN_TRANS_update_received_FROM_waiting_for_update_TO_waiting_for_update_BY_updateupdate_port_update_received;
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
