package mine_water_level_control_system;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import alarm_station.*;
import devices.*;
import environment_monitoring_station.*;
import logger.*;
import periodic_task.*;
import pump_station.*;
import room.basic.service.timing.*;
import test.*;
import room.basic.service.timing.PTimer.*;
import alarm_station.alarm_controller_iprotocol_t.*;
import devices.methane_protocol_t.*;
import mine_water_level_control_system.mine_water_level_control_system_iprotocol_t.*;
import periodic_task.periodic_task_iprotocol_t.*;
import pump_station.pump_controller_iprotocol_t.*;
import devices.switch_protocol_t.*;
import test.test_protocol_t.*;

/*--------------------- begin user code ---------------------*/
import java.util.logging.*;
import java.io.IOException;

/*--------------------- end user code ---------------------*/


public class mine_water_level_control_system_t extends logger_t {


	//--------------------- ports
	protected mine_water_level_control_system_iprotocol_tPort iport = null;
	protected periodic_task_iprotocol_tConjPort co_sensor_controller_iport = null;
	protected periodic_task_iprotocol_tConjPort o_sensor_controller_iport = null;
	protected periodic_task_iprotocol_tConjPort ch4_sensor_controller_iport = null;
	protected periodic_task_iprotocol_tConjPort water_level_detectors_sensor_controller_iport = null;
	protected periodic_task_iprotocol_tConjPort water_flow_sensors_controller_iport = null;
	protected alarm_controller_iprotocol_tConjPort alarm_controller_iport = null;
	protected pump_controller_iprotocol_tConjPort pump_controller_iport = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_iport = 8;
	public static final int IFITEM_co_sensor_controller_iport = 1;
	public static final int IFITEM_o_sensor_controller_iport = 2;
	public static final int IFITEM_ch4_sensor_controller_iport = 3;
	public static final int IFITEM_water_level_detectors_sensor_controller_iport = 4;
	public static final int IFITEM_water_flow_sensors_controller_iport = 5;
	public static final int IFITEM_alarm_controller_iport = 6;
	public static final int IFITEM_pump_controller_iport = 7;

	/*--------------------- attributes ---------------------*/

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public mine_water_level_control_system_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("mine_water_level_control_system_t");

		// initialize attributes

		// own ports
		iport = new mine_water_level_control_system_iprotocol_tPort(this, "iport", IFITEM_iport);
		co_sensor_controller_iport = new periodic_task_iprotocol_tConjPort(this, "co_sensor_controller_iport", IFITEM_co_sensor_controller_iport);
		o_sensor_controller_iport = new periodic_task_iprotocol_tConjPort(this, "o_sensor_controller_iport", IFITEM_o_sensor_controller_iport);
		ch4_sensor_controller_iport = new periodic_task_iprotocol_tConjPort(this, "ch4_sensor_controller_iport", IFITEM_ch4_sensor_controller_iport);
		water_level_detectors_sensor_controller_iport = new periodic_task_iprotocol_tConjPort(this, "water_level_detectors_sensor_controller_iport", IFITEM_water_level_detectors_sensor_controller_iport);
		water_flow_sensors_controller_iport = new periodic_task_iprotocol_tConjPort(this, "water_flow_sensors_controller_iport", IFITEM_water_flow_sensors_controller_iport);
		alarm_controller_iport = new alarm_controller_iprotocol_tConjPort(this, "alarm_controller_iport", IFITEM_alarm_controller_iport);
		pump_controller_iport = new pump_controller_iprotocol_tConjPort(this, "pump_controller_iport", IFITEM_pump_controller_iport);

		// own saps

