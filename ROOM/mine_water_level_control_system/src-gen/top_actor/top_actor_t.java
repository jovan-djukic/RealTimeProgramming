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
		public static final int CHECK_PERIOD = 10;
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
	public  int number_of_activations;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public top_actor_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("top_actor_t");

		// initialize attributes
		this.setNumber_of_activations(0);

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
	public void setNumber_of_activations(int number_of_activations) {
		 this.number_of_activations = number_of_activations;
	}
	public int getNumber_of_activations() {
		return this.number_of_activations;
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
	public static final int STATE_step1 = 3;
	public static final int STATE_step2 = 4;
	public static final int STATE_step3 = 5;
	public static final int STATE_step4 = 6;
	public static final int STATE_step5 = 7;
	public static final int STATE_step6 = 8;
	public static final int STATE_step7 = 9;
	public static final int STATE_step8 = 10;
	public static final int STATE_step9 = 11;
	public static final int STATE_step10 = 12;
	public static final int STATE_step11 = 13;
	public static final int STATE_step12 = 14;
	public static final int STATE_step13 = 15;
	public static final int STATE_step14 = 16;
	public static final int STATE_step15 = 17;
	public static final int STATE_step16 = 18;
	public static final int STATE_step17 = 19;
	public static final int STATE_step18 = 20;
	public static final int STATE_step19 = 21;
	public static final int STATE_MAX = 22;
	
	/* transition chains */
	public static final int CHAIN_TRANS_stall0_FROM_step0_TO_step0_BY_timeouttimer_access_point_stall0 = 1;
	public static final int CHAIN_TRANS_transition0_FROM_step0_TO_step1_BY_timeouttimer_access_point = 2;
	public static final int CHAIN_TRANS_transition1_FROM_step1_TO_step2_BY_checkingmine_water_level_control_system_test_port = 3;
	public static final int CHAIN_TRANS_stall2_FROM_step2_TO_step2_BY_checkingmine_water_level_control_system_test_port_stall2 = 4;
	public static final int CHAIN_TRANS_transition2_FROM_step2_TO_step3_BY_checkingmine_water_level_control_system_test_port = 5;
	public static final int CHAIN_TRANS_transition3_FROM_step3_TO_step4_BY_activatedmine_water_level_control_system_test_port = 6;
	public static final int CHAIN_TRANS_stall4_FROM_step4_TO_step4_BY_timeouttimer_access_point_stall4 = 7;
	public static final int CHAIN_TRANS_transition4_FROM_step4_TO_step5_BY_timeouttimer_access_point = 8;
	public static final int CHAIN_TRANS_transition5_FROM_step5_TO_step6_BY_checkingmine_water_level_control_system_test_port = 9;
	public static final int CHAIN_TRANS_stall6_FROM_step6_TO_step6_BY_checkingmine_water_level_control_system_test_port_stall6 = 10;
	public static final int CHAIN_TRANS_transition6_FROM_step6_TO_step7_BY_alarmmine_water_level_control_system_test_port = 11;
	public static final int CHAIN_TRANS_stall7_FROM_step7_TO_step7_BY_timeouttimer_access_point_stall7 = 12;
	public static final int CHAIN_TRANS_transition7_FROM_step7_TO_step8_BY_timeouttimer_access_point = 13;
	public static final int CHAIN_TRANS_transition8_FROM_step8_TO_step9_BY_checkingmine_water_level_control_system_test_port = 14;
	public static final int CHAIN_TRANS_transition9_FROM_step9_TO_step10_BY_activatedmine_water_level_control_system_test_port = 15;
	public static final int CHAIN_TRANS_transition10_FROM_step10_TO_step11_BY_checkingmine_water_level_control_system_test_port = 16;
	public static final int CHAIN_TRANS_stall11_FROM_step11_TO_step11_BY_checkingmine_water_level_control_system_test_port_stall11 = 17;
	public static final int CHAIN_TRANS_transition11_FROM_step11_TO_step12_BY_checkingmine_water_level_control_system_test_port = 18;
	public static final int CHAIN_TRANS_transition12_FROM_step12_TO_step13_BY_activatedmine_water_level_control_system_test_port = 19;
	public static final int CHAIN_TRANS_stall13_FROM_step13_TO_step13_BY_timeouttimer_access_point_stall13 = 20;
	public static final int CHAIN_TRANS_transition13_FROM_step13_TO_step14_BY_timeouttimer_access_point = 21;
	public static final int CHAIN_TRANS_transition14_FROM_step14_TO_step15_BY_checkingmine_water_level_control_system_test_port = 22;
	public static final int CHAIN_TRANS_stall15_FROM_step15_TO_step15_BY_checkingmine_water_level_control_system_test_port_stall15 = 23;
	public static final int CHAIN_TRANS_transition15_FROM_step15_TO_step16_BY_checkingmine_water_level_control_system_test_port = 24;
	public static final int CHAIN_TRANS_transition16_FROM_step16_TO_step17_BY_activatedmine_water_level_control_system_test_port = 25;
	public static final int CHAIN_TRANS_INITIAL_TO__step0 = 26;
	
	/* triggers */
	public static final int POLLING = 0;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"step0",
		"step1",
		"step2",
		"step3",
		"step4",
		"step5",
		"step6",
		"step7",
		"step8",
		"step9",
		"step10",
		"step11",
		"step12",
		"step13",
		"step14",
		"step15",
		"step16",
		"step17",
		"step18",
		"step19"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
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
	    
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition1_FROM_step1_TO_step2_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller detected state change" );
	    
	    this.number_of_activations = 0;
	}
	protected void action_TRANS_stall2_FROM_step2_TO_step2_BY_checkingmine_water_level_control_system_test_port_stall2(InterfaceItemBase ifitem) {
	    this.number_of_activations++;
	    System.out.println ( "Water flow sensor activated, " + this.number_of_activations + " / " + CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS );
	}
	protected void action_TRANS_transition2_FROM_step2_TO_step3_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor activated" );
	    System.out.println ( "One more try left before activation of alarm" );
	    System.out.println ( "Setting water flow sensor to avoid alarm activation" );
	    
	    super.water_flow_sensor.value = 1;
	}
	protected void action_TRANS_transition3_FROM_step3_TO_step4_BY_activatedmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor in sleeping state" );
	    System.out.println ( "Setting low water level sensor" );
	    
	    super.low_water_level_sensor.value = 1;
	    super.high_water_level_sensor.value = 0;
	    this.number_of_activations = 0;
	    
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_stall4_FROM_step4_TO_step4_BY_timeouttimer_access_point_stall4(InterfaceItemBase ifitem) {
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition4_FROM_step4_TO_step5_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println ( "Pump turned off" );
	    this.number_of_activations = 0;
	}
	protected void action_TRANS_transition5_FROM_step5_TO_step6_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller detected state change" );
	    
	    this.number_of_activations = 0;
	}
	protected void action_TRANS_stall6_FROM_step6_TO_step6_BY_checkingmine_water_level_control_system_test_port_stall6(InterfaceItemBase ifitem) {
	    this.number_of_activations++;
	    System.out.println ( "Water flow sensor activated, " + this.number_of_activations + " / " + CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS );
	}
	protected void action_TRANS_transition6_FROM_step6_TO_step7_BY_alarmmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Last check" );
	    System.out.println ( "Turn on alarm message sent" );
	    
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_stall7_FROM_step7_TO_step7_BY_timeouttimer_access_point_stall7(InterfaceItemBase ifitem) {
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition7_FROM_step7_TO_step8_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println ( "Alarm on state confirmed" );
	    System.out.println ( "Unsetting water flow sensor" );
	    
	    super.water_flow_sensor.value = 0;
	}
	protected void action_TRANS_transition8_FROM_step8_TO_step9_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller is checking" );
	}
	protected void action_TRANS_transition9_FROM_step9_TO_step10_BY_activatedmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller is sleeping" );
	    System.out.println ( "Setting water flow sensor" );
	    
	    super.water_flow_sensor.value = 1;
	}
	protected void action_TRANS_transition10_FROM_step10_TO_step11_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller initial check" );
	    
	    this.number_of_activations = 0;
	}
	protected void action_TRANS_stall11_FROM_step11_TO_step11_BY_checkingmine_water_level_control_system_test_port_stall11(InterfaceItemBase ifitem) {
	    this.number_of_activations++;
	    System.out.println ( "Water flow sensor activated, " + this.number_of_activations + " / " + CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS );
	}
	protected void action_TRANS_transition11_FROM_step11_TO_step12_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Last check before alarm activation" );
	    System.out.println ( "Unsetting water flow sensor" );
	    
	    this.water_flow_sensor.value = 0;
	}
	protected void action_TRANS_transition12_FROM_step12_TO_step13_BY_activatedmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller is in sleeping state" );
	    System.out.println ( "Turning on pump using user port" );
	    
	    super.mine_water_level_control_system_user_port.turn_on ( );
	    
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_stall13_FROM_step13_TO_step13_BY_timeouttimer_access_point_stall13(InterfaceItemBase ifitem) {
	    super.timer_access_point.startTimeout (
	    	CONSTANTS.SCENARION.CHECK_PERIOD
	    );
	}
	protected void action_TRANS_transition13_FROM_step13_TO_step14_BY_timeouttimer_access_point(InterfaceItemBase ifitem) {
	    System.out.println ( "Pump turned on" );
	}
	protected void action_TRANS_transition14_FROM_step14_TO_step15_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller initial check" );
	    
	    this.number_of_activations = 0;
	}
	protected void action_TRANS_stall15_FROM_step15_TO_step15_BY_checkingmine_water_level_control_system_test_port_stall15(InterfaceItemBase ifitem) {
	    this.number_of_activations++;
	    System.out.println ( "Water flow sensor activated, " + this.number_of_activations + " / " + CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS );
	}
	protected void action_TRANS_transition15_FROM_step15_TO_step16_BY_checkingmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Last check before alarm activation" );
	    System.out.println ( "Turning pump off using user port" );
	    
	    super.mine_water_level_control_system_user_port.turn_off ( );
	}
	protected void action_TRANS_transition16_FROM_step16_TO_step17_BY_activatedmine_water_level_control_system_test_port(InterfaceItemBase ifitem) {
	    System.out.println ( "Water flow sensor controller is in sleeping state" );
	    
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
				case STATE_step3:
					this.history[STATE_TOP] = STATE_step3;
					current__et = STATE_TOP;
					break;
				case STATE_step4:
					this.history[STATE_TOP] = STATE_step4;
					current__et = STATE_TOP;
					break;
				case STATE_step5:
					this.history[STATE_TOP] = STATE_step5;
					current__et = STATE_TOP;
					break;
				case STATE_step6:
					this.history[STATE_TOP] = STATE_step6;
					current__et = STATE_TOP;
					break;
				case STATE_step7:
					this.history[STATE_TOP] = STATE_step7;
					current__et = STATE_TOP;
					break;
				case STATE_step8:
					this.history[STATE_TOP] = STATE_step8;
					current__et = STATE_TOP;
					break;
				case STATE_step9:
					this.history[STATE_TOP] = STATE_step9;
					current__et = STATE_TOP;
					break;
				case STATE_step10:
					this.history[STATE_TOP] = STATE_step10;
					current__et = STATE_TOP;
					break;
				case STATE_step11:
					this.history[STATE_TOP] = STATE_step11;
					current__et = STATE_TOP;
					break;
				case STATE_step12:
					this.history[STATE_TOP] = STATE_step12;
					current__et = STATE_TOP;
					break;
				case STATE_step13:
					this.history[STATE_TOP] = STATE_step13;
					current__et = STATE_TOP;
					break;
				case STATE_step14:
					this.history[STATE_TOP] = STATE_step14;
					current__et = STATE_TOP;
					break;
				case STATE_step15:
					this.history[STATE_TOP] = STATE_step15;
					current__et = STATE_TOP;
					break;
				case STATE_step16:
					this.history[STATE_TOP] = STATE_step16;
					current__et = STATE_TOP;
					break;
				case STATE_step17:
					this.history[STATE_TOP] = STATE_step17;
					current__et = STATE_TOP;
					break;
				case STATE_step18:
					this.history[STATE_TOP] = STATE_step18;
					current__et = STATE_TOP;
					break;
				case STATE_step19:
					this.history[STATE_TOP] = STATE_step19;
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
			case top_actor_t.CHAIN_TRANS_transition1_FROM_step1_TO_step2_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition1_FROM_step1_TO_step2_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step2;
			}
			case top_actor_t.CHAIN_TRANS_stall2_FROM_step2_TO_step2_BY_checkingmine_water_level_control_system_test_port_stall2:
			{
				action_TRANS_stall2_FROM_step2_TO_step2_BY_checkingmine_water_level_control_system_test_port_stall2(ifitem);
				return STATE_step2;
			}
			case top_actor_t.CHAIN_TRANS_transition2_FROM_step2_TO_step3_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition2_FROM_step2_TO_step3_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step3;
			}
			case top_actor_t.CHAIN_TRANS_transition3_FROM_step3_TO_step4_BY_activatedmine_water_level_control_system_test_port:
			{
				action_TRANS_transition3_FROM_step3_TO_step4_BY_activatedmine_water_level_control_system_test_port(ifitem);
				return STATE_step4;
			}
			case top_actor_t.CHAIN_TRANS_stall4_FROM_step4_TO_step4_BY_timeouttimer_access_point_stall4:
			{
				action_TRANS_stall4_FROM_step4_TO_step4_BY_timeouttimer_access_point_stall4(ifitem);
				return STATE_step4;
			}
			case top_actor_t.CHAIN_TRANS_transition4_FROM_step4_TO_step5_BY_timeouttimer_access_point:
			{
				action_TRANS_transition4_FROM_step4_TO_step5_BY_timeouttimer_access_point(ifitem);
				return STATE_step5;
			}
			case top_actor_t.CHAIN_TRANS_transition5_FROM_step5_TO_step6_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition5_FROM_step5_TO_step6_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step6;
			}
			case top_actor_t.CHAIN_TRANS_stall6_FROM_step6_TO_step6_BY_checkingmine_water_level_control_system_test_port_stall6:
			{
				action_TRANS_stall6_FROM_step6_TO_step6_BY_checkingmine_water_level_control_system_test_port_stall6(ifitem);
				return STATE_step6;
			}
			case top_actor_t.CHAIN_TRANS_transition6_FROM_step6_TO_step7_BY_alarmmine_water_level_control_system_test_port:
			{
				action_TRANS_transition6_FROM_step6_TO_step7_BY_alarmmine_water_level_control_system_test_port(ifitem);
				return STATE_step7;
			}
			case top_actor_t.CHAIN_TRANS_stall7_FROM_step7_TO_step7_BY_timeouttimer_access_point_stall7:
			{
				action_TRANS_stall7_FROM_step7_TO_step7_BY_timeouttimer_access_point_stall7(ifitem);
				return STATE_step7;
			}
			case top_actor_t.CHAIN_TRANS_transition7_FROM_step7_TO_step8_BY_timeouttimer_access_point:
			{
				action_TRANS_transition7_FROM_step7_TO_step8_BY_timeouttimer_access_point(ifitem);
				return STATE_step8;
			}
			case top_actor_t.CHAIN_TRANS_transition8_FROM_step8_TO_step9_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition8_FROM_step8_TO_step9_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step9;
			}
			case top_actor_t.CHAIN_TRANS_transition9_FROM_step9_TO_step10_BY_activatedmine_water_level_control_system_test_port:
			{
				action_TRANS_transition9_FROM_step9_TO_step10_BY_activatedmine_water_level_control_system_test_port(ifitem);
				return STATE_step10;
			}
			case top_actor_t.CHAIN_TRANS_transition10_FROM_step10_TO_step11_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition10_FROM_step10_TO_step11_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step11;
			}
			case top_actor_t.CHAIN_TRANS_stall11_FROM_step11_TO_step11_BY_checkingmine_water_level_control_system_test_port_stall11:
			{
				action_TRANS_stall11_FROM_step11_TO_step11_BY_checkingmine_water_level_control_system_test_port_stall11(ifitem);
				return STATE_step11;
			}
			case top_actor_t.CHAIN_TRANS_transition11_FROM_step11_TO_step12_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition11_FROM_step11_TO_step12_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step12;
			}
			case top_actor_t.CHAIN_TRANS_transition12_FROM_step12_TO_step13_BY_activatedmine_water_level_control_system_test_port:
			{
				action_TRANS_transition12_FROM_step12_TO_step13_BY_activatedmine_water_level_control_system_test_port(ifitem);
				return STATE_step13;
			}
			case top_actor_t.CHAIN_TRANS_stall13_FROM_step13_TO_step13_BY_timeouttimer_access_point_stall13:
			{
				action_TRANS_stall13_FROM_step13_TO_step13_BY_timeouttimer_access_point_stall13(ifitem);
				return STATE_step13;
			}
			case top_actor_t.CHAIN_TRANS_transition13_FROM_step13_TO_step14_BY_timeouttimer_access_point:
			{
				action_TRANS_transition13_FROM_step13_TO_step14_BY_timeouttimer_access_point(ifitem);
				return STATE_step14;
			}
			case top_actor_t.CHAIN_TRANS_transition14_FROM_step14_TO_step15_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition14_FROM_step14_TO_step15_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step15;
			}
			case top_actor_t.CHAIN_TRANS_stall15_FROM_step15_TO_step15_BY_checkingmine_water_level_control_system_test_port_stall15:
			{
				action_TRANS_stall15_FROM_step15_TO_step15_BY_checkingmine_water_level_control_system_test_port_stall15(ifitem);
				return STATE_step15;
			}
			case top_actor_t.CHAIN_TRANS_transition15_FROM_step15_TO_step16_BY_checkingmine_water_level_control_system_test_port:
			{
				action_TRANS_transition15_FROM_step15_TO_step16_BY_checkingmine_water_level_control_system_test_port(ifitem);
				return STATE_step16;
			}
			case top_actor_t.CHAIN_TRANS_transition16_FROM_step16_TO_step17_BY_activatedmine_water_level_control_system_test_port:
			{
				action_TRANS_transition16_FROM_step16_TO_step17_BY_activatedmine_water_level_control_system_test_port(ifitem);
				return STATE_step17;
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
				case STATE_step3:
					/* in leaf state: return state id */
					return STATE_step3;
				case STATE_step4:
					/* in leaf state: return state id */
					return STATE_step4;
				case STATE_step5:
					/* in leaf state: return state id */
					return STATE_step5;
				case STATE_step6:
					/* in leaf state: return state id */
					return STATE_step6;
				case STATE_step7:
					/* in leaf state: return state id */
					return STATE_step7;
				case STATE_step8:
					/* in leaf state: return state id */
					return STATE_step8;
				case STATE_step9:
					/* in leaf state: return state id */
					return STATE_step9;
				case STATE_step10:
					/* in leaf state: return state id */
					return STATE_step10;
				case STATE_step11:
					/* in leaf state: return state id */
					return STATE_step11;
				case STATE_step12:
					/* in leaf state: return state id */
					return STATE_step12;
				case STATE_step13:
					/* in leaf state: return state id */
					return STATE_step13;
				case STATE_step14:
					/* in leaf state: return state id */
					return STATE_step14;
				case STATE_step15:
					/* in leaf state: return state id */
					return STATE_step15;
				case STATE_step16:
					/* in leaf state: return state id */
					return STATE_step16;
				case STATE_step17:
					/* in leaf state: return state id */
					return STATE_step17;
				case STATE_step18:
					/* in leaf state: return state id */
					return STATE_step18;
				case STATE_step19:
					/* in leaf state: return state id */
					return STATE_step19;
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
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition1_FROM_step1_TO_step2_BY_checkingmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step2:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    { 
			                    if (this.number_of_activations != ( CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS - 1 )	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall2_FROM_step2_TO_step2_BY_checkingmine_water_level_control_system_test_port_stall2;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (this.number_of_activations == ( CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS - 1 )	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition2_FROM_step2_TO_step3_BY_checkingmine_water_level_control_system_test_port;
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
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__activated:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition3_FROM_step3_TO_step4_BY_activatedmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step4:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (super.pump.state != device_state_t.OFF	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall4_FROM_step4_TO_step4_BY_timeouttimer_access_point_stall4;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (super.pump.state == device_state_t.OFF	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition4_FROM_step4_TO_step5_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step5:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition5_FROM_step5_TO_step6_BY_checkingmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step6:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    { 
			                    if (this.number_of_activations != CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall6_FROM_step6_TO_step6_BY_checkingmine_water_level_control_system_test_port_stall6;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                case TRIG_mine_water_level_control_system_test_port__alarm:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition6_FROM_step6_TO_step7_BY_alarmmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step7:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (super.alarm.state != device_state_t.ON	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall7_FROM_step7_TO_step7_BY_timeouttimer_access_point_stall7;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (super.alarm.state == device_state_t.ON	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition7_FROM_step7_TO_step8_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step8:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition8_FROM_step8_TO_step9_BY_checkingmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step9:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__activated:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition9_FROM_step9_TO_step10_BY_activatedmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step10:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition10_FROM_step10_TO_step11_BY_checkingmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step11:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    { 
			                    if (this.number_of_activations != ( CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS - 1 )	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall11_FROM_step11_TO_step11_BY_checkingmine_water_level_control_system_test_port_stall11;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (this.number_of_activations == ( CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS - 1 )	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition11_FROM_step11_TO_step12_BY_checkingmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step12:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__activated:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition12_FROM_step12_TO_step13_BY_activatedmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step13:
			        switch(trigger__et) {
			                case TRIG_timer_access_point__timeout:
			                    { 
			                    if (super.pump.state != device_state_t.ON	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall13_FROM_step13_TO_step13_BY_timeouttimer_access_point_stall13;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (super.pump.state == device_state_t.ON	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition13_FROM_step13_TO_step14_BY_timeouttimer_access_point;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step14:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition14_FROM_step14_TO_step15_BY_checkingmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step15:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__checking:
			                    { 
			                    if (this.number_of_activations != ( CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS - 1 )	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_stall15_FROM_step15_TO_step15_BY_checkingmine_water_level_control_system_test_port_stall15;
			                        catching_state__et = STATE_TOP;
			                    } else 
			                    if (this.number_of_activations == ( CONSTANTS.WATER_FLOW_SENSOR_CONTROLLER.NUMBER_OF_ACTIVATIONS - 1 )	
			                    )
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition15_FROM_step15_TO_step16_BY_checkingmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step16:
			        switch(trigger__et) {
			                case TRIG_mine_water_level_control_system_test_port__activated:
			                    {
			                        chain__et = top_actor_t.CHAIN_TRANS_transition16_FROM_step16_TO_step17_BY_activatedmine_water_level_control_system_test_port;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_step17:
			        break;
			    case STATE_step18:
			        break;
			    case STATE_step19:
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
