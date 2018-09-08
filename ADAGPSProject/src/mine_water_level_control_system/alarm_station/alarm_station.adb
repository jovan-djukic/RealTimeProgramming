with GNATCOLL.Traces;

with constants;
with devices;

package body alarm_station is
	type alarm_state_t is ( 
		ALARM_TURNED_ON,
		ALARM_TURNED_OFF
	);

	task body alarm_controller_t  is
		number_of_activations : Integer := 0;
		state                 : alarm_state_t;
	begin
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.mine_water_level_control_system.top.stream,
			Message => "Starting"
		);

		state := ALARM_TURNED_OFF;
		
		running_loop : loop
			case ( state ) is
				when ALARM_TURNED_OFF => 
					alarm.set_state (
						new_state => devices.OFF
					);
					
					select 
						accept turn_on do 
							null;
						end turn_on;
						GNATCOLL.Traces.Trace (
							Handle  => constants.log.mine_water_level_control_system.alarm_controller.stream,
							Message => "Initial turn on received, turning on alarm"
						);

						state := ALARM_TURNED_ON;
						number_of_activations := number_of_activations + 1;
					or
						accept turn_off do
							null;
						end turn_off;
					or
						accept stop do
							null;
						end stop;
						exit running_loop;
					end select;

				when ALARM_TURNED_ON  => 
					alarm.set_state (
						new_state => devices.ON
					);

					select 
						accept turn_on do
							null;
						end turn_on;
						GNATCOLL.Traces.Trace (
							Handle  => constants.log.mine_water_level_control_system.alarm_controller.stream,
							Message => "Following turn on received"
						);

						number_of_activations := number_of_activations + 1;
					or
						accept turn_off do
							null;
						end turn_off;

						number_of_activations := number_of_activations - 1;
						if ( number_of_activations = 0 ) then
							GNATCOLL.Traces.Trace (
								Handle  => constants.log.mine_water_level_control_system.alarm_controller.stream,
								Message => "Last turn off received turning off alarm"
							);

							state := ALARM_TURNED_OFF;
						else
							GNATCOLL.Traces.Trace (
								Handle  => constants.log.mine_water_level_control_system.alarm_controller.stream,
								Message => "Turn off received"
							);
						end if;
					or
						accept stop do
							null;
						end stop;
						exit running_loop;
					end select;
			end case;	
		end loop running_loop;

		GNATCOLL.Traces.Trace (
			Handle  => constants.log.mine_water_level_control_system.alarm_controller.stream,
			Message => "Finished"
		);
	end alarm_controller_t;	
end alarm_station;
