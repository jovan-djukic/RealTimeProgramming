package devices is

	type device_state_t is ( ON, OFF );

	protected type device_t is
		function get_state return device_state_t;
		procedure set_state ( 
			new_state : in device_state_t 
		);
		entry wait_for_state_change;
	private
		state   : device_state_t := OFF;
		changed : boolean        := True;
	end device_t;

	protected type sensor_t (
		intital_value           : access constant Float;
		threshold               : access constant Float;
		detects_above_threshold : Boolean
	) is
		function get_value return Float;
		function get_read_error_occurred return Boolean;
		function get_threshold return Float;
		procedure set_value (
			new_value : Float
		);
		procedure set_read_error_occured (
			new_read_error_occurred : Boolean
		);
		function is_threshold_breached return Boolean;
		entry wait_for_value_change;
	private
		value               : Float   := intital_value.all;
		read_error_occurred : Boolean := False;
		changed             : Boolean := True;
	end sensor_t;

end devices;
