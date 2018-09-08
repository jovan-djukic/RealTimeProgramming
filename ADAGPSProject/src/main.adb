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
	co_sensor : access devices.sensor_t;

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
		intital_value           => constants.system.sensors.co_sensor.initial_value'Access,
		threshold               => constants.system.sensors.co_sensor.threshold'Access,
		detects_above_threshold => constants.system.sensors.co_sensor.detects_above_threshold
	);
	
	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Instantiating system"
	);
	
	top := new mine_water_level_control_system.top_t (
		pump  => pump,
		alarm => alarm
	);

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Instantiating GUI"
	);
	
	gui_controller := new gui.gui_controller_t (
		pump      => pump,
		alarm     => alarm,
		co_sensor => co_sensor,
		top       => top
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
