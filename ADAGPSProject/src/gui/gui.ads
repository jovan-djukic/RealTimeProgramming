with Gtk.Window;
with devices;

package gui is
	procedure initialize_gui (
		window : out Gtk.Window.Gtk_Window;
		pump   : access devices.device_t;
		alarm  : access devices.device_t
	);
end gui;
