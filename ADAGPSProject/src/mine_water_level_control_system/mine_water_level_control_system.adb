with devices;
with pump_station;
	
package body mine_water_level_control_system is
	task body top_t is
		pump_controller : access pump_station.pump_controller_t;
	begin
		pump_controller := new pump_station.pump_controller_t (
			pump => pump
		);
		loop
			select
				accept turn_on;
				pump_controller.turn_on;
			or
				accept turn_off;
				pump_controller.turn_off;
			end select;
		end loop;
	end top_t;

end mine_water_level_control_system;
