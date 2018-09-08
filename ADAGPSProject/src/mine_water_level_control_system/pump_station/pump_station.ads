with GNATCOLL.Traces;
with devices;
	
package pump_station is
	task type pump_controller_t (
		pump   : access devices.device_t;
		stream : GNATCOLL.Traces.Trace_Handle
	) is
		entry turn_on;
		entry turn_off;
		entry threshold_breached;
		entry state_normal;
		entry stop;
	end pump_controller_t;
end pump_station;
