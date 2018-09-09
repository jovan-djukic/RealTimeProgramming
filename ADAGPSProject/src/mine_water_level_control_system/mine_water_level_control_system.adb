with Ada.Task_Identification;

with GNATCOLL.Traces;

with constants;
with devices;
with pump_station;
with alarm_station;
with environment_station;

package body mine_water_level_control_system is
	task body top_t is
		pump_controller                : access pump_station.pump_controller_t;
		alarm_controller               : access alarm_station.alarm_controller_t;
		co_sensor_controller           : access environment_station.gas_sensor_controller_t;
		o_sensor_controller            : access environment_station.gas_sensor_controller_t;
		ch4_sensor_controller          : access environment_station.ch4_sensor_controller_t;
		water_level_sensors_controller : access environment_station.water_level_sensors_controller_t;
		water_flow_sensor_controller   : access pump_station.water_flow_sensor_controller_t;
	begin
		GNATCOLL.Traces.Trace (
			Handle  => stream,
			Message => "Starting"
		);

		pump_controller := new pump_station.pump_controller_t (
			priority => pump_controller_priority,
			pump     => pump
		);

		alarm_controller := new alarm_station.alarm_controller_t (
			priority => alarm_controller_priority,
			alarm    => alarm
		);	

		co_sensor_controller := new environment_station.gas_sensor_controller_t (
			priority                            => co_sensor_controller_priority,
			read_error_occurred_count_threshold => co_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => co_sensor_controller_period_in_ms,
			dealine_in_ms                       => co_sensor_controller_deadline_in_ms,
			sensor                              => co_sensor,
			alarm_controller                    => alarm_controller,
			stream                              => co_sensor_controller_stream
		);

		o_sensor_controller := new environment_station.gas_sensor_controller_t (
			priority                            => o_sensor_controller_priority,
			read_error_occurred_count_threshold => o_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => o_sensor_controller_period_in_ms,
			dealine_in_ms                       => o_sensor_controller_deadline_in_ms,
			sensor                              => o_sensor,
			alarm_controller                    => alarm_controller,
			stream								=> o_sensor_controller_stream
		);

		ch4_sensor_controller := new environment_station.ch4_sensor_controller_t (
			priority                            => ch4_sensor_controller_priority,
			read_error_occurred_count_threshold => ch4_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => ch4_sensor_controller_period_in_ms,
			dealine_in_ms                       => ch4_sensor_controller_deadline_in_ms,
			sensor                              => ch4_sensor,
			alarm_controller                    => alarm_controller,
			pump_controller                     => pump_controller,
			stream								=> ch4_sensor_controller_stream
		);

		water_level_sensors_controller := new environment_station.water_level_sensors_controller_t (
			priority                => water_level_sensors_controller_priority,
			dealine_in_ms           => water_level_sensors_controller_deadline_in_ms,
			water_level_sensors  	=> water_level_sensors,
			pump_controller         => pump_controller,
			stream                  => water_level_sensors_controller_stream
		);

		water_flow_sensor_controller := new pump_station.water_flow_sensor_controller_t ( 
			priority              => water_flow_sensor_controller_priority,
			pump                  => pump,
			alarm_controller      => alarm_controller,
			water_flow_sensor     => water_flow_sensor,
			period_in_ms          => water_flow_sensor_controller_period_in_ms,
			deadline_in_ms        => water_flow_sensor_controller_deadline_in_ms,
			number_of_activations => water_flow_sensor_controller_number_of_activations,
			stream                => water_flow_sensor_controller_stream
		);

		running_loop : loop
			select
				accept turn_on do
					null;
				end turn_on;

				pump_controller.turn_on (
					stream => stream
				);
			or
				accept turn_off do
					null;
				end turn_off;

				pump_controller.turn_off (
					stream => stream
				);
			or
				accept stop do
					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Stopping water level sensors controller"
					);

					Ada.Task_Identification.Abort_Task (
						T => water_level_sensors_controller'Identity
					);

					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Stopping CO sensor controller"
					);

					co_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Stopping O sensor controller"
					);

					o_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Stopping CH4 sensor controller"
					);

					ch4_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => stream,
						Message => "Stopping water flow sensor controller"
					);

					water_flow_sensor_controller.stop;

					while ( not water_level_sensors_controller'Terminated ) loop
						null;
					end loop;

				end stop;
				exit running_loop;
			end select;
		end loop running_loop;

		GNATCOLL.Traces.Trace (
			Handle  => stream,
			Message => "Finished"
		);
	end top_t;

end mine_water_level_control_system;
