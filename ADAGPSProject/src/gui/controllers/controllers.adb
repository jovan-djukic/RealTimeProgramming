with devices;
with Gtk.Button;
with Gdk.RGBA;
with Glib;

with Ada.Text_IO;

package body controllers is
	task body device_state_controller_t is
	begin
		check_loop : loop
			device.wait_for_state_change;
		   	
		   	if ( devices."=" ( device.get_state, devices.ON ) ) then
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
	begin
		check_loop : loop
			sensor.wait_for_value_change;

			progress_bar.Set_Fraction (
				Fraction => Glib.Gdouble (
					sensor.get_value / ( Float ( multiplication_factor ) * sensor.get_threshold )
				)
			);
		   	
			label.Set_Text (
				Str => ( sensor.get_value'Image & " / " & sensor.get_threshold'Image )
			);

		   	if ( sensor.get_read_error_occurred = True ) then
		   		color_button.Set_Rgba (
		   			Color => read_error_occurred_color.all
		   		);
		   	elsif ( sensor.is_threshold_breached = True ) then
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
		procedure set_level (
			new_level : Float
		) is
		begin
			if ( new_level /= level ) then
				if ( new_level <= 0.0 ) then
					level := 0.0;
				elsif ( new_level >= maximum_level.all ) then	
					level := maximum_level.all;
				else
					level := new_level;
				end if;
				
				changed := True;
			end if;
		end set_level;
		
		function get_level return Float is
		begin
			return level;
		end get_level;

		function get_maximum_level return Float is
		begin
			return maximum_level.all;
		end get_maximum_level;

		entry wait_for_level_change when ( changed = True ) is
		begin
			changed := False;
		end wait_for_level_change;
	end water_tank_t;

	task body water_level_sensors_state_controller_t is
	begin
		check_loop : loop
			water_tank.wait_for_level_change;

			progress_bar.Set_Fraction (
				Fraction => Glib.Gdouble (
					Float ( water_tank.get_level ) / water_tank.get_maximum_level	
				)
			);
		   	
			label.Set_Text (
				Str => ( water_level_sensors.get_low_water_level_threshold'Image & " > " & water_tank.get_level'Image & " < " & water_level_sensors.get_high_water_level_threshold'Image )
			);

		   	if ( water_level_sensors.is_low_water_level_threshold_breached = True ) then
		   		color_button.Set_Rgba (
		   			Color => low_water_level_breached_color.all
		   		);
		   	elsif ( water_level_sensors.is_high_water_level_threshold_breached = True ) then
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

	task body water_flow_sensor_state_controller_t is
	begin
		check_loop : loop
			water_flow_sensor.wait_for_value_change;

		   	if ( water_flow_sensor.is_water_flowing = True ) then
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
end controllers;

