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
	co_sensor  : access devices.sensor_t;
	o_sensor   : access devices.sensor_t;
	ch4_sensor : access devices.sensor_t;

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
		intital_value           => constants.system.sensors.co.initial_value'Access,
		threshold               => constants.system.sensors.co.threshold'Access,
		maximum_value           => constants.system.sensors.co.maximum_value'Access,
		detects_above_threshold => constants.system.sensors.co.detects_above_threshold
	);

	o_sensor := new devices.sensor_t (
		intital_value           => constants.system.sensors.o.initial_value'Access,
		threshold               => constants.system.sensors.o.threshold'Access,
		maximum_value           => constants.system.sensors.o.maximum_value'Access,
		detects_above_threshold => constants.system.sensors.o.detects_above_threshold
	);

	ch4_sensor := new devices.sensor_t (
		intital_value           => constants.system.sensors.ch4.initial_value'Access,
		threshold               => constants.system.sensors.ch4.threshold'Access,
		maximum_value           => constants.system.sensors.ch4.maximum_value'Access,
		detects_above_threshold => constants.system.sensors.ch4.detects_above_threshold
	);
	
	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Instantiating system"
	);
	
	top := new mine_water_level_control_system.top_t (
		-- devices
		pump       => pump,
		alarm      => alarm,
		co_sensor  => co_sensor,
		o_sensor   => o_sensor,
		ch4_sensor => ch4_sensor,
		
		-- sensor controller parameters
		co_sensor_controller_read_error_occurred_count_threshold  => constants.system.controllers.co.read_error_occurred_count_threshold,
		co_sensor_controller_period_in_ms                         => constants.system.controllers.co.period_in_ms,
		co_sensor_controller_deadline_in_ms                       => constants.system.controllers.co.dealine_in_ms,
		o_sensor_controller_read_error_occurred_count_threshold   => constants.system.controllers.o.read_error_occurred_count_threshold,
		o_sensor_controller_period_in_ms                          => constants.system.controllers.o.period_in_ms,
		o_sensor_controller_deadline_in_ms                        => constants.system.controllers.o.dealine_in_ms,
		ch4_sensor_controller_read_error_occurred_count_threshold => constants.system.controllers.ch4.read_error_occurred_count_threshold,
		ch4_sensor_controller_period_in_ms                        => constants.system.controllers.ch4.period_in_ms,
		ch4_sensor_controller_deadline_in_ms                      => constants.system.controllers.ch4.dealine_in_ms,

		-- log streams
		top_stream                   => constants.log.mine_water_level_control_system.top.stream,
		pump_controller_stream       => constants.log.mine_water_level_control_system.pump_station.pump_controller.stream,
		alarm_controller_stream      => constants.log.mine_water_level_control_system.alarm_station.alarm_controller.stream,
		co_sensor_controller_stream  => constants.log.mine_water_level_control_system.environment_station.co_sensor_controller.stream,
		o_sensor_controller_stream   => constants.log.mine_water_level_control_system.environment_station.o_sensor_controller.stream,
		ch4_sensor_controller_stream => constants.log.mine_water_level_control_system.environment_station.ch4_sensor_controller.stream
	);

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Instantiating GUI"
	);
	
	gui_controller := new gui.gui_controller_t (
		pump       => pump,
		alarm      => alarm,
		co_sensor  => co_sensor,
		o_sensor   => o_sensor,
		ch4_sensor => ch4_sensor,
		top        => top
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