		// own service implementations

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "co_sensor_controller");
		new gas_sensor_controller_t(this, "co_sensor_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "o_sensor_controller");
		new gas_sensor_controller_t(this, "o_sensor_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "ch4_sensor_controller");
		new methane_sensor_controller_t(this, "ch4_sensor_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "water_level_detectors_sensor_controller");
		new water_level_sensors_controller_t(this, "water_level_detectors_sensor_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "water_flow_sensors_controller");
		new water_flow_sensor_controller_t(this, "water_flow_sensors_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "alarm_controller");
		new alarm_controller_t(this, "alarm_controller");
		DebuggingService.getInstance().addMessageActorCreate(this, "pump_controller");
		new pump_controller_t(this, "pump_controller");

		// wiring
		InterfaceItemBase.connect(this, "co_sensor_controller/iport", "co_sensor_controller_iport");
		InterfaceItemBase.connect(this, "co_sensor_controller/alarm_port", "alarm_controller/co_sensor_controller_port");
		InterfaceItemBase.connect(this, "o_sensor_controller/iport", "o_sensor_controller_iport");
		InterfaceItemBase.connect(this, "o_sensor_controller/alarm_port", "alarm_controller/o_sensor_controller_port");
		InterfaceItemBase.connect(this, "ch4_sensor_controller/iport", "ch4_sensor_controller_iport");
		InterfaceItemBase.connect(this, "ch4_sensor_controller/alarm_port", "alarm_controller/ch4_sensor_controller_port");
		InterfaceItemBase.connect(this, "ch4_sensor_controller/methane_port", "pump_controller/methane_port");
		InterfaceItemBase.connect(this, "water_level_detectors_sensor_controller/iport", "water_level_detectors_sensor_controller_iport");
		InterfaceItemBase.connect(this, "water_level_detectors_sensor_controller/pump_port", "pump_controller/water_level_detectors_controller_port");
		InterfaceItemBase.connect(this, "water_flow_sensors_controller/iport", "water_flow_sensors_controller_iport");
		InterfaceItemBase.connect(this, "water_flow_sensors_controller/alarm_port", "alarm_controller/water_flow_sensor_controller_port");
		InterfaceItemBase.connect(this, "pump_controller/iport", "pump_controller_iport");
		InterfaceItemBase.connect(this, "alarm_controller/iport", "alarm_controller_iport");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */


	//--------------------- port getters
	public mine_water_level_control_system_iprotocol_tPort getIport (){
		return this.iport;
	}
	public periodic_task_iprotocol_tConjPort getCo_sensor_controller_iport (){
		return this.co_sensor_controller_iport;
	}
	public periodic_task_iprotocol_tConjPort getO_sensor_controller_iport (){
		return this.o_sensor_controller_iport;
	}
	public periodic_task_iprotocol_tConjPort getCh4_sensor_controller_iport (){
		return this.ch4_sensor_controller_iport;
	}
	public periodic_task_iprotocol_tConjPort getWater_level_detectors_sensor_controller_iport (){
		return this.water_level_detectors_sensor_controller_iport;
	}
	public periodic_task_iprotocol_tConjPort getWater_flow_sensors_controller_iport (){
		return this.water_flow_sensors_controller_iport;
	}
	public alarm_controller_iprotocol_tConjPort getAlarm_controller_iport (){
		return this.alarm_controller_iport;
	}
	public pump_controller_iprotocol_tConjPort getPump_controller_iport (){
		return this.pump_controller_iport;
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
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_iport__initialize = IFITEM_iport + EVT_SHIFT*mine_water_level_control_system_iprotocol_t.IN_initialize;
	
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
		super.info ( this.getName ( ), "System waiting for initialization message" );
	}
	protected void entry_running() {
		super.info ( this.getName ( ), "System initialized and running" );
	}
	
	/* Action Codes */
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_running_BY_initializeiport(InterfaceItemBase ifitem, mine_water_level_control_system_idata_t data) {
	    // initialize gas sensors
	    this.co_sensor_controller_iport.initialize ( data.co_sensor_controller_idata );
	    this.o_sensor_controller_iport.initialize ( data.o_sensor_controller_idata );
	    this.ch4_sensor_controller_iport.initialize ( data.ch4_sensor_controller_idata );
	    
	    this.water_level_detectors_sensor_controller_iport.initialize ( data.water_level_detectors_sensor_controller_idata );
	    this.water_flow_sensors_controller_iport.initialize ( data.water_flow_sensor_controller_idata );
	    
	    this.alarm_controller_iport.initialize ( data.alarm_controller_idata );
	    this.pump_controller_iport.initialize ( data.pump_controller_idata );
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
					if (!(skip_entry__et)) entry_running();
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
