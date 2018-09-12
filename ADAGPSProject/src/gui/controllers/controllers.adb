with Gtk.Button;
with Gdk.RGBA;
with Glib;

with devices;

package body controllers is
	task body device_state_controller_t is
		device_state        : devices.device_state_t;
		is_device_turned_on : Boolean;
	begin
		check_loop : loop
			device.wait_for_change (
				out_state => device_state
			);

			is_device_turned_on := devices."=" (
				Left  => device_state,
				Right => devices.ON
			);
		   	
		   	if ( is_device_turned_on = True ) then
		   		button.Override_Color (
		   			State => button.Get_State_Flags,
		   			Color => on_color.all
		   		);
		   	else
		   		button.Override_Color (
		   			State => button.Get_State_Flags,
		   			Color => off_color.all
		   		);
		   	end if;
		end loop check_loop;
	end device_state_controller_t;

	task body gas_sensor_state_controller_t is
		gas_sensor_value               : Float;
		gas_sensor_read_error_occurred : Boolean;
		is_threshold_breached          : Boolean;
	begin
		check_loop : loop
			gas_sensor.wait_for_change ( 
				out_value               => gas_sensor_value,
				out_read_error_occurred => gas_sensor_read_error_occurred
			);

			progress_bar.Set_Fraction (
				Fraction => Glib.Gdouble (
					gas_sensor_value / maximum_value.all 
				)
			);
		   	
			label.Set_Text (
				Str => ( gas_sensor_value'Image & " / " & threshold.all'Image )
			);

			if ( detects_above_threshold = True ) then
				if ( gas_sensor_value > threshold.all ) then
					is_threshold_breached := True;
				else
					is_threshold_breached := False;
				end if;
			else 
				if ( gas_sensor_value < threshold.all ) then
					is_threshold_breached := True;
				else
					is_threshold_breached := False;
				end if;
			end if;

		   	if ( gas_sensor_read_error_occurred = True ) then
		   		color_button.Set_Rgba (
		   			Color => read_error_occurred_color.all
		   		);
		   	elsif ( is_threshold_breached = True ) then
		   		color_button.Set_Rgba (
		   			Color => threshold_breached_color.all
		   		);
		   	else 
		   		color_button.Set_Rgba (
		   			Color => state_normal_color.all
		   		);
		   	end if;
		end loop check_loop;
	end gas_sensor_state_controller_t;

	protected body water_tank_t is
		procedure set_water_level (
			new_water_level : Float
		) is
		begin
			if ( new_water_level /= water_level ) then
				changed := True;
			end if;

			if ( new_water_level <= 0.0 ) then
				water_level := 0.0;
			elsif ( new_water_level >= maximum_water_level.all ) then	
				water_level := maximum_water_level.all;
			else
				water_level := new_water_level;
			end if;
		end set_water_level;
		
		entry wait_for_change (
			out_water_level         : out Float;
			out_maximum_water_level : out Float
		) when ( changed = True ) is
		begin
			changed                 := False;
			out_water_level         := water_level;
			out_maximum_water_level := maximum_water_level.all;
		end wait_for_change;
	end water_tank_t;

	task body water_flow_sensor_state_controller_t is
		is_water_flowing : Boolean;
	begin
		check_loop : loop
			water_flow_sensor.wait_for_change (	
				out_water_flowing => is_water_flowing
			);

		   	if ( is_water_flowing = True ) then
		   		button.Override_Color (
		   			State => button.Get_State_Flags,
		   			Color => flowing_color.all
		   		);
		   	else 
		   		button.Override_Color (
		   			State => button.Get_State_Flags,
		   			Color => not_flowing_color.all
		   		);
		   	end if;
		end loop check_loop;
	end water_flow_sensor_state_controller_t;

	task body water_level_sensors_state_controller_t is
		water_level         : Float;
		maximum_water_level : Float;
	begin
		check_loop : loop
			water_tank.wait_for_change (
				out_water_level         => water_level,
				out_maximum_water_level => maximum_water_level
			);

			progress_bar.Set_Fraction (
				Fraction => Glib.Gdouble (
					water_level / maximum_water_level
				)
			);
		   	
			label.Set_Text (
				Str => ( low_water_level_threshold.all'Image & " > " & water_level'Image & " < " & high_water_level_threshold.all'Image )
			);

		   	if ( water_level < low_water_level_threshold.all ) then
		   		color_button.Set_Rgba (
		   			Color => low_water_level_breached_color.all
		   		);
		   	elsif ( water_level > high_water_level_threshold.all ) then
		   		color_button.Set_Rgba (
		   			Color => high_water_level_breached_color.all
		   		);
		   	else 
		   		color_button.Set_Rgba (
		   			Color => state_normal_color.all
		   		);
		   	end if;
		end loop check_loop;
	end water_level_sensors_state_controller_t;

end controllers;

