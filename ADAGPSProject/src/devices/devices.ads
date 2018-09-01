package devices is

	type device_state_t is ( ON, OFF );

	protected type device_t is
		function get_state return device_state_t;
		procedure set_state ( 
			new_state : in device_state_t 
		);
		entry wait_for_state_change;
	private
		state   : device_state_t := OFF;
		changed : boolean        := True;
	end device_t;

end devices;
