pragma Task_Dispatching_Policy ( FIFO_Within_Priorities );
pragma Locking_Policy ( Ceiling_Locking );
pragma Queuing_Policy ( Priority_Queuing );

with GNATCOLL.Traces;
with Gtk.Button;

with devices;
with constants;
with mine_water_level_control_system;
with gui;

-- for debugging
with Ada.Text_IO;

-- set task dispatching policy 
procedure main is
	-- devices
	pump  : access devices.device_t;
	alarm : access devices.device_t;

	-- sensors
	-- gas sensors
	co_sensor  : access devices.sensor_t;
	o_sensor   : access devices.sensor_t;
	ch4_sensor : access devices.sensor_t;

	-- water sensors
	water_level_sensors : access devices.water_level_sensors_t;
	water_flow_sensor   : access devices.water_flow_sensor_t;

	gui_controller   : access gui.gui_controller_t;
	top              : access mine_water_level_control_system.top_t;

begin
	-- initialize logging
	GNATCOLL.Traces.Parse_Config (
		Config => constants.log.configuration
	);


	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Main starting"
	);

	-- allocate devices
	pump  := new devices.device_t;
	alarm := new devices.device_t;
	
	-- allocate sensors
	co_sensor := new devices.sensor_t (
		intital_value           => constants.mine_water_level_control_system.sensors.gas_sensors.co.initial_value'Access,
		threshold               => constants.mine_water_level_control_system.sensors.gas_sensors.co.threshold'Access,
		maximum_value           => constants.mine_water_level_control_system.sensors.gas_sensors.co.maximum_value'Access,
		detects_above_threshold => constants.mine_water_level_control_system.sensors.gas_sensors.co.detects_above_threshold
	);

	o_sensor := new devices.sensor_t (
		intital_value           => constants.mine_water_level_control_system.sensors.gas_sensors.o.initial_value'Access,
		threshold               => constants.mine_water_level_control_system.sensors.gas_sensors.o.threshold'Access,
		maximum_value           => constants.mine_water_level_control_system.sensors.gas_sensors.o.maximum_value'Access,
		detects_above_threshold => constants.mine_water_level_control_system.sensors.gas_sensors.o.detects_above_threshold
	);

	ch4_sensor := new devices.sensor_t (
		intital_value           => constants.mine_water_level_control_system.sensors.gas_sensors.ch4.initial_value'Access,
		threshold               => constants.mine_water_level_control_system.sensors.gas_sensors.ch4.threshold'Access,
		maximum_value           => constants.mine_water_level_control_system.sensors.gas_sensors.ch4.maximum_value'Access,
		detects_above_threshold => constants.mine_water_level_control_system.sensors.gas_sensors.ch4.detects_above_threshold
	);

	water_level_sensors := new devices.water_level_sensors_t (
		initial_water_level        => constants.mine_water_level_control_system.sensors.water_level_sensors.initial_water_level'Access,
		low_water_level_threshold  => constants.mine_water_level_control_system.sensors.water_level_sensors.low_water_level_threshold'Access,
		high_water_level_threshold => constants.mine_water_level_control_system.sensors.water_level_sensors.high_water_level_threshold'Access
	);

	water_flow_sensor := new devices.water_flow_sensor_t;
	
	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Instantiating system"
	);
	
	top := new mine_water_level_control_system.top_t (
		-- devices
		pump                => pump,
		alarm               => alarm,
		co_sensor           => co_sensor,
		o_sensor            => o_sensor,
		ch4_sensor          => ch4_sensor,
		water_level_sensors => water_level_sensors,
		water_flow_sensor   => water_flow_sensor,

		-- top priority
		priority => constants.mine_water_level_control_system.top.priority,

		-- controller priorities 
		pump_controller_priority                => constants.mine_water_level_control_system.top.controllers.pump.priority,
		alarm_controller_priority               => constants.mine_water_level_control_system.top.controllers.alarm.priority,
		co_sensor_controller_priority           => constants.mine_water_level_control_system.top.controllers.co.priority,
		o_sensor_controller_priority            => constants.mine_water_level_control_system.top.controllers.o.priority,
		ch4_sensor_controller_priority          => constants.mine_water_level_control_system.top.controllers.ch4.priority,
		water_level_sensors_controller_priority => constants.mine_water_level_control_system.top.controllers.water_level_sensors.priority,
		water_flow_sensor_controller_priority   => constants.mine_water_level_control_system.top.controllers.water_flow_sensor.priority,

		-- sensor controller parameters
		co_sensor_controller_read_error_occurred_count_threshold  => constants.mine_water_level_control_system.top.controllers.co.read_error_occurred_count_threshold,
		co_sensor_controller_period_in_ms                         => constants.mine_water_level_control_system.top.controllers.co.period_in_ms,
		co_sensor_controller_deadline_in_ms                       => constants.mine_water_level_control_system.top.controllers.co.dealine_in_ms,
		o_sensor_controller_read_error_occurred_count_threshold   => constants.mine_water_level_control_system.top.controllers.o.read_error_occurred_count_threshold,
		o_sensor_controller_period_in_ms                          => constants.mine_water_level_control_system.top.controllers.o.period_in_ms,
		o_sensor_controller_deadline_in_ms                        => constants.mine_water_level_control_system.top.controllers.o.dealine_in_ms,
		ch4_sensor_controller_read_error_occurred_count_threshold => constants.mine_water_level_control_system.top.controllers.ch4.read_error_occurred_count_threshold,
		ch4_sensor_controller_period_in_ms                        => constants.mine_water_level_control_system.top.controllers.ch4.period_in_ms,
		ch4_sensor_controller_deadline_in_ms                      => constants.mine_water_level_control_system.top.controllers.ch4.dealine_in_ms,
		water_level_sensors_controller_deadline_in_ms             => constants.mine_water_level_control_system.top.controllers.water_level_sensors.dealine_in_ms,
		water_flow_sensor_controller_period_in_ms                 => constants.mine_water_level_control_system.top.controllers.water_flow_sensor.period_in_ms,
		water_flow_sensor_controller_deadline_in_ms               => constants.mine_water_level_control_system.top.controllers.water_flow_sensor.dealine_in_ms,
		water_flow_sensor_controller_number_of_activations        => constants.mine_water_level_control_system.top.controllers.water_flow_sensor.number_of_activations,

		-- top log stream
		stream => constants.log.mine_water_level_control_system.top.stream,
		
		-- controller log streams
		co_sensor_controller_stream           => constants.log.mine_water_level_control_system.environment_station.co_sensor_controller.stream,
		o_sensor_controller_stream            => constants.log.mine_water_level_control_system.environment_station.o_sensor_controller.stream,
		ch4_sensor_controller_stream          => constants.log.mine_water_level_control_system.environment_station.ch4_sensor_controller.stream,
		water_level_sensors_controller_stream => constants.log.mine_water_level_control_system.environment_station.water_level_sensors_controller.stream,
		water_flow_sensor_controller_stream   => constants.log.mine_water_level_control_system.environment_station.water_flow_sensor_controller.stream
	);

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Instantiating GUI"
	);
	
	gui_controller := new gui.gui_controller_t (
		pump                => pump,
		alarm               => alarm,
		co_sensor           => co_sensor,
		o_sensor            => o_sensor,
		ch4_sensor          => ch4_sensor,
		water_level_sensors => water_level_sensors,
		water_flow_sensor   => water_flow_sensor,
		top                 => top
	);	

	-- wait for all tasks to finish and finalize logging
	-- wait for user to quit
	gui_controller.finished;

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "GUI task finished"
	);

	top.stop;

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Mine water level control system task finished"
	);
	
	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Main ending"
	);

	GNATCOLL.Traces.Finalize;
end main;
