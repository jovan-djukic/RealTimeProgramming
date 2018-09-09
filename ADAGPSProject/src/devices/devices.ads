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
		maximum_value           : access constant Float;
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

	protected type water_level_sensors_t (
		initial_water_level        : access constant Float;
		low_water_level_threshold  : access constant Float;
		high_water_level_threshold : access constant Float
	) is
		function is_low_water_level_threshold_breached return Boolean;

		function is_high_water_level_threshold_breached return Boolean;

		function get_low_water_level_threshold return Float;

		function get_high_water_level_threshold return Float;

		procedure update (
			water_level : Float
		);
		
		entry wait_for_value_change;
	private
		breached                            : Boolean := ( ( initial_water_level.all > high_water_level_threshold.all )  or  ( initial_water_level.all < low_water_level_threshold.all ) );
		first_time                          : Boolean := not ( ( initial_water_level.all > high_water_level_threshold.all )  or  ( initial_water_level.all < low_water_level_threshold.all ) );
		low_water_level_threshold_breached  : Boolean := ( initial_water_level.all < low_water_level_threshold.all );
		high_water_level_threshold_breached : Boolean := ( initial_water_level.all > high_water_level_threshold.all );
	end water_level_sensors_t;

	protected type water_flow_sensor_t is

		function is_water_flowing return Boolean;

		procedure set_water_flowing (
			new_water_flowing : Boolean 
		);
		
		entry wait_for_value_change;
	private
		water_flowing : Boolean := False;
		changed		  : Boolean := True;
	end water_flow_sensor_t;

end devices;
