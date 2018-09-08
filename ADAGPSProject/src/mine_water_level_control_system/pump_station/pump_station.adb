with GNATCOLL.Traces;

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
		GNATCOLL.Traces.Trace (
			Handle  => stream,
			Message => "Starting"
		);

		state := PUMP_TURNED_OFF;
		running_loop : loop 
			case state is

				when PUMP_TURNED_OFF => begin
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Turning off pump"
					);

					pump.set_state (
						new_state => devices.OFF 
					);	

					select
						accept turn_on do
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Turn on received"
							);

							state := PUMP_TURNED_ON;
						end turn_on;
					or
						accept turn_off do
							null;
						end turn_off;
					or
						accept threshold_breached do
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Threshold breached received"
							);

							pump_state := pump.get_state;
							state      := METHANE_THREASHOLD_BREACHED;
						end threshold_breached;
					or
						accept state_normal do
							null;
						end state_normal;
					or
						accept stop do
							null;
						end stop;
						exit running_loop;
					end select;
				end;

				when PUMP_TURNED_ON => begin
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Turning on pump"
					);

					pump.set_state ( 
						new_state => devices.ON 
					);	

					select
						accept turn_on do
							null;
						end turn_on;
					or
						accept turn_off do
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Turn off received"
							);

							state := PUMP_TURNED_OFF;
						end turn_off;
					or
						accept threshold_breached do
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Threashold breached received"
							);

							pump_state := pump.get_state;
							state      := METHANE_THREASHOLD_BREACHED;
						end threshold_breached;
					or
						accept state_normal do
							null;
						end state_normal;
					or
						accept stop do
							null;
						end stop;	
						exit running_loop;
					end select;
				end;
				
				when METHANE_THREASHOLD_BREACHED => begin
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Turning off pump"
					);

					pump.set_state ( 
						new_state => devices.OFF 
					);	

					select
						accept turn_on do 
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Turning on pump received while methane threshold breached"
							);

							pump_state := devices.ON;
						end turn_on;
					or
						accept turn_off do
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "Turning off pump received while methane threshold breached"
							);

							pump_state := devices.OFF;
						end turn_off;
					or
						accept threshold_breached do
							null;
						end threshold_breached;
					or 
						accept state_normal do
							GNATCOLL.Traces.Trace (
								Handle  => stream,
								Message => "State normal received"
							);
	
							if ( devices."=" ( pump_state, devices.ON ) ) then
								state := PUMP_TURNED_ON;
							else
								state := PUMP_TURNED_OFF;
							end if;
						end state_normal;
					or
						accept stop do
							null;
						end stop;
						exit running_loop;
					end select;
				end;
			end case;	
		end loop running_loop;

		GNATCOLL.Traces.Trace (
			Handle  => stream,
			Message => "Finished"
		);
	end;
end pump_station;
