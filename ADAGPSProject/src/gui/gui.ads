with System;

with Gtk.Button;

with devices;
with mine_water_level_control_system;

package gui is
	task type gui_controller_t (
		pump                : access devices.device_t;
		alarm               : access devices.device_t;
		co_sensor           : access devices.sensor_t;
		o_sensor            : access devices.sensor_t;
		ch4_sensor          : access devices.sensor_t;
		water_level_sensors : access devices.water_level_sensors_t;
		water_flow_sensor   : access devices.water_flow_sensor_t;
		top                 : access mine_water_level_control_system.top_t
	) is
		pragma Priority ( System.Priority'First );

		entry finished;
	end gui_controller_t;

end gui;
