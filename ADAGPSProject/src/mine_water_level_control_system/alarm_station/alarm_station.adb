with GNATCOLL.Traces;

with constants;
with devices;

package body alarm_station is

	protected body alarm_controller_t  is
		procedure turn_on (
			stream : in GNATCOLL.Traces.Trace_Handle
		) is 
		begin
			case ( state ) is
				when ALARM_TURNED_OFF => begin
					alarm.set_state (
						new_state => devices.ON
					);
					
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Initial alarm turn on, turning on alarm"
					);

					state                 := ALARM_TURNED_ON;
					number_of_activations := number_of_activations + 1;
				end;

				when ALARM_TURNED_ON  => begin
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Following alarm turn on"
					);

					number_of_activations := number_of_activations + 1;
				end;
			end case;	
		end turn_on;	

		procedure turn_off (
			stream : in GNATCOLL.Traces.Trace_Handle
		) is 
		begin
			case ( state ) is
				when ALARM_TURNED_OFF => begin
					null;
				end;

				when ALARM_TURNED_ON  => begin
					number_of_activations := number_of_activations - 1;

					if ( number_of_activations = 0 ) then
						GNATCOLL.Traces.Trace (
							Handle  => stream,
							Message => "Last alarm turn off, turning off alarm"
						);

						alarm.set_state (
							new_state => devices.OFF
						);

						state := ALARM_TURNED_OFF;
					else
						GNATCOLL.Traces.Trace (
							Handle  => stream,
							Message => "Alarm turn off"
						);
					end if;
				end;
			end case;	
		end turn_off;	

	end alarm_controller_t;	
end alarm_station;
