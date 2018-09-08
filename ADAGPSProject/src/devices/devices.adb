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
			if ( new_state /= state ) then
				changed := True;
			end if;

			state   := new_state;
		end set_state;

		entry wait_for_state_change when ( changed = True ) is
		begin
			changed := False;
		end wait_for_state_change;
	end device_t;

	protected body sensor_t is
		function get_value return Float is
		begin
			return value;
		end get_value;

		function get_read_error_occurred return Boolean is
		begin
			return read_error_occurred;
		end get_read_error_occurred;

		function get_threshold return Float is
		begin
			return threshold.all;
		end get_threshold;

		procedure set_value (
			new_value : Float
		) is
		begin
			if ( new_value /= value ) then
				if ( new_value <= 0.0 ) then
					value := 0.0;
				elsif ( new_value >= maximum_value.all ) then
					value := maximum_value.all;
				else
					value   := new_value;
				end if;
				changed := True;
			end if;
		end set_value;

		procedure set_read_error_occured (
			new_read_error_occurred : Boolean
		) is
		begin
			if ( new_read_error_occurred /= read_error_occurred ) then
				read_error_occurred := new_read_error_occurred;
				changed             := True;
			end if;
		end set_read_error_occured;

		function is_threshold_breached return Boolean is
		begin
			if ( detects_above_threshold = True ) then
				return ( value > threshold.all );
			else
				return ( value < threshold.all );
			end if;
		end is_threshold_breached;

		entry wait_for_value_change when ( changed = True ) is
		begin
			changed := False;
		end wait_for_value_change;

	end sensor_t;
end devices;

