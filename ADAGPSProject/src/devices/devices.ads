with GNATCOLL.Traces;
with Ada.Real_Time;

package devices is

	type device_state_t is ( ON, OFF );

	protected type device_t (
		name : access constant String
	) is
		function get_state (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return device_state_t;

		procedure set_state ( 
			trace_handle : in GNATCOLL.Traces.Trace_Handle;
			new_state 	 : in device_state_t 
		);

		-- gui entry for activating controller task
		-- no logging since it will be displayed on the screen
		entry wait_for_change (
			out_state : out device_state_t
		);
	private
		state   : device_state_t := OFF;
		changed : boolean        := True;
	end device_t;

	protected type gas_sensor_t (
		name 						 : access constant String;
		conversion_time_in_ms		 : Integer;
		initial_value           	 : access constant Float;
		initial_read_error_occurred : Boolean
	) is
		procedure start_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		);

		function check_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean;

		function get_value (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Float;

		procedure set_value (
			trace_handle : in GNATCOLL.Traces.Trace_Handle;
			new_value    : in Float
		);

		function get_read_error_occurred (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean;

		procedure set_read_error_occured (
			trace_handle            : in GNATCOLL.Traces.Trace_Handle;
			new_read_error_occurred : Boolean
		);

		-- gui entry for activating controller task
		-- no logging since it will be displayed on the screen
		entry wait_for_change (
			out_value 				: out Float;
			out_read_error_occurred : out Boolean
		);
	private
		value               : Float   := initial_value.all;
		read_error_occurred : Boolean := initial_read_error_occurred;
		changed             : Boolean := True;

		last_conversion_start_time : Ada.Real_time.Time := Ada.Real_Time."-" (
			Left  => Ada.Real_Time.Clock,
			Right => Ada.Real_Time.Milliseconds (
				MS => conversion_time_in_ms
			)
		);

		conversion_time : Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds (
			MS => conversion_time_in_ms
		);
	end gas_sensor_t;

	protected type water_flow_sensor_t (
		name                  : access constant String;
		conversion_time_in_ms : Integer;
		initial_water_flowing : Boolean
	) is
		procedure start_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		);

		function check_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean;

		function is_water_flowing (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean;

		procedure set_water_flowing (
			trace_handle 	  : in GNATCOLL.Traces.Trace_Handle;
			new_water_flowing : in Boolean 
		);

		-- gui entry for activating controller task
		-- no logging since it will be displayed on the screen
		entry wait_for_change (
			out_water_flowing : out Boolean
		);
	private
		water_flowing : Boolean := initial_water_flowing;
		changed		  : Boolean := True;

		last_conversion_start_time : Ada.Real_time.Time := Ada.Real_Time."-" (
			Left  => Ada.Real_Time.Clock,
			Right => Ada.Real_Time.Milliseconds (
				MS => conversion_time_in_ms
			)
		);

		conversion_time : Ada.Real_Time.Time_Span := Ada.Real_Time.Milliseconds (
			MS => conversion_time_in_ms
		);
	end water_flow_sensor_t;

	protected type water_level_sensors_t (
		name                  	   : access constant String;
		initial_water_level        : access constant Float;
		low_water_level_threshold  : access constant Float;
		high_water_level_threshold : access constant Float
	) is

		entry wait_for_interrupt;
		
		function is_low_water_level_threshold_breached (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean;

		function is_high_water_level_threshold_breached (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean;

		-- gui procedure for updating
		-- no logging since it will be displayed on the screen
		procedure update (
			water_level : Float
		);
	private
		interrupt_pending                   : Boolean := ( ( initial_water_level.all > high_water_level_threshold.all )  or  ( initial_water_level.all < low_water_level_threshold.all ) );
		first_time                          : Boolean := not ( ( initial_water_level.all > high_water_level_threshold.all )  or  ( initial_water_level.all < low_water_level_threshold.all ) );
		low_water_level_threshold_breached  : Boolean := ( initial_water_level.all < low_water_level_threshold.all );
		high_water_level_threshold_breached : Boolean := ( initial_water_level.all > high_water_level_threshold.all );
	end water_level_sensors_t;


end devices;
