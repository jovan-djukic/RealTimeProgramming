with GNATCOLL.Traces;

with constants;
with devices;
with mine_water_level_control_system;
with gui;

-- for debugging
with Ada.Text_IO;

procedure main is
	pump           : access devices.device_t;
	alarm          : access devices.device_t;
	gui_controller : access gui.gui_controller_t;
	top            : access mine_water_level_control_system.top_t;
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
		pump  => pump,
		alarm => alarm,
		top   => top
	);	

	-- wait for all tasks to finish and finalize logging
	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "GUI task finished"
	);

	gui_controller.stop;

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Mine water level control system task finished"
	);
	
	top.stop;

	GNATCOLL.Traces.Trace (
		Handle  => constants.log.main.stream,
		Message => "Main ending"
	);

	GNATCOLL.Traces.Finalize;
end main;
