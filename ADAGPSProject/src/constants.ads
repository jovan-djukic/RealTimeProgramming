with System;
with GNATCOLL.Traces;
with Ada.Characters.Latin_1;
with Glib;
with Gdk.RGBA;
with devices;

package constants is
	package mine_water_level_control_system is
		package devices is
			package pump is 
				name : aliased constant String := "PUMP";
			end pump;
			package alarm is 
				name : aliased constant String := "ALARM";
			end alarm;
		end devices;

		package sensors is
			package gas is
				package co is
					name                       : aliased constant String := "CO SENSOR";
					conversion_time_in_ms      : constant Integer        := 50;
					initial_value              : aliased constant Float  := 0.0;
					initial_read_error_occured : constant Boolean        := false;
				end co;

				package o is
					name                       : aliased constant String := "CO SENSOR";
					conversion_time_in_ms      : constant Integer        := 50;
					initial_value              : aliased constant Float  := 150.0;
					initial_read_error_occured : constant Boolean        := false;
				end o;

				package ch4 is
					name                       : aliased constant String := "CH4 SENSOR";
					conversion_time_in_ms      : constant Integer        := 50;
					initial_value              : aliased constant Float  := 0.0;
					initial_read_error_occured : constant Boolean        := false;
				end ch4;
			end gas;

			package water_flow is
				name                       : aliased constant String  := "WATER FLOW SENSOR";
				conversion_time_in_ms      : constant Integer         := 50;
				initial_water_flowing      : aliased constant Boolean := False;
			end water_flow;

			package water_level is
				name                       : aliased constant String  := "WATER LEVEL SENSORS";
				initial_water_level        : aliased constant Float := 100.0;
				low_water_level_threshold  : aliased constant Float := 50.0;
				high_water_level_threshold : aliased constant Float := 150.0;
			end water_level;
		end sensors;
		
		package top is
			package controllers is
				package ch4 is
					priority                            : constant System.Priority := System.Priority'Last;
					threshold							: aliased constant Float := 100.0;
					read_error_occurred_count_threshold : constant Integer := 2;
					period_in_ms                        : constant Integer := 150;
					dealine_in_ms                       : constant Integer := 100;
				end ch4;
	
				package co is
					priority                            : constant System.Priority := ch4.priority - 1;
					threshold							: aliased constant Float := 100.0;
					read_error_occurred_count_threshold : constant Integer := 2;
					period_in_ms                        : constant Integer := 150;
					dealine_in_ms                       : constant Integer := 100;
				end co;
	
				package o is
					priority                            : constant System.Priority := ch4.priority - 2;
					threshold							: aliased constant Float := 100.0;
					read_error_occurred_count_threshold : constant Integer := 2;
					period_in_ms                        : constant Integer := 150;
					dealine_in_ms                       : constant Integer := 100;
				end o;

				package water_level is
					priority                   : constant System.Priority := ch4.priority - 4;
					low_water_level_threshold  : aliased constant Float   := 50.0;
					high_water_level_threshold : aliased constant Float   := 150.0;
					dealine_in_ms              : constant Integer         := 200;
				end water_level;

				package water_flow is
					priority              : constant System.Priority := ch4.priority - 3;
					period_in_ms          : constant Integer         := 150;
					dealine_in_ms         : constant Integer         := 100;
					number_of_activations : constant Integer         := 6;
				end water_flow;

				package pump is
					priority : constant System.Priority := ch4.priority;
				end pump;
	
				package alarm is
					priority : constant System.Priority := ch4.priority;
				end alarm;
			end controllers;

			priority : constant System.Priority := controllers.co.priority - 3;
		end top;
	end mine_water_level_control_system;

	package gui is
		package window is
			title  : constant String  := "Mine water level control system";
			width  : constant Glib.Gint := 600;
			height : constant Glib.Gint := 400;
		end window;

		package user_buttons is
			package devices is
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
			end devices;

			package sensors is
				package gas is
					package co is
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
					end co;

					package o is
						gas_label : constant String := "O";
						package increase is
							label            : constant String        := gas_label & " increase";
							left             : constant Glib.Gint     := 1;
							top              : constant Glib.Gint     := 5;
							width            : constant Glib.Gint     := 2;
							height           : constant Glib.Gint     := 1;
							delta_percentage : aliased constant Float := 0.1;
						end increase;

						package decrease is
							label            : constant String        := gas_label & " decrease";
							left             : constant Glib.Gint     := 1;
							top              : constant Glib.Gint     := 6;
							width            : constant Glib.Gint     := 2;
							height           : constant Glib.Gint     := 1;
							delta_percentage : aliased constant Float := 0.1;
						end decrease;

						package error_in_reading is
							label  : constant String    := gas_label & " error in reading";
							left   : constant Glib.Gint := 1;
							top    : constant Glib.Gint := 7;
							width  : constant Glib.Gint := 2;
							height : constant Glib.Gint := 1;
						end error_in_reading;
					end o;

					package ch4 is
						gas_label : constant String := "CH4";
						package increase is
							label            : constant String        := gas_label & " increase";
							left             : constant Glib.Gint     := 1;
							top              : constant Glib.Gint     := 9;
							width            : constant Glib.Gint     := 2;
							height           : constant Glib.Gint     := 1;
							delta_percentage : aliased constant Float := 0.1;
						end increase;

						package decrease is
							label            : constant String        := gas_label & " decrease";
							left             : constant Glib.Gint     := 1;
							top              : constant Glib.Gint     := 10;
							width            : constant Glib.Gint     := 2;
							height           : constant Glib.Gint     := 1;
							delta_percentage : aliased constant Float := 0.1;
						end decrease;

						package error_in_reading is
							label  : constant String    := gas_label & " error in reading";
							left   : constant Glib.Gint := 1;
							top    : constant Glib.Gint := 11;
							width  : constant Glib.Gint := 2;
							height : constant Glib.Gint := 1;
						end error_in_reading;
					end ch4;

				end gas;
				
				package water_level is
					liquid_label : constant String := "H20";
					package increase is
						label            : constant String        := liquid_label & " increase";
						left             : constant Glib.Gint     := 15;
						top              : constant Glib.Gint     := 9;
						width            : constant Glib.Gint     := 3;
						height           : constant Glib.Gint     := 1;
						delta_percentage : aliased constant Float := 0.1;
					end increase;

					package decrease is
						label            : constant String        := liquid_label & " decrease";
						left             : constant Glib.Gint     := 15;
						top              : constant Glib.Gint     := 10;
						width            : constant Glib.Gint     := 3;
						height           : constant Glib.Gint     := 1;
						delta_percentage : aliased constant Float := 0.1;
					end decrease;

				end water_level;

				package water_flow is
					water_flow_label : constant String := "Water flow";
					package turn_on is
						label  : constant String    := water_flow_label & " turn on";
						left   : constant Glib.Gint := 3;
						top    : constant Glib.Gint := 14;
						width  : constant Glib.Gint := 4;
						height : constant Glib.Gint := 1;
					end turn_on;

					package turn_off is
						label  : constant String    := water_flow_label & " turn off";
						left   : constant Glib.Gint := 7;
						top    : constant Glib.Gint := 14;
						width  : constant Glib.Gint := 4;
						height : constant Glib.Gint := 1;
					end turn_off;
				end water_flow;

			end sensors;
		end user_buttons;
		
		package state_widgets is
			package devices is
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
			end devices;

			package sensors is

				package gas is

					package co is
						package progress_bar is
							left   : constant Glib.Gint := 3;
							top    : constant Glib.Gint := 1;
							width  : constant Glib.Gint := 10;
							height : constant Glib.Gint := 3;
						end progress_bar;

						package label is
							left   : constant Glib.Gint := 3;
							top    : constant Glib.Gint := 0;
							width  : constant Glib.Gint := 10;
							height : constant Glib.Gint := 1;
						end label;

						package color_button is
							left   : constant Glib.Gint := 13;
							top    : constant Glib.Gint := 1;
							width  : constant Glib.Gint := 1;
							height : constant Glib.Gint := 3;
						end color_button;
					end co;

					package o is
						package progress_bar is
							left   : constant Glib.Gint := 3;
							top    : constant Glib.Gint := 5;
							width  : constant Glib.Gint := 10;
							height : constant Glib.Gint := 3;
						end progress_bar;

						package label is
							left   : constant Glib.Gint := 3;
							top    : constant Glib.Gint := 4;
							width  : constant Glib.Gint := 10;
							height : constant Glib.Gint := 1;
						end label;

						package color_button is
							left   : constant Glib.Gint := 13;
							top    : constant Glib.Gint := 5;
							width  : constant Glib.Gint := 1;
							height : constant Glib.Gint := 3;
						end color_button;
					end o;

					package ch4 is
						package progress_bar is
							left   : constant Glib.Gint := 3;
							top    : constant Glib.Gint := 9;
							width  : constant Glib.Gint := 10;
							height : constant Glib.Gint := 3;
						end progress_bar;

						package label is
							left   : constant Glib.Gint := 3;
							top    : constant Glib.Gint := 8;
							width  : constant Glib.Gint := 10;
							height : constant Glib.Gint := 1;
						end label;

						package color_button is
							left   : constant Glib.Gint := 13;
							top    : constant Glib.Gint := 9;
							width  : constant Glib.Gint := 1;
							height : constant Glib.Gint := 3;
						end color_button;
					end ch4;

				end gas;
				
				package water_level is
					package progress_bar is
						left   : constant Glib.Gint := 15;
						top    : constant Glib.Gint := 12;
						width  : constant Glib.Gint := 3;
						height : constant Glib.Gint := 9;
					end progress_bar;

					package label is
						left   : constant Glib.Gint := 15;
						top    : constant Glib.Gint := 8;
						width  : constant Glib.Gint := 3;
						height : constant Glib.Gint := 1;
					end label;

					package color_button is
						left   : constant Glib.Gint := 15;
						top    : constant Glib.Gint := 11;
						width  : constant Glib.Gint := 3;
						height : constant Glib.Gint := 1;
					end color_button;
				end water_level;

				package water_flow is
					label  : constant String    := "WATER FLOW";
					left   : constant Glib.Gint := 5;
					top    : constant Glib.Gint := 13;
					width  : constant Glib.Gint := 4;
					height : constant Glib.Gint := 1;
				end water_flow;

			end sensors;

		end state_widgets;
		
		package state_widget_controllers is
			package devices is
				package pump is
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
				end pump;

				package alarm is
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
				end alarm;
			end devices;
			
			package sensors is
				package gas is
					package co is
						maximum_value     		  : aliased constant Float := 200.0;
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
					end co;

					package o is
						maximum_value     		  : aliased constant Float := 200.0;
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
					end o;

					package ch4 is
						maximum_value     		  : aliased constant Float := 200.0;
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
					end ch4;
				end gas;

				package water_tank is
					initial_water_level  : aliased constant Float := 100.0;
					maximum_water_level  : aliased constant Float := 200.0;
				end water_tank;
				
				package water_level is
					state_normal_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 0 ),
						Green => Glib.Gdouble ( 0 ),
						Blue  => Glib.Gdouble ( 1 ),
						Alpha => Glib.Gdouble ( 1 )
					);

					low_water_level_breached_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 1 ),
						Green => Glib.Gdouble ( 0 ),
						Blue  => Glib.Gdouble ( 0 ),
						Alpha => Glib.Gdouble ( 1 )
					);

					high_water_level_breached_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 0 ),
						Green => Glib.Gdouble ( 1 ),
						Blue  => Glib.Gdouble ( 1 ),
						Alpha => Glib.Gdouble ( 1 )
					);
				end water_level;

				package water_flow is
					flowing_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 0 ),
						Green => Glib.Gdouble ( 1 ),
						Blue  => Glib.Gdouble ( 1 ),
						Alpha => Glib.Gdouble ( 1 )
					);

					not_flowing_color : aliased constant Gdk.RGBA.Gdk_RGBA := (
						Red   => Glib.Gdouble ( 1 ),
						Green => Glib.Gdouble ( 0 ),
						Blue  => Glib.Gdouble ( 0 ),
						Alpha => Glib.Gdouble ( 1 )
					);
				end water_flow;
			end sensors;
		end state_widget_controllers;
	end gui;
	
	package log is
		configuration : constant String := 
			"+" & Ada.Characters.Latin_1.LF & 
			">log.txt" & Ada.Characters.Latin_1.LF &
			"*.EXCEPTIONS=yes";

		package main is
			trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
				Unit_Name => "main"
			);
		end main;

		package gui is
			trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
				Unit_Name => "GUI"
			);
		end gui;

		package mine_water_level_control_system is
			package top is
				trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
					Unit_Name => "Top"
				);
			end top;

			package environment_station is
				package co_sensor_controller is
					trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
						Unit_Name => "CO sensor controller"
					);
				end co_sensor_controller;

				package o_sensor_controller is
					trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
						Unit_Name => "O sensor controller"
					);
				end o_sensor_controller;

				package ch4_sensor_controller is
					trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
						Unit_Name => "CH4 sensor controller"
					);
				end ch4_sensor_controller;

				package water_level_sensors_controller is
					trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
						Unit_Name => "Water level sensor controller"
					);
				end water_level_sensors_controller;

				package water_flow_sensor_controller is
					trace_handle : constant GNATCOLL.Traces.Trace_Handle := GNATCOLL.Traces.Create (
						Unit_Name => "Water flow controller"
					);
				end water_flow_sensor_controller;
			end environment_station;
		end mine_water_level_control_system;
	end log;	
end constants;
