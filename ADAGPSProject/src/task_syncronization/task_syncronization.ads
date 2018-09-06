package task_syncronization is
	type program_state_t is ( 
		RUNNING,
		STOPPED
	);

	protected type termination_object_t (
		initial_program_state : program_state_t
	) is
		function is_program_over return Boolean;
		procedure end_program;
	private
		program_state : program_state_t := initial_program_state;
	end termination_object_t;
end task_syncronization;
