with System;
with GNATCOLL.Traces;
with alarm_station;
with pump_station;
with devices;

package environment_station is
	task type gas_sensor_controller_t (
		priority						    : System.Priority;
		read_error_occurred_count_threshold : Integer;
		period_in_ms                        : Integer;
		dealine_in_ms                       : Integer;
		sensor                              : access devices.sensor_t;
		alarm_controller                    : access alarm_station.alarm_controller_t;
		stream                              : GNATCOLL.Traces.Trace_Handle
	) is
		pragma Priority ( priority );

		entry stop;	
	end gas_sensor_controller_t;	

	task type ch4_sensor_controller_t (
		priority						    : System.Priority;
		read_error_occurred_count_threshold : Integer;
		period_in_ms                        : Integer;
		dealine_in_ms                       : Integer;
		sensor                              : access devices.sensor_t;
		alarm_controller                    : access alarm_station.alarm_controller_t;
		pump_controller                     : access pump_station.pump_controller_t;
		stream                              : GNATCOLL.Traces.Trace_Handle
	) is
		pragma Priority ( priority );

		entry stop;	
	end ch4_sensor_controller_t;	

	task type water_level_sensors_controller_t (
		priority                : System.Priority;
		dealine_in_ms           : Integer;
		water_level_sensors  	: access devices.water_level_sensors_t;
		pump_controller         : access pump_station.pump_controller_t;
		stream                  : GNATCOLL.Traces.Trace_Handle
	) is
		pragma Priority ( priority );
	end water_level_sensors_controller_t;	
end environment_station;
