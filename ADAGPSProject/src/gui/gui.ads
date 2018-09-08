with Gtk.Button;

with devices;
with mine_water_level_control_system;

package gui is
	task type gui_controller_t (
		pump      : access devices.device_t;
		alarm     : access devices.device_t;
		co_sensor : access devices.sensor_t;
		top       : access mine_water_level_control_system.top_t
	) is
		entry finished;
	end gui_controller_t;

end gui;
