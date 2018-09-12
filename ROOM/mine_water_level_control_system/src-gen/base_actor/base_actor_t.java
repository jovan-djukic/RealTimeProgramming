package base_actor;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import alarm_station.*;
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


public class base_actor_t extends ActorClassBase {


	//--------------------- ports
	protected mine_water_level_control_system_iprotocol_tConjPort mine_water_level_control_system_iport = null;
	protected switch_protocol_tConjPort mine_water_level_control_system_user_port = null;
	protected test_protocol_tConjPort mine_water_level_control_system_test_port = null;
	protected water_level_sensors_update_protocol_tConjPort mine_water_level_control_system_update_port = null;

	//--------------------- saps
	protected PTimerConjPort timer_access_point = null;

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_mine_water_level_control_system_iport = 1;
	public static final int IFITEM_mine_water_level_control_system_user_port = 2;
	public static final int IFITEM_mine_water_level_control_system_test_port = 3;
	public static final int IFITEM_mine_water_level_control_system_update_port = 4;
	public static final int IFITEM_timer_access_point = 5;

	/*--------------------- attributes ---------------------*/
	public  logger_t logger;
	public  gas_sensor_t o_sensor;
	public  gas_sensor_t co_sensor;
	public  gas_sensor_t ch4_sensor;
	public  sensor_t water_flow_sensor;
	public  device_t alarm;
	public  device_t pump;

	/*--------------------- operations ---------------------*/
	public  int get_above_threshold_value(boolean detect_above_threshold, int threshold) {
		if ( detect_above_threshold == true ) {
			return threshold + 1;
		} else {
			return threshold - 1;
		}
	}
	public  int get_below_threshold_value(boolean detect_above_threshold, int threshold) {
		if ( detect_above_threshold == true ) {
			return threshold - 1;
		} else {
			return threshold + 1;
		}
	}
	public  boolean is_pump_turned_on() {
		int pump_state = this.pump.get_state (
			this.logger,
			super.getName ( )
		);
		
		if ( pump_state == device_state_t.ON ) {
			return true;
		} else {
			return false;
		}
	}
	public  boolean is_alarm_turned_on() {
		int alarm_state = this.alarm.get_state (
			this.logger,
			super.getName ( )
		);
		
		if ( alarm_state == device_state_t.ON ) {
			return true;
		} else {
			return false;
		}
	}
	public  void pause() {
		System.out.println ( 
			"Pausing for " + Constants.SCENARIO.SLEEP_TIME_IN_S + " seconds"
		);
		
		try {
			java.lang.Thread.sleep ( 
				Constants.SCENARIO.SLEEP_TIME_IN_S * 1000
			);
		} catch ( 
			InterruptedException e
		) { }
	}
	public  void stop_scenario() {
		System.out.println ( "Scenario is a success " );
		System.exit ( 1 );
	}


	//--------------------- construction
	public base_actor_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("base_actor_t");

		// initialize attributes
		this.setLogger(new logger_t());
		this.setO_sensor(new gas_sensor_t());
		this.setCo_sensor(new gas_sensor_t());
		this.setCh4_sensor(new gas_sensor_t());
		this.setWater_flow_sensor(new sensor_t());
		this.setAlarm(new device_t());
		this.setPump(new device_t());

		// own ports
		mine_water_level_control_system_iport = new mine_water_level_control_system_iprotocol_tConjPort(this, "mine_water_level_control_system_iport", IFITEM_mine_water_level_control_system_iport);
		mine_water_level_control_system_user_port = new switch_protocol_tConjPort(this, "mine_water_level_control_system_user_port", IFITEM_mine_water_level_control_system_user_port);
		mine_water_level_control_system_test_port = new test_protocol_tConjPort(this, "mine_water_level_control_system_test_port", IFITEM_mine_water_level_control_system_test_port);
		mine_water_level_control_system_update_port = new water_level_sensors_update_protocol_tConjPort(this, "mine_water_level_control_system_update_port", IFITEM_mine_water_level_control_system_update_port);

		// own saps
		timer_access_point = new PTimerConjPort(this, "timer_access_point", IFITEM_timer_access_point, 0);

