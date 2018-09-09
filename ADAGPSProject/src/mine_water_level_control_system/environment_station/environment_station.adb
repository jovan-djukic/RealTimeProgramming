with GNATCOLL.Traces;
with Ada.Real_Time;

with devices;

package body environment_station is
	
	task body gas_sensor_controller_t is
		period : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => period_in_ms
		);

		deadline : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => dealine_in_ms 
		);

		next_activation          : Ada.Real_Time.Time;
		read_error_occured_count : Integer := 0;
		alarm_turned_on			 : Boolean := False;	
	begin
		next_activation := Ada.Real_Time.Clock; 

		running_loop : loop
			select
				accept stop do 
					null;
				end stop;
				exit running_loop;
			else
				select 
					delay Ada.Real_Time.To_Duration (
						TS => deadline
					);

					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Deadline breached"
					);
				then abort
				
				if ( sensor.get_read_error_occurred = True ) then
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Sensor read error occurred"
					);

					read_error_occured_count := read_error_occured_count + 1;
					
					if ( read_error_occured_count >= read_error_occurred_count_threshold ) then
						if ( alarm_turned_on = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Read error count threshold breached, turning on alarm"
							);

							alarm_controller.turn_on (
								stream => stream
							);

							alarm_turned_on := True;
						end if;
					end if;	
				else
					read_error_occured_count := 0;

					if ( sensor.is_threshold_breached = True ) then
						if ( alarm_turned_on = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Threshold breached, turning on alarm"
							);

							alarm_controller.turn_on (
								stream => stream
							);
							alarm_turned_on := True;
						end if;
					else
						if ( alarm_turned_on = True ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "State normal, turning off alarm"
							);

							alarm_controller.turn_off (
								stream => stream
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
			delay until next_activation;

		end loop running_loop;

		
		GNATCOLL.Traces.Trace (
			Handle  => stream,
			Message => "Finished"
		);

	end gas_sensor_controller_t;

	task body ch4_sensor_controller_t is
		period : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => period_in_ms
		);

		deadline : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => dealine_in_ms 
		);

		next_activation          : Ada.Real_Time.Time;
		read_error_occured_count : Integer := 0;
		alarm_turned_on          : Boolean := False;
		pump_threashold_breached : Boolean := False;
	begin
		next_activation := Ada.Real_Time.Clock; 

		running_loop : loop
			select
				accept stop do 
					null;
				end stop;
				exit running_loop;
			else
				select 
					delay Ada.Real_Time.To_Duration (
						TS => deadline
					);

					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Deadline breached"
					);
				then abort
				
				if ( sensor.get_read_error_occurred = True ) then
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Sensor read error occurred"
					);

					read_error_occured_count := read_error_occured_count + 1;
					
					if ( read_error_occured_count >= read_error_occurred_count_threshold ) then
						if ( alarm_turned_on = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Read error count threshold breached, turning on alarm"
							);

							alarm_controller.turn_on (
								stream => stream
							);

							alarm_turned_on := True;
						end if;
					end if;	
				else
					read_error_occured_count := 0;

					if ( sensor.is_threshold_breached = True ) then
						if ( alarm_turned_on = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Threshold breached, turning on alarm"
							);

							alarm_controller.turn_on (
								stream => stream
							);
							alarm_turned_on := True;
						end if;
						
						if ( pump_threashold_breached = False ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Threshold breached, signalizing pump"
							);

							pump_controller.threshold_breached (
								stream => stream
							);

							pump_threashold_breached := True;
						end if;
					else
						if ( alarm_turned_on = True ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "State normal, turning off alarm"
							);

							alarm_controller.turn_off (
								stream => stream
							);

							alarm_turned_on := False;
						end if;

						if ( pump_threashold_breached = True ) then
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "State normal, signalizing pump"
							);

							pump_controller.state_normal (
								stream => stream
							);

							pump_threashold_breached := False;
						end if;
					end if;
				end if;
				
				end select;
			end select;
			
			next_activation := Ada.Real_Time."+" ( 	
				Left  => next_activation,
				Right => period
			);
			delay until next_activation;

		end loop running_loop;

		
		GNATCOLL.Traces.Trace (
			Handle  => stream,
			Message => "Finished"
		);

	end ch4_sensor_controller_t;

	task body water_level_sensors_controller_t is
		deadline : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => dealine_in_ms 
		);
	begin
		running_loop : loop
			water_level_sensors.wait_for_value_change;
			select 
				delay Ada.Real_Time.To_Duration (
					TS => deadline
				);

				GNATCOLL.Traces.Trace (
					Handle  => stream,
					Message => "Deadline breached"
				);
			then abort
				if ( water_level_sensors.is_low_water_level_threshold_breached = True ) then
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Low water level threshold breached"
					);

					pump_controller.turn_off (
						stream => stream
					);
				elsif ( water_level_sensors.is_high_water_level_threshold_breached = True ) then 
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "High water level threshold breached"
					);

					pump_controller.turn_on (
						stream => stream
					);
				end if;
			end select;
		end loop running_loop;
	end water_level_sensors_controller_t;
end environment_station;
