with Gtk.Main;
with Gtk.Window;
with Gtk.Widget;
with Gtk.Grid;
with Gtk.Button;
with Gdk.RGBA;
with Glib;

with constants;
with gui;
with devices;

-- for testing
with Ada.Text_IO;

package body gui is
	-- device controller, when state changes it updates button
	task type device_state_gui_controller_t is
	begin
		entry initialize ( 
			device_to_watch : in access devices.device_t;
			device_button   : in access Gtk.Button.Gtk_Button
		);
	end device_state_gui_controller_t;

	task body device_state_gui_controller_t is
		device    : access devices.device_t;
		button    : access Gtk.Button.Gtk_Button;
		on_label  : String;
		off_label : String;
	begin
		accept initialize (
			device_to_watch  : in access devices.device_t;
			button_to_set    : in access Gtk.Button.Gtk_Button;
			device_on_label  : String;
			device_off_label : String
		) do
			device    : = device;
			button    : = button_to_set;
			on_label  : = device_on_label;
			off_label : = device_off_label;
		end initialize;
		
		loop
			device.wait_for_state_change;
			
			if ( device.get_state = ON ) then
				button.Override_Color (
					State => button.Get_State,
					Color => constants.gui.device_state_colors.on_color
				);
				button.Set_Label (
					Label => on_label
				);
			else
				button.Override_Color (
					State => button.Get_State,
					Color => constants.gui.device_state_colors.off_color
				);
				button.Set_Label (
					Label => off_label
				);
			end if;
		end loop;
	end device_state_gui_controller_t;

	-- attach device buttons and start controllers
	procedure attach_device_buttons (
		grid : out Gtk.Grid.Gtk_Grid,
		pump : access devices.device_t,
		alarm : access devices.device_t
	) is
		-- buttons
		pump_button  : Gtk.Button.Gtk_Button;
		alarm_button : Gtk.Button.Gtk_Button;

		-- controllers
		pump_controller  : device_state_gui_controller_t;
		alarm_controller : device_state_gui_controller_t;
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

	end attach_device_buttons

	-- callbacks for user buttons
	procedure turn_on_button_clicked (
		self : access Gtk.Button.Gtk_Button_Record'Class
	) is
	begin
		Ada.Text_IO.Put_Line ( "Turn on button clicked" );	
	end turn_on_button_clicked;

	procedure turn_off_button_clicked (
		self : access Gtk.Button.Gtk_Button_Record'Class
	) is
	begin
		Ada.Text_IO.Put_Line ( "Turn off button clicked" );	
	end turn_off_button_clicked;

	-- attach user buttons to gui
	procedure attach_user_buttons (
		grid : out Gtk.Grid.Gtk_Grid
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
			Call => turn_on_button_clicked'Access
		);

		turn_off_button.On_Clicked (
			Call => turn_off_button_clicked'Access
		);
	end attach_user_buttons;

	-- quit procedure
	procedure quit ( self : access Gtk.Widget.Gtk_Widget_Record'class ) is
	begin
		Gtk.Main.Main_Quit;
	end quit;
	
	procedure initialize_gui (
		window : out Gtk.Window.Gtk_Window,
		pump   : access devices.device_t,
		alarm  : access devices.device_t
	) is
		grid : Gtk.Grid.Gtk_Grid;
	begin
		-- setup window
	
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
		
		attach_user_buttons (
			grid => grid
		);	

		attach_device_buttons (
			grid  => grid,
			pump  => pump,
			alarm => alarm
		);
		
		window.Show_All;
	end initialize_gui;
end gui;

