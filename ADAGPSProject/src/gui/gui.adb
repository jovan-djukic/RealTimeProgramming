with Gtk.Main;
with Gtk.Window;
with Gtk.Widget;
with Gtk.Grid;
with Gtk.Button;
with Gtk.Progress_Bar;
with Gtk.Color_Button;
with Gtk.Label;
with Gtk.Enums;

with Gdk.RGBA;
with Glib;
with GNATCOLL.Traces;

with Ada.Task_Identification;

with controllers;
with constants;
with devices;

package body gui is
	-- device and sensor state widgets types and attach procedures

	-- attach device state button
	procedure attach_state_button (
		grid   : in Gtk.Grid.Gtk_Grid;
		left   : in Glib.Gint;
		top    : in Glib.Gint;
		width  : in Glib.Gint;
		height : in Glib.Gint;
		label  : in String;
		button : out Gtk.Button.Gtk_Button
	) is
	begin
		-- create buttons
		Gtk.Button.Gtk_New (
			Button => button,
			Label  => label 
		);		

		button.Set_Sensitive (
			Sensitive => False
		);

		-- attach to grid
		grid.Attach (
			Child  => button,
			Left   => left,
			Top    => top,
			Width  => width,
			Height => height
		);
	end attach_state_button;

	type device_state_widgets_t is
	record
		button  : Gtk.Button.Gtk_Button;
	end record;

	-- attach device state widgets
	procedure attach_device_state_widgets (
		grid                 : in Gtk.Grid.Gtk_Grid;
		button_left          : in Glib.Gint;
		button_top           : in Glib.Gint;
		button_width         : in Glib.Gint;
		button_height        : in Glib.Gint;
		button_label         : in String;
		device_state_widgets : out device_state_widgets_t
	) is
	begin
		attach_state_button (
			grid   => grid,
			left   => button_left,
			top    => button_top,
			width  => button_width,
			height => button_height,
			label  => button_label,
			button => device_state_widgets.button
		);
	end attach_device_state_widgets;
	
	type device_state_widgets_list_t is
	record
		pump  : device_state_widgets_t;
		alarm : device_state_widgets_t;
	end record;

	-- attach device state widgets list
	procedure attach_device_state_widgets_list (
		grid                      : in Gtk.Grid.Gtk_Grid;
		device_state_widgets_list : out device_state_widgets_list_t
	) is
	begin
		attach_device_state_widgets (
			grid                 => grid,
			button_left          => constants.gui.state_widgets.device_state_widgets.pump.left,
			button_top           => constants.gui.state_widgets.device_state_widgets.pump.top,
			button_width         => constants.gui.state_widgets.device_state_widgets.pump.width,
			button_height        => constants.gui.state_widgets.device_state_widgets.pump.height,
			button_label         => constants.gui.state_widgets.device_state_widgets.pump.label,
			device_state_widgets => device_state_widgets_list.pump
		);

		attach_device_state_widgets (
			grid                 => grid,
			button_left          => constants.gui.state_widgets.device_state_widgets.alarm.left,
			button_top           => constants.gui.state_widgets.device_state_widgets.alarm.top,
			button_width         => constants.gui.state_widgets.device_state_widgets.alarm.width,
			button_height        => constants.gui.state_widgets.device_state_widgets.alarm.height,
			button_label         => constants.gui.state_widgets.device_state_widgets.alarm.label,
			device_state_widgets => device_state_widgets_list.alarm
		);
	end attach_device_state_widgets_list;

	procedure attach_gas_sensor_progress_bar (
		grid         : in Gtk.Grid.Gtk_Grid;
		left         : in Glib.Gint;
		top          : in Glib.Gint;
		width        : in Glib.Gint;
		height       : in Glib.Gint;
		orientation  : in Gtk.Enums.Gtk_Orientation;
		inverted     : Boolean;
		progress_bar : out Gtk.Progress_Bar.Gtk_Progress_Bar
	) is
	begin
		-- create progressbar
		Gtk.Progress_Bar.Gtk_New (
			Progress_Bar => progress_bar
		);		

		-- set orientation
		progress_bar.Set_Orientation (
			Orientation => orientation
		);

		-- set inverted
		progress_bar.Set_Inverted (
			Inverted => inverted 
		);

		-- attach to grid
		grid.Attach (
			Child  => progress_bar,
			Left   => left,
			Top    => top,
			Width  => width,
			Height => height
		);
	end attach_gas_sensor_progress_bar;

	procedure attach_gas_sensor_label (
		grid   : in Gtk.Grid.Gtk_Grid;
		left   : in Glib.Gint;
		top    : in Glib.Gint;
		width  : in Glib.Gint;
		height : in Glib.Gint;
		label  : out Gtk.Label.Gtk_Label
	) is
	begin
		-- create label 
		Gtk.Label.Gtk_New (
			Label => label
		);	

		-- attach to grid
		grid.Attach (
			Child  => label,
			Left   => left,
			Top    => top,
			Width  => width,
			Height => height
		);
	end attach_gas_sensor_label;

	procedure attach_gas_sensor_color_button (
		grid         : in Gtk.Grid.Gtk_Grid;
		left         : in Glib.Gint;
		top          : in Glib.Gint;
		width        : in Glib.Gint;
		height       : in Glib.Gint;
		color_button : out Gtk.Color_Button.Gtk_Color_Button
	) is
	begin
		-- create label 
		Gtk.Color_Button.Gtk_New (
			Button => color_button 
		);	

		color_button.Set_Sensitive (
			Sensitive => False
		);

		-- attach to grid
		grid.Attach (
			Child  => color_button,
			Left   => left,
			Top    => top,
			Width  => width,
			Height => height
		);
	end attach_gas_sensor_color_button;
	
	type gas_sensor_state_widgets_t is
	record
		progress_bar : Gtk.Progress_Bar.Gtk_Progress_Bar;
		label        : Gtk.Label.Gtk_Label;
		color_button : Gtk.Color_Button.Gtk_Color_Button;
	end record;

	procedure attach_gas_sensor_state_widgets (
		grid                     : in Gtk.Grid.Gtk_Grid;
		progress_bar_left        : in Glib.Gint;
		progress_bar_top         : in Glib.Gint;
		progress_bar_width       : in Glib.Gint;
		progress_bar_height      : in Glib.Gint;
		label_left               : in Glib.Gint;
		label_top                : in Glib.Gint;
		label_width              : in Glib.Gint;
		label_height             : in Glib.Gint;
		color_button_left        : in Glib.Gint;
		color_button_top         : in Glib.Gint;
		color_button_width       : in Glib.Gint;
		color_button_height      : in Glib.Gint;
		gas_sensor_state_widgets : out gas_sensor_state_widgets_t
	) is
	begin
		attach_gas_sensor_progress_bar (
			grid         => grid,
			left         => progress_bar_left,
			top          => progress_bar_top,
			width        => progress_bar_width,
			height       => progress_bar_height,
			orientation  => Gtk.Enums.Orientation_Horizontal,
			inverted     => False,
			progress_bar => gas_sensor_state_widgets.progress_bar
		);

		attach_gas_sensor_label (
			grid   => grid,
			left   => label_left,
			top    => label_top,
			width  => label_width,
			height => label_height,
			label  => gas_sensor_state_widgets.label
		);

		attach_gas_sensor_color_button (
			grid         => grid,
			left         => color_button_left,
			top          => color_button_top,
			width        => color_button_width,
			height       => color_button_height,
			color_button => gas_sensor_state_widgets.color_button
		);
	end attach_gas_sensor_state_widgets;

	type gas_sensor_state_widgets_list_t is
	record
		co  : gas_sensor_state_widgets_t;
		o   : gas_sensor_state_widgets_t;
		ch4 : gas_sensor_state_widgets_t;
	end record;

	procedure attach_gas_sensor_state_widgets_list (
		grid                          : in Gtk.Grid.Gtk_Grid;
		gas_sensor_state_widgets_list : out gas_sensor_state_widgets_list_t
	) is
	begin
		attach_gas_sensor_state_widgets (
			grid                     => grid,
			progress_bar_left        => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.progress_bar.left,
			progress_bar_top         => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.progress_bar.top,
			progress_bar_width       => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.progress_bar.width,
			progress_bar_height      => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.progress_bar.height,
			label_left               => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.label.left,
			label_top                => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.label.top,
			label_width              => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.label.width,
			label_height             => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.label.height,
			color_button_left        => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.color_button.left,
			color_button_top         => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.color_button.top,
			color_button_width       => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.color_button.width,
			color_button_height      => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.co.color_button.height,
			gas_sensor_state_widgets => gas_sensor_state_widgets_list.co
		);

		attach_gas_sensor_state_widgets (
			grid                     => grid,
			progress_bar_left        => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.progress_bar.left,
			progress_bar_top         => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.progress_bar.top,
			progress_bar_width       => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.progress_bar.width,
			progress_bar_height      => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.progress_bar.height,
			label_left               => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.label.left,
			label_top                => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.label.top,
			label_width              => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.label.width,
			label_height             => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.label.height,
			color_button_left        => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.color_button.left,
			color_button_top         => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.color_button.top,
			color_button_width       => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.color_button.width,
			color_button_height      => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.o.color_button.height,
			gas_sensor_state_widgets => gas_sensor_state_widgets_list.o
		);

		attach_gas_sensor_state_widgets (
			grid                     => grid,
			progress_bar_left        => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.progress_bar.left,
			progress_bar_top         => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.progress_bar.top,
			progress_bar_width       => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.progress_bar.width,
			progress_bar_height      => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.progress_bar.height,
			label_left               => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.label.left,
			label_top                => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.label.top,
			label_width              => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.label.width,
			label_height             => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.label.height,
			color_button_left        => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.color_button.left,
			color_button_top         => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.color_button.top,
			color_button_width       => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.color_button.width,
			color_button_height      => constants.gui.state_widgets.sensor_state_widgets.gas_sensors.ch4.color_button.height,
			gas_sensor_state_widgets => gas_sensor_state_widgets_list.ch4
		);
	end attach_gas_sensor_state_widgets_list;

	type water_level_sensors_state_widgets_t is
	record
		progress_bar : Gtk.Progress_Bar.Gtk_Progress_Bar;
		label        : Gtk.Label.Gtk_Label;
		color_button : Gtk.Color_Button.Gtk_Color_Button;
	end record;

	procedure attach_water_level_sensors_state_widgets (
		grid                              : in Gtk.Grid.Gtk_Grid;
		water_level_sensors_state_widgets : out water_level_sensors_state_widgets_t
	) is
	begin
		attach_gas_sensor_progress_bar (
			grid         => grid,
			left         => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.progress_bar.left,
			top          => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.progress_bar.top,
			width        => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.progress_bar.width,
			height       => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.progress_bar.height,
			orientation  => Gtk.Enums.Orientation_Vertical,
			inverted     => True,
			progress_bar => water_level_sensors_state_widgets.progress_bar
		);

		attach_gas_sensor_label (
			grid   => grid,
			left   => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.label.left,
			top    => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.label.top,
			width  => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.label.width,
			height => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.label.height,
			label  => water_level_sensors_state_widgets.label
		);

		attach_gas_sensor_color_button (
			grid         => grid,
			left         => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.color_button.left,
			top          => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.color_button.top,
			width        => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.color_button.width,
			height       => constants.gui.state_widgets.sensor_state_widgets.water_level_sensors.color_button.height,
			color_button => water_level_sensors_state_widgets.color_button
		);
	end attach_water_level_sensors_state_widgets;
	
	type water_flow_sensor_state_widgets_t is
	record
		button : Gtk.Button.Gtk_Button;
	end record;

	procedure attach_water_flow_sensor_state_widgets (
		grid                            : in Gtk.Grid.Gtk_Grid;
		water_flow_sensor_state_widgets : out water_flow_sensor_state_widgets_t
	) is
	begin
		attach_state_button (
			grid   => grid,
			left   => constants.gui.state_widgets.sensor_state_widgets.water_flow_sensor.left, 
			top    => constants.gui.state_widgets.sensor_state_widgets.water_flow_sensor.top,
			width  => constants.gui.state_widgets.sensor_state_widgets.water_flow_sensor.width,
			height => constants.gui.state_widgets.sensor_state_widgets.water_flow_sensor.height,
			label  => constants.gui.state_widgets.sensor_state_widgets.water_flow_sensor.label,
			button => water_flow_sensor_state_widgets.button
		);
	end attach_water_flow_sensor_state_widgets;

	type sensor_state_widgets_list_t is
	record
		gas_sensors         : gas_sensor_state_widgets_list_t;
		water_level_sensors : water_level_sensors_state_widgets_t;
		water_flow_sensor   : water_flow_sensor_state_widgets_t;
	end record;

	procedure attach_sensor_state_widgets_list (
		grid                      : in Gtk.Grid.Gtk_Grid;
		sensor_state_widgets_list : out sensor_state_widgets_list_t
	) is
	begin
		attach_gas_sensor_state_widgets_list (
			grid                          => grid,
			gas_sensor_state_widgets_list => sensor_state_widgets_list.gas_sensors
		);

		attach_water_level_sensors_state_widgets (
			grid                              => grid,
			water_level_sensors_state_widgets => sensor_state_widgets_list.water_level_sensors
		);

		attach_water_flow_sensor_state_widgets (
			grid                            => grid,
			water_flow_sensor_state_widgets => sensor_state_widgets_list.water_flow_sensor
		);
	end attach_sensor_state_widgets_list;

	type state_widgets_t is
	record
		devices : device_state_widgets_list_t;
		sensors : sensor_state_widgets_list_t;
	end record;

	procedure attach_state_widgets (
		grid          : in Gtk.Grid.Gtk_Grid;
		state_widgets : out state_widgets_t
	) is
	begin
		attach_device_state_widgets_list (
			grid                          => grid,
			device_state_widgets_list => state_widgets.devices
		);

		attach_sensor_state_widgets_list (
			grid                      => grid,
			sensor_state_widgets_list => state_widgets.sensors
		);
	end attach_state_widgets;

	-- attach user buttons to gui
	procedure attach_user_button (
		grid                : in Gtk.Grid.Gtk_Grid;
		left                : in Glib.Gint;
		top                 : in Glib.Gint;
		width               : in Glib.Gint;
		height              : in Glib.Gint;
		label               : in String;
		on_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		button              : out Gtk.Button.Gtk_Button
	) is
	begin
		-- create and add user buttons
		Gtk.Button.Gtk_New (
			Button => button,
			Label  => label
		);	
	
		grid.Attach (
			Child  => button,
			Left   => left,
			Top    => top,
			Width  => width,
			Height => height
		);
	

		button.On_Clicked (
			Call => on_clicked_callback
		);
	end attach_user_button;

	-- user buttons types
	type device_buttons_t is
	record
		turn_on  : Gtk.Button.Gtk_Button;
		turn_off : Gtk.Button.Gtk_Button;
	end record;

	-- attach device buttons 
	procedure attach_device_buttons (
		grid                             : in Gtk.Grid.Gtk_Grid;
		turn_on_button_left              : in Glib.Gint;
		turn_on_button_top               : in Glib.Gint;
		turn_on_button_width             : in Glib.Gint;
		turn_on_button_height            : in Glib.Gint;
		turn_on_button_label             : in String;
		turn_on_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		turn_off_button_left             : in Glib.Gint;
		turn_off_button_top              : in Glib.Gint;
		turn_off_button_width            : in Glib.Gint;
		turn_off_button_height           : in Glib.Gint;
		turn_off_button_label            : in String;
		turn_off_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		device_buttons                   : out device_buttons_t
	) is
	begin
		attach_user_button (
			grid                => grid, 
			left                => turn_on_button_left,
			top                 => turn_on_button_top,
			width               => turn_on_button_width,
			height              => turn_on_button_height,
			label               => turn_on_button_label,
			on_clicked_callback => turn_on_button_clicked_callback, 
			button              => device_buttons.turn_on
		);

		attach_user_button (
			grid                => grid, 
			left                => turn_off_button_left,
			top                 => turn_off_button_top,
			width               => turn_off_button_width,
			height              => turn_off_button_height,
			label               => turn_off_button_label,
			on_clicked_callback => turn_off_button_clicked_callback, 
			button              => device_buttons.turn_off
		);
	end attach_device_buttons;


	type device_buttons_list_t is
	record
		pump : device_buttons_t; 
	end record;

	procedure attach_device_buttons_list (
		grid                                  : in Gtk.Grid.Gtk_Grid;
		pump_turn_on_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		pump_turn_off_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		device_buttons_list                   : out device_buttons_list_t
	) is
	begin
		attach_device_buttons (
			grid                             => grid,
			turn_on_button_left              => constants.gui.user_buttons.devices.pump.turn_on.left,
			turn_on_button_top               => constants.gui.user_buttons.devices.pump.turn_on.top,
			turn_on_button_width             => constants.gui.user_buttons.devices.pump.turn_on.width,
			turn_on_button_height            => constants.gui.user_buttons.devices.pump.turn_on.height,
			turn_on_button_label             => constants.gui.user_buttons.devices.pump.turn_on.label,
			turn_on_button_clicked_callback  => pump_turn_on_button_clicked_callback,
			turn_off_button_left             => constants.gui.user_buttons.devices.pump.turn_off.left,
			turn_off_button_top              => constants.gui.user_buttons.devices.pump.turn_off.top,
			turn_off_button_width            => constants.gui.user_buttons.devices.pump.turn_off.width,
			turn_off_button_height           => constants.gui.user_buttons.devices.pump.turn_off.height,
			turn_off_button_label            => constants.gui.user_buttons.devices.pump.turn_off.label,
			turn_off_button_clicked_callback => pump_turn_off_button_clicked_callback,
			device_buttons                   => device_buttons_list.pump
		);
	end attach_device_buttons_list;

	type gas_sensor_buttons_t is
	record
		increase         : Gtk.Button.Gtk_Button;
		decrease         : Gtk.Button.Gtk_Button;
		error_in_reading : Gtk.Button.Gtk_Button;
	end record;

	procedure attach_gas_sensor_buttons (
		grid                                     : in Gtk.Grid.Gtk_Grid;
		increase_button_left                     : in Glib.Gint;
		increase_button_top                      : in Glib.Gint;
		increase_button_width                    : in Glib.Gint;
		increase_button_height                   : in Glib.Gint;
		increase_button_label                    : in String;
		increase_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		decrease_button_left                     : in Glib.Gint;
		decrease_button_top                      : in Glib.Gint;
		decrease_button_width                    : in Glib.Gint;
		decrease_button_height                   : in Glib.Gint;
		decrease_button_label                    : in String;
		decrease_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		error_in_reading_button_left             : in Glib.Gint;
		error_in_reading_button_top              : in Glib.Gint;
		error_in_reading_button_width            : in Glib.Gint;
		error_in_reading_button_height           : in Glib.Gint;
		error_in_reading_button_label            : in String;
		error_in_reading_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		gas_sensor_buttons 						 : out gas_sensor_buttons_t
	) is
	begin
		attach_user_button (
			grid                => grid, 
			left                => increase_button_left,
			top                 => increase_button_top,
			width               => increase_button_width,
			height              => increase_button_height,
			label               => increase_button_label,
			on_clicked_callback => increase_button_clicked_callback, 
			button              => gas_sensor_buttons.increase
		);

		attach_user_button (
			grid                => grid, 
			left                => decrease_button_left,
			top                 => decrease_button_top,
			width               => decrease_button_width,
			height              => decrease_button_height,
			label               => decrease_button_label,
			on_clicked_callback => decrease_button_clicked_callback, 
			button              => gas_sensor_buttons.decrease
		);

		attach_user_button (
			grid                => grid, 
			left                => error_in_reading_button_left,
			top                 => error_in_reading_button_top,
			width               => error_in_reading_button_width,
			height              => error_in_reading_button_height,
			label               => error_in_reading_button_label,
			on_clicked_callback => error_in_reading_button_clicked_callback, 
			button              => gas_sensor_buttons.error_in_reading
		);
	end attach_gas_sensor_buttons;

	type gas_sensor_buttons_list_t is
	record
		co  : gas_sensor_buttons_t;
		o   : gas_sensor_buttons_t;
		ch4 : gas_sensor_buttons_t;
	end record;

	procedure attach_gas_sensor_buttons_list (
		grid                                         : in Gtk.Grid.Gtk_Grid;
		co_increase_button_clicked_callback          : in Gtk.Button.Cb_Gtk_Button_Void;
		co_decrease_button_clicked_callback          : in Gtk.Button.Cb_Gtk_Button_Void;
		co_error_in_reading_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		o_increase_button_clicked_callback           : in Gtk.Button.Cb_Gtk_Button_Void;
		o_decrease_button_clicked_callback           : in Gtk.Button.Cb_Gtk_Button_Void;
		o_error_in_reading_button_clicked_callback   : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_increase_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_decrease_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_error_in_reading_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		gas_sensor_buttons_list                      : out gas_sensor_buttons_list_t
	) is
	begin
		attach_gas_sensor_buttons (
			grid                                     => grid,
			increase_button_left                     => constants.gui.user_buttons.sensors.gas_sensors.co.increase.left,
			increase_button_top                      => constants.gui.user_buttons.sensors.gas_sensors.co.increase.top,
			increase_button_width                    => constants.gui.user_buttons.sensors.gas_sensors.co.increase.width,
			increase_button_height                   => constants.gui.user_buttons.sensors.gas_sensors.co.increase.height,
			increase_button_label                    => constants.gui.user_buttons.sensors.gas_sensors.co.increase.label,
			increase_button_clicked_callback         => co_increase_button_clicked_callback,
			decrease_button_left                     => constants.gui.user_buttons.sensors.gas_sensors.co.decrease.left, 
			decrease_button_top                      => constants.gui.user_buttons.sensors.gas_sensors.co.decrease.top,
			decrease_button_width                    => constants.gui.user_buttons.sensors.gas_sensors.co.decrease.width,
			decrease_button_height                   => constants.gui.user_buttons.sensors.gas_sensors.co.decrease.height,
			decrease_button_label                    => constants.gui.user_buttons.sensors.gas_sensors.co.decrease.label,
			decrease_button_clicked_callback         => co_decrease_button_clicked_callback,
			error_in_reading_button_left             => constants.gui.user_buttons.sensors.gas_sensors.co.error_in_reading.left, 
			error_in_reading_button_top              => constants.gui.user_buttons.sensors.gas_sensors.co.error_in_reading.top,
			error_in_reading_button_width            => constants.gui.user_buttons.sensors.gas_sensors.co.error_in_reading.width,
			error_in_reading_button_height           => constants.gui.user_buttons.sensors.gas_sensors.co.error_in_reading.height,
			error_in_reading_button_label            => constants.gui.user_buttons.sensors.gas_sensors.co.error_in_reading.label,
			error_in_reading_button_clicked_callback => co_error_in_reading_button_clicked_callback,
			gas_sensor_buttons 						 => gas_sensor_buttons_list.co 
		);

		attach_gas_sensor_buttons (
			grid                                     => grid,
			increase_button_left                     => constants.gui.user_buttons.sensors.gas_sensors.o.increase.left,
			increase_button_top                      => constants.gui.user_buttons.sensors.gas_sensors.o.increase.top,
			increase_button_width                    => constants.gui.user_buttons.sensors.gas_sensors.o.increase.width,
			increase_button_height                   => constants.gui.user_buttons.sensors.gas_sensors.o.increase.height,
			increase_button_label                    => constants.gui.user_buttons.sensors.gas_sensors.o.increase.label,
			increase_button_clicked_callback         => o_increase_button_clicked_callback,
			decrease_button_left                     => constants.gui.user_buttons.sensors.gas_sensors.o.decrease.left, 
			decrease_button_top                      => constants.gui.user_buttons.sensors.gas_sensors.o.decrease.top,
			decrease_button_width                    => constants.gui.user_buttons.sensors.gas_sensors.o.decrease.width,
			decrease_button_height                   => constants.gui.user_buttons.sensors.gas_sensors.o.decrease.height,
			decrease_button_label                    => constants.gui.user_buttons.sensors.gas_sensors.o.decrease.label,
			decrease_button_clicked_callback         => o_decrease_button_clicked_callback,
			error_in_reading_button_left             => constants.gui.user_buttons.sensors.gas_sensors.o.error_in_reading.left, 
			error_in_reading_button_top              => constants.gui.user_buttons.sensors.gas_sensors.o.error_in_reading.top,
			error_in_reading_button_width            => constants.gui.user_buttons.sensors.gas_sensors.o.error_in_reading.width,
			error_in_reading_button_height           => constants.gui.user_buttons.sensors.gas_sensors.o.error_in_reading.height,
			error_in_reading_button_label            => constants.gui.user_buttons.sensors.gas_sensors.o.error_in_reading.label,
			error_in_reading_button_clicked_callback => o_error_in_reading_button_clicked_callback,
			gas_sensor_buttons 						 => gas_sensor_buttons_list.o 
		);

		attach_gas_sensor_buttons (
			grid                                     => grid,
			increase_button_left                     => constants.gui.user_buttons.sensors.gas_sensors.ch4.increase.left,
			increase_button_top                      => constants.gui.user_buttons.sensors.gas_sensors.ch4.increase.top,
			increase_button_width                    => constants.gui.user_buttons.sensors.gas_sensors.ch4.increase.width,
			increase_button_height                   => constants.gui.user_buttons.sensors.gas_sensors.ch4.increase.height,
			increase_button_label                    => constants.gui.user_buttons.sensors.gas_sensors.ch4.increase.label,
			increase_button_clicked_callback         => ch4_increase_button_clicked_callback,
			decrease_button_left                     => constants.gui.user_buttons.sensors.gas_sensors.ch4.decrease.left, 
			decrease_button_top                      => constants.gui.user_buttons.sensors.gas_sensors.ch4.decrease.top,
			decrease_button_width                    => constants.gui.user_buttons.sensors.gas_sensors.ch4.decrease.width,
			decrease_button_height                   => constants.gui.user_buttons.sensors.gas_sensors.ch4.decrease.height,
			decrease_button_label                    => constants.gui.user_buttons.sensors.gas_sensors.ch4.decrease.label,
			decrease_button_clicked_callback         => ch4_decrease_button_clicked_callback,
			error_in_reading_button_left             => constants.gui.user_buttons.sensors.gas_sensors.ch4.error_in_reading.left, 
			error_in_reading_button_top              => constants.gui.user_buttons.sensors.gas_sensors.ch4.error_in_reading.top,
			error_in_reading_button_width            => constants.gui.user_buttons.sensors.gas_sensors.ch4.error_in_reading.width,
			error_in_reading_button_height           => constants.gui.user_buttons.sensors.gas_sensors.ch4.error_in_reading.height,
			error_in_reading_button_label            => constants.gui.user_buttons.sensors.gas_sensors.ch4.error_in_reading.label,
			error_in_reading_button_clicked_callback => ch4_error_in_reading_button_clicked_callback,
			gas_sensor_buttons 						 => gas_sensor_buttons_list.ch4 
		);
	end attach_gas_sensor_buttons_list;

	type water_level_sensors_buttons_t is
	record
		increase : Gtk.Button.Gtk_Button;
		decrease : Gtk.Button.Gtk_Button;
	end record;

	procedure attach_water_level_sensors_buttons (
		grid                             : in Gtk.Grid.Gtk_Grid;
		increase_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		decrease_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_level_sensors_buttons      : out water_level_sensors_buttons_t
	) is
	begin
		attach_user_button (
			grid                => grid, 
			left                => constants.gui.user_buttons.sensors.water_level_sensors.increase.left,
			top                 => constants.gui.user_buttons.sensors.water_level_sensors.increase.top,
			width               => constants.gui.user_buttons.sensors.water_level_sensors.increase.width,
			height              => constants.gui.user_buttons.sensors.water_level_sensors.increase.height,
			label               => constants.gui.user_buttons.sensors.water_level_sensors.increase.label,
			on_clicked_callback => increase_button_clicked_callback, 
			button              => water_level_sensors_buttons.increase
		);

		attach_user_button (
			grid                => grid, 
			left                => constants.gui.user_buttons.sensors.water_level_sensors.decrease.left,
			top                 => constants.gui.user_buttons.sensors.water_level_sensors.decrease.top,
			width               => constants.gui.user_buttons.sensors.water_level_sensors.decrease.width,
			height              => constants.gui.user_buttons.sensors.water_level_sensors.decrease.height,
			label               => constants.gui.user_buttons.sensors.water_level_sensors.decrease.label,
			on_clicked_callback => decrease_button_clicked_callback, 
			button              => water_level_sensors_buttons.decrease
		);
	end attach_water_level_sensors_buttons;

	type water_flow_sensor_buttons_t is
	record
		turn_on  : Gtk.Button.Gtk_Button;
		turn_off : Gtk.Button.Gtk_Button;
	end record;

	procedure attach_water_flow_sensor_buttons (
		grid                             : in Gtk.Grid.Gtk_Grid;
		turn_on_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		turn_off_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_flow_sensor_buttons        : out water_flow_sensor_buttons_t
	) is
	begin
		attach_user_button (
			grid                => grid, 
			left                => constants.gui.user_buttons.sensors.water_flow_sensor.turn_on.left,
			top                 => constants.gui.user_buttons.sensors.water_flow_sensor.turn_on.top,
			width               => constants.gui.user_buttons.sensors.water_flow_sensor.turn_on.width,
			height              => constants.gui.user_buttons.sensors.water_flow_sensor.turn_on.height,
			label               => constants.gui.user_buttons.sensors.water_flow_sensor.turn_on.label,
			on_clicked_callback => turn_on_button_clicked_callback, 
			button              => water_flow_sensor_buttons.turn_on
		);

		attach_user_button (
			grid                => grid, 
			left                => constants.gui.user_buttons.sensors.water_flow_sensor.turn_off.left,
			top                 => constants.gui.user_buttons.sensors.water_flow_sensor.turn_off.top,
			width               => constants.gui.user_buttons.sensors.water_flow_sensor.turn_off.width,
			height              => constants.gui.user_buttons.sensors.water_flow_sensor.turn_off.height,
			label               => constants.gui.user_buttons.sensors.water_flow_sensor.turn_off.label,
			on_clicked_callback => turn_off_button_clicked_callback, 
			button              => water_flow_sensor_buttons.turn_off
		);
	end attach_water_flow_sensor_buttons;

	type sensor_buttons_list_t is
	record
		gas_sensors         : gas_sensor_buttons_list_t;
		water_level_sensors : water_level_sensors_buttons_t;
		water_flow_sensor   : water_flow_sensor_buttons_t;
	end record;

	procedure attach_sensor_buttons_list (
		grid                                         : in Gtk.Grid.Gtk_Grid;
		co_increase_button_clicked_callback          : in Gtk.Button.Cb_Gtk_Button_Void;
		co_decrease_button_clicked_callback          : in Gtk.Button.Cb_Gtk_Button_Void;
		co_error_in_reading_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		o_increase_button_clicked_callback           : in Gtk.Button.Cb_Gtk_Button_Void;
		o_decrease_button_clicked_callback           : in Gtk.Button.Cb_Gtk_Button_Void;
		o_error_in_reading_button_clicked_callback   : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_increase_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_decrease_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_error_in_reading_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_level_increase_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_level_decrease_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_flow_turn_on_button_clicked_callback   : in Gtk.Button.Cb_Gtk_Button_Void;
		water_flow_turn_off_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		sensor_buttons_list                          : out sensor_buttons_list_t
	) is
	begin
		attach_gas_sensor_buttons_list (
			grid                                         => grid,
			co_increase_button_clicked_callback          => co_increase_button_clicked_callback,
			co_decrease_button_clicked_callback          => co_decrease_button_clicked_callback,
			co_error_in_reading_button_clicked_callback  => co_error_in_reading_button_clicked_callback,
			o_increase_button_clicked_callback           => o_increase_button_clicked_callback,
			o_decrease_button_clicked_callback           => o_decrease_button_clicked_callback,
			o_error_in_reading_button_clicked_callback   => o_error_in_reading_button_clicked_callback,
			ch4_increase_button_clicked_callback         => ch4_increase_button_clicked_callback,
			ch4_decrease_button_clicked_callback         => ch4_decrease_button_clicked_callback,
			ch4_error_in_reading_button_clicked_callback => ch4_error_in_reading_button_clicked_callback,
			gas_sensor_buttons_list                      => sensor_buttons_list.gas_sensors
		);

		attach_water_level_sensors_buttons (
			grid                             => grid,
			increase_button_clicked_callback => water_level_increase_button_clicked_callback,
			decrease_button_clicked_callback => water_level_decrease_button_clicked_callback,
			water_level_sensors_buttons      => sensor_buttons_list.water_level_sensors
		);

		attach_water_flow_sensor_buttons (
			grid                             => grid,
			turn_on_button_clicked_callback  => water_flow_turn_on_button_clicked_callback,
			turn_off_button_clicked_callback => water_flow_turn_off_button_clicked_callback,
			water_flow_sensor_buttons        => sensor_buttons_list.water_flow_sensor
		);
	end attach_sensor_buttons_list;

	type user_buttons_t is
	record
		devices : device_buttons_list_t;
		sensors : sensor_buttons_list_t;
	end record;	

	procedure attach_user_buttons (
		grid                                         : in Gtk.Grid.Gtk_Grid;
		pump_turn_on_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		pump_turn_off_button_clicked_callback        : in Gtk.Button.Cb_Gtk_Button_Void;
		co_increase_button_clicked_callback          : in Gtk.Button.Cb_Gtk_Button_Void;
		co_decrease_button_clicked_callback          : in Gtk.Button.Cb_Gtk_Button_Void;
		co_error_in_reading_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		o_increase_button_clicked_callback           : in Gtk.Button.Cb_Gtk_Button_Void;
		o_decrease_button_clicked_callback           : in Gtk.Button.Cb_Gtk_Button_Void;
		o_error_in_reading_button_clicked_callback   : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_increase_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_decrease_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		ch4_error_in_reading_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_level_increase_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_level_decrease_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		water_flow_turn_on_button_clicked_callback   : in Gtk.Button.Cb_Gtk_Button_Void;
		water_flow_turn_off_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		user_buttons                                 : out user_buttons_t
	) is
	begin
		attach_device_buttons_list (
			grid                                  => grid,
			pump_turn_on_button_clicked_callback  => pump_turn_on_button_clicked_callback,
			pump_turn_off_button_clicked_callback => pump_turn_off_button_clicked_callback,
			device_buttons_list                   => user_buttons.devices
		);

		attach_sensor_buttons_list (
			grid                                         => grid,
			co_increase_button_clicked_callback          => co_increase_button_clicked_callback,
			co_decrease_button_clicked_callback          => co_decrease_button_clicked_callback,
			co_error_in_reading_button_clicked_callback  => co_error_in_reading_button_clicked_callback,
			o_increase_button_clicked_callback           => o_increase_button_clicked_callback,
			o_decrease_button_clicked_callback           => o_decrease_button_clicked_callback,
			o_error_in_reading_button_clicked_callback   => o_error_in_reading_button_clicked_callback,
			ch4_increase_button_clicked_callback         => ch4_increase_button_clicked_callback,
			ch4_decrease_button_clicked_callback         => ch4_decrease_button_clicked_callback,
			ch4_error_in_reading_button_clicked_callback => ch4_error_in_reading_button_clicked_callback,
			water_level_increase_button_clicked_callback => water_level_increase_button_clicked_callback,
			water_level_decrease_button_clicked_callback => water_level_decrease_button_clicked_callback,
			water_flow_turn_on_button_clicked_callback   => water_flow_turn_on_button_clicked_callback,
			water_flow_turn_off_button_clicked_callback  => water_flow_turn_off_button_clicked_callback,
			sensor_buttons_list                          => user_buttons.sensors
		);
	end attach_user_buttons;

	--grid initialization
	procedure initialize_grid (
		window : in Gtk.Window.Gtk_Window;
		grid   : out Gtk.Grid.Gtk_Grid
	) is
	begin
		Gtk.Grid.Gtk_New (
			Self => grid
		);
	
		window.Add (
			Widget => grid
		);
	end initialize_grid;

	-- window intialization
	procedure initialize_window (
		title               : in String;
		width               : in Glib.Gint;
		height              : in Glib.Gint;
		resizible           : in Boolean;
		on_destroy_callback : in Gtk.Widget.Cb_Gtk_Widget_Void;
		window              : out Gtk.Window.Gtk_Window
	) is
	begin
		Gtk.Window.Gtk_New (
			Window => window 
		);
	
		window.Set_Title (
			Title => title 
		);
	
		window.Set_Size_Request (
			Width  => width,
			Height => height
		);
	
		window.Set_Resizable (
			Resizable => resizible 
		);
		
		window.On_Destroy (
			Call => on_destroy_callback
		);	
	end initialize_window;

	-- main gui task
	task body gui_controller_t is

		window : Gtk.Window.Gtk_Window;
		grid   : Gtk.Grid.Gtk_Grid;

		user_buttons  : user_buttons_t;
		state_widgets : state_widgets_t;

		water_tank : access controllers.water_tank_t;
		
		-- device state controllers
		pump_controller  : access controllers.device_state_controller_t;
		alarm_controller : access controllers.device_state_controller_t;

		-- sensor state controllers
		co_sensor_controller           : access controllers.gas_sensor_state_controller_t;
		o_sensor_controller            : access controllers.gas_sensor_state_controller_t;
		ch4_sensor_controller          : access controllers.gas_sensor_state_controller_t;
		water_level_sensors_controller : access controllers.water_level_sensors_state_controller_t;
		water_flow_sensor_controller   : access controllers.water_flow_sensor_state_controller_t;

		-- callbacks for window
		procedure quit ( self : access Gtk.Widget.Gtk_Widget_Record'class ) is
		begin
			Gtk.Main.Main_Quit;
		end quit;

		-- button callbacks

		-- pump button callback
		procedure pump_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
		begin

			if ( self = user_buttons.devices.pump.turn_on ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "Pump turn on button clicked"
				);

				top.turn_on;
			else
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "Pump turn off button clicked"
				);

				top.turn_off;
			end if;

		end pump_button_clicked;
		
		-- sensor button callbacks
		procedure co_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
			value : Float;
		begin

			if ( self = user_buttons.sensors.gas_sensors.co.increase ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO increase button clicked"
				);
				value := co_sensor.get_value;
				value := value + co_sensor.get_threshold * constants.gui.user_buttons.sensors.gas_sensors.co.increase.delta_percentage;
				co_sensor.set_value (
					new_value => value
				);	
			elsif ( self = user_buttons.sensors.gas_sensors.co.decrease ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO decrease button clicked"
				);
				value := co_sensor.get_value;
				value := value - co_sensor.get_threshold * constants.gui.user_buttons.sensors.gas_sensors.co.increase.delta_percentage;
				co_sensor.set_value (
					new_value => value
				);	
			elsif ( self = user_buttons.sensors.gas_sensors.co.error_in_reading ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO error in reading button clicked"
				);
				
				co_sensor.set_read_error_occured (
					new_read_error_occurred => not co_sensor.get_read_error_occurred
				);
			end if;
		end co_button_clicked;

		procedure o_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
			value : Float;
		begin

			if ( self = user_buttons.sensors.gas_sensors.o.increase ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO increase button clicked"
				);

				value := o_sensor.get_value;
				value := value + o_sensor.get_threshold * constants.gui.user_buttons.sensors.gas_sensors.o.increase.delta_percentage;
				o_sensor.set_value (
					new_value => value
				);	
			elsif ( self = user_buttons.sensors.gas_sensors.o.decrease ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO decrease button clicked"
				);

				value := o_sensor.get_value;
				value := value - o_sensor.get_threshold * constants.gui.user_buttons.sensors.gas_sensors.o.increase.delta_percentage;
				o_sensor.set_value (
					new_value => value
				);	
			elsif ( self = user_buttons.sensors.gas_sensors.o.error_in_reading ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO error in reading button clicked"
				);
				
				o_sensor.set_read_error_occured (
					new_read_error_occurred => not o_sensor.get_read_error_occurred
				);
			end if;
		end o_button_clicked;

		procedure ch4_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
			value : Float;
		begin

			if ( self = user_buttons.sensors.gas_sensors.ch4.increase ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO increase button clicked"
				);

				value := ch4_sensor.get_value;
				value := value + ch4_sensor.get_threshold * constants.gui.user_buttons.sensors.gas_sensors.ch4.increase.delta_percentage;
				ch4_sensor.set_value (
					new_value => value
				);	
			elsif ( self = user_buttons.sensors.gas_sensors.ch4.decrease ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO decrease button clicked"
				);

				value := ch4_sensor.get_value;
				value := value - ch4_sensor.get_threshold * constants.gui.user_buttons.sensors.gas_sensors.ch4.increase.delta_percentage;
				ch4_sensor.set_value (
					new_value => value
				);	
			elsif ( self = user_buttons.sensors.gas_sensors.ch4.error_in_reading ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "CO error in reading button clicked"
				);
				
				ch4_sensor.set_read_error_occured (
					new_read_error_occurred => not ch4_sensor.get_read_error_occurred
				);
			end if;
		end ch4_button_clicked;

		procedure water_level_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
			value : Float;
		begin

			if ( self = user_buttons.sensors.water_level_sensors.increase ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "H20 increase button clicked"
				);
				
				value := water_tank.get_level;
				value := value + constants.gui.user_buttons.sensors.water_level_sensors.increase.delta_percentage * water_tank.get_maximum_level;
				water_tank.set_level (
					new_level => value
				);
			elsif ( self = user_buttons.sensors.water_level_sensors.decrease ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "H20 decrease button clicked"
				);

				value := water_tank.get_level;
				value := value - constants.gui.user_buttons.sensors.water_level_sensors.decrease.delta_percentage * water_tank.get_maximum_level;
				water_tank.set_level (
					new_level => value
				);
			end if;

			water_level_sensors.update (
				water_level => value
			);
		end water_level_button_clicked;

		procedure water_flow_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
		begin
			if ( self = user_buttons.sensors.water_flow_sensor.turn_on ) then
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "Water flow turn on button clicked"
				);

				water_flow_sensor.set_water_flowing (
					new_water_flowing => True
				);
			else
				GNATCOLL.Traces.Trace (
					Handle  => constants.log.gui.stream,
					Message => "Water flow turn off button clicked"
				);

				water_flow_sensor.set_water_flowing (
					new_water_flowing => False 
				);
			end if;
		end water_flow_button_clicked;

	begin
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Initializing GTK"
		);

		-- initialize gtk
		Gtk.Main.Init;

		-- setup window
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Initializing window"
		);

		initialize_window (
			title               => constants.gui.window.title,
			width               => constants.gui.window.width,
			height              => constants.gui.window.height,
			resizible           => False,
			on_destroy_callback => quit'Unrestricted_Access,
			window              => window
		);
	
		-- setup grid
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Initializing grid"
		);

		initialize_grid (
			window => window,
			grid   => grid
		);

		-- attaching user buttons
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Attaching user buttons"
		);

		attach_user_buttons (
			grid                                         => grid,
			pump_turn_on_button_clicked_callback         => pump_button_clicked'Unrestricted_Access,
			pump_turn_off_button_clicked_callback        => pump_button_clicked'Unrestricted_Access,
			co_increase_button_clicked_callback          => co_button_clicked'Unrestricted_Access,
			co_decrease_button_clicked_callback          => co_button_clicked'Unrestricted_Access,
			co_error_in_reading_button_clicked_callback  => co_button_clicked'Unrestricted_Access,
			o_increase_button_clicked_callback           => o_button_clicked'Unrestricted_Access,
			o_decrease_button_clicked_callback           => o_button_clicked'Unrestricted_Access,
			o_error_in_reading_button_clicked_callback   => o_button_clicked'Unrestricted_Access,
			ch4_increase_button_clicked_callback         => ch4_button_clicked'Unrestricted_Access,
			ch4_decrease_button_clicked_callback         => ch4_button_clicked'Unrestricted_Access,
			ch4_error_in_reading_button_clicked_callback => ch4_button_clicked'Unrestricted_Access,
			water_level_increase_button_clicked_callback => water_level_button_clicked'Unrestricted_Access,
			water_level_decrease_button_clicked_callback => water_level_button_clicked'Unrestricted_Access,
			water_flow_turn_on_button_clicked_callback   => water_flow_button_clicked'Unrestricted_Access,
			water_flow_turn_off_button_clicked_callback  => water_flow_button_clicked'Unrestricted_Access,
			user_buttons                                 => user_buttons
		);


		-- attaching state widgets
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Attaching state widgets"
		);

		attach_state_widgets (
			grid          => grid,
			state_widgets => state_widgets
		);
		
		window.Show_All;

		-- start device controllers
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Starting device controllers"
		);

		pump_controller := new controllers.device_state_controller_t (
			device    => pump,
			button    => state_widgets.devices.pump.button,
			on_color  => constants.gui.state_widget_controllers.devices.pump.on_color'Access,
			off_color => constants.gui.state_widget_controllers.devices.pump.off_color'Access
		);

		alarm_controller := new controllers.device_state_controller_t (
			device    => alarm,
			button    => state_widgets.devices.alarm.button,
			on_color  => constants.gui.state_widget_controllers.devices.alarm.on_color'Access,
			off_color => constants.gui.state_widget_controllers.devices.alarm.off_color'Access
		);

		co_sensor_controller := new controllers.gas_sensor_state_controller_t (
			sensor                    => co_sensor,
			progress_bar              => state_widgets.sensors.gas_sensors.co.progress_bar,
			label                     => state_widgets.sensors.gas_sensors.co.label,
			color_button              => state_widgets.sensors.gas_sensors.co.color_button,
			multiplication_factor     => constants.gui.state_widget_controllers.sensors.gas_sensors.co.multiplication_factor,
			state_normal_color        => constants.gui.state_widget_controllers.sensors.gas_sensors.co.state_normal_color'Access,
			threshold_breached_color  => constants.gui.state_widget_controllers.sensors.gas_sensors.co.threshold_breached_color'Access,
			read_error_occurred_color => constants.gui.state_widget_controllers.sensors.gas_sensors.co.read_error_occurred_color'Access
		);

		o_sensor_controller := new controllers.gas_sensor_state_controller_t (
			sensor                    => o_sensor,
			progress_bar              => state_widgets.sensors.gas_sensors.o.progress_bar,
			label                     => state_widgets.sensors.gas_sensors.o.label,
			color_button              => state_widgets.sensors.gas_sensors.o.color_button,
			multiplication_factor     => constants.gui.state_widget_controllers.sensors.gas_sensors.o.multiplication_factor,
			state_normal_color        => constants.gui.state_widget_controllers.sensors.gas_sensors.o.state_normal_color'Access,
			threshold_breached_color  => constants.gui.state_widget_controllers.sensors.gas_sensors.o.threshold_breached_color'Access,
			read_error_occurred_color => constants.gui.state_widget_controllers.sensors.gas_sensors.o.read_error_occurred_color'Access
		);

		ch4_sensor_controller := new controllers.gas_sensor_state_controller_t (
			sensor                    => ch4_sensor,
			progress_bar              => state_widgets.sensors.gas_sensors.ch4.progress_bar,
			label                     => state_widgets.sensors.gas_sensors.ch4.label,
			color_button              => state_widgets.sensors.gas_sensors.ch4.color_button,
			multiplication_factor     => constants.gui.state_widget_controllers.sensors.gas_sensors.ch4.multiplication_factor,
			state_normal_color        => constants.gui.state_widget_controllers.sensors.gas_sensors.ch4.state_normal_color'Access,
			threshold_breached_color  => constants.gui.state_widget_controllers.sensors.gas_sensors.ch4.threshold_breached_color'Access,
			read_error_occurred_color => constants.gui.state_widget_controllers.sensors.gas_sensors.ch4.read_error_occurred_color'Access
		);

		water_tank := new controllers.water_tank_t (
			initital_level => constants.mine_water_level_control_system.sensors.water_level_sensors.initial_water_level'Access, 
			maximum_level  => constants.mine_water_level_control_system.sensors.water_level_sensors.maximum_water_level'Access
		);

		water_level_sensors_controller := new controllers.water_level_sensors_state_controller_t (
			water_level_sensors         	=> water_level_sensors,
			water_tank                      => water_tank,
			progress_bar                    => state_widgets.sensors.water_level_sensors.progress_bar,
			label                           => state_widgets.sensors.water_level_sensors.label,
			color_button                    => state_widgets.sensors.water_level_sensors.color_button,
			state_normal_color              => constants.gui.state_widget_controllers.sensors.water_level_sensors.state_normal_color'Access,
			low_water_level_breached_color  => constants.gui.state_widget_controllers.sensors.water_level_sensors.low_water_level_breached_color'Access,
			high_water_level_breached_color => constants.gui.state_widget_controllers.sensors.water_level_sensors.high_water_level_breached_color'Access
		);

		water_flow_sensor_controller := new controllers.water_flow_sensor_state_controller_t (
			water_flow_sensor => water_flow_sensor,
			button            => state_widgets.sensors.water_flow_sensor.button,
			flowing_color     => constants.gui.state_widget_controllers.sensors.water_flow_sensor.flowing_color'Access,
			not_flowing_color => constants.gui.state_widget_controllers.sensors.water_flow_sensor.not_flowing_color'Access
		);


		-- start main loop
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Starting main loop"
		);

		Gtk.Main.Main;

		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Waiting for device controllers to finish"
		);

		Ada.Task_Identification.Abort_Task (
			T => pump_controller'Identity
		);

		Ada.Task_Identification.Abort_Task (
			T => alarm_controller'Identity
		);

		Ada.Task_Identification.Abort_Task (
			T => co_sensor_controller'Identity
		);

		Ada.Task_Identification.Abort_Task (
			T => o_sensor_controller'Identity
		);

		Ada.Task_Identification.Abort_Task (
			T => ch4_sensor_controller'Identity
		);

		Ada.Task_Identification.Abort_Task (
			T => water_level_sensors_controller'Identity
		);

		Ada.Task_Identification.Abort_Task (
			T => water_flow_sensor_controller'Identity
		);

		while ( 
			( not pump_controller'Terminated ) or
			( not alarm_controller'Terminated ) or
			( not co_sensor_controller'Terminated ) or
			( not o_sensor_controller'Terminated ) or
			( not ch4_sensor_controller'Terminated ) or
			( not water_level_sensors_controller'Terminated ) or
			( not water_flow_sensor_controller'Terminated ) 
		) loop 
			null;
		end loop;

		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "All tasks finished"
		);

		accept finished do
			null;
		end finished; 
	end gui_controller_t;
end gui;

