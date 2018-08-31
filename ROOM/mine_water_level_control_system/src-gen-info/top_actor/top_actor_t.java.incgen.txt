package top_actor;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import alarm_station.*;
import base_actor.*;
import devices.*;
import environment_monitoring_station.*;
import logger.*;
import mine_water_level_control_system.*;
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
class CONSTANTS {
	public class O_SENSOR_CONTROLLER {
		public static final int PERIOD = 150;
		public static final boolean DETECT_ABOVE_THRESHOLD = false;
		public static final int THRESHOLD = 10;
		public static final int ERROR_COUNT_THRESHOLD = 2;
	}

	public class O_SENSOR {
		public static final int IVALUE = 50;
		public static final boolean IERROR_OCCURRED = false;
	}

	public class CO_SENSOR_CONTROLLER {
		public static final int PERIOD = 150;
		public static final boolean DETECT_ABOVE_THRESHOLD = true;
		public static final int THRESHOLD = 100;
		public static final int ERROR_COUNT_THRESHOLD = 2;
	}

	public class CO_SENSOR {
		public static final int IVALUE = 50;
		public static final boolean IERROR_OCCURRED = false;
	}

	public class CH4_SENSOR_CONTROLLER {
		public static final int PERIOD = 150;
		public static final boolean DETECT_ABOVE_THRESHOLD = true;
		public static final int THRESHOLD = 100;
		public static final int ERROR_COUNT_THRESHOLD = 2;
	}
	
	public class CH4_SENSOR {
		public static final int IVALUE = 50;
		public static final boolean IERROR_OCCURRED = false;
	}

	public class WATER_LEVEL_SENSORS_CONTROLLER {
		public static final int PERIOD = 5000;
	}
	
	public class LOW_WATER_LEVEL_SENSOR {
		public static final int IVALUE = 0;
	}

	public class HIGH_WATER_LEVEL_SENSOR {
		public static final int IVALUE = 0;
	}

	public class WATER_FLOW_SENSOR_CONTROLLER {
		public static final int PERIOD = 150;
		public static final int NUMBER_OF_ACTIVATIONS = 6;
	}

	public class WATER_FLOW_SENSOR {
		public static final int IVALUE = 0;
	}

	public class ALARM {
		public static final int ISTATE  = device_state_t.OFF;
	}

	public class PUMP {
		public static final int ISTATE  = device_state_t.OFF;
	}
	
	public class SCENARION {
		public static final int CHECK_PERIOD = 300;
	}
}

/*--------------------- end user code ---------------------*/


public class top_actor_t extends base_actor_t {


	//--------------------- ports

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs

	/*--------------------- attributes ---------------------*/

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public top_actor_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("top_actor_t");

		// initialize attributes

		// own ports

		// own saps

		// own service implementations

		// sub actors

		// wiring
		InterfaceItemBase.connect(this, "mine_water_level_control_system/iport", "mine_water_level_control_system_iport");
		InterfaceItemBase.connect(this, "mine_water_level_control_system/pump_controller/user_port", "mine_water_level_control_system_user_port");
		InterfaceItemBase.connect(this, "mine_water_level_control_system/water_flow_sensors_controller/test_port", "mine_water_level_control_system_test_port");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */


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
	public static final int STATE_step1 = 3;
	public static final int STATE_step2 = 4;
	public static final int STATE_MAX = 5;
	
	/* transition chains */
	public static final int CHAIN_TRANS_stall0_FROM_step0_TO_step0_BY_timeouttimer_access_point_stall0 = 1;
	public static final int CHAIN_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point = 2;
	public static final int CHAIN_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1 = 3;
	public static final int CHAIN_TRANS_transition1_FROM_step1_TO_step2_BY_timeouttimer_access_point = 4;
	public static final int CHAIN_TRANS_INITIAL_TO__step0 = 5;
	
