with devices;
	
package body pump_station is
	-- pump controller
	type pump_controller_state_t is ( 
		PUMP_TURNED_OFF,
		PUMP_TURNED_ON,
		METHANE_THREASHOLD_BREACHED
	);

	task body pump_controller_t is
		state      : pump_controller_state_t;
		pump_state : devices.device_state_t;
	begin
		state := PUMP_TURNED_OFF;
		running_loop : loop 

			case state is

				when PUMP_TURNED_OFF => begin
					pump.set_state ( devices.OFF );	
					select
						accept turn_on do
							state := PUMP_TURNED_ON;
						end turn_on;
					or
						accept threshold_breached do
							pump_state := pump.get_state;
							pump.set_state ( devices.OFF );
							state      := METHANE_THREASHOLD_BREACHED;
						end threshold_breached;
					or
						accept stop;
						exit running_loop;
					end select;
				end;

				when PUMP_TURNED_ON => begin
					pump.set_state ( devices.OFF );	
					select
						accept turn_off do
							state := PUMP_TURNED_OFF;
						end turn_off;
					or
						accept threshold_breached do
							pump_state := pump.get_state;
							pump.set_state ( devices.OFF );
							state      := METHANE_THREASHOLD_BREACHED;
						end threshold_breached;
					or
						accept stop;
						exit running_loop;
					end select;
				end;
				
				when METHANE_THREASHOLD_BREACHED => begin
					select
						accept turn_on do 
							null;
						end turn_on;
					or
						accept turn_off do
							null;
						end turn_off;
					or 
						accept state_normal do
							null;
						end state_normal;
					end select;
				end;
			end case;	
		end loop running_loop;
	end;
end pump_station;
