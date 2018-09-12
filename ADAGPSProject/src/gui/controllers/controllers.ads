with System;

with Gtk.Button;
with Gtk.Progress_Bar;
with Gdk.RGBA;

with Gtk.Label;
with Gtk.Color_Button;

with devices;

package controllers is

	-- device controller, when state changes it updates button
	task type device_state_controller_t (
		device    : access devices.device_t;
		button    : Gtk.Button.Gtk_Button;
		on_color  : access constant Gdk.RGBA.Gdk_RGBA;
		off_color : access constant Gdk.RGBA.Gdk_RGBA
	)is
		pragma Priority ( System.Priority'First ); 
	end device_state_controller_t;

	-- sensor controller, when state changes it updates progress bar 
	task type gas_sensor_state_controller_t (
		gas_sensor                : access devices.gas_sensor_t;
		threshold                 : access constant Float;
		maximum_value             : access constant Float;
		detects_above_threshold   : Boolean;
		progress_bar              : Gtk.Progress_Bar.Gtk_Progress_Bar;
		label                     : Gtk.Label.Gtk_Label;
		color_button              : Gtk.Color_Button.Gtk_Color_Button;
		state_normal_color        : access constant Gdk.RGBA.Gdk_RGBA;
		threshold_breached_color  : access constant Gdk.RGBA.Gdk_RGBA;
		read_error_occurred_color : access constant Gdk.RGBA.Gdk_RGBA
	) is
		pragma Priority ( System.Priority'First ); 
	end gas_sensor_state_controller_t;

	-- water level controller
	protected type water_tank_t (
		initital_water_level : access constant Float;
		maximum_water_level  : access constant Float
	) is
		procedure set_water_level ( 
			new_water_level : Float
		);
		
		entry wait_for_change (
			out_water_level         : out Float;
			out_maximum_water_level : out Float
		);
	private
		water_level : Float   := initital_water_level.all;
		changed     : Boolean := True;
	end water_tank_t;	

	task type water_flow_sensor_state_controller_t (
		water_flow_sensor : access devices.water_flow_sensor_t;
		button            : Gtk.Button.Gtk_Button;
		flowing_color     : access constant Gdk.RGBA.Gdk_RGBA;
		not_flowing_color : access constant Gdk.RGBA.Gdk_RGBA
	) is
		pragma Priority ( System.Priority'First ); 
	end water_flow_sensor_state_controller_t;

	task type water_level_sensors_state_controller_t (
		water_tank                      : access water_tank_t;
		low_water_level_threshold		: access constant Float;
		high_water_level_threshold		: access constant Float;
		progress_bar                    : Gtk.Progress_Bar.Gtk_Progress_Bar;
		label                           : Gtk.Label.Gtk_Label;
		color_button                    : Gtk.Color_Button.Gtk_Color_Button;
		state_normal_color              : access constant Gdk.RGBA.Gdk_RGBA;
		low_water_level_breached_color  : access constant Gdk.RGBA.Gdk_RGBA;
		high_water_level_breached_color : access constant Gdk.RGBA.Gdk_RGBA
	) is
		pragma Priority ( System.Priority'First ); 
	end water_level_sensors_state_controller_t;
end controllers;
