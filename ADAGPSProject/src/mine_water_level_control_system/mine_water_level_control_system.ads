with System;
with GNATCOLL.Traces;
with devices;

package mine_water_level_control_system is
	task type top_t (
		-- devices
		pump                : access devices.device_t;
		alarm               : access devices.device_t;
		co_sensor           : access devices.gas_sensor_t;
		o_sensor            : access devices.gas_sensor_t;
		ch4_sensor          : access devices.gas_sensor_t;
		water_level_sensors : access devices.water_level_sensors_t;
		water_flow_sensor   : access devices.water_flow_sensor_t;

		-- priority
		priority : System.Priority;
		
		-- controller priorities
		pump_controller_priority                : System.Priority;
		alarm_controller_priority               : System.Priority;
		co_sensor_controller_priority           : System.Priority;
		o_sensor_controller_priority            : System.Priority;
		ch4_sensor_controller_priority          : System.Priority;
		water_level_sensors_controller_priority : System.Priority;
		water_flow_sensor_controller_priority   : System.Priority;

		-- sensor controller paramters
		co_sensor_controller_threshold 							  : access constant Float;
		co_sensor_controller_read_error_occurred_count_threshold  : Integer;
		co_sensor_controller_period_in_ms                         : Integer;
		co_sensor_controller_deadline_in_ms                       : Integer;
		o_sensor_controller_threshold 							  : access constant Float;
		o_sensor_controller_read_error_occurred_count_threshold   : Integer;
		o_sensor_controller_period_in_ms                          : Integer;
		o_sensor_controller_deadline_in_ms                        : Integer;
		ch4_sensor_controller_threshold 						  : access constant Float;
		ch4_sensor_controller_read_error_occurred_count_threshold : Integer;
		ch4_sensor_controller_period_in_ms                        : Integer;
		ch4_sensor_controller_deadline_in_ms                      : Integer;
		water_level_sensors_controller_deadline_in_ms             : Integer;
		water_flow_sensor_controller_period_in_ms                 : Integer;
		water_flow_sensor_controller_deadline_in_ms               : Integer;
		water_flow_sensor_controller_number_of_activations        : Integer;
		
		-- top trace_handle
		trace_handle : GNATCOLL.Traces.Trace_Handle;

		-- controller strems 
		co_sensor_controller_trace_handle           : GNATCOLL.Traces.Trace_Handle;
		o_sensor_controller_trace_handle            : GNATCOLL.Traces.Trace_Handle;
		ch4_sensor_controller_trace_handle          : GNATCOLL.Traces.Trace_Handle;
		water_level_sensors_controller_trace_handle : GNATCOLL.Traces.Trace_Handle;
		water_flow_sensor_controller_trace_handle   : GNATCOLL.Traces.Trace_Handle
	) is
		pragma Priority ( priority );

		entry turn_on;
		entry turn_off;
		entry stop;
	end top_t;

end mine_water_level_control_system;
