package top_actor;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import alarm_station.*;
import base_actor.*;
import deadline_task.*;
import devices.*;
import environment_monitoring_station.*;
import logger.*;
import mine_water_level_control_system.*;
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

/*--------------------- begin user code ---------------------*/
class Constants {
	public class ALARM {
		public static final String NAME   = "ALARM";
		public static final int    ISTATE = device_state_t.OFF;
	}

	public class PUMP {
		public static final String NAME   = "PUMP";
		public static final int    ISTATE = device_state_t.OFF;
	}
	
	public class WATER_LEVEL_SENSORS {
		public static final int LOW_WATER_LEVEL_THRESHOLD  = 50;
		public static final int HIGH_WATER_LEVEL_THRESHOLD = 150;
		public static final int WATER_LEVEL 				  = 100;
	}

	public class WATER_FLOW_SENSOR {
		public static final String NAME 			  = "WATER FLOW SENSOR";
		public static final int IVALUE 				  = 0;
		public static final int CONVERSION_TIME_IN_MS = 50;
	}

	public class CO_SENSOR {
		public static final String NAME 			     = "CO SENSOR";
		public static final int IVALUE 				     = 50;
		public static final boolean IREAD_ERROR_OCCURRED = false;
		public static final int CONVERSION_TIME_IN_MS    = 50;
	}

	public class O_SENSOR {
		public static final String NAME 			  	 = "O SENSOR";
		public static final int IVALUE 				  	 = 150;
		public static final boolean IREAD_ERROR_OCCURRED = false;
		public static final int CONVERSION_TIME_IN_MS 	 = 50;
	}

	public class CH4_SENSOR {
		public static final String NAME 			  	 = "CH4 SENSOR";
		public static final int IVALUE 				  	 = 100;
		public static final boolean IREAD_ERROR_OCCURRED = false;
		public static final int CONVERSION_TIME_IN_MS    = 50;
	}

	public class CO_SENSOR_CONTROLLER {
		public static final int DEADLINE_IN_MS 		  = 100;
		public static final int PERIOD_IN_MS 		  = 150;
		public static final int THRESHOLD 			  = 100;
		public static final int ERROR_COUNT_THRESHOLD = 2;
	}

	public class O_SENSOR_CONTROLLER {
		public static final int DEADLINE_IN_MS 		  = 100;
		public static final int PERIOD_IN_MS 		  = 150;
		public static final int THRESHOLD 			  = 100;
		public static final int ERROR_COUNT_THRESHOLD = 2;
	}

	public class CH4_SENSOR_CONTROLLER {
		public static final int DEADLINE_IN_MS 		  = 100;
		public static final int PERIOD_IN_MS 		  = 150;
		public static final int THRESHOLD 			  = 100;
		public static final int ERROR_COUNT_THRESHOLD = 2;
	}


	public class WATER_LEVEL_SENSORS_CONTROLLER {
		public static final int DEADLINE_IN_MS = 200;
	}
	
	public class WATER_FLOW_SENSOR_CONTROLLER {
		public static final int DEADLINE_IN_MS 		  = 100;
		public static final int PERIOD_IN_MS 	      = 150;
		public static final int NUMBER_OF_ACTIVATIONS = 6;
	}


	public class SCENARIO {
		public static final int CHECK_PERIOD = 10;
		public static final int SLEEP_TIME_IN_S = 2;
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
		InterfaceItemBase.connect(this, "mine_water_level_control_system/user_port", "mine_water_level_control_system_user_port");
		InterfaceItemBase.connect(this, "mine_water_level_control_system/water_flow_sensor_controller/test_port", "mine_water_level_control_system_test_port");
		InterfaceItemBase.connect(this, "mine_water_level_control_system/water_level_sensors/update_port", "mine_water_level_control_system_update_port");


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
	public static final int STATE_step3 = 5;
	public static final int STATE_MAX = 6;
	
	/* transition chains */
	public static final int CHAIN_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point = 1;
	public static final int CHAIN_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1 = 2;
	public static final int CHAIN_TRANS_transition1_FROM_step1_TO_step2_BY_timeouttimer_access_point = 3;
	public static final int CHAIN_TRANS_stall2_FROM_step2_TO_step2_BY_timeouttimer_access_point_stall2 = 4;
	public static final int CHAIN_TRANS_transition2_FROM_step2_TO_step3_BY_timeouttimer_access_point = 5;
	public static final int CHAIN_TRANS_INITIAL_TO__step0 = 6;
	
