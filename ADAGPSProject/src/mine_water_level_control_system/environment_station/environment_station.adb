with GNATCOLL.Traces;
with Ada.Real_Time;

with devices;

with Ada.Text_IO;

package body environment_station is
	
	task body gas_sensor_controller_t is
		period : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => period_in_ms
		);

		deadline : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => dealine_in_ms 
		);

		next_activation            : Ada.Real_Time.Time;
		next_deadline              : Ada.Real_Time.Time;
		read_error_occurred_count  : Integer := 0;
		alarm_turned_on            : Boolean := False;
		sensor_value               : Float;
		sensor_read_error_occurred : Boolean;
		is_threshold_breached      : Boolean;
	begin
		next_activation := Ada.Real_Time.Clock;
		next_deadline   := Ada.Real_Time."+" (
			Left  => next_activation,
			Right => deadline
		);

		running_loop : loop
			select
				accept stop do 
					null;
				end stop;
				exit running_loop;
			else
				select 
					delay until next_deadline;

					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Deadline breached"
					);

					Ada.Text_IO.Put_Line (
						Item => "Deadline breached"
					);
				then abort
				
					sensor_read_error_occurred := sensor.get_read_error_occurred (
						trace_handle => trace_handle
					);
					
					if ( sensor_read_error_occurred = True ) then
						GNATCOLL.Traces.Trace (
							Handle  => trace_handle,
							Message => "Sensor read error occurred"
						);

						read_error_occurred_count := read_error_occurred_count + 1;
						
						if ( read_error_occurred_count >= read_error_occurred_count_threshold ) then
							if ( alarm_turned_on = False ) then
								GNATCOLL.Traces.Trace (
									Handle  => trace_handle,
									Message => "Read error count threshold breached, turning on alarm"
								);

								alarm_controller.turn_on (
									trace_handle => trace_handle
								);

								alarm_turned_on := True;
							end if;
						end if;	
					else
						read_error_occurred_count := 0;

						sensor_value := sensor.get_value (
							trace_handle => trace_handle 
						);

						if ( detect_above_threshold = True ) then
							if ( sensor_value > threshold.all ) then
								is_threshold_breached := True;
							else 
								is_threshold_breached := False;
							end if;
						else
							if ( sensor_value < threshold.all ) then
								is_threshold_breached := True;
							else 
								is_threshold_breached := False;
							end if;
						end if;

						if ( is_threshold_breached = True ) then
							if ( alarm_turned_on = False ) then
								GNATCOLL.Traces.Trace (
									Handle  => trace_handle,
									Message => "Threshold breached, turning on alarm"
								);

								alarm_controller.turn_on (
									trace_handle => trace_handle
								);
								alarm_turned_on := True;
							end if;
						else
							if ( alarm_turned_on = True ) then
								GNATCOLL.Traces.Trace (
									Handle  => trace_handle,
									Message => "State normal, turning off alarm"
								);

								alarm_controller.turn_off (
									trace_handle => trace_handle
								);

								alarm_turned_on := False;
							end if;
						end if;
					end if;
				
				end select;
			end select;
			
			next_activation := Ada.Real_Time."+" ( 	
				Left  => next_activation,
				Right => period
			);

			next_deadline   := Ada.Real_Time."+" (
				Left  => next_activation,
				Right => deadline
			);

			delay until next_activation;

		end loop running_loop;

		
		GNATCOLL.Traces.Trace (
			Handle  => trace_handle,
			Message => "Finished"
		);
	
		exception
			when others =>
				Ada.Text_IO.Put_Line ( "EXCEPTION" );
				raise;
	end gas_sensor_controller_t;

	task body ch4_sensor_controller_t is
		period : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => period_in_ms
		);

		deadline : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => dealine_in_ms 
		);

		next_activation            : Ada.Real_Time.Time;
		next_deadline              : Ada.Real_Time.Time;
		read_error_occurred_count  : Integer := 0;
		alarm_turned_on            : Boolean := False;
		pump_threashold_breached   : Boolean := False;
		sensor_value               : Float;
		sensor_read_error_occurred : Boolean;
	begin
		next_activation := Ada.Real_Time.Clock;
		next_deadline   := Ada.Real_Time."+" (
			Left  => next_activation,
			Right => deadline
		);

		running_loop : loop
			select
				accept stop do 
					null;
				end stop;
				exit running_loop;
			else
				select 
					delay until next_deadline;

					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Deadline breached"
					);

					Ada.Text_IO.Put_Line (
						Item => "Deadline breached"
					);
				then abort

				sensor_read_error_occurred := sensor.get_read_error_occurred (
					trace_handle => trace_handle
				);
				
				if ( sensor_read_error_occurred = True ) then
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Sensor read error occurred"
					);

					read_error_occurred_count := read_error_occurred_count + 1;
					
					if ( read_error_occurred_count >= read_error_occurred_count_threshold ) then
						if ( alarm_turned_on = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => trace_handle,
								Message => "Read error count threshold breached, turning on alarm"
							);

							alarm_controller.turn_on (
								trace_handle => trace_handle
							);

							alarm_turned_on := True;
						end if;
					end if;	
				else
					read_error_occurred_count := 0;

					sensor_value := sensor.get_value (
						trace_handle => trace_handle
					);

					if ( sensor_value > threshold.all ) then
						if ( alarm_turned_on = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => trace_handle,
								Message => "Threshold breached, turning on alarm"
							);

							alarm_controller.turn_on (
								trace_handle => trace_handle
							);
							alarm_turned_on := True;
						end if;
						
						if ( pump_threashold_breached = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => trace_handle,
								Message => "Threshold breached, signalizing pump"
							);

							pump_controller.threshold_breached (
								trace_handle => trace_handle
							);

							pump_threashold_breached := True;
						end if;
					else
						if ( alarm_turned_on = True ) then
							GNATCOLL.Traces.Trace (
								Handle  => trace_handle,
								Message => "State normal, turning off alarm"
							);

							alarm_controller.turn_off (
								trace_handle => trace_handle
							);

							alarm_turned_on := False;
						end if;

						if ( pump_threashold_breached = True ) then
							GNATCOLL.Traces.Trace (
								Handle  => trace_handle,
								Message => "State normal, signalizing pump"
							);

							pump_controller.state_normal (
								trace_handle => trace_handle
							);

							pump_threashold_breached := False;
						end if;
					end if;
				end if;
				
				end select;
			end select;

			sensor.start_conversion (
				trace_handle => trace_handle
			);
			
			next_activation := Ada.Real_Time."+" ( 	
				Left  => next_activation,
				Right => period
			);

			next_deadline   := Ada.Real_Time."+" (
				Left  => next_activation,
				Right => deadline
			);

			delay until next_activation;

		end loop running_loop;

		
		GNATCOLL.Traces.Trace (
			Handle  => trace_handle,
			Message => "Finished"
		);

	end ch4_sensor_controller_t;

	task body water_level_sensors_controller_t is
		deadline : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => dealine_in_ms 
		);
		
		is_low_water_level_threshold_breached  : Boolean;
		is_high_water_level_threshold_breached : Boolean;
	begin
		running_loop : loop
			water_level_sensors.wait_for_interrupt;

			select 
				delay Ada.Real_Time.To_Duration (
					TS => deadline
				);

				GNATCOLL.Traces.Trace (
					Handle  => trace_handle,
					Message => "Deadline breached"
				);
			then abort
				is_low_water_level_threshold_breached := water_level_sensors.is_low_water_level_threshold_breached (
					trace_handle => trace_handle
				);

				is_high_water_level_threshold_breached := water_level_sensors.is_high_water_level_threshold_breached (
					trace_handle => trace_handle
				);

				if ( is_low_water_level_threshold_breached = True ) then
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Low water level threshold breached"
					);

					pump_controller.turn_off (
						trace_handle => trace_handle
					);
				elsif ( is_high_water_level_threshold_breached = True ) then 
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "High water level threshold breached"
					);

					pump_controller.turn_on (
						trace_handle => trace_handle
					);
				end if;
			end select;

		end loop running_loop;
	end water_level_sensors_controller_t;

end environment_station;
