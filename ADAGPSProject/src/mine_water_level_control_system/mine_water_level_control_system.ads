with devices;

package mine_water_level_control_system is
	task type top_t (
		pump  : access devices.device_t;
		alarm : access devices.device_t
	) is
		entry turn_on;
		entry turn_off;
		entry stop;
	end top_t;
end mine_water_level_control_system;
