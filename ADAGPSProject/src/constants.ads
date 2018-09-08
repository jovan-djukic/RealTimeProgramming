with Glib;
with Gdk.RGBA;
with GNATCOLL.Traces;
with Ada.Characters.Latin_1;

package constants is
	package system is
		package sensors is
			package co_sensor is
				initial_value           : aliased constant Float := 0.0;
				threshold               : aliased constant Float := 100.0;
				detects_above_threshold : constant Boolean       := True;
			end co_sensor;
		end sensors;
	end system;

	package gui is
		package window is
			title  : constant String  := "Mine water level control system";
			width  : constant Glib.Gint := 600;
			height : constant Glib.Gint := 600;
		end window;

		package user_buttons is

			package pump is
				package turn_on is
					label  : constant String    := "Turn on pump";
					left   : constant Glib.Gint := 14;
					top    : constant Glib.Gint := 1;
					width  : constant Glib.Gint := 2;
					height : constant Glib.Gint := 1;
				end turn_on;

				package turn_off is
					label  : constant String    := "Turn off pump";
					left   : constant Glib.Gint := 17;
					top    : constant Glib.Gint := 1;
					width  : constant Glib.Gint := 2;
					height : constant Glib.Gint := 1;
				end turn_off;
			end pump;

			package sensors is
				package co_sensor is
					gas_label : constant String := "CO";
					package increase is
						label            : constant String        := gas_label & " increase";
						left             : constant Glib.Gint     := 1;
						top              : constant Glib.Gint     := 1;
						width            : constant Glib.Gint     := 2;
						height           : constant Glib.Gint     := 1;
						delta_percentage : aliased constant Float := 0.1;
					end increase;

					package decrease is
						label            : constant String        := gas_label & " decrease";
						left             : constant Glib.Gint     := 1;
						top              : constant Glib.Gint     := 2;
						width            : constant Glib.Gint     := 2;
						height           : constant Glib.Gint     := 1;
						delta_percentage : aliased constant Float := 0.1;
					end decrease;

					package error_in_reading is
						label  : constant String    := gas_label & " error in reading";
						left   : constant Glib.Gint := 1;
						top    : constant Glib.Gint := 3;
						width  : constant Glib.Gint := 2;
						height : constant Glib.Gint := 1;
					end error_in_reading;
				end co_sensor;

			end sensors;

		end user_buttons;
		
		package state_widgets is
			package device_state_widgets is
				package pump is
					label  : constant String    := "PUMP";
					left   : constant Glib.Gint := 15;
					top    : constant Glib.Gint := 2;
					width  : constant Glib.Gint := 3;
					height : constant Glib.Gint := 1;
				end pump;

				package alarm is
					label  : constant String    := "ALARM";
					left   : constant Glib.Gint := 15;
					top    : constant Glib.Gint := 3;
					width  : constant Glib.Gint := 3;
					height : constant Glib.Gint := 1;
				end alarm;
			end device_state_widgets;

			package sensor_state_widgets is
				package co_sensor_progress_bar is
					left   : constant Glib.Gint := 3;
					top    : constant Glib.Gint := 1;
					width  : constant Glib.Gint := 9;
					height : constant Glib.Gint := 3;
				end co_sensor_progress_bar;
			end sensor_state_widgets;
		end state_widgets;
		
		package state_widget_controllers is
			package device_state_controllers is
				package pump_controller is
					on_color  : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 0 ),
						Green => Glib.Gdouble ( 1 ),
						Blue  => Glib.Gdouble ( 0 ),
						Alpha => Glib.Gdouble ( 1 )
					);

					off_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 1 ),
						Green => Glib.Gdouble ( 0 ),
						Blue  => Glib.Gdouble ( 0 ),
						Alpha => Glib.Gdouble ( 1 )
					);
				end pump_controller;

				package alarm_controller is
					on_color  : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 0 ),
						Green => Glib.Gdouble ( 1 ),
						Blue  => Glib.Gdouble ( 0 ),
						Alpha => Glib.Gdouble ( 1 )
					);

					off_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 1 ),
						Green => Glib.Gdouble ( 0 ),
						Blue  => Glib.Gdouble ( 0 ),
						Alpha => Glib.Gdouble ( 1 )
					);
				end alarm_controller;
			end device_state_controllers;
			
			package gas_sensor_state_controllers is
				multiplication_factor     : constant Integer := 2;
				state_normal_color        : aliased constant Gdk.RGBA.Gdk_RGBA := (
					Red   => Glib.Gdouble ( 0 ),
					Green => Glib.Gdouble ( 0 ),
					Blue  => Glib.Gdouble ( 1 ),
					Alpha => Glib.Gdouble ( 1 )
				);

				threshold_breached_color  : aliased constant Gdk.RGBA.Gdk_RGBA := (
					Red   => Glib.Gdouble ( 1 ),
					Green => Glib.Gdouble ( 0 ),
					Blue  => Glib.Gdouble ( 0 ),
					Alpha => Glib.Gdouble ( 1 )
				);

				read_error_occurred_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
					Red   => Glib.Gdouble ( 0 ),
					Green => Glib.Gdouble ( 1 ),
					Blue  => Glib.Gdouble ( 1 ),
					Alpha => Glib.Gdouble ( 1 )
				);
			end gas_sensor_state_controllers;
		end state_widget_controllers;
	end gui;
	
	package log is
		configuration : constant String := 
			"+" & Ada.Characters.Latin_1.LF & 
			">log.txt" & Ada.Characters.Latin_1.LF &
			"*.EXCEPTIONS=yes";

		package main is
			stream : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
				Unit_Name => "main"
			);
		end main;

		package gui is
			stream : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
				Unit_Name => "GUI"
			);
		end gui;

		package mine_water_level_control_system is
			package top is
				stream : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
					Unit_Name => "Mine water level control system"
				);
			end top;

			package pump_controller is
				stream : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
					Unit_Name => "pump controller"
				);
			end pump_controller;

			package alarm_controller is
				stream : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
					Unit_Name => "alarm controller"
				);
			end alarm_controller;
		end mine_water_level_control_system;
	end log;	
end constants;
