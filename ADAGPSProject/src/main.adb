with Gtk.Main;
with Gtk.Window;
with devices;

with gui;

procedure main is
	main_window : Gtk.Window.Gtk_Window;
	pump        : access devices.device_t;
	alarm       : access devices.device_t;
begin
	-- allocate devices
	pump  := new device_t;
	alarm := new device_t;

	-- initialize gtk
	Gtk.Main.Init;

	gui.initialize_gui (
		window => main_window,
		pump   => pump,
		alarm  => alarm
	);

	-- start main loop
	Gtk.Main.Main;
end main;
