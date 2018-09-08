with GNATCOLL.Traces;
with devices;

package mine_water_level_control_system is
	task type top_t (
		-- devices
		pump       : access devices.device_t;
		alarm      : access devices.device_t;
		co_sensor  : access devices.sensor_t;
		o_sensor   : access devices.sensor_t;
		ch4_sensor : access devices.sensor_t;
		
		-- sensor controller paramters
		co_sensor_controller_read_error_occurred_count_threshold  : Integer;
		co_sensor_controller_period_in_ms                         : Integer;
		co_sensor_controller_deadline_in_ms                       : Integer;
		o_sensor_controller_read_error_occurred_count_threshold   : Integer;
		o_sensor_controller_period_in_ms                          : Integer;
		o_sensor_controller_deadline_in_ms                        : Integer;
		ch4_sensor_controller_read_error_occurred_count_threshold : Integer;
		ch4_sensor_controller_period_in_ms                        : Integer;
		ch4_sensor_controller_deadline_in_ms                      : Integer;
		
		-- streams
		top_stream                   : GNATCOLL.Traces.Trace_Handle;
		pump_controller_stream       : GNATCOLL.Traces.Trace_Handle;
		alarm_controller_stream      : GNATCOLL.Traces.Trace_Handle;
		co_sensor_controller_stream  : GNATCOLL.Traces.Trace_Handle;
		o_sensor_controller_stream   : GNATCOLL.Traces.Trace_Handle;
		ch4_sensor_controller_stream : GNATCOLL.Traces.Trace_Handle
	) is
		entry turn_on;
		entry turn_off;
		entry stop;
	end top_t;
end mine_water_level_control_system;
