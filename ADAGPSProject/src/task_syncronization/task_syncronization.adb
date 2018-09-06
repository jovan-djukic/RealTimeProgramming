package body task_syncronization is
	
	protected body termination_object_t is
		function is_program_over return Boolean is
		begin
			return ( program_state = STOPPED );	
		end is_program_over; 

		procedure end_program is
		begin
			program_state := STOPPED;
		end end_program;	
	end termination_object_t;

end task_syncronization;
