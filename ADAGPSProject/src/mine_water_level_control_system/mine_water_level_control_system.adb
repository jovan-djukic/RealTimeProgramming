with Ada.Task_Identification;

with GNATCOLL.Traces;

with constants;
with devices;
with pump_station;
with alarm_station;
with environment_station;

with Ada.Text_IO;

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
			Handle  => trace_handle,
			Message => "Starting"
		);

		pump_controller := new pump_station.pump_controller_t (
			priority => pump_controller_priority,
			pump     => pump
		);

		alarm_controller := new alarm_station.alarm_controller_t (
			priority             => alarm_controller_priority,
			alarm                => alarm,
			number_of_activators => 4
		);	

		co_sensor_controller := new environment_station.gas_sensor_controller_t (
			priority                            => co_sensor_controller_priority,
			detect_above_threshold				=> True,
			threshold							=> co_sensor_controller_threshold,
			read_error_occurred_count_threshold => co_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => co_sensor_controller_period_in_ms,
			dealine_in_ms                       => co_sensor_controller_deadline_in_ms,
			sensor                              => co_sensor,
			alarm_controller                    => alarm_controller,
			trace_handle                        => co_sensor_controller_trace_handle
		);

		o_sensor_controller := new environment_station.gas_sensor_controller_t (
			priority                            => o_sensor_controller_priority,
			detect_above_threshold				=> False,
			threshold							=> o_sensor_controller_threshold,
			read_error_occurred_count_threshold => o_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => o_sensor_controller_period_in_ms,
			dealine_in_ms                       => o_sensor_controller_deadline_in_ms,
			sensor                              => o_sensor,
			alarm_controller                    => alarm_controller,
			trace_handle						=> o_sensor_controller_trace_handle
		);

		ch4_sensor_controller := new environment_station.ch4_sensor_controller_t (
			priority                            => ch4_sensor_controller_priority,
			threshold							=> ch4_sensor_controller_threshold,
			read_error_occurred_count_threshold => ch4_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => ch4_sensor_controller_period_in_ms,
			dealine_in_ms                       => ch4_sensor_controller_deadline_in_ms,
			sensor                              => ch4_sensor,
			alarm_controller                    => alarm_controller,
			pump_controller                     => pump_controller,
			trace_handle						=> ch4_sensor_controller_trace_handle
		);

		water_level_sensors_controller := new environment_station.water_level_sensors_controller_t (
			priority            => water_level_sensors_controller_priority,
			dealine_in_ms       => water_level_sensors_controller_deadline_in_ms,
			water_level_sensors => water_level_sensors,
			pump_controller     => pump_controller,
			trace_handle        => water_level_sensors_controller_trace_handle
		);

		water_flow_sensor_controller := new pump_station.water_flow_sensor_controller_t ( 
			priority              => water_flow_sensor_controller_priority,
			pump                  => pump,
			alarm_controller      => alarm_controller,
			water_flow_sensor     => water_flow_sensor,
			period_in_ms          => water_flow_sensor_controller_period_in_ms,
			deadline_in_ms        => water_flow_sensor_controller_deadline_in_ms,
			number_of_activations => water_flow_sensor_controller_number_of_activations,
			trace_handle          => water_flow_sensor_controller_trace_handle
		);

		running_loop : loop
			select
				accept turn_on do
					null;
				end turn_on;

				pump_controller.turn_on (
					trace_handle => trace_handle
				);
			or
				accept turn_off do
					null;
				end turn_off;

				pump_controller.turn_off (
					trace_handle => trace_handle
				);
			or
				accept stop do
					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Stopping water level sensors controller"
					);

					Ada.Task_Identification.Abort_Task (
						T => water_level_sensors_controller'Identity
					);

					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Stopping CO sensor controller"
					);

					co_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Stopping O sensor controller"
					);

					o_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
						Message => "Stopping CH4 sensor controller"
					);

					ch4_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => trace_handle,
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
			Handle  => trace_handle,
			Message => "Finished"
		);
	end top_t;

end mine_water_level_control_system;
