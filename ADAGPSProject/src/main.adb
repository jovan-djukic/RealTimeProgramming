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

procedure main is
	-- devices
	pump  : access devices.device_t;
	alarm : access devices.device_t;

	-- sensors
	-- gas sensors
	co_sensor  : access devices.gas_sensor_t;
	o_sensor   : access devices.gas_sensor_t;
	ch4_sensor : access devices.gas_sensor_t;

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
		Handle  => constants.log.main.trace_handle,
		Message => "Main starting"
	);

	-- allocate devices
	pump := new devices.device_t (
		name => constants.mine_water_level_control_system.devices.pump.name'Access
	);

	alarm := new devices.device_t (
		name => constants.mine_water_level_control_system.devices.alarm.name'Access
	);
	
	-- allocate sensors
	co_sensor := new devices.gas_sensor_t (
		name                        => constants.mine_water_level_control_system.sensors.gas.co.name'Access,
		conversion_time_in_ms       => constants.mine_water_level_control_system.sensors.gas.co.conversion_time_in_ms,
		initial_value               => constants.mine_water_level_control_system.sensors.gas.co.initial_value'Access,
		initial_read_error_occurred => constants.mine_water_level_control_system.sensors.gas.co.initial_read_error_occured
	);

	o_sensor := new devices.gas_sensor_t (
		name                        => constants.mine_water_level_control_system.sensors.gas.o.name'Access,
		conversion_time_in_ms       => constants.mine_water_level_control_system.sensors.gas.o.conversion_time_in_ms,
		initial_value               => constants.mine_water_level_control_system.sensors.gas.o.initial_value'Access,
		initial_read_error_occurred => constants.mine_water_level_control_system.sensors.gas.o.initial_read_error_occured
	);

	ch4_sensor := new devices.gas_sensor_t (
		name                        => constants.mine_water_level_control_system.sensors.gas.ch4.name'Access,
		conversion_time_in_ms       => constants.mine_water_level_control_system.sensors.gas.ch4.conversion_time_in_ms,
		initial_value               => constants.mine_water_level_control_system.sensors.gas.ch4.initial_value'Access,
		initial_read_error_occurred => constants.mine_water_level_control_system.sensors.gas.ch4.initial_read_error_occured
	);

	water_level_sensors := new devices.water_level_sensors_t (
		name                       => constants.mine_water_level_control_system.sensors.water_level.name'Access,
		initial_water_level        => constants.mine_water_level_control_system.sensors.water_level.initial_water_level'Access,
		low_water_level_threshold  => constants.mine_water_level_control_system.sensors.water_level.low_water_level_threshold'Access,
		high_water_level_threshold => constants.mine_water_level_control_system.sensors.water_level.high_water_level_threshold'Access
	);

	water_flow_sensor := new devices.water_flow_sensor_t (
		name                  => constants.mine_water_level_control_system.sensors.water_flow.name'Access,
		conversion_time_in_ms => constants.mine_water_level_control_system.sensors.water_flow.conversion_time_in_ms,
		initial_water_flowing => constants.mine_water_level_control_system.sensors.water_flow.initial_water_flowing
	);
	
	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.trace_handle,
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
		water_level_sensors_controller_priority => constants.mine_water_level_control_system.top.controllers.water_level.priority,
		water_flow_sensor_controller_priority   => constants.mine_water_level_control_system.top.controllers.water_flow.priority,

		-- sensor controller parameters
		co_sensor_controller_threshold                            => constants.mine_water_level_control_system.top.controllers.co.threshold'Access,
		co_sensor_controller_read_error_occurred_count_threshold  => constants.mine_water_level_control_system.top.controllers.co.read_error_occurred_count_threshold,
		co_sensor_controller_period_in_ms                         => constants.mine_water_level_control_system.top.controllers.co.period_in_ms,
		co_sensor_controller_deadline_in_ms                       => constants.mine_water_level_control_system.top.controllers.co.dealine_in_ms,
		o_sensor_controller_threshold                             => constants.mine_water_level_control_system.top.controllers.o.threshold'Access,
		o_sensor_controller_read_error_occurred_count_threshold   => constants.mine_water_level_control_system.top.controllers.o.read_error_occurred_count_threshold,
		o_sensor_controller_period_in_ms                          => constants.mine_water_level_control_system.top.controllers.o.period_in_ms,
		o_sensor_controller_deadline_in_ms                        => constants.mine_water_level_control_system.top.controllers.o.dealine_in_ms,
		ch4_sensor_controller_threshold                           => constants.mine_water_level_control_system.top.controllers.ch4.threshold'Access,
		ch4_sensor_controller_read_error_occurred_count_threshold => constants.mine_water_level_control_system.top.controllers.ch4.read_error_occurred_count_threshold,
		ch4_sensor_controller_period_in_ms                        => constants.mine_water_level_control_system.top.controllers.ch4.period_in_ms,
		ch4_sensor_controller_deadline_in_ms                      => constants.mine_water_level_control_system.top.controllers.ch4.dealine_in_ms,
		water_level_sensors_controller_deadline_in_ms             => constants.mine_water_level_control_system.top.controllers.water_level.dealine_in_ms,
		water_flow_sensor_controller_period_in_ms                 => constants.mine_water_level_control_system.top.controllers.water_flow.period_in_ms,
		water_flow_sensor_controller_deadline_in_ms               => constants.mine_water_level_control_system.top.controllers.water_flow.dealine_in_ms,
		water_flow_sensor_controller_number_of_activations        => constants.mine_water_level_control_system.top.controllers.water_flow.number_of_activations,

		-- top log trace_handle
		trace_handle => constants.log.mine_water_level_control_system.top.trace_handle,
		
		-- controller log trace_handles
		co_sensor_controller_trace_handle           => constants.log.mine_water_level_control_system.environment_station.co_sensor_controller.trace_handle,
		o_sensor_controller_trace_handle            => constants.log.mine_water_level_control_system.environment_station.o_sensor_controller.trace_handle,
		ch4_sensor_controller_trace_handle          => constants.log.mine_water_level_control_system.environment_station.ch4_sensor_controller.trace_handle,
		water_level_sensors_controller_trace_handle => constants.log.mine_water_level_control_system.environment_station.water_level_sensors_controller.trace_handle,
		water_flow_sensor_controller_trace_handle   => constants.log.mine_water_level_control_system.environment_station.water_flow_sensor_controller.trace_handle
	);

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.trace_handle,
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
		Handle  => constants.log.main.trace_handle,
		Message => "GUI task finished"
	);

	top.stop;

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.trace_handle,
		Message => "Mine water level control system task finished"
	);
	
	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.trace_handle,
		Message => "Main ending"
	);

	GNATCOLL.Traces.Finalize;
end main;
