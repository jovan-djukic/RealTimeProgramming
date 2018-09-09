with System;
with GNATCOLL.Traces;
with devices;

package alarm_station is
	type alarm_state_t is ( 
		ALARM_TURNED_ON,
		ALARM_TURNED_OFF
	);

	protected type alarm_controller_t (
		priority : System.Priority;
		alarm    : access devices.device_t
	) is
		pragma Priority ( priority );

		procedure turn_on (
			stream : in GNATCOLL.Traces.Trace_Handle
		);

		procedure turn_off (
			stream : in GNATCOLL.Traces.Trace_Handle
		);
	private
		number_of_activations : Integer 	  := 0;
		state                 : alarm_state_t := ALARM_TURNED_OFF;
	end alarm_controller_t;	
end alarm_station;