	/* triggers */
	public static final int POLLING = 0;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"step0",
		"step1",
		"step2",
		"step3"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	protected void entry_step1() {
		System.out.println (
			"STEP 1"
		);
	}
	protected void entry_step2() {
		System.out.println (
			"STEP 2"
		);
	}
	protected void entry_step3() {
		System.out.println (
			"STEP 3"
		);
	}
	
	/* Action Codes */
	protected void action_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println ( 
	    	"Setting high water level sensor"
	    );
	    
	    super.mine_water_level_control_system_update_port.update (
	    	Constants.WATER_LEVEL_SENSORS.HIGH_WATER_LEVEL_THRESHOLD + 1	
	    );
	    
	    super.timer_access_point.startTimeout (
	    	Constants.SCENARIO.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1(InterfaceItemBase ifitem) {
	    super.timer_access_point.startTimeout (
	    	Constants.SCENARIO.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition1_FROM_step1_TO_step2_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println (
	    	"Pump activated"
	    );
	    
	    super.pause ( );	
	    
	    System.out.println (
	    	"Setting low water level sensor controller ( unsetting high because both cannot be active ) "
	    );
	    
	    super.mine_water_level_control_system_update_port.update (
	    	Constants.WATER_LEVEL_SENSORS.LOW_WATER_LEVEL_THRESHOLD - 1	
	    );
	    
	    super.timer_access_point.startTimeout (
	    	Constants.SCENARIO.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_stall2_FROM_step2_TO_step2_BY_timeouttimer_access_point_stall2(InterfaceItemBase ifitem) {
	    super.timer_access_point.startTimeout (
	    	Constants.SCENARIO.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition2_FROM_step2_TO_step3_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println (
	    	"Pump deactivated"
	    );
	    
	    super.stop_scenario ( );
	}
	protected void action_TRANS_INITIAL_TO__step0() {
	    this.co_sensor.initialize (
	    	this.logger,
	    	super.getName ( ),
	    	Constants.CO_SENSOR.IVALUE,
	    	Constants.CO_SENSOR.IREAD_ERROR_OCCURRED,
	    	Constants.CO_SENSOR.CONVERSION_TIME_IN_MS,
	    	Constants.CO_SENSOR.NAME
	    );
	    
	    // initialize sensors 
	    this.o_sensor.initialize (
	    	this.logger,
	    	super.getName ( ),
	    	Constants.O_SENSOR.IVALUE,
	    	Constants.O_SENSOR.IREAD_ERROR_OCCURRED,
	    	Constants.O_SENSOR.CONVERSION_TIME_IN_MS,
	    	Constants.O_SENSOR.NAME
	    );
	    
	    this.ch4_sensor.initialize (
	    	this.logger,
	    	super.getName ( ),
	    	Constants.CH4_SENSOR.IVALUE,
	    	Constants.CH4_SENSOR.IREAD_ERROR_OCCURRED,
	    	Constants.CH4_SENSOR.CONVERSION_TIME_IN_MS,
	    	Constants.CH4_SENSOR.NAME
	    );
	    
	    this.water_flow_sensor.initialize_without_read_error_occurred (
	    	this.logger,
	    	super.getName ( ),
	    	Constants.WATER_FLOW_SENSOR.IVALUE,
	    	Constants.WATER_FLOW_SENSOR.CONVERSION_TIME_IN_MS,
	    	Constants.WATER_FLOW_SENSOR.NAME
	    );
	    
	    this.alarm.initialize (
	    	this.logger,
	    	super.getName ( ),
	    	Constants.ALARM.NAME,
	    	Constants.ALARM.ISTATE
	    );
	    
	    this.pump.initialize (
	    	this.logger,
	    	super.getName ( ),
	    	Constants.PUMP.NAME,
	    	Constants.PUMP.ISTATE
	    );
	    
	    // initialize system
	    this.mine_water_level_control_system_iport.initialize ( 
	    	new mine_water_level_control_system_idata_t (
	    		Constants.WATER_LEVEL_SENSORS.WATER_LEVEL,
	    		Constants.WATER_LEVEL_SENSORS.LOW_WATER_LEVEL_THRESHOLD,
	    		Constants.WATER_LEVEL_SENSORS.HIGH_WATER_LEVEL_THRESHOLD,
	    		this.alarm,
	    		this.pump,
	    		this.co_sensor,	
	    		Constants.CO_SENSOR_CONTROLLER.DEADLINE_IN_MS,
	    		Constants.CO_SENSOR_CONTROLLER.PERIOD_IN_MS,
	    		Constants.CO_SENSOR_CONTROLLER.THRESHOLD,
	    		Constants.CO_SENSOR_CONTROLLER.ERROR_COUNT_THRESHOLD,
	    		this.o_sensor,	
	    		Constants.O_SENSOR_CONTROLLER.DEADLINE_IN_MS,
	    		Constants.O_SENSOR_CONTROLLER.PERIOD_IN_MS,
	    		Constants.O_SENSOR_CONTROLLER.THRESHOLD,
	    		Constants.O_SENSOR_CONTROLLER.ERROR_COUNT_THRESHOLD,
	    		this.ch4_sensor,	
	    		Constants.CH4_SENSOR_CONTROLLER.DEADLINE_IN_MS,
	    		Constants.CH4_SENSOR_CONTROLLER.PERIOD_IN_MS,
	    		Constants.CH4_SENSOR_CONTROLLER.THRESHOLD,
	    		Constants.CH4_SENSOR_CONTROLLER.ERROR_COUNT_THRESHOLD,
	    		Constants.WATER_LEVEL_SENSORS_CONTROLLER.DEADLINE_IN_MS,
	    		this.water_flow_sensor,	
	    		Constants.WATER_FLOW_SENSOR_CONTROLLER.DEADLINE_IN_MS,
	    		Constants.WATER_FLOW_SENSOR_CONTROLLER.PERIOD_IN_MS,
	    		Constants.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS
	    	)
	    );
	    
	    super.timer_access_point.startTimeout (
	    	Constants.SCENARIO.CHECK_PERIOD
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
				case STATE_step3:
					this.history[STATE_TOP] = STATE_step3;
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
			case top_actor_t.CHAIN_TRANS_stall2_FROM_step2_TO_step2_BY_timeouttimer_access_point_stall2:
			{
				action_TRANS_stall2_FROM_step2_TO_step2_BY_timeouttimer_access_point_stall2(ifitem);
				return STATE_step2;
			}
			case top_actor_t.CHAIN_TRANS_transition2_FROM_step2_TO_step3_BY_timeouttimer_access_point:
			{
				action_TRANS_transition2_FROM_step2_TO_step3_BY_timeouttimer_access_point(ifitem);
				return STATE_step3;
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
		boolean skip_entry__et = false;
		if (state__et >= STATE_MAX) {
			state__et =  (state__et - STATE_MAX);
			skip_entry__et = true;
		}
		while (true) {
			switch (state__et) {
				case STATE_step1:
					if (!(skip_entry__et)) entry_step1();
					/* in leaf state: return state id */
					return STATE_step1;
				case STATE_step2:
					if (!(skip_entry__et)) entry_step2();
					/* in leaf state: return state id */
					return STATE_step2;
				case STATE_step3:
					if (!(skip_entry__et)) entry_step3();
					/* in leaf state: return state id */
					return STATE_step3;
				case STATE_step0:
					if (!(skip_entry__et)) entry_step0();
					/* in leaf state: return state id */
					return STATE_step0;
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
			                    if (super.is_pump_turned_on ( ) == false
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall1_FROM_step1_TO_step1_BY_timeouttimer_access_point_stall1;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (super.is_pump_turned_on ( ) == true 
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
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (super.is_pump_turned_on ( ) == true 
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall2_FROM_step2_TO_step2_BY_timeouttimer_access_point_stall2;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (super.is_pump_turned_on ( ) == false 
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition2_FROM_step2_TO_step3_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step3:
			        break;
			    case STATE_step0:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point;
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
