package body devices is

	protected body device_t is
		function get_state return device_state_t is
		begin
			return state;
		end get_state;
		
		procedure set_state ( 
			new_state : in device_state_t 
		) is 
		begin
			state   := new_state;
			changed := True;
		end set_state;
		
		entry wait_for_state_change when changed = True is
		begin
			changed := False;
		end wait_for_state_change;
	end device_t;

end devices;

