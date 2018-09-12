with Ada.Real_Time;

with GNATCOLL.Traces;
with devices;

with Ada.Text_IO;

package body pump_station is
	protected body pump_controller_t is
		procedure turn_on (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) is
		begin
			case ( state ) is
				when PUMP_TURNED_OFF => begin

					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Turning on pump"
					);

					state := PUMP_TURNED_ON;
					pump.set_state (
						trace_handle => trace_handle,
						new_state    => devices.ON
					);	
				end;

				when PUMP_TURNED_ON => begin
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Pump already on"
					);
				end;
				
				when METHANE_THREASHOLD_BREACHED => begin
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Turning on pump while methane threshold breached"
					);

					pump_state := devices.ON;
				end;
			end case;	
		end turn_on;

		procedure turn_off (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) is
		begin
			case ( state ) is
				when PUMP_TURNED_OFF => begin
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Pump already off"
					);
				end;

				when PUMP_TURNED_ON => begin
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Turning off pump"
					);

					state := PUMP_TURNED_OFF;
					pump.set_state ( 
						trace_handle => trace_handle,
						new_state    => devices.OFF
					);	
				end;
				
				when METHANE_THREASHOLD_BREACHED => begin
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Turning off pump received while methane threshold breached"
					);

					pump_state := devices.OFF;
				end;
			end case;	
		end turn_off;

		procedure threshold_breached (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Threshold breached, turning off pump"
			);

			state      := METHANE_THREASHOLD_BREACHED;

			pump_state := pump.get_state (
				trace_handle => trace_handle
			);

			pump.set_state ( 
				trace_handle => trace_handle,
				new_state    => devices.OFF
			);
		end threshold_breached;

		procedure state_normal (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) is
			was_pump_turned_on : Boolean;
		begin
			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "State normal, returning pump to previous state"
			);

			was_pump_turned_on := devices."=" ( 
				Left  => pump_state,
				Right => devices.ON
			);

			if ( was_pump_turned_on = True ) then
				state := PUMP_TURNED_ON;

				pump.set_state (
					trace_handle => trace_handle,
					new_state    => devices.ON
				);
			else
				state := PUMP_TURNED_OFF;
				pump.set_state (
					trace_handle => trace_handle,
					new_state    => devices.OFF
				);
			end if;
		end state_normal;
	end pump_controller_t;

	type water_flow_sensor_controller_state_t is (
		STATE_NORMAL,
		CHECKING,
		ALARM
	);

	task body water_flow_sensor_controller_t is
		period : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => period_in_ms
		);

		deadline : constant Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds ( 
			MS => deadline_in_ms 
		);

		next_activation    : Ada.Real_Time.Time                   := Ada.Real_Time.Clock;
		state              : water_flow_sensor_controller_state_t := STATE_NORMAL;
		current_activation : Integer                              := 0;

		pump_state : devices.device_state_t := pump.get_state (
			trace_handle => trace_handle
		);

		function is_expected_water_flow return Boolean is
			current_pump_state : devices.device_state_t;
			is_pump_on         : Boolean;
			is_water_flowing      : Boolean;
		begin
			current_pump_state := pump.get_state (
				trace_handle => trace_handle
			);

			is_pump_on := devices."=" ( 
				Left  => current_pump_state,
				Right => devices.ON
			);

			is_water_flowing := water_flow_sensor.is_water_flowing (
				trace_handle => trace_handle
			);

			if ( ( ( is_pump_on = True ) and ( is_water_flowing = True ) ) or ( ( is_pump_on = False ) and ( is_water_flowing = False ) ) ) then
				return True;
			else 
				return False;	
			end if;
		end is_expected_water_flow;
		
		function is_pump_state_same return Boolean is
			current_pump_state : devices.device_state_t;
		begin
			current_pump_state := pump.get_state (
				trace_handle => trace_handle
			);

			return devices."=" (
				Left  => current_pump_state,
				Right => pump_state
			);
		end is_pump_state_same;		
	begin


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
						Handle  => trace_handle,
						Message => "Deadline breached"
					);

					Ada.Text_IO.Put_Line (
						Item => "Deadline breached by water flow sensor controller"
					);

				then abort
					case ( state ) is
						when STATE_NORMAL => begin
							if ( ( is_pump_state_same = False ) or ( is_expected_water_flow = False ) ) then
								GNATCOLL.Traces.Trace (
									Handle  => trace_handle,
									Message => "Pump state changed or water flow sensor broken"
								);

								current_activation := 0;
								state              := CHECKING;
							end if;
						end;
						
						when CHECKING => begin
							if ( is_expected_water_flow = True ) then
								GNATCOLL.Traces.Trace (
									Handle  => trace_handle,
									Message => "Pump is in previous state or water flow sensor fixed"
								);

								current_activation := 0;
								state              := STATE_NORMAL;

								pump_state := pump.get_state (
									trace_handle => trace_handle
								);
							else
								current_activation := current_activation + 1;
								
								if ( current_activation = number_of_activations ) then
									GNATCOLL.Traces.Trace (
										Handle  => trace_handle,
										Message => "Water flow sensor deadline passed, turning on alarm"
									);

									alarm_controller.turn_on ( 
										trace_handle => trace_handle
									);

									current_activation := 0;
									state              := ALARM;
								else
									GNATCOLL.Traces.Trace (
										Handle  => trace_handle,
										Message => "Check failed, current activation: " & current_activation'Image & " / " & number_of_activations'Image
									);
								end if;
							end if;
						end;
						
						when ALARM => begin
							if ( is_expected_water_flow = True ) then
								GNATCOLL.Traces.Trace (
									Handle  => trace_handle,
									Message => "Pump is in previous state or water flow sensor fixed, turning off alarm"
								);

								alarm_controller.turn_off ( 
									trace_handle => trace_handle
								);

								current_activation := 0;
								state              := CHECKING;
							end if;
						end;
					end case;
				end select;
			end select;

			water_flow_sensor.start_conversion (
				trace_handle => trace_handle
			);
			
			next_activation := Ada.Real_Time."+" ( 	
				Left  => next_activation,
				Right => period
			);
			delay until next_activation;

		end loop running_loop;
		
		GNATCOLL.Traces.Trace (
			Handle  => trace_handle,
			Message => "Finished"
		);
	end water_flow_sensor_controller_t;

end pump_station;
