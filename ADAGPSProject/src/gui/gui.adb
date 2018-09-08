with Gtk.Main;
with Gtk.Window;
with Gtk.Widget;
with Gtk.Grid;
with Gtk.Button;
with Gtk.Progress_Bar;
with Gdk.RGBA;
with Glib;
with GNATCOLL.Traces;


with Ada.Task_Identification;

with controllers;
with constants;
with devices;

-- for testing
with Ada.Text_IO;

package body gui is
	-- attach device state button
	procedure attach_device_state_button (
		grid   : in Gtk.Grid.Gtk_Grid;
		label  : in String;
		left   : in Glib.Gint;
		top    : in Glib.Gint;
		width  : in Glib.Gint;
		height : in Glib.Gint;
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

	end attach_device_state_button;
	
	procedure attach_device_state_buttons (
		grid         : in Gtk.Grid.Gtk_Grid;
		pump_button  : out Gtk.Button.Gtk_Button;
		alarm_button : out Gtk.Button.Gtk_Button
	) is
	begin
		attach_device_state_button (
			grid   => grid,
			label  => constants.gui.state_widgets.device_state_widgets.pump.label,
			left   => constants.gui.state_widgets.device_state_widgets.pump.left,
			top    => constants.gui.state_widgets.device_state_widgets.pump.top,
			width  => constants.gui.state_widgets.device_state_widgets.pump.width,
			height => constants.gui.state_widgets.device_state_widgets.pump.height,
			button => pump_button 
		);

		attach_device_state_button (
			grid   => grid,
			label  => constants.gui.state_widgets.device_state_widgets.alarm.label,
			left   => constants.gui.state_widgets.device_state_widgets.alarm.left,
			top    => constants.gui.state_widgets.device_state_widgets.alarm.top,
			width  => constants.gui.state_widgets.device_state_widgets.alarm.width,
			height => constants.gui.state_widgets.device_state_widgets.alarm.height,
			button => alarm_button 
		);
	end attach_device_state_buttons;

	procedure attach_sensor_state_progress_bar (
		grid         : in Gtk.Grid.Gtk_Grid;
		left         : in Glib.Gint;
		top          : in Glib.Gint;
		width        : in Glib.Gint;
		height       : in Glib.Gint;
		progress_bar : out Gtk.Progress_Bar.Gtk_Progress_Bar
	) is
	begin
		-- create progressbar
		Gtk.Progress_Bar.Gtk_New (
			Progress_Bar => progress_bar
		);		

		-- attach to grid
		grid.Attach (
			Child  => progress_bar,
			Left   => left,
			Top    => top,
			Width  => width,
			Height => height
		);
	end attach_sensor_state_progress_bar;

	procedure attach_sensor_state_progress_bars (
		grid                   : in Gtk.Grid.Gtk_Grid;
		co_sensor_progress_bar : out Gtk.Progress_Bar.Gtk_Progress_Bar
	) is
	begin
		attach_sensor_state_progress_bar (
			grid         => grid,
			left         => constants.gui.state_widgets.sensor_state_widgets.co_sensor_progress_bar.left,
			top          => constants.gui.state_widgets.sensor_state_widgets.co_sensor_progress_bar.top,
			width        => constants.gui.state_widgets.sensor_state_widgets.co_sensor_progress_bar.width,
			height       => constants.gui.state_widgets.sensor_state_widgets.co_sensor_progress_bar.height,
			progress_bar => co_sensor_progress_bar 
		);
	end attach_sensor_state_progress_bars;

	-- attach user buttons to gui
	procedure attach_user_button (
		label               : in String;
		grid                : in Gtk.Grid.Gtk_Grid;
		left                : in Glib.Gint;
		top                 : in Glib.Gint;
		width               : in Glib.Gint;
		height              : in Glib.Gint;
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
	
	-- attach pump buttons
	procedure attach_pump_buttons (
		grid                             : in Gtk.Grid.Gtk_Grid;
		turn_on_button_clicked_callback  : in Gtk.Button.Cb_Gtk_Button_Void;
		turn_off_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		turn_on_pump_button              : out Gtk.Button.Gtk_Button;
		turn_off_pump_button             : out Gtk.Button.Gtk_Button
	) is
	begin
		attach_user_button (
			label               => constants.gui.user_buttons.pump.turn_on.label,
			grid                => grid, 
			left                => constants.gui.user_buttons.pump.turn_on.left,
			top                 => constants.gui.user_buttons.pump.turn_on.top,
			width               => constants.gui.user_buttons.pump.turn_on.width,
			height              => constants.gui.user_buttons.pump.turn_on.height,
			on_clicked_callback => turn_on_button_clicked_callback, 
			button              => turn_on_pump_button
		);

		attach_user_button (
			label               => constants.gui.user_buttons.pump.turn_off.label,
			grid                => grid, 
			left                => constants.gui.user_buttons.pump.turn_off.left,
			top                 => constants.gui.user_buttons.pump.turn_off.top,
			width               => constants.gui.user_buttons.pump.turn_off.width,
			height              => constants.gui.user_buttons.pump.turn_off.height,
			on_clicked_callback => turn_off_button_clicked_callback,
			button              => turn_off_pump_button
		);
	end attach_pump_buttons;

	-- attach sensor buttons
	procedure attach_sensor_buttons (
		grid                                        : in Gtk.Grid.Gtk_Grid;
		co_increase_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		co_decrease_button_clicked_callback         : in Gtk.Button.Cb_Gtk_Button_Void;
		co_error_in_reading_button_clicked_callback : in Gtk.Button.Cb_Gtk_Button_Void;
		co_increase_button                          : out Gtk.Button.Gtk_Button;
		co_decrease_button                          : out Gtk.Button.Gtk_Button;
		co_error_in_reading_button                  : out Gtk.Button.Gtk_Button
	) is
	begin
		attach_user_button (
			label               => constants.gui.user_buttons.sensors.co_sensor.increase.label,
			grid                => grid, 
			left                => constants.gui.user_buttons.sensors.co_sensor.increase.left,
			top                 => constants.gui.user_buttons.sensors.co_sensor.increase.top,
			width               => constants.gui.user_buttons.sensors.co_sensor.increase.width,
			height              => constants.gui.user_buttons.sensors.co_sensor.increase.height,
			on_clicked_callback => co_increase_button_clicked_callback, 
			button              => co_increase_button 
		);

		attach_user_button (
			label               => constants.gui.user_buttons.sensors.co_sensor.decrease.label,
			grid                => grid, 
			left                => constants.gui.user_buttons.sensors.co_sensor.decrease.left,
			top                 => constants.gui.user_buttons.sensors.co_sensor.decrease.top,
			width               => constants.gui.user_buttons.sensors.co_sensor.decrease.width,
			height              => constants.gui.user_buttons.sensors.co_sensor.decrease.height,
			on_clicked_callback => co_decrease_button_clicked_callback, 
			button              => co_decrease_button 
		);

		attach_user_button (
			label               => constants.gui.user_buttons.sensors.co_sensor.error_in_reading.label,
			grid                => grid, 
			left                => constants.gui.user_buttons.sensors.co_sensor.error_in_reading.left,
			top                 => constants.gui.user_buttons.sensors.co_sensor.error_in_reading.top,
			width               => constants.gui.user_buttons.sensors.co_sensor.error_in_reading.width,
			height              => constants.gui.user_buttons.sensors.co_sensor.error_in_reading.height,
			on_clicked_callback => co_error_in_reading_button_clicked_callback, 
			button              => co_error_in_reading_button 
		);
	end attach_sensor_buttons;

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

	type device_buttons_t is
	record
		turn_on  : Gtk.Button.Gtk_Button;
		turn_off : Gtk.Button.Gtk_Button;
	end record;

	type sensor_buttons_t is
	record
		increase_button         : Gtk.Button.Gtk_Button;
		decrease_button         : Gtk.Button.Gtk_Button;
		error_in_reading_button : Gtk.Button.Gtk_Button;
	end record;

	type user_buttons_t is
	record
		pump_buttons      : device_buttons_t;
		co_sensor_buttons : sensor_buttons_t;
	end record;	

	type device_state_widgets_t is
	record
		pump_button  : Gtk.Button.Gtk_Button;
		alarm_button : Gtk.Button.Gtk_Button;
	end record;
	
	type sensor_state_widgets_t is
	record
		co_sensor_progress_bar : Gtk.Progress_Bar.Gtk_Progress_Bar;
	end record;

	type state_widgets_t is
	record
		device_state_widgets : device_state_widgets_t;
		sensor_state_widgets : sensor_state_widgets_t;
	end record;
	
	-- main gui task
	task body gui_controller_t is

		window : Gtk.Window.Gtk_Window;
		grid   : Gtk.Grid.Gtk_Grid;

		user_buttons  : user_buttons_t;
		state_widgets : state_widgets_t;
		
		-- device state controllers
		pump_controller  : access controllers.device_state_controller_t;
		alarm_controller : access controllers.device_state_controller_t;

		-- sensor state controllers
		co_sensor_controller : access controllers.gas_sensor_state_controller_t;

		-- callbacks for window
		procedure quit ( self : access Gtk.Widget.Gtk_Widget_Record'class ) is
		begin
			Gtk.Main.Main_Quit;
		end quit;

		-- button callbacks

		-- pump button callbacks
		procedure pump_turn_on_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => constants.log.gui.stream,
				Message => "Pump turn on button clicked"
			);

			top.turn_on;
		end pump_turn_on_button_clicked;
		
		procedure pump_turn_off_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => constants.log.gui.stream,
				Message => "Pump turn off button clicked"
			);

			top.turn_off;
		end pump_turn_off_button_clicked;
		
		-- sensor button callbacks
		procedure co_increase_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
			value : Float;
		begin
			GNATCOLL.Traces.Trace (
				Handle  => constants.log.gui.stream,
				Message => "CO increase button clicked"
			);

			value := co_sensor.get_value;
			value := value + co_sensor.get_threshold * constants.gui.user_buttons.sensors.co_sensor.increase.delta_percentage;
			co_sensor.set_value (
				new_value => value
			);	
		end co_increase_button_clicked;

		procedure co_decrease_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
			value : Float;
		begin
			GNATCOLL.Traces.Trace (
				Handle  => constants.log.gui.stream,
				Message => "CO decrease button clicked"
			);

			value := co_sensor.get_value;
			value := value - co_sensor.get_threshold * constants.gui.user_buttons.sensors.co_sensor.increase.delta_percentage;
			co_sensor.set_value (
				new_value => value
			);	
		end co_decrease_button_clicked;

		procedure co_error_in_reading_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => constants.log.gui.stream,
				Message => "CO error in reading button clicked"
			);
			
			co_sensor.set_read_error_occured (
				new_read_error_occurred => not co_sensor.get_read_error_occurred
			);
		end co_error_in_reading_button_clicked;
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

		-- attach pump buttons
		attach_pump_buttons (
			grid                             => grid,
			turn_on_button_clicked_callback  => pump_turn_on_button_clicked'Unrestricted_Access,
			turn_off_button_clicked_callback => pump_turn_off_button_clicked'Unrestricted_Access,
			turn_on_pump_button              => user_buttons.pump_buttons.turn_on,
			turn_off_pump_button             => user_buttons.pump_buttons.turn_off
		);

		attach_sensor_buttons (
			grid                                        => grid,
			co_increase_button_clicked_callback         => co_increase_button_clicked'Unrestricted_Access,
			co_decrease_button_clicked_callback         => co_decrease_button_clicked'Unrestricted_Access,
			co_error_in_reading_button_clicked_callback => co_error_in_reading_button_clicked'Unrestricted_Access,
			co_increase_button                          => user_buttons.co_sensor_buttons.increase_button,
			co_decrease_button                          => user_buttons.co_sensor_buttons.decrease_button,
			co_error_in_reading_button                  => user_buttons.co_sensor_buttons.error_in_reading_button
		);

		-- attaching device buttons
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Attaching device buttons"
		);
		
		attach_device_state_buttons (
			grid         => grid,
			pump_button  => state_widgets.device_state_widgets.pump_button,
			alarm_button => state_widgets.device_state_widgets.alarm_button
		);

		attach_sensor_state_progress_bars (
			grid                   => grid,
			co_sensor_progress_bar => state_widgets.sensor_state_widgets.co_sensor_progress_bar
		);
		
		window.Show_All;

		-- start device controllers
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Starting device controllers"
		);

		pump_controller := new controllers.device_state_controller_t (
			device    => pump,
			button    => state_widgets.device_state_widgets.pump_button,
			on_color  => constants.gui.state_widget_controllers.device_state_controllers.pump_controller.on_color'Access,
			off_color => constants.gui.state_widget_controllers.device_state_controllers.pump_controller.off_color'Access
		);

		alarm_controller := new controllers.device_state_controller_t (
			device    => alarm,
			button    => state_widgets.device_state_widgets.alarm_button,
			on_color  => constants.gui.state_widget_controllers.device_state_controllers.alarm_controller.on_color'Access,
			off_color => constants.gui.state_widget_controllers.device_state_controllers.alarm_controller.off_color'Access
		);

		co_sensor_controller := new controllers.gas_sensor_state_controller_t (
			sensor                    => co_sensor,
			progress_bar              => state_widgets.sensor_state_widgets.co_sensor_progress_bar,
			multiplication_factor     => constants.gui.state_widget_controllers.gas_sensor_state_controllers.multiplication_factor,
			state_normal_color        => constants.gui.state_widget_controllers.gas_sensor_state_controllers.state_normal_color'Access,
			threshold_breached_color  => constants.gui.state_widget_controllers.gas_sensor_state_controllers.threshold_breached_color'Access,
			read_error_occurred_color => constants.gui.state_widget_controllers.gas_sensor_state_controllers.read_error_occurred_color'Access
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


		while ( 
			( not pump_controller'Terminated ) or
			( not alarm_controller'Terminated ) or
			( not co_sensor_controller'Terminated ) 
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