		// own service implementations

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "mine_water_level_control_system");
		new mine_water_level_control_system_t(this, "mine_water_level_control_system");

		// wiring
		InterfaceItemBase.connect(this, "mine_water_level_control_system/iport", "mine_water_level_control_system_iport");
		InterfaceItemBase.connect(this, "mine_water_level_control_system/user_port", "mine_water_level_control_system_user_port");
		InterfaceItemBase.connect(this, "mine_water_level_control_system/water_flow_sensor_controller/test_port", "mine_water_level_control_system_test_port");
		InterfaceItemBase.connect(this, "mine_water_level_control_system/water_level_sensors/update_port", "mine_water_level_control_system_update_port");


		/* user defined constructor body */

	}

	/* --------------------- attribute setters and getters */
	public void setLogger(logger_t logger) {
		 this.logger = logger;
	}
	public logger_t getLogger() {
		return this.logger;
	}
	public void setO_sensor(gas_sensor_t o_sensor) {
		 this.o_sensor = o_sensor;
	}
	public gas_sensor_t getO_sensor() {
		return this.o_sensor;
	}
	public void setCo_sensor(gas_sensor_t co_sensor) {
		 this.co_sensor = co_sensor;
	}
	public gas_sensor_t getCo_sensor() {
		return this.co_sensor;
	}
	public void setCh4_sensor(gas_sensor_t ch4_sensor) {
		 this.ch4_sensor = ch4_sensor;
	}
	public gas_sensor_t getCh4_sensor() {
		return this.ch4_sensor;
	}
	public void setWater_flow_sensor(sensor_t water_flow_sensor) {
		 this.water_flow_sensor = water_flow_sensor;
	}
	public sensor_t getWater_flow_sensor() {
		return this.water_flow_sensor;
	}
	public void setAlarm(device_t alarm) {
		 this.alarm = alarm;
	}
	public device_t getAlarm() {
		return this.alarm;
	}
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}


	//--------------------- port getters
	public mine_water_level_control_system_iprotocol_tConjPort getMine_water_level_control_system_iport (){
		return this.mine_water_level_control_system_iport;
	}
	public switch_protocol_tConjPort getMine_water_level_control_system_user_port (){
		return this.mine_water_level_control_system_user_port;
	}
	public test_protocol_tConjPort getMine_water_level_control_system_test_port (){
		return this.mine_water_level_control_system_test_port;
	}
	public water_level_sensors_update_protocol_tConjPort getMine_water_level_control_system_update_port (){
		return this.mine_water_level_control_system_update_port;
	}
	public PTimerConjPort getTimer_access_point (){
		return this.timer_access_point;
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
	public static final int STATE_step0 = 2;
	public static final int STATE_MAX = 3;
	
	/* transition chains */
	public static final int CHAIN_TRANS_INITIAL_TO__step0 = 1;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_mine_water_level_control_system_test_port__activated = IFITEM_mine_water_level_control_system_test_port + EVT_SHIFT*test_protocol_t.OUT_activated;
	public static final int TRIG_mine_water_level_control_system_test_port__checking = IFITEM_mine_water_level_control_system_test_port + EVT_SHIFT*test_protocol_t.OUT_checking;
	public static final int TRIG_mine_water_level_control_system_test_port__alarm = IFITEM_mine_water_level_control_system_test_port + EVT_SHIFT*test_protocol_t.OUT_alarm;
	public static final int TRIG_timer_access_point__timeout = IFITEM_timer_access_point + EVT_SHIFT*PTimer.OUT_timeout;
	public static final int TRIG_timer_access_point__internalTimer = IFITEM_timer_access_point + EVT_SHIFT*PTimer.OUT_internalTimer;
	public static final int TRIG_timer_access_point__internalTimeout = IFITEM_timer_access_point + EVT_SHIFT*PTimer.OUT_internalTimeout;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"step0"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	protected void entry_step0() {
		System.out.println (
			"STEP 0"
		);
	}
	
	/* Action Codes */
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
			case base_actor_t.CHAIN_TRANS_INITIAL_TO__step0:
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
		int chain__et = base_actor_t.CHAIN_TRANS_INITIAL_TO__step0;
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
			    case STATE_step0:
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
