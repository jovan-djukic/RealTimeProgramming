with GNATCOLL.Traces;

with constants;
with devices;
with pump_station;
with alarm_station;

package body mine_water_level_control_system is
	task body top_t is
		pump_controller  : access pump_station.pump_controller_t;
		alarm_controller : access alarm_station.alarm_controller_t;
	begin
		GNATCOLL.Traces.Trace (
			Handle  => constants.log.mine_water_level_control_system.top.stream,
			Message => "Starting"
		);

		pump_controller := new pump_station.pump_controller_t (
			pump => pump 
		);

		alarm_controller := new alarm_station.alarm_controller_t (
			alarm => alarm 
		);	

		running_loop : loop
			select
				accept turn_on do
					null;
				end turn_on;
				pump_controller.turn_on;
			or
				accept turn_off do
					null;
				end turn_off;
				pump_controller.turn_off;
			or
				accept stop do
					GNATCOLL.Traces.Trace (
						Handle  => constants.log.mine_water_level_control_system.top.stream,
						Message => "Stopping pump controller"
					);

					pump_controller.stop;	

					GNATCOLL.Traces.Trace (
						Handle  => constants.log.mine_water_level_control_system.top.stream,
						Message => "Stopping alarm controller"
					);

					alarm_controller.stop;
				end stop;
					exit running_loop;
			end select;
		end loop running_loop;

		GNATCOLL.Traces.Trace (
			Handle  => constants.log.mine_water_level_control_system.top.stream,
			Message => "Finished"
		);
	end top_t;

end mine_water_level_control_system;
