with Glib;
with Gdk.RGBA;
with GNATCOLL.Traces;
with Ada.Characters.Latin_1;

package constants is
	package gui is
		package window is
			title  : constant String  := "Mine water level control system";
			width  : constant Glib.Gint := 600;
			height : constant Glib.Gint := 600;
		end window;

		package user_buttons is
			package turn_on_button is
				label  : constant String    := "Turn on pump";
				left   : constant Glib.Gint := 14;
				top    : constant Glib.Gint := 1;
				width  : constant Glib.Gint := 2;
				height : constant Glib.Gint := 1;
			end turn_on_button;

			package turn_off_button is
				label  : constant String    := "Turn off pump";
				left   : constant Glib.Gint := 17;
				top    : constant Glib.Gint := 1;
				width  : constant Glib.Gint := 2;
				height : constant Glib.Gint := 1;
			end turn_off_button;
		end user_buttons;
		
		package device_state_buttons is
			package pump is
				on_label  : constant String    := "Pump activated";
				off_label : constant String    := "Pump deactivated";
				left      : constant Glib.Gint := 15;
				top       : constant Glib.Gint := 3;
				width     : constant Glib.Gint := 3;
				height    : constant Glib.Gint := 1;
			end pump;

			package alarm is
				on_label  : constant String := "Alarm activated";
				off_label : constant String := "Alarm deactivated";
				left      : constant Glib.Gint := 15;
				top       : constant Glib.Gint := 5;
				width     : constant Glib.Gint := 3;
				height    : constant Glib.Gint := 1;
			end alarm;

			package colors is
				on_color : constant Gdk.RGBA.Gdk_RGBA := (
					Red   => Glib.Gdouble ( 0 ),
					Green => Glib.Gdouble ( 1 ),
					Blue  => Glib.Gdouble ( 0 ),
					Alpha => Glib.Gdouble ( 1 )
				);

				off_color : constant Gdk.RGBA.Gdk_RGBA := (
					Red   => Glib.Gdouble ( 1 ),
					Green => Glib.Gdouble ( 0 ),
					Blue  => Glib.Gdouble ( 0 ),
					Alpha => Glib.Gdouble ( 1 )
				);
			end colors;
		end device_state_buttons;
	end gui;
	
	package log is
		configuration : constant String := 
			"+" & Ada.Characters.Latin_1.LF & 
			">log.txt";

		package main is
			stream : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
				Unit_Name => "MAIN"
			);
		end main;

		package gui is
			stream : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
				Unit_Name => "GUI"
			);
		end gui;
	end log;	
end constants;
