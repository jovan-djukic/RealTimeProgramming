with GNATCOLL.Traces;

with constants;
with devices;
with pump_station;
with alarm_station;
with environment_station;

package body mine_water_level_control_system is
	task body top_t is
		pump_controller       : access pump_station.pump_controller_t;
		alarm_controller      : access alarm_station.alarm_controller_t;
		co_sensor_controller  : access environment_station.gas_sensor_controller_t;
		o_sensor_controller   : access environment_station.gas_sensor_controller_t;
		ch4_sensor_controller : access environment_station.ch4_sensor_controller_t;
	begin
		GNATCOLL.Traces.Trace (
			Handle  => top_stream,
			Message => "Starting"
		);

		pump_controller := new pump_station.pump_controller_t (
			pump   => pump,
			stream => pump_controller_stream
		);

		alarm_controller := new alarm_station.alarm_controller_t (
			alarm  => alarm,
			stream => alarm_controller_stream
		);	

		co_sensor_controller := new environment_station.gas_sensor_controller_t (
			read_error_occurred_count_threshold => co_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => co_sensor_controller_period_in_ms,
			dealine_in_ms                       => co_sensor_controller_deadline_in_ms,
			sensor                              => co_sensor,
			alarm_controller                    => alarm_controller,
			stream								=> co_sensor_controller_stream
		);

		o_sensor_controller := new environment_station.gas_sensor_controller_t (
			read_error_occurred_count_threshold => o_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => o_sensor_controller_period_in_ms,
			dealine_in_ms                       => o_sensor_controller_deadline_in_ms,
			sensor                              => o_sensor,
			alarm_controller                    => alarm_controller,
			stream								=> o_sensor_controller_stream
		);

		ch4_sensor_controller := new environment_station.ch4_sensor_controller_t (
			read_error_occurred_count_threshold => ch4_sensor_controller_read_error_occurred_count_threshold,
			period_in_ms                        => ch4_sensor_controller_period_in_ms,
			dealine_in_ms                       => ch4_sensor_controller_deadline_in_ms,
			sensor                              => ch4_sensor,
			alarm_controller                    => alarm_controller,
			pump_controller                     => pump_controller,
			stream								=> ch4_sensor_controller_stream
		);

		running_loop : loop
			select
				accept turn_on do
					null;
				end turn_on;
				pump_controller.turn_on;
			or
				accept turn_off do
					null;
				end turn_off;
				pump_controller.turn_off;
			or
				accept stop do
					GNATCOLL.Traces.Trace (
						Handle  => top_stream,
						Message => "Stopping pump controller"
					);

					pump_controller.stop;	

					GNATCOLL.Traces.Trace (
						Handle  => top_stream,
						Message => "Stopping alarm controller"
					);

					alarm_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => top_stream,
						Message => "Stopping CO sensor controller"
					);

					co_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => top_stream,
						Message => "Stopping O sensor controller"
					);

					o_sensor_controller.stop;

					GNATCOLL.Traces.Trace (
						Handle  => top_stream,
						Message => "Stopping CH4 sensor controller"
					);

					ch4_sensor_controller.stop;
				end stop;
				exit running_loop;
			end select;
		end loop running_loop;

		GNATCOLL.Traces.Trace (
			Handle  => top_stream,
			Message => "Finished"
		);
	end top_t;

end mine_water_level_control_system;
