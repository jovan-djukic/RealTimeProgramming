package mine_water_level_control_system;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import alarm_station.*;
import deadline_task.*;
import devices.*;
import environment_monitoring_station.*;
import logger.*;
import periodic_task.*;
import pump_station.*;
import room.basic.service.timing.*;
import test.*;
import room.basic.service.timing.PTimer.*;
import deadline_task.deadline_task_iprotocol_t.*;
import mine_water_level_control_system.mine_water_level_control_system_iprotocol_t.*;
import mine_water_level_control_system.switch_protocol_t.*;
import test.test_protocol_t.*;
import devices.water_level_sensors_interrupt_protocol_t.*;
import devices.water_level_sensors_iprotocol_t.*;
import devices.water_level_sensors_update_protocol_t.*;



public class mine_water_level_control_system_t extends ActorClassBase {


	//--------------------- ports
	protected mine_water_level_control_system_iprotocol_tPort iport = null;
	protected switch_protocol_tPort user_port = null;
	protected water_level_sensors_iprotocol_tConjPort water_level_sensors_iport = null;
	protected deadline_task_iprotocol_tConjPort co_sensor_controller_iport = null;
	protected deadline_task_iprotocol_tConjPort o_sensor_controller_iport = null;
	protected deadline_task_iprotocol_tConjPort ch4_sensor_controller_iport = null;
	protected deadline_task_iprotocol_tConjPort water_level_sensors_controller_iport = null;
	protected deadline_task_iprotocol_tConjPort water_flow_sensor_controller_iport = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_iport = 7;
	public static final int IFITEM_user_port = 8;
	public static final int IFITEM_water_level_sensors_iport = 1;
	public static final int IFITEM_co_sensor_controller_iport = 2;
	public static final int IFITEM_o_sensor_controller_iport = 3;
	public static final int IFITEM_ch4_sensor_controller_iport = 4;
	public static final int IFITEM_water_level_sensors_controller_iport = 5;
	public static final int IFITEM_water_flow_sensor_controller_iport = 6;

	/*--------------------- attributes ---------------------*/
	public  logger_t logger;
	public  alarm_controller_t alarm_controller;
	public  pump_controller_t pump_controller;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public mine_water_level_control_system_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("mine_water_level_control_system_t");

		// initialize attributes
		this.setLogger(new logger_t());
		this.setAlarm_controller(new alarm_controller_t());
		this.setPump_controller(new pump_controller_t());

		// own ports
		iport = new mine_water_level_control_system_iprotocol_tPort(this, "iport", IFITEM_iport);
		user_port = new switch_protocol_tPort(this, "user_port", IFITEM_user_port);
		water_level_sensors_iport = new water_level_sensors_iprotocol_tConjPort(this, "water_level_sensors_iport", IFITEM_water_level_sensors_iport);
		co_sensor_controller_iport = new deadline_task_iprotocol_tConjPort(this, "co_sensor_controller_iport", IFITEM_co_sensor_controller_iport);
		o_sensor_controller_iport = new deadline_task_iprotocol_tConjPort(this, "o_sensor_controller_iport", IFITEM_o_sensor_controller_iport);
		ch4_sensor_controller_iport = new deadline_task_iprotocol_tConjPort(this, "ch4_sensor_controller_iport", IFITEM_ch4_sensor_controller_iport);
		water_level_sensors_controller_iport = new deadline_task_iprotocol_tConjPort(this, "water_level_sensors_controller_iport", IFITEM_water_level_sensors_controller_iport);
		water_flow_sensor_controller_iport = new deadline_task_iprotocol_tConjPort(this, "water_flow_sensor_controller_iport", IFITEM_water_flow_sensor_controller_iport);

		// own saps

