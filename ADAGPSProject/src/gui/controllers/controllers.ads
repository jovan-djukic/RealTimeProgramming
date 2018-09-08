with devices;
with Gtk.Button;
with Gtk.Progress_Bar;
with Gdk.RGBA;

package controllers is
	-- device controller, when state changes it updates button
	task type device_state_controller_t (
		device    : access devices.device_t;
		button    : Gtk.Button.Gtk_Button;
		on_color  : access constant Gdk.RGBA.Gdk_RGBA;
		off_color : access constant Gdk.RGBA.Gdk_RGBA
	)is
	end device_state_controller_t;

	-- sensor controller, when state changes it updates progress bar 
	task type gas_sensor_state_controller_t (
		sensor                    : access devices.sensor_t;
		progress_bar              : Gtk.Progress_Bar.Gtk_Progress_Bar;
		multiplication_factor     : Integer;
		state_normal_color        : access constant Gdk.RGBA.Gdk_RGBA;
		threshold_breached_color  : access constant Gdk.RGBA.Gdk_RGBA;
		read_error_occurred_color : access constant Gdk.RGBA.Gdk_RGBA
	) is
	end gas_sensor_state_controller_t;
end controllers;
