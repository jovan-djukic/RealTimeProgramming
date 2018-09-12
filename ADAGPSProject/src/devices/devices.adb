with GNATCOLL.Traces;

with Ada.Text_IO;

package body devices is

	protected body device_t is
		function get_state (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return device_state_t is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Getting state for device " & name.all & " which is " & state'Image
			);
			return state;
		end get_state;
		
		procedure set_state ( 
			trace_handle : in GNATCOLL.Traces.Trace_Handle;
			new_state 	 : in device_state_t 
		) is 
		begin
			if ( new_state /= state ) then
				changed := True;
			end if;

			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Setting state for device " & name.all & " to " & new_state'Image
			);

			state := new_state;
		end set_state;

		entry wait_for_change (
			out_state : out device_state_t
		) when ( changed = True ) is
		begin
			changed   := False;
			out_state := state;
		end wait_for_change;

	end device_t;

	protected body gas_sensor_t is

		procedure start_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) is 
		begin
			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Starting conversion for sensor " & name.all
			);
			
			last_conversion_start_time := Ada.Real_Time.Clock;
		end start_conversion;

		function check_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean is 
			elapsed_time              : Ada.Real_Time.Time_Span;
			is_conversion_successfull : Boolean; 
		begin

			elapsed_time := Ada.Real_Time."-" (
				Left  => Ada.Real_Time.Clock,
				Right => last_conversion_start_time
			);	

			is_conversion_successfull := Ada.Real_Time.">=" (
				Left  => elapsed_time,
				Right => conversion_time
			); 

			if ( is_conversion_successfull = False ) then
				GNATCOLL.Traces.Trace (
					Handle  => trace_handle,
					Message => "Conversion unsusccessful for sensor " & name.all
				);

				Ada.Text_IO.Put_Line (
					Item => "Conversion unsusccessful for sensor " & name.all
				);
			end if;
	
			return is_conversion_successfull;
		end check_conversion;

		function get_value (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Float is
			is_conversion_successfull : Boolean;
			return_value              : Float;
		begin
			is_conversion_successfull := check_conversion (
				trace_handle => trace_handle
			);

			if ( is_conversion_successfull = True ) then 
				return_value := value;
			else 
				return_value := 0.0;
			end if;

			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Getting value for sensor " & name.all & " which is " & return_value'Image
			);
			
			return return_value;
		end get_value;

		procedure set_value (
			trace_handle : in GNATCOLL.Traces.Trace_Handle;
			new_value    : in Float
		) is
		begin
			if ( new_value /= value ) then
				changed := True;
			end if;

			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Setting value for sensor " & name.all & " to " & new_value'Image
			);

			value := new_value;
		end set_value;


		function get_read_error_occurred (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean is
			is_conversion_successfull  : Boolean;
			return_read_error_occurred : Boolean;
		begin
			is_conversion_successfull := check_conversion (
				trace_handle => trace_handle
			);


			if ( is_conversion_successfull = True ) then 
				return_read_error_occurred := read_error_occurred;
			else 
				return_read_error_occurred := true;
			end if;

			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Getting read error occurred for sensor " & name.all & " which is " & return_read_error_occurred'Image
			);

			return return_read_error_occurred;
		end get_read_error_occurred;

		procedure set_read_error_occured (
			trace_handle            : in GNATCOLL.Traces.Trace_Handle;
			new_read_error_occurred : Boolean
		) is
		begin
			if ( new_read_error_occurred /= read_error_occurred ) then
				changed := True;
			end if;

			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Setting read error occurred for sensor " & name.all & " to " & new_read_error_occurred'Image
			);

			read_error_occurred := new_read_error_occurred;
		end set_read_error_occured;

		entry wait_for_change	(
			out_value 				: out Float;
			out_read_error_occurred : out Boolean
		) when ( changed = True ) is
		begin
			changed                 := False;
			out_value               := value;
			out_read_error_occurred := read_error_occurred;
		end wait_for_change;

	end gas_sensor_t;

	protected body water_flow_sensor_t is
		procedure start_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) is 
		begin
			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Starting conversion for sensor " & name.all
			);
			
			last_conversion_start_time := Ada.Real_Time.Clock;
		end start_conversion;

		function check_conversion (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean is 
			elapsed_time              : Ada.Real_Time.Time_Span;
			is_conversion_successfull : Boolean; 
		begin
			elapsed_time := Ada.Real_Time."-" (
				Left  => Ada.Real_Time.Clock,
				Right => last_conversion_start_time
			);	

			is_conversion_successfull := Ada.Real_Time.">=" (
				Left  => elapsed_time,
				Right => conversion_time
			); 

			if ( is_conversion_successfull = False ) then
				GNATCOLL.Traces.Trace (
					Handle  => trace_handle,
					Message => "Conversion unsusccessful for sensor " & name.all
				);

				Ada.Text_IO.Put_Line (
					Item => "Conversion unsusccessful for sensor " & name.all
				);
			end if;
	
			return is_conversion_successfull;
		end check_conversion;

		function is_water_flowing (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean is
			is_conversion_successfull : Boolean;
			return_is_water_flowing   : Boolean;
		begin
			is_conversion_successfull := check_conversion (
				trace_handle => trace_handle
			);

			if ( is_conversion_successfull = True ) then 
				return_is_water_flowing := water_flowing;
			else 
				return_is_water_flowing := false;
			end if;

			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Getting water flowing for sensor " & name.all & " which is " & return_is_water_flowing'Image
			);
			
			return return_is_water_flowing;	
		end is_water_flowing;


		procedure set_water_flowing (
			trace_handle 	  : in GNATCOLL.Traces.Trace_Handle;
			new_water_flowing : in Boolean 
		) is
		begin
			if ( new_water_flowing /= water_flowing ) then
				changed := True;
			end if;

			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Setting water fowing for sensor " & name.all & " to " & new_water_flowing'Image
			);

			water_flowing := new_water_flowing;
		end set_water_flowing;

		entry wait_for_change (
			out_water_flowing : out Boolean
		) when ( changed = True ) is
		begin
			changed           := False;
			out_water_flowing := water_flowing;
		end wait_for_change;

	end water_flow_sensor_t;

	protected body water_level_sensors_t is
		entry wait_for_interrupt when ( interrupt_pending = True ) is
		begin
			interrupt_pending := False;
		end wait_for_interrupt;

		function is_low_water_level_threshold_breached (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Getting low water level threshold breached for sensor " & name.all & " which is " & low_water_level_threshold_breached'Image
			);

			return low_water_level_threshold_breached; 	
		end is_low_water_level_threshold_breached;

		function is_high_water_level_threshold_breached (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		) return Boolean is
		begin
			GNATCOLL.Traces.Trace (
				Handle  => trace_handle,
				Message => "Getting high water level threshold breached for sensor " & name.all & " which is " & high_water_level_threshold_breached'Image
			);

			return high_water_level_threshold_breached; 	
		end is_high_water_level_threshold_breached;
	
		procedure update ( 
			water_level : Float
		) is
		begin
			low_water_level_threshold_breached  := ( water_level < low_water_level_threshold.all );
			high_water_level_threshold_breached := ( water_level > high_water_level_threshold.all );

			if ( ( low_water_level_threshold_breached = True ) or ( high_water_level_threshold_breached = True ) ) then
				if ( first_time = False ) then
					first_time        := True;
					interrupt_pending := True;
				end if;
			else 
				first_time := False;
			end if;
		end update;

	end water_level_sensors_t;

end devices;

