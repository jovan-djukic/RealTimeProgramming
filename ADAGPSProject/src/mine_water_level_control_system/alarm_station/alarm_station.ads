with devices;

package alarm_station is
	task type alarm_controller_t (
		alarm : access devices.device_t
	) is
		entry turn_on;
		entry turn_off;
		entry stop;
	end alarm_controller_t;	
end alarm_station;