		// own service implementations

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "water_level_sensors");
		new water_level_sensors_t(this, "water_level_sensors");
		DebuggingService.getInstance().addMessageActorCreate(this, "co_sensor_controller");
		new gas_sensor_controller_t(this, "co_sensor_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "o_sensor_controller");
		new gas_sensor_controller_t(this, "o_sensor_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "ch4_sensor_controller");
		new methane_sensor_controller_t(this, "ch4_sensor_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "water_level_sensors_controller");
		new water_level_sensors_controller_t(this, "water_level_sensors_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "water_flow_sensor_controller");
		new water_flow_sensor_controller_t(this, "water_flow_sensor_controller");

		// wiring
		InterfaceItemBase.connect(this, "water_level_sensors/iport", "water_level_sensors_iport");
		InterfaceItemBase.connect(this, "water_level_sensors/interrupt_port", "water_level_sensors_controller/interrupt_port");
		InterfaceItemBase.connect(this, "co_sensor_controller/iport", "co_sensor_controller_iport");
		InterfaceItemBase.connect(this, "o_sensor_controller/iport", "o_sensor_controller_iport");
		InterfaceItemBase.connect(this, "ch4_sensor_controller/iport", "ch4_sensor_controller_iport");
		InterfaceItemBase.connect(this, "water_level_sensors_controller/iport", "water_level_sensors_controller_iport");
		InterfaceItemBase.connect(this, "water_flow_sensor_controller/iport", "water_flow_sensor_controller_iport");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setLogger(logger_t logger) {
		 this.logger = logger;
	}
	public logger_t getLogger() {
		return this.logger;
	}
	public void setAlarm_controller(alarm_controller_t alarm_controller) {
		 this.alarm_controller = alarm_controller;
	}
	public alarm_controller_t getAlarm_controller() {
		return this.alarm_controller;
	}
	public void setPump_controller(pump_controller_t pump_controller) {
		 this.pump_controller = pump_controller;
	}
	public pump_controller_t getPump_controller() {
		return this.pump_controller;
	}


	//--------------------- port getters
	public mine_water_level_control_system_iprotocol_tPort getIport (){
		return this.iport;
	}
	public switch_protocol_tPort getUser_port (){
		return this.user_port;
	}
	public water_level_sensors_iprotocol_tConjPort getWater_level_sensors_iport (){
		return this.water_level_sensors_iport;
	}
	public deadline_task_iprotocol_tConjPort getCo_sensor_controller_iport (){
		return this.co_sensor_controller_iport;
	}
	public deadline_task_iprotocol_tConjPort getO_sensor_controller_iport (){
		return this.o_sensor_controller_iport;
	}
	public deadline_task_iprotocol_tConjPort getCh4_sensor_controller_iport (){
		return this.ch4_sensor_controller_iport;
	}
	public deadline_task_iprotocol_tConjPort getWater_level_sensors_controller_iport (){
		return this.water_level_sensors_controller_iport;
	}
	public deadline_task_iprotocol_tConjPort getWater_flow_sensor_controller_iport (){
		return this.water_flow_sensor_controller_iport;
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
	public static final int STATE_running = 3;
	public static final int STATE_MAX = 4;
	
	/* transition chains */
	public static final int CHAIN_TRANS_INITIAL_TO__waiting_for_imessage = 1;
	public static final int CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_running_BY_initializeiport = 2;
	public static final int CHAIN_TRANS_turn_on_received_FROM_running_TO_running_BY_turn_onuser_port_turn_on_received = 3;
	public static final int CHAIN_TRANS_turn_off_received_FROM_running_TO_running_BY_turn_offuser_port_turn_off_received = 4;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_iport__initialize = IFITEM_iport + EVT_SHIFT*mine_water_level_control_system_iprotocol_t.IN_initialize;
	public static final int TRIG_user_port__turn_on = IFITEM_user_port + EVT_SHIFT*switch_protocol_t.IN_turn_on;
	public static final int TRIG_user_port__turn_off = IFITEM_user_port + EVT_SHIFT*switch_protocol_t.IN_turn_off;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"waiting_for_imessage",
		"running"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	protected void entry_waiting_for_imessage() {
		this.logger.info ( 
			this.getName ( ), 
			"System waiting for initialization message"
		);
	}
	
	/* Action Codes */
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_running_BY_initializeiport(InterfaceItemBase ifitem, mine_water_level_control_system_idata_t data) {
	    // initialize
	    this.water_level_sensors_iport.initialize (
	    	new water_level_sensors_idata_t (
	    		data.water_level_sensors_low_water_level_threshold,
	    		data.water_level_sensors_high_water_level_threshold,
	    		data.water_level 
	    	)	
	    );
	    
	    // initialize alarm
	    this.alarm_controller.initialize (
	    	this.logger,
	    	super.getName ( ),
	    	new alarm_controller_idata_t (
	    		data.alarm,
	    		4
	    	)	
	    );
	    
	    // initialize pump 
	    this.pump_controller.initialize (
	    	this.logger,
	    	super.getName ( ),
	    	new pump_controller_idata_t (
	    		data.pump
	    	)	
	    );
	    
	    // initialize gas sensors
	    // co sensor
	    this.co_sensor_controller_iport.initialize (
	    	new gas_sensor_controller_idata_t (
	    		data.co_sensor_controller_deadline_in_ms,
	    		data.co_sensor_controller_period_in_ms,
	    		true,
	    		data.co_sensor_controller_threshold,
	    		data.co_sensor,
	    		data.co_sensor_controller_error_count_threshold,
	    		this.alarm_controller 	
	    	)
	    );
	    
	    // o sensor
	    this.o_sensor_controller_iport.initialize (
	    	new gas_sensor_controller_idata_t (
	    		data.o_sensor_controller_deadline_in_ms,
	    		data.o_sensor_controller_period_in_ms,
	    		false,
	    		data.o_sensor_controller_threshold,
	    		data.o_sensor,
	    		data.o_sensor_controller_error_count_threshold,
	    		this.alarm_controller 	
	    	)
	    );
	    
	    // ch4 sensor
	    this.ch4_sensor_controller_iport.initialize (
	    	new methane_sensor_controller_idata_t (
	    		data.ch4_sensor_controller_deadline_in_ms,
	    		data.ch4_sensor_controller_period_in_ms,
	    		true,
	    		data.ch4_sensor_controller_threshold,
	    		data.ch4_sensor,
	    		data.ch4_sensor_controller_error_count_threshold,
	    		this.alarm_controller,
	    		this.pump_controller
	    	)
	    );
	    
	    
	    this.water_level_sensors_controller_iport.initialize (
	    	new water_level_sensors_controller_idata_t (
	    		data.water_level_sensors_controller_deadline_in_ms,
	    		this.pump_controller
	    	)
	    );
	    
	    this.water_flow_sensor_controller_iport.initialize (
	    	new water_flow_sensor_controller_idata_t (
	    		data.water_flow_sensor_controller_deadline_in_ms,
	    		data.water_flow_sensor_controller_period_in_ms,
	    		data.pump,
	    		data.water_flow_sensor,
	    		data.water_flow_sensor_controller_number_of_activations,
	    		this.alarm_controller
	    	)
	    );
	}
	protected void action_TRANS_turn_on_received_FROM_running_TO_running_BY_turn_onuser_port_turn_on_received(InterfaceItemBase ifitem) {
	    this.logger.info (
	    	super.getName ( ),
	    	"User turn on received"		
	    );
	    
	    this.pump_controller.turn_on (
	    	this.logger,
	    	super.getName ( )
	    );							
	}
	protected void action_TRANS_turn_off_received_FROM_running_TO_running_BY_turn_offuser_port_turn_off_received(InterfaceItemBase ifitem) {
	    this.logger.info (
	    	super.getName ( ),
	    	"User turn on received"		
	    );
	    
	    this.pump_controller.turn_off (
	    	this.logger,
	    	super.getName ( )
	    );							
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
				case STATE_running:
					this.history[STATE_TOP] = STATE_running;
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
			case mine_water_level_control_system_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case mine_water_level_control_system_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_running_BY_initializeiport:
			{
				mine_water_level_control_system_idata_t data = (mine_water_level_control_system_idata_t) generic_data__et;
				action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_running_BY_initializeiport(ifitem, data);
				return STATE_running;
			}
			case mine_water_level_control_system_t.CHAIN_TRANS_turn_on_received_FROM_running_TO_running_BY_turn_onuser_port_turn_on_received:
			{
				action_TRANS_turn_on_received_FROM_running_TO_running_BY_turn_onuser_port_turn_on_received(ifitem);
				return STATE_running;
			}
			case mine_water_level_control_system_t.CHAIN_TRANS_turn_off_received_FROM_running_TO_running_BY_turn_offuser_port_turn_off_received:
			{
				action_TRANS_turn_off_received_FROM_running_TO_running_BY_turn_offuser_port_turn_off_received(ifitem);
				return STATE_running;
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
					if (!(skip_entry__et)) entry_waiting_for_imessage();
					/* in leaf state: return state id */
					return STATE_waiting_for_imessage;
				case STATE_running:
					/* in leaf state: return state id */
					return STATE_running;
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
		int chain__et = mine_water_level_control_system_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
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
			                        chain__et = mine_water_level_control_system_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_running_BY_initializeiport;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_running:
			        switch(trigger__et) {
			                case TRIG_user_port__turn_on:
			                    {
			                        chain__et = mine_water_level_control_system_t.CHAIN_TRANS_turn_on_received_FROM_running_TO_running_BY_turn_onuser_port_turn_on_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_user_port__turn_off:
			                    {
			                        chain__et = mine_water_level_control_system_t.CHAIN_TRANS_turn_off_received_FROM_running_TO_running_BY_turn_offuser_port_turn_off_received;
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