	/* triggers */
	public static final int POLLING = 0;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"step0",
		"step1",
		"step2"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	
	/* Action Codes */
	protected void action_TRANS_stall0_FROM_step0_TO_step0_BY_timeouttimer_access_point_stall0(InterfaceItemBase ifitem) {
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println ( "Pump activated" );
	    System.out.println ( "Setting low water level sensor controller ( disabling high because both cannot be active ) " );
	    
	    super.high_water_level_sensor.value = 0;
	    super.low_water_level_sensor.value = 1;
	    
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1(InterfaceItemBase ifitem) {
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition1_FROM_step1_TO_step2_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println ( "Pump deactivated" );
	    
	    super.stop_scenario ( );
	}
	protected void action_TRANS_INITIAL_TO__step0() {
	    // initialize sensors 
	    this.o_sensor.value = CONSTANTS.O_SENSOR.IVALUE;
	    this.o_sensor.error_occurred = CONSTANTS.O_SENSOR.IERROR_OCCURRED;
	    
	    this.co_sensor.value = CONSTANTS.CO_SENSOR.IVALUE;
	    this.co_sensor.error_occurred = CONSTANTS.CO_SENSOR.IERROR_OCCURRED;
	    
	    this.ch4_sensor.value = CONSTANTS.CH4_SENSOR.IVALUE;
	    this.ch4_sensor.error_occurred = CONSTANTS.CH4_SENSOR.IERROR_OCCURRED;
	    
	    this.low_water_level_sensor.value = CONSTANTS.LOW_WATER_LEVEL_SENSOR.IVALUE;
	    this.high_water_level_sensor.value = CONSTANTS.HIGH_WATER_LEVEL_SENSOR.IVALUE;
	    
	    this.water_flow_sensor.value = CONSTANTS.WATER_FLOW_SENSOR.IVALUE;
	    
	    this.alarm.state = CONSTANTS.ALARM.ISTATE;
	    
	    this.pump.state = CONSTANTS.PUMP.ISTATE;
	    
	    // initialize system
	    this.mine_water_level_control_system_iport.initialize ( 
	    	new gas_sensor_controller_idata_t ( 
	    		CONSTANTS.CO_SENSOR_CONTROLLER.PERIOD,
	    		CONSTANTS.CO_SENSOR_CONTROLLER.DETECT_ABOVE_THRESHOLD,
	    		CONSTANTS.CO_SENSOR_CONTROLLER.THRESHOLD,
	    		this.co_sensor,
	    		CONSTANTS.CO_SENSOR_CONTROLLER.ERROR_COUNT_THRESHOLD
	    	),
	    	new gas_sensor_controller_idata_t ( 
	    		CONSTANTS.O_SENSOR_CONTROLLER.PERIOD,
	    		CONSTANTS.O_SENSOR_CONTROLLER.DETECT_ABOVE_THRESHOLD,
	    		CONSTANTS.O_SENSOR_CONTROLLER.THRESHOLD,
	    		this.o_sensor,
	    		CONSTANTS.O_SENSOR_CONTROLLER.ERROR_COUNT_THRESHOLD
	    	),
	    	new gas_sensor_controller_idata_t ( 
	    		CONSTANTS.CH4_SENSOR_CONTROLLER.PERIOD,
	    		CONSTANTS.CH4_SENSOR_CONTROLLER.DETECT_ABOVE_THRESHOLD,
	    		CONSTANTS.CH4_SENSOR_CONTROLLER.THRESHOLD,
	    		this.ch4_sensor,
	    		CONSTANTS.CH4_SENSOR_CONTROLLER.ERROR_COUNT_THRESHOLD
	    	),
	    	new water_level_sensors_controller_idata_t ( 
	    		CONSTANTS.WATER_LEVEL_SENSORS_CONTROLLER.PERIOD,
	    		this.low_water_level_sensor,
	    		this.high_water_level_sensor
	    	),
	    	new water_flow_sensor_controller_idata_t (
	    		CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.PERIOD,
	    		this.pump,
	    		this.water_flow_sensor,
	    		CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS
	    	),
	    	new alarm_controller_idata_t ( 
	    		this.alarm	
	    	),
	    	new pump_controller_idata_t ( 
	    		this.pump
	    	)
	    );
	    
	    System.out.println ( "Setting high water level sensor" );
	    
	    super.high_water_level_sensor.value = 1;
	    
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
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
				case STATE_step1:
					this.history[STATE_TOP] = STATE_step1;
					current__et = STATE_TOP;
					break;
				case STATE_step2:
					this.history[STATE_TOP] = STATE_step2;
					current__et = STATE_TOP;
					break;
				case STATE_step0:
					this.history[STATE_TOP] = STATE_step0;
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
			case top_actor_t.CHAIN_TRANS_stall0_FROM_step0_TO_step0_BY_timeouttimer_access_point_stall0:
			{
				action_TRANS_stall0_FROM_step0_TO_step0_BY_timeouttimer_access_point_stall0(ifitem);
				return STATE_step0;
			}
			case top_actor_t.CHAIN_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point:
			{
				action_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point(ifitem);
				return STATE_step1;
			}
			case top_actor_t.CHAIN_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1:
			{
				action_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1(ifitem);
				return STATE_step1;
			}
			case top_actor_t.CHAIN_TRANS_transition1_FROM_step1_TO_step2_BY_timeouttimer_access_point:
			{
				action_TRANS_transition1_FROM_step1_TO_step2_BY_timeouttimer_access_point(ifitem);
				return STATE_step2;
			}
			case top_actor_t.CHAIN_TRANS_INITIAL_TO__step0:
			{
				action_TRANS_INITIAL_TO__step0();
				return STATE_step0;
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
				case STATE_step1:
					/* in leaf state: return state id */
					return STATE_step1;
				case STATE_step2:
					/* in leaf state: return state id */
					return STATE_step2;
				case STATE_step0:
					/* in leaf state: return state id */
					return STATE_step0;
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
		int chain__et = top_actor_t.CHAIN_TRANS_INITIAL_TO__step0;
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
			    case STATE_step1:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (super.pump.state != device_state_t.OFF
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (super.pump.state == device_state_t.OFF
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition1_FROM_step1_TO_step2_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step2:
			        break;
			    case STATE_step0:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (super.pump.state != device_state_t.ON
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall0_FROM_step0_TO_step0_BY_timeouttimer_access_point_stall0;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (super.pump.state == device_state_t.ON
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point;
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
