with devices;
with Gtk.Button;
with Gdk.RGBA;
with Glib;

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
end controllers;

