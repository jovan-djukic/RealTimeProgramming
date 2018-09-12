with System;

with GNATCOLL.Traces;

with devices;
with alarm_station;
	
package pump_station is
	type pump_controller_state_t is ( 
		PUMP_TURNED_OFF,
		PUMP_TURNED_ON,
		METHANE_THREASHOLD_BREACHED
	);

	protected type pump_controller_t (
		priority : System.Priority;
		pump     : access devices.device_t
	) is
		pragma Priority ( priority );

		procedure turn_on (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		);

		procedure turn_off (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		);

		procedure threshold_breached (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		);

		procedure state_normal (
			trace_handle : in GNATCOLL.Traces.Trace_Handle
		);

	private
		state      : pump_controller_state_t := PUMP_TURNED_OFF;
		pump_state : devices.device_state_t  := devices.OFF;
	end pump_controller_t;

	task type water_flow_sensor_controller_t (
		priority              : System.Priority;
		pump                  : access devices.device_t;
		alarm_controller      : access alarm_station.alarm_controller_t;
		water_flow_sensor     : access devices.water_flow_sensor_t;
		period_in_ms          : Integer;
		deadline_in_ms        : Integer;
		number_of_activations : Integer;
		trace_handle          : GNATCOLL.Traces.Trace_Handle
	) is
		pragma Priority ( priority );

		entry stop;
	end water_flow_sensor_controller_t;
	
		
end pump_station;
