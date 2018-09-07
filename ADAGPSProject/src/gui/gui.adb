with Gtk.Main;
with Gtk.Window;
with Gtk.Widget;
with Gtk.Grid;
with Gtk.Button;
with Gdk.RGBA;
with Glib;
with GNATCOLL.Traces;

with Ada.Task_Identification;

with constants;
with devices;

-- for testing
with Ada.Text_IO;

package body gui is
	-- device controller, when state changes it updates button
	task type device_state_gui_controller_t (
		device             : access devices.device_t;
		button             : Gtk.Button.Gtk_Button
	)is
	end device_state_gui_controller_t;

	task body device_state_gui_controller_t is
	begin
		check_loop : loop
			device.wait_for_state_change;
		   	
		   	if ( devices."=" ( device.get_state, devices.ON ) ) then
		   		button.Override_Color (
		   			State => button.Get_State_Flags,
		   			Color => constants.gui.device_state_buttons.colors.on_color
		   		);
		   	else
		   		button.Override_Color (
		   			State => button.Get_State_Flags,
		   			Color => constants.gui.device_state_buttons.colors.off_color
		   		);
		   	end if;
		end loop check_loop;
	end device_state_gui_controller_t;

	-- attach device buttons and start controllers
	procedure attach_device_buttons (
		grid         : in Gtk.Grid.Gtk_Grid;
		pump         : access devices.device_t;
		alarm        : access devices.device_t;
		pump_button  : out Gtk.Button.Gtk_Button;
		alarm_button : out Gtk.Button.Gtk_Button
	) is
	begin
		-- create buttons
		Gtk.Button.Gtk_New (
			Button => pump_button,
			Label  => ""
		);		

		Gtk.Button.Gtk_New (
			Button => alarm_button,
			Label  => ""
		);		

		pump_button.Set_Label (
			Label => constants.gui.device_state_buttons.pump.label
		);

		alarm_button.Set_Label (
			Label => constants.gui.device_state_buttons.alarm.label
		);
		
		-- attach to grid
		grid.Attach (
			Child  => pump_button,
			Left   => constants.gui.device_state_buttons.pump.left,
			Top    => constants.gui.device_state_buttons.pump.top,
			Width  => constants.gui.device_state_buttons.pump.width,
			Height => constants.gui.device_state_buttons.pump.height
		);

		grid.Attach (
			Child  => alarm_button,
			Left   => constants.gui.device_state_buttons.alarm.left,
			Top    => constants.gui.device_state_buttons.alarm.top,
			Width  => constants.gui.device_state_buttons.alarm.width,
			Height => constants.gui.device_state_buttons.alarm.height
		);

	end attach_device_buttons;

	-- attach user buttons to gui
	procedure attach_user_buttons (
		grid                     : in Gtk.Grid.Gtk_Grid;
		turn_on_button_callback  : Gtk.Button.Cb_Gtk_Button_Void;
		turn_off_button_callback : Gtk.Button.Cb_Gtk_Button_Void
	) is
		-- user buttons 
		turn_on_button : Gtk.Button.Gtk_Button;
		turn_off_button : Gtk.Button.Gtk_Button;
	begin
		-- create and add user buttons
		Gtk.Button.Gtk_New (
			Button => turn_on_button,
			Label => constants.gui.user_buttons.turn_on_button.label
		);	
	
		Gtk.Button.Gtk_New (
			Button => turn_off_button,
			Label => constants.gui.user_buttons.turn_off_button.label
		);	
	
		grid.Attach (
			Child  => turn_on_button,
			Left   => constants.gui.user_buttons.turn_on_button.left,
			Top    => constants.gui.user_buttons.turn_on_button.top,
			Width  => constants.gui.user_buttons.turn_on_button.width,
			Height => constants.gui.user_buttons.turn_on_button.height
		);
	
		grid.Attach (
			Child  => turn_off_button,
			Left   => constants.gui.user_buttons.turn_off_button.left,
			Top    => constants.gui.user_buttons.turn_off_button.top,
			Width  => constants.gui.user_buttons.turn_off_button.width,
			Height => constants.gui.user_buttons.turn_off_button.height
		);

		turn_on_button.On_Clicked (
			Call => turn_on_button_callback 
		);

		turn_off_button.On_Clicked (
			Call => turn_off_button_callback
		);
	end attach_user_buttons;

	-- quit procedure
	procedure quit ( self : access Gtk.Widget.Gtk_Widget_Record'class ) is
	begin
		Gtk.Main.Main_Quit;
	end quit;
	
	task body gui_controller_t is
		window : Gtk.Window.Gtk_Window;
		grid : Gtk.Grid.Gtk_Grid;

		pump_button : Gtk.Button.Gtk_Button;
		alarm_button : Gtk.Button.Gtk_Button;
		
		pump_controller : access device_state_gui_controller_t;
		alarm_controller : access device_state_gui_controller_t;

		-- callbacks for user buttons
		procedure turn_on_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => constants.log.gui.stream,
				Message => "Pump turn on button clicked"
			);
			top.turn_on;
		end turn_on_button_clicked;

		procedure turn_off_button_clicked (
			self : access Gtk.Button.Gtk_Button_Record'Class
		) is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => constants.log.gui.stream,
				Message => "Pump turn off button clicked"
			);
			top.turn_off;
		end turn_off_button_clicked;

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
			Message => "Creating window"
		);
	
		Gtk.Window.Gtk_New (
			Window => window 
		);
	
		window.Set_Title (
			Title => constants.gui.window.title 
		);
	
		window.Set_Size_Request (
			Width  => constants.gui.window.width,
			Height => constants.gui.window.height
		);
	
		window.Set_Resizable (
			Resizable => False 
		);
		
		window.On_Destroy (
			Call => gui.quit'Access 
		);	
	
		-- setup grid
		Gtk.Grid.Gtk_New (
			Self => grid
		);
	
		window.Add (
			Widget => grid
		);

		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Attaching user buttons"
		);
		
		attach_user_buttons (
			grid                     => grid,
			turn_on_button_callback  => turn_on_button_clicked'Unrestricted_Access,
			turn_off_button_callback => turn_off_button_clicked'Unrestricted_Access
		);	

		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Attaching device buttons"
		);

		attach_device_buttons (
			grid  => grid,
			pump  => pump,
			alarm => alarm,
			pump_button => pump_button,
			alarm_button => alarm_button
		);
		
		window.Show_All;

		-- start device controllers
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "Starting device controllers"
		);

		pump_controller := new device_state_gui_controller_t (
			device    => pump,
			button    => pump_button
		);

		alarm_controller := new device_state_gui_controller_t (
			device    => alarm,
			button    => alarm_button
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


		while ( not pump_controller'Terminated ) loop 
			null;
		end loop;

		while ( not alarm_controller'Terminated ) loop 
			null;
		end loop;

		GNATCOLL.Traces.Trace (
			Handle  => constants.log.gui.stream,
			Message => "All tasks finished"
		);

		accept stop do
			null;
		end stop; 
	end gui_controller_t;
end gui;

