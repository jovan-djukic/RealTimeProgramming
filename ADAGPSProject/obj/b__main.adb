pragma Warnings (Off);
pragma Ada_95;
pragma Source_File_Name (ada_main, Spec_File_Name => "b__main.ads");
pragma Source_File_Name (ada_main, Body_File_Name => "b__main.adb");
pragma Suppress (Overflow_Check);

with System.Restrictions;
with Ada.Exceptions;

package body ada_main is

   E070 : Short_Integer; pragma Import (Ada, E070, "system__os_lib_E");
   E011 : Short_Integer; pragma Import (Ada, E011, "system__soft_links_E");
   E023 : Short_Integer; pragma Import (Ada, E023, "system__exception_table_E");
   E066 : Short_Integer; pragma Import (Ada, E066, "ada__io_exceptions_E");
   E050 : Short_Integer; pragma Import (Ada, E050, "ada__strings_E");
   E038 : Short_Integer; pragma Import (Ada, E038, "ada__containers_E");
   E025 : Short_Integer; pragma Import (Ada, E025, "system__exceptions_E");
   E076 : Short_Integer; pragma Import (Ada, E076, "interfaces__c_E");
   E052 : Short_Integer; pragma Import (Ada, E052, "ada__strings__maps_E");
   E056 : Short_Integer; pragma Import (Ada, E056, "ada__strings__maps__constants_E");
   E019 : Short_Integer; pragma Import (Ada, E019, "system__soft_links__initialize_E");
   E078 : Short_Integer; pragma Import (Ada, E078, "system__object_reader_E");
   E045 : Short_Integer; pragma Import (Ada, E045, "system__dwarf_lines_E");
   E037 : Short_Integer; pragma Import (Ada, E037, "system__traceback__symbolic_E");
   E097 : Short_Integer; pragma Import (Ada, E097, "ada__tags_E");
   E105 : Short_Integer; pragma Import (Ada, E105, "ada__streams_E");
   E146 : Short_Integer; pragma Import (Ada, E146, "interfaces__c__strings_E");
   E113 : Short_Integer; pragma Import (Ada, E113, "system__file_control_block_E");
   E112 : Short_Integer; pragma Import (Ada, E112, "system__finalization_root_E");
   E110 : Short_Integer; pragma Import (Ada, E110, "ada__finalization_E");
   E109 : Short_Integer; pragma Import (Ada, E109, "system__file_io_E");
   E231 : Short_Integer; pragma Import (Ada, E231, "ada__streams__stream_io_E");
   E170 : Short_Integer; pragma Import (Ada, E170, "system__storage_pools_E");
   E166 : Short_Integer; pragma Import (Ada, E166, "system__finalization_masters_E");
   E164 : Short_Integer; pragma Import (Ada, E164, "system__storage_pools__subpools_E");
   E158 : Short_Integer; pragma Import (Ada, E158, "ada__strings__unbounded_E");
   E308 : Short_Integer; pragma Import (Ada, E308, "system__task_info_E");
   E184 : Short_Integer; pragma Import (Ada, E184, "system__regpat_E");
   E121 : Short_Integer; pragma Import (Ada, E121, "ada__calendar_E");
   E567 : Short_Integer; pragma Import (Ada, E567, "ada__calendar__delays_E");
   E130 : Short_Integer; pragma Import (Ada, E130, "ada__calendar__time_zones_E");
   E334 : Short_Integer; pragma Import (Ada, E334, "ada__real_time_E");
   E103 : Short_Integer; pragma Import (Ada, E103, "ada__text_io_E");
   E153 : Short_Integer; pragma Import (Ada, E153, "gnat__calendar_E");
   E156 : Short_Integer; pragma Import (Ada, E156, "gnat__calendar__time_io_E");
   E248 : Short_Integer; pragma Import (Ada, E248, "gnat__directory_operations_E");
   E264 : Short_Integer; pragma Import (Ada, E264, "system__assertions_E");
   E207 : Short_Integer; pragma Import (Ada, E207, "system__checked_pools_E");
   E196 : Short_Integer; pragma Import (Ada, E196, "gnat__debug_pools_E");
   E216 : Short_Integer; pragma Import (Ada, E216, "system__pool_global_E");
   E258 : Short_Integer; pragma Import (Ada, E258, "gnat__expect_E");
   E227 : Short_Integer; pragma Import (Ada, E227, "system__pool_size_E");
   E244 : Short_Integer; pragma Import (Ada, E244, "system__regexp_E");
   E238 : Short_Integer; pragma Import (Ada, E238, "ada__directories_E");
   E320 : Short_Integer; pragma Import (Ada, E320, "system__tasking__initialization_E");
   E294 : Short_Integer; pragma Import (Ada, E294, "system__tasking__protected_objects_E");
   E316 : Short_Integer; pragma Import (Ada, E316, "system__tasking__protected_objects__entries_E");
   E328 : Short_Integer; pragma Import (Ada, E328, "system__tasking__queuing_E");
   E539 : Short_Integer; pragma Import (Ada, E539, "system__tasking__stages_E");
   E571 : Short_Integer; pragma Import (Ada, E571, "system__tasking__async_delays_E");
   E337 : Short_Integer; pragma Import (Ada, E337, "glib_E");
   E340 : Short_Integer; pragma Import (Ada, E340, "gtkada__types_E");
   E402 : Short_Integer; pragma Import (Ada, E402, "gdk__frame_timings_E");
   E358 : Short_Integer; pragma Import (Ada, E358, "glib__glist_E");
   E384 : Short_Integer; pragma Import (Ada, E384, "gdk__visual_E");
   E360 : Short_Integer; pragma Import (Ada, E360, "glib__gslist_E");
   E190 : Short_Integer; pragma Import (Ada, E190, "gnatcoll__atomic_E");
   E192 : Short_Integer; pragma Import (Ada, E192, "gnatcoll__memory_E");
   E224 : Short_Integer; pragma Import (Ada, E224, "gnatcoll__storage_pools__headers_E");
   E222 : Short_Integer; pragma Import (Ada, E222, "gnatcoll__refcount_E");
   E220 : Short_Integer; pragma Import (Ada, E220, "gnatcoll__strings_impl_E");
   E218 : Short_Integer; pragma Import (Ada, E218, "gnatcoll__strings_E");
   E217 : Short_Integer; pragma Import (Ada, E217, "gnatcoll__strings_E");
   E260 : Short_Integer; pragma Import (Ada, E260, "gnatcoll__templates_E");
   E262 : Short_Integer; pragma Import (Ada, E262, "gnatcoll__terminal_E");
   E254 : Short_Integer; pragma Import (Ada, E254, "gnatcoll__utils_E");
   E214 : Short_Integer; pragma Import (Ada, E214, "gnatcoll__io_E");
   E252 : Short_Integer; pragma Import (Ada, E252, "gnatcoll__path_E");
   E236 : Short_Integer; pragma Import (Ada, E236, "gnatcoll__io__native_E");
   E209 : Short_Integer; pragma Import (Ada, E209, "gnatcoll__mmap_E");
   E211 : Short_Integer; pragma Import (Ada, E211, "gnatcoll__mmap__system_E");
   E280 : Short_Integer; pragma Import (Ada, E280, "gnatcoll__remote_E");
   E284 : Short_Integer; pragma Import (Ada, E284, "gnatcoll__remote__db_E");
   E277 : Short_Integer; pragma Import (Ada, E277, "gnatcoll__io__remote_E");
   E282 : Short_Integer; pragma Import (Ada, E282, "gnatcoll__io__remote__windows_E");
   E279 : Short_Integer; pragma Import (Ada, E279, "gnatcoll__io__remote__unix_E");
   E269 : Short_Integer; pragma Import (Ada, E269, "gnatcoll__vfs_E");
   E119 : Short_Integer; pragma Import (Ada, E119, "gnatcoll__traces_E");
   E116 : Short_Integer; pragma Import (Ada, E116, "devices_E");
   E352 : Short_Integer; pragma Import (Ada, E352, "gtkada__c_E");
   E346 : Short_Integer; pragma Import (Ada, E346, "glib__object_E");
   E356 : Short_Integer; pragma Import (Ada, E356, "glib__values_E");
   E354 : Short_Integer; pragma Import (Ada, E354, "glib__types_E");
   E348 : Short_Integer; pragma Import (Ada, E348, "glib__type_conversion_hooks_E");
   E350 : Short_Integer; pragma Import (Ada, E350, "gtkada__bindings_E");
   E367 : Short_Integer; pragma Import (Ada, E367, "cairo_E");
   E369 : Short_Integer; pragma Import (Ada, E369, "cairo__region_E");
   E373 : Short_Integer; pragma Import (Ada, E373, "gdk__rectangle_E");
   E344 : Short_Integer; pragma Import (Ada, E344, "glib__generic_properties_E");
   E394 : Short_Integer; pragma Import (Ada, E394, "gdk__color_E");
   E342 : Short_Integer; pragma Import (Ada, E342, "gdk__rgba_E");
   E114 : Short_Integer; pragma Import (Ada, E114, "constants_E");
   E563 : Short_Integer; pragma Import (Ada, E563, "alarm_station_E");
   E371 : Short_Integer; pragma Import (Ada, E371, "gdk__event_E");
   E505 : Short_Integer; pragma Import (Ada, E505, "glib__key_file_E");
   E386 : Short_Integer; pragma Import (Ada, E386, "glib__properties_E");
   E467 : Short_Integer; pragma Import (Ada, E467, "glib__string_E");
   E465 : Short_Integer; pragma Import (Ada, E465, "glib__variant_E");
   E463 : Short_Integer; pragma Import (Ada, E463, "glib__g_icon_E");
   E527 : Short_Integer; pragma Import (Ada, E527, "gtk__actionable_E");
   E410 : Short_Integer; pragma Import (Ada, E410, "gtk__builder_E");
   E447 : Short_Integer; pragma Import (Ada, E447, "gtk__buildable_E");
   E479 : Short_Integer; pragma Import (Ada, E479, "gtk__cell_area_context_E");
   E495 : Short_Integer; pragma Import (Ada, E495, "gtk__css_section_E");
   E388 : Short_Integer; pragma Import (Ada, E388, "gtk__enums_E");
   E453 : Short_Integer; pragma Import (Ada, E453, "gtk__orientable_E");
   E507 : Short_Integer; pragma Import (Ada, E507, "gtk__paper_size_E");
   E503 : Short_Integer; pragma Import (Ada, E503, "gtk__page_setup_E");
   E515 : Short_Integer; pragma Import (Ada, E515, "gtk__print_settings_E");
   E418 : Short_Integer; pragma Import (Ada, E418, "gtk__target_entry_E");
   E416 : Short_Integer; pragma Import (Ada, E416, "gtk__target_list_E");
   E423 : Short_Integer; pragma Import (Ada, E423, "pango__enums_E");
   E441 : Short_Integer; pragma Import (Ada, E441, "pango__attributes_E");
   E427 : Short_Integer; pragma Import (Ada, E427, "pango__font_metrics_E");
   E429 : Short_Integer; pragma Import (Ada, E429, "pango__language_E");
   E425 : Short_Integer; pragma Import (Ada, E425, "pango__font_E");
   E521 : Short_Integer; pragma Import (Ada, E521, "gtk__text_attributes_E");
   E523 : Short_Integer; pragma Import (Ada, E523, "gtk__text_tag_E");
   E433 : Short_Integer; pragma Import (Ada, E433, "pango__font_face_E");
   E431 : Short_Integer; pragma Import (Ada, E431, "pango__font_family_E");
   E435 : Short_Integer; pragma Import (Ada, E435, "pango__fontset_E");
   E437 : Short_Integer; pragma Import (Ada, E437, "pango__matrix_E");
   E421 : Short_Integer; pragma Import (Ada, E421, "pango__context_E");
   E511 : Short_Integer; pragma Import (Ada, E511, "pango__font_map_E");
   E443 : Short_Integer; pragma Import (Ada, E443, "pango__tabs_E");
   E439 : Short_Integer; pragma Import (Ada, E439, "pango__layout_E");
   E509 : Short_Integer; pragma Import (Ada, E509, "gtk__print_context_E");
   E382 : Short_Integer; pragma Import (Ada, E382, "gdk__display_E");
   E513 : Short_Integer; pragma Import (Ada, E513, "gtk__print_operation_preview_E");
   E485 : Short_Integer; pragma Import (Ada, E485, "gtk__tree_model_E");
   E473 : Short_Integer; pragma Import (Ada, E473, "gtk__entry_buffer_E");
   E471 : Short_Integer; pragma Import (Ada, E471, "gtk__editable_E");
   E469 : Short_Integer; pragma Import (Ada, E469, "gtk__cell_editable_E");
   E451 : Short_Integer; pragma Import (Ada, E451, "gtk__adjustment_E");
   E414 : Short_Integer; pragma Import (Ada, E414, "gtk__style_E");
   E408 : Short_Integer; pragma Import (Ada, E408, "gtk__accel_group_E");
   E400 : Short_Integer; pragma Import (Ada, E400, "gdk__frame_clock_E");
   E404 : Short_Integer; pragma Import (Ada, E404, "gdk__pixbuf_E");
   E491 : Short_Integer; pragma Import (Ada, E491, "gtk__icon_source_E");
   E380 : Short_Integer; pragma Import (Ada, E380, "gdk__screen_E");
   E519 : Short_Integer; pragma Import (Ada, E519, "gtk__text_iter_E");
   E412 : Short_Integer; pragma Import (Ada, E412, "gtk__selection_data_E");
   E396 : Short_Integer; pragma Import (Ada, E396, "gdk__device_E");
   E398 : Short_Integer; pragma Import (Ada, E398, "gdk__drag_contexts_E");
   E392 : Short_Integer; pragma Import (Ada, E392, "gtk__widget_E");
   E497 : Short_Integer; pragma Import (Ada, E497, "gtk__misc_E");
   E390 : Short_Integer; pragma Import (Ada, E390, "gtk__style_provider_E");
   E378 : Short_Integer; pragma Import (Ada, E378, "gtk__settings_E");
   E457 : Short_Integer; pragma Import (Ada, E457, "gdk__window_E");
   E493 : Short_Integer; pragma Import (Ada, E493, "gtk__style_context_E");
   E489 : Short_Integer; pragma Import (Ada, E489, "gtk__icon_set_E");
   E487 : Short_Integer; pragma Import (Ada, E487, "gtk__image_E");
   E483 : Short_Integer; pragma Import (Ada, E483, "gtk__cell_renderer_E");
   E449 : Short_Integer; pragma Import (Ada, E449, "gtk__container_E");
   E459 : Short_Integer; pragma Import (Ada, E459, "gtk__bin_E");
   E445 : Short_Integer; pragma Import (Ada, E445, "gtk__box_E");
   E517 : Short_Integer; pragma Import (Ada, E517, "gtk__status_bar_E");
   E499 : Short_Integer; pragma Import (Ada, E499, "gtk__notebook_E");
   E481 : Short_Integer; pragma Import (Ada, E481, "gtk__cell_layout_E");
   E477 : Short_Integer; pragma Import (Ada, E477, "gtk__cell_area_E");
   E475 : Short_Integer; pragma Import (Ada, E475, "gtk__entry_completion_E");
   E455 : Short_Integer; pragma Import (Ada, E455, "gtk__window_E");
   E376 : Short_Integer; pragma Import (Ada, E376, "gtk__dialog_E");
   E501 : Short_Integer; pragma Import (Ada, E501, "gtk__print_operation_E");
   E461 : Short_Integer; pragma Import (Ada, E461, "gtk__gentry_E");
   E365 : Short_Integer; pragma Import (Ada, E365, "gtk__arguments_E");
   E549 : Short_Integer; pragma Import (Ada, E549, "glib__menu_model_E");
   E525 : Short_Integer; pragma Import (Ada, E525, "gtk__action_E");
   E529 : Short_Integer; pragma Import (Ada, E529, "gtk__activatable_E");
   E363 : Short_Integer; pragma Import (Ada, E363, "gtk__button_E");
   E543 : Short_Integer; pragma Import (Ada, E543, "gtk__color_chooser_E");
   E541 : Short_Integer; pragma Import (Ada, E541, "gtk__color_button_E");
   E557 : Short_Integer; pragma Import (Ada, E557, "gtk__grid_E");
   E559 : Short_Integer; pragma Import (Ada, E559, "gtk__main_E");
   E551 : Short_Integer; pragma Import (Ada, E551, "gtk__menu_item_E");
   E553 : Short_Integer; pragma Import (Ada, E553, "gtk__menu_shell_E");
   E547 : Short_Integer; pragma Import (Ada, E547, "gtk__menu_E");
   E545 : Short_Integer; pragma Import (Ada, E545, "gtk__label_E");
   E555 : Short_Integer; pragma Import (Ada, E555, "gtk__progress_bar_E");
   E535 : Short_Integer; pragma Import (Ada, E535, "controllers_E");
   E575 : Short_Integer; pragma Import (Ada, E575, "pump_station_E");
   E565 : Short_Integer; pragma Import (Ada, E565, "environment_station_E");
   E561 : Short_Integer; pragma Import (Ada, E561, "mine_water_level_control_system_E");
   E531 : Short_Integer; pragma Import (Ada, E531, "gui_E");

   Sec_Default_Sized_Stacks : array (1 .. 1) of aliased System.Secondary_Stack.SS_Stack (System.Parameters.Runtime_Default_Sec_Stack_Size);

   Local_Priority_Specific_Dispatching : constant String := "";
   Local_Interrupt_States : constant String := "";

   Is_Elaborated : Boolean := False;

   procedure finalize_library is
   begin
      declare
         procedure F1;
         pragma Import (Ada, F1, "gui__finalize_body");
      begin
         E531 := E531 - 1;
         F1;
      end;
      E555 := E555 - 1;
      declare
         procedure F2;
         pragma Import (Ada, F2, "gtk__progress_bar__finalize_spec");
      begin
         F2;
      end;
      E545 := E545 - 1;
      declare
         procedure F3;
         pragma Import (Ada, F3, "gtk__label__finalize_spec");
      begin
         F3;
      end;
      E547 := E547 - 1;
      declare
         procedure F4;
         pragma Import (Ada, F4, "gtk__menu__finalize_spec");
      begin
         F4;
      end;
      E553 := E553 - 1;
      declare
         procedure F5;
         pragma Import (Ada, F5, "gtk__menu_shell__finalize_spec");
      begin
         F5;
      end;
      E551 := E551 - 1;
      declare
         procedure F6;
         pragma Import (Ada, F6, "gtk__menu_item__finalize_spec");
      begin
         F6;
      end;
      E557 := E557 - 1;
      declare
         procedure F7;
         pragma Import (Ada, F7, "gtk__grid__finalize_spec");
      begin
         F7;
      end;
      E541 := E541 - 1;
      declare
         procedure F8;
         pragma Import (Ada, F8, "gtk__color_button__finalize_spec");
      begin
         F8;
      end;
      E363 := E363 - 1;
      declare
         procedure F9;
         pragma Import (Ada, F9, "gtk__button__finalize_spec");
      begin
         F9;
      end;
      E525 := E525 - 1;
      declare
         procedure F10;
         pragma Import (Ada, F10, "gtk__action__finalize_spec");
      begin
         F10;
      end;
      E549 := E549 - 1;
      declare
         procedure F11;
         pragma Import (Ada, F11, "glib__menu_model__finalize_spec");
      begin
         F11;
      end;
      E382 := E382 - 1;
      E400 := E400 - 1;
      E408 := E408 - 1;
      E392 := E392 - 1;
      E414 := E414 - 1;
      E449 := E449 - 1;
      E451 := E451 - 1;
      E376 := E376 - 1;
      E455 := E455 - 1;
      E473 := E473 - 1;
      E483 := E483 - 1;
      E475 := E475 - 1;
      E477 := E477 - 1;
      E485 := E485 - 1;
      E461 := E461 - 1;
      E493 := E493 - 1;
      E499 := E499 - 1;
      E501 := E501 - 1;
      E517 := E517 - 1;
      declare
         procedure F12;
         pragma Import (Ada, F12, "gtk__gentry__finalize_spec");
      begin
         F12;
      end;
      declare
         procedure F13;
         pragma Import (Ada, F13, "gtk__print_operation__finalize_spec");
      begin
         F13;
      end;
      declare
         procedure F14;
         pragma Import (Ada, F14, "gtk__dialog__finalize_spec");
      begin
         F14;
      end;
      declare
         procedure F15;
         pragma Import (Ada, F15, "gtk__window__finalize_spec");
      begin
         F15;
      end;
      declare
         procedure F16;
         pragma Import (Ada, F16, "gtk__entry_completion__finalize_spec");
      begin
         F16;
      end;
      declare
         procedure F17;
         pragma Import (Ada, F17, "gtk__cell_area__finalize_spec");
      begin
         F17;
      end;
      declare
         procedure F18;
         pragma Import (Ada, F18, "gtk__notebook__finalize_spec");
      begin
         F18;
      end;
      declare
         procedure F19;
         pragma Import (Ada, F19, "gtk__status_bar__finalize_spec");
      begin
         F19;
      end;
      E445 := E445 - 1;
      declare
         procedure F20;
         pragma Import (Ada, F20, "gtk__box__finalize_spec");
      begin
         F20;
      end;
      E459 := E459 - 1;
      declare
         procedure F21;
         pragma Import (Ada, F21, "gtk__bin__finalize_spec");
      begin
         F21;
      end;
      declare
         procedure F22;
         pragma Import (Ada, F22, "gtk__container__finalize_spec");
      begin
         F22;
      end;
      declare
         procedure F23;
         pragma Import (Ada, F23, "gtk__cell_renderer__finalize_spec");
      begin
         F23;
      end;
      E487 := E487 - 1;
      declare
         procedure F24;
         pragma Import (Ada, F24, "gtk__image__finalize_spec");
      begin
         F24;
      end;
      E489 := E489 - 1;
      declare
         procedure F25;
         pragma Import (Ada, F25, "gtk__icon_set__finalize_spec");
      begin
         F25;
      end;
      declare
         procedure F26;
         pragma Import (Ada, F26, "gtk__style_context__finalize_spec");
      begin
         F26;
      end;
      E378 := E378 - 1;
      declare
         procedure F27;
         pragma Import (Ada, F27, "gtk__settings__finalize_spec");
      begin
         F27;
      end;
      E497 := E497 - 1;
      declare
         procedure F28;
         pragma Import (Ada, F28, "gtk__misc__finalize_spec");
      begin
         F28;
      end;
      declare
         procedure F29;
         pragma Import (Ada, F29, "gtk__widget__finalize_spec");
      begin
         F29;
      end;
      E396 := E396 - 1;
      E398 := E398 - 1;
      declare
         procedure F30;
         pragma Import (Ada, F30, "gdk__drag_contexts__finalize_spec");
      begin
         F30;
      end;
      declare
         procedure F31;
         pragma Import (Ada, F31, "gdk__device__finalize_spec");
      begin
         F31;
      end;
      E412 := E412 - 1;
      declare
         procedure F32;
         pragma Import (Ada, F32, "gtk__selection_data__finalize_spec");
      begin
         F32;
      end;
      E380 := E380 - 1;
      declare
         procedure F33;
         pragma Import (Ada, F33, "gdk__screen__finalize_spec");
      begin
         F33;
      end;
      E404 := E404 - 1;
      E491 := E491 - 1;
      declare
         procedure F34;
         pragma Import (Ada, F34, "gtk__icon_source__finalize_spec");
      begin
         F34;
      end;
      declare
         procedure F35;
         pragma Import (Ada, F35, "gdk__pixbuf__finalize_spec");
      begin
         F35;
      end;
      declare
         procedure F36;
         pragma Import (Ada, F36, "gdk__frame_clock__finalize_spec");
      begin
         F36;
      end;
      declare
         procedure F37;
         pragma Import (Ada, F37, "gtk__accel_group__finalize_spec");
      begin
         F37;
      end;
      declare
         procedure F38;
         pragma Import (Ada, F38, "gtk__style__finalize_spec");
      begin
         F38;
      end;
      declare
         procedure F39;
         pragma Import (Ada, F39, "gtk__adjustment__finalize_spec");
      begin
         F39;
      end;
      declare
         procedure F40;
         pragma Import (Ada, F40, "gtk__entry_buffer__finalize_spec");
      begin
         F40;
      end;
      declare
         procedure F41;
         pragma Import (Ada, F41, "gtk__tree_model__finalize_spec");
      begin
         F41;
      end;
      declare
         procedure F42;
         pragma Import (Ada, F42, "gdk__display__finalize_spec");
      begin
         F42;
      end;
      E509 := E509 - 1;
      declare
         procedure F43;
         pragma Import (Ada, F43, "gtk__print_context__finalize_spec");
      begin
         F43;
      end;
      E439 := E439 - 1;
      declare
         procedure F44;
         pragma Import (Ada, F44, "pango__layout__finalize_spec");
      begin
         F44;
      end;
      E443 := E443 - 1;
      declare
         procedure F45;
         pragma Import (Ada, F45, "pango__tabs__finalize_spec");
      begin
         F45;
      end;
      E511 := E511 - 1;
      declare
         procedure F46;
         pragma Import (Ada, F46, "pango__font_map__finalize_spec");
      begin
         F46;
      end;
      E421 := E421 - 1;
      declare
         procedure F47;
         pragma Import (Ada, F47, "pango__context__finalize_spec");
      begin
         F47;
      end;
      E435 := E435 - 1;
      declare
         procedure F48;
         pragma Import (Ada, F48, "pango__fontset__finalize_spec");
      begin
         F48;
      end;
      E431 := E431 - 1;
      declare
         procedure F49;
         pragma Import (Ada, F49, "pango__font_family__finalize_spec");
      begin
         F49;
      end;
      E433 := E433 - 1;
      declare
         procedure F50;
         pragma Import (Ada, F50, "pango__font_face__finalize_spec");
      begin
         F50;
      end;
      E523 := E523 - 1;
      declare
         procedure F51;
         pragma Import (Ada, F51, "gtk__text_tag__finalize_spec");
      begin
         F51;
      end;
      E425 := E425 - 1;
      declare
         procedure F52;
         pragma Import (Ada, F52, "pango__font__finalize_spec");
      begin
         F52;
      end;
      E429 := E429 - 1;
      declare
         procedure F53;
         pragma Import (Ada, F53, "pango__language__finalize_spec");
      begin
         F53;
      end;
      E427 := E427 - 1;
      declare
         procedure F54;
         pragma Import (Ada, F54, "pango__font_metrics__finalize_spec");
      begin
         F54;
      end;
      E441 := E441 - 1;
      declare
         procedure F55;
         pragma Import (Ada, F55, "pango__attributes__finalize_spec");
      begin
         F55;
      end;
      E416 := E416 - 1;
      declare
         procedure F56;
         pragma Import (Ada, F56, "gtk__target_list__finalize_spec");
      begin
         F56;
      end;
      E515 := E515 - 1;
      declare
         procedure F57;
         pragma Import (Ada, F57, "gtk__print_settings__finalize_spec");
      begin
         F57;
      end;
      E503 := E503 - 1;
      declare
         procedure F58;
         pragma Import (Ada, F58, "gtk__page_setup__finalize_spec");
      begin
         F58;
      end;
      E507 := E507 - 1;
      declare
         procedure F59;
         pragma Import (Ada, F59, "gtk__paper_size__finalize_spec");
      begin
         F59;
      end;
      E495 := E495 - 1;
      declare
         procedure F60;
         pragma Import (Ada, F60, "gtk__css_section__finalize_spec");
      begin
         F60;
      end;
      E479 := E479 - 1;
      declare
         procedure F61;
         pragma Import (Ada, F61, "gtk__cell_area_context__finalize_spec");
      begin
         F61;
      end;
      E410 := E410 - 1;
      declare
         procedure F62;
         pragma Import (Ada, F62, "gtk__builder__finalize_spec");
      begin
         F62;
      end;
      E465 := E465 - 1;
      declare
         procedure F63;
         pragma Import (Ada, F63, "glib__variant__finalize_spec");
      begin
         F63;
      end;
      E346 := E346 - 1;
      declare
         procedure F64;
         pragma Import (Ada, F64, "glib__object__finalize_spec");
      begin
         F64;
      end;
      declare
         procedure F65;
         pragma Import (Ada, F65, "gnatcoll__traces__finalize_body");
      begin
         E119 := E119 - 1;
         F65;
      end;
      declare
         procedure F66;
         pragma Import (Ada, F66, "gnatcoll__traces__finalize_spec");
      begin
         F66;
      end;
      E269 := E269 - 1;
      declare
         procedure F67;
         pragma Import (Ada, F67, "gnatcoll__vfs__finalize_spec");
      begin
         F67;
      end;
      E277 := E277 - 1;
      declare
         procedure F68;
         pragma Import (Ada, F68, "gnatcoll__io__remote__finalize_spec");
      begin
         F68;
      end;
      declare
         procedure F69;
         pragma Import (Ada, F69, "gnatcoll__remote__finalize_spec");
      begin
         E280 := E280 - 1;
         F69;
      end;
      E236 := E236 - 1;
      declare
         procedure F70;
         pragma Import (Ada, F70, "gnatcoll__io__native__finalize_spec");
      begin
         F70;
      end;
      E214 := E214 - 1;
      declare
         procedure F71;
         pragma Import (Ada, F71, "gnatcoll__io__finalize_spec");
      begin
         F71;
      end;
      E262 := E262 - 1;
      declare
         procedure F72;
         pragma Import (Ada, F72, "gnatcoll__terminal__finalize_spec");
      begin
         F72;
      end;
      E222 := E222 - 1;
      declare
         procedure F73;
         pragma Import (Ada, F73, "gnatcoll__refcount__finalize_spec");
      begin
         F73;
      end;
      declare
         procedure F74;
         pragma Import (Ada, F74, "gnatcoll__memory__finalize_body");
      begin
         E192 := E192 - 1;
         F74;
      end;
      E402 := E402 - 1;
      declare
         procedure F75;
         pragma Import (Ada, F75, "gdk__frame_timings__finalize_spec");
      begin
         F75;
      end;
      E337 := E337 - 1;
      declare
         procedure F76;
         pragma Import (Ada, F76, "glib__finalize_spec");
      begin
         F76;
      end;
      E316 := E316 - 1;
      declare
         procedure F77;
         pragma Import (Ada, F77, "system__tasking__protected_objects__entries__finalize_spec");
      begin
         F77;
      end;
      E238 := E238 - 1;
      declare
         procedure F78;
         pragma Import (Ada, F78, "ada__directories__finalize_spec");
      begin
         F78;
      end;
      E244 := E244 - 1;
      declare
         procedure F79;
         pragma Import (Ada, F79, "system__regexp__finalize_spec");
      begin
         F79;
      end;
      E227 := E227 - 1;
      declare
         procedure F80;
         pragma Import (Ada, F80, "system__pool_size__finalize_spec");
      begin
         F80;
      end;
      E258 := E258 - 1;
      declare
         procedure F81;
         pragma Import (Ada, F81, "gnat__expect__finalize_spec");
      begin
         F81;
      end;
      E216 := E216 - 1;
      declare
         procedure F82;
         pragma Import (Ada, F82, "system__pool_global__finalize_spec");
      begin
         F82;
      end;
      declare
         procedure F83;
         pragma Import (Ada, F83, "gnat__debug_pools__finalize_body");
      begin
         E196 := E196 - 1;
         F83;
      end;
      declare
         procedure F84;
         pragma Import (Ada, F84, "gnat__debug_pools__finalize_spec");
      begin
         F84;
      end;
      E103 := E103 - 1;
      declare
         procedure F85;
         pragma Import (Ada, F85, "ada__text_io__finalize_spec");
      begin
         F85;
      end;
      E158 := E158 - 1;
      declare
         procedure F86;
         pragma Import (Ada, F86, "ada__strings__unbounded__finalize_spec");
      begin
         F86;
      end;
      E164 := E164 - 1;
      declare
         procedure F87;
         pragma Import (Ada, F87, "system__storage_pools__subpools__finalize_spec");
      begin
         F87;
      end;
      E166 := E166 - 1;
      declare
         procedure F88;
         pragma Import (Ada, F88, "system__finalization_masters__finalize_spec");
      begin
         F88;
      end;
      E231 := E231 - 1;
      declare
         procedure F89;
         pragma Import (Ada, F89, "ada__streams__stream_io__finalize_spec");
      begin
         F89;
      end;
      declare
         procedure F90;
         pragma Import (Ada, F90, "system__file_io__finalize_body");
      begin
         E109 := E109 - 1;
         F90;
      end;
      declare
         procedure Reraise_Library_Exception_If_Any;
            pragma Import (Ada, Reraise_Library_Exception_If_Any, "__gnat_reraise_library_exception_if_any");
      begin
         Reraise_Library_Exception_If_Any;
      end;
   end finalize_library;

   procedure adafinal is
      procedure s_stalib_adafinal;
      pragma Import (C, s_stalib_adafinal, "system__standard_library__adafinal");

      procedure Runtime_Finalize;
      pragma Import (C, Runtime_Finalize, "__gnat_runtime_finalize");

   begin
      if not Is_Elaborated then
         return;
      end if;
      Is_Elaborated := False;
      Runtime_Finalize;
      s_stalib_adafinal;
   end adafinal;

   type No_Param_Proc is access procedure;

   procedure adainit is
      Main_Priority : Integer;
      pragma Import (C, Main_Priority, "__gl_main_priority");
      Time_Slice_Value : Integer;
      pragma Import (C, Time_Slice_Value, "__gl_time_slice_val");
      WC_Encoding : Character;
      pragma Import (C, WC_Encoding, "__gl_wc_encoding");
      Locking_Policy : Character;
      pragma Import (C, Locking_Policy, "__gl_locking_policy");
      Queuing_Policy : Character;
      pragma Import (C, Queuing_Policy, "__gl_queuing_policy");
      Task_Dispatching_Policy : Character;
      pragma Import (C, Task_Dispatching_Policy, "__gl_task_dispatching_policy");
      Priority_Specific_Dispatching : System.Address;
      pragma Import (C, Priority_Specific_Dispatching, "__gl_priority_specific_dispatching");
      Num_Specific_Dispatching : Integer;
      pragma Import (C, Num_Specific_Dispatching, "__gl_num_specific_dispatching");
      Main_CPU : Integer;
      pragma Import (C, Main_CPU, "__gl_main_cpu");
      Interrupt_States : System.Address;
      pragma Import (C, Interrupt_States, "__gl_interrupt_states");
      Num_Interrupt_States : Integer;
      pragma Import (C, Num_Interrupt_States, "__gl_num_interrupt_states");
      Unreserve_All_Interrupts : Integer;
      pragma Import (C, Unreserve_All_Interrupts, "__gl_unreserve_all_interrupts");
      Detect_Blocking : Integer;
      pragma Import (C, Detect_Blocking, "__gl_detect_blocking");
      Default_Stack_Size : Integer;
      pragma Import (C, Default_Stack_Size, "__gl_default_stack_size");
      Default_Secondary_Stack_Size : System.Parameters.Size_Type;
      pragma Import (C, Default_Secondary_Stack_Size, "__gnat_default_ss_size");
      Leap_Seconds_Support : Integer;
      pragma Import (C, Leap_Seconds_Support, "__gl_leap_seconds_support");
      Bind_Env_Addr : System.Address;
      pragma Import (C, Bind_Env_Addr, "__gl_bind_env_addr");

      procedure Runtime_Initialize (Install_Handler : Integer);
      pragma Import (C, Runtime_Initialize, "__gnat_runtime_initialize");

      Finalize_Library_Objects : No_Param_Proc;
      pragma Import (C, Finalize_Library_Objects, "__gnat_finalize_library_objects");
      Binder_Sec_Stacks_Count : Natural;
      pragma Import (Ada, Binder_Sec_Stacks_Count, "__gnat_binder_ss_count");
      Default_Sized_SS_Pool : System.Address;
      pragma Import (Ada, Default_Sized_SS_Pool, "__gnat_default_ss_pool");

   begin
      if Is_Elaborated then
         return;
      end if;
      Is_Elaborated := True;
      Main_Priority := -1;
      Time_Slice_Value := 0;
      WC_Encoding := 'b';
      Locking_Policy := 'C';
      Queuing_Policy := 'P';
      Task_Dispatching_Policy := 'F';
      System.Restrictions.Run_Time_Restrictions :=
        (Set =>
          (False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False, False, False, True, False, 
           False, False, False, False, False, True, False, False, 
           False, False, False, False, False, False, False, False, 
           False, False, False, False),
         Value => (0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
         Violated =>
          (True, False, False, False, True, True, True, False, 
           True, False, False, True, True, True, True, False, 
           False, True, False, False, True, True, False, True, 
           True, False, True, True, True, True, False, True, 
           False, False, False, True, False, True, True, False, 
           True, True, True, True, False, True, False, True, 
           True, False, True, True, False, True, True, False, 
           True, True, False, True, False, True, True, True, 
           True, False, True, False, True, True, True, False, 
           True, True, False, True, True, True, True, False, 
           False, True, False, False, False, True, True, True, 
           True, True, True, False),
         Count => (0, 0, 0, 1, 3, 3, 4, 2, 6, 0),
         Unknown => (False, False, False, False, False, False, True, True, True, False));
      Priority_Specific_Dispatching :=
        Local_Priority_Specific_Dispatching'Address;
      Num_Specific_Dispatching := 0;
      Main_CPU := -1;
      Interrupt_States := Local_Interrupt_States'Address;
      Num_Interrupt_States := 0;
      Unreserve_All_Interrupts := 0;
      Detect_Blocking := 0;
      Default_Stack_Size := -1;
      Leap_Seconds_Support := 0;

      ada_main'Elab_Body;
      Default_Secondary_Stack_Size := System.Parameters.Runtime_Default_Sec_Stack_Size;
      Binder_Sec_Stacks_Count := 1;
      Default_Sized_SS_Pool := Sec_Default_Sized_Stacks'Address;

      Runtime_Initialize (1);

      Finalize_Library_Objects := finalize_library'access;

      System.Soft_Links'Elab_Spec;
      System.Exception_Table'Elab_Body;
      E023 := E023 + 1;
      Ada.Io_Exceptions'Elab_Spec;
      E066 := E066 + 1;
      Ada.Strings'Elab_Spec;
      E050 := E050 + 1;
      Ada.Containers'Elab_Spec;
      E038 := E038 + 1;
      System.Exceptions'Elab_Spec;
      E025 := E025 + 1;
      Interfaces.C'Elab_Spec;
      System.Os_Lib'Elab_Body;
      E070 := E070 + 1;
      Ada.Strings.Maps'Elab_Spec;
      Ada.Strings.Maps.Constants'Elab_Spec;
      E056 := E056 + 1;
      System.Soft_Links.Initialize'Elab_Body;
      E019 := E019 + 1;
      E011 := E011 + 1;
      System.Object_Reader'Elab_Spec;
      System.Dwarf_Lines'Elab_Spec;
      E045 := E045 + 1;
      E076 := E076 + 1;
      E052 := E052 + 1;
      System.Traceback.Symbolic'Elab_Body;
      E037 := E037 + 1;
      E078 := E078 + 1;
      Ada.Tags'Elab_Spec;
      Ada.Tags'Elab_Body;
      E097 := E097 + 1;
      Ada.Streams'Elab_Spec;
      E105 := E105 + 1;
      Interfaces.C.Strings'Elab_Spec;
      E146 := E146 + 1;
      System.File_Control_Block'Elab_Spec;
      E113 := E113 + 1;
      System.Finalization_Root'Elab_Spec;
      E112 := E112 + 1;
      Ada.Finalization'Elab_Spec;
      E110 := E110 + 1;
      System.File_Io'Elab_Body;
      E109 := E109 + 1;
      Ada.Streams.Stream_Io'Elab_Spec;
      E231 := E231 + 1;
      System.Storage_Pools'Elab_Spec;
      E170 := E170 + 1;
      System.Finalization_Masters'Elab_Spec;
      System.Finalization_Masters'Elab_Body;
      E166 := E166 + 1;
      System.Storage_Pools.Subpools'Elab_Spec;
      E164 := E164 + 1;
      Ada.Strings.Unbounded'Elab_Spec;
      E158 := E158 + 1;
      System.Task_Info'Elab_Spec;
      E308 := E308 + 1;
      System.Regpat'Elab_Spec;
      E184 := E184 + 1;
      Ada.Calendar'Elab_Spec;
      Ada.Calendar'Elab_Body;
      E121 := E121 + 1;
      Ada.Calendar.Delays'Elab_Body;
      E567 := E567 + 1;
      Ada.Calendar.Time_Zones'Elab_Spec;
      E130 := E130 + 1;
      Ada.Real_Time'Elab_Spec;
      Ada.Real_Time'Elab_Body;
      E334 := E334 + 1;
      Ada.Text_Io'Elab_Spec;
      Ada.Text_Io'Elab_Body;
      E103 := E103 + 1;
      Gnat.Calendar'Elab_Spec;
      E153 := E153 + 1;
      Gnat.Calendar.Time_Io'Elab_Spec;
      E156 := E156 + 1;
      Gnat.Directory_Operations'Elab_Spec;
      Gnat.Directory_Operations'Elab_Body;
      E248 := E248 + 1;
      System.Assertions'Elab_Spec;
      E264 := E264 + 1;
      System.Checked_Pools'Elab_Spec;
      E207 := E207 + 1;
      Gnat.Debug_Pools'Elab_Spec;
      Gnat.Debug_Pools'Elab_Body;
      E196 := E196 + 1;
      System.Pool_Global'Elab_Spec;
      E216 := E216 + 1;
      Gnat.Expect'Elab_Spec;
      E258 := E258 + 1;
      System.Pool_Size'Elab_Spec;
      E227 := E227 + 1;
      System.Regexp'Elab_Spec;
      E244 := E244 + 1;
      Ada.Directories'Elab_Spec;
      Ada.Directories'Elab_Body;
      E238 := E238 + 1;
      System.Tasking.Initialization'Elab_Body;
      E320 := E320 + 1;
      System.Tasking.Protected_Objects'Elab_Body;
      E294 := E294 + 1;
      System.Tasking.Protected_Objects.Entries'Elab_Spec;
      E316 := E316 + 1;
      System.Tasking.Queuing'Elab_Body;
      E328 := E328 + 1;
      System.Tasking.Stages'Elab_Body;
      E539 := E539 + 1;
      System.Tasking.Async_Delays'Elab_Body;
      E571 := E571 + 1;
      Glib'Elab_Spec;
      Gtkada.Types'Elab_Spec;
      E340 := E340 + 1;
      E337 := E337 + 1;
      Gdk.Frame_Timings'Elab_Spec;
      Gdk.Frame_Timings'Elab_Body;
      E402 := E402 + 1;
      E358 := E358 + 1;
      Gdk.Visual'Elab_Body;
      E384 := E384 + 1;
      E360 := E360 + 1;
      E190 := E190 + 1;
      GNATCOLL.MEMORY'ELAB_BODY;
      E192 := E192 + 1;
      E224 := E224 + 1;
      GNATCOLL.REFCOUNT'ELAB_SPEC;
      E222 := E222 + 1;
      E220 := E220 + 1;
      GNATCOLL.STRINGS'ELAB_SPEC;
      GNATCOLL.STRINGS'ELAB_BODY;
      E218 := E218 + 1;
      GNATCOLL.TEMPLATES'ELAB_SPEC;
      E260 := E260 + 1;
      GNATCOLL.TERMINAL'ELAB_SPEC;
      GNATCOLL.TERMINAL'ELAB_BODY;
      E262 := E262 + 1;
      E254 := E254 + 1;
      GNATCOLL.IO'ELAB_SPEC;
      GNATCOLL.IO'ELAB_BODY;
      E214 := E214 + 1;
      GNATCOLL.PATH'ELAB_SPEC;
      GNATCOLL.PATH'ELAB_BODY;
      E252 := E252 + 1;
      GNATCOLL.IO.NATIVE'ELAB_SPEC;
      GNATCOLL.MMAP'ELAB_SPEC;
      GNATCOLL.IO.NATIVE'ELAB_BODY;
      E236 := E236 + 1;
      E209 := E209 + 1;
      E211 := E211 + 1;
      GNATCOLL.REMOTE'ELAB_SPEC;
      E280 := E280 + 1;
      GNATCOLL.REMOTE.DB'ELAB_SPEC;
      E284 := E284 + 1;
      GNATCOLL.IO.REMOTE'ELAB_SPEC;
      E282 := E282 + 1;
      E279 := E279 + 1;
      GNATCOLL.IO.REMOTE'ELAB_BODY;
      E277 := E277 + 1;
      GNATCOLL.VFS'ELAB_SPEC;
      GNATCOLL.VFS'ELAB_BODY;
      E269 := E269 + 1;
      GNATCOLL.TRACES'ELAB_SPEC;
      GNATCOLL.TRACES'ELAB_BODY;
      E119 := E119 + 1;
      E116 := E116 + 1;
      E352 := E352 + 1;
      Glib.Object'Elab_Spec;
      Glib.Values'Elab_Body;
      E356 := E356 + 1;
      E348 := E348 + 1;
      E354 := E354 + 1;
      E350 := E350 + 1;
      Glib.Object'Elab_Body;
      E346 := E346 + 1;
      E367 := E367 + 1;
      E369 := E369 + 1;
      E373 := E373 + 1;
      Glib.Generic_Properties'Elab_Spec;
      Glib.Generic_Properties'Elab_Body;
      E344 := E344 + 1;
      Gdk.Color'Elab_Spec;
      E394 := E394 + 1;
      E342 := E342 + 1;
      constants'elab_spec;
      E114 := E114 + 1;
      E563 := E563 + 1;
      E371 := E371 + 1;
      E505 := E505 + 1;
      E386 := E386 + 1;
      E467 := E467 + 1;
      Glib.Variant'Elab_Spec;
      Glib.Variant'Elab_Body;
      E465 := E465 + 1;
      E463 := E463 + 1;
      Gtk.Actionable'Elab_Spec;
      E527 := E527 + 1;
      Gtk.Builder'Elab_Spec;
      Gtk.Builder'Elab_Body;
      E410 := E410 + 1;
      E447 := E447 + 1;
      Gtk.Cell_Area_Context'Elab_Spec;
      Gtk.Cell_Area_Context'Elab_Body;
      E479 := E479 + 1;
      Gtk.Css_Section'Elab_Spec;
      Gtk.Css_Section'Elab_Body;
      E495 := E495 + 1;
      E388 := E388 + 1;
      Gtk.Orientable'Elab_Spec;
      E453 := E453 + 1;
      Gtk.Paper_Size'Elab_Spec;
      Gtk.Paper_Size'Elab_Body;
      E507 := E507 + 1;
      Gtk.Page_Setup'Elab_Spec;
      Gtk.Page_Setup'Elab_Body;
      E503 := E503 + 1;
      Gtk.Print_Settings'Elab_Spec;
      Gtk.Print_Settings'Elab_Body;
      E515 := E515 + 1;
      E418 := E418 + 1;
      Gtk.Target_List'Elab_Spec;
      Gtk.Target_List'Elab_Body;
      E416 := E416 + 1;
      E423 := E423 + 1;
      Pango.Attributes'Elab_Spec;
      Pango.Attributes'Elab_Body;
      E441 := E441 + 1;
      Pango.Font_Metrics'Elab_Spec;
      Pango.Font_Metrics'Elab_Body;
      E427 := E427 + 1;
      Pango.Language'Elab_Spec;
      Pango.Language'Elab_Body;
      E429 := E429 + 1;
      Pango.Font'Elab_Spec;
      Pango.Font'Elab_Body;
      E425 := E425 + 1;
      E521 := E521 + 1;
      Gtk.Text_Tag'Elab_Spec;
      Gtk.Text_Tag'Elab_Body;
      E523 := E523 + 1;
      Pango.Font_Face'Elab_Spec;
      Pango.Font_Face'Elab_Body;
      E433 := E433 + 1;
      Pango.Font_Family'Elab_Spec;
      Pango.Font_Family'Elab_Body;
      E431 := E431 + 1;
      Pango.Fontset'Elab_Spec;
      Pango.Fontset'Elab_Body;
      E435 := E435 + 1;
      E437 := E437 + 1;
      Pango.Context'Elab_Spec;
      Pango.Context'Elab_Body;
      E421 := E421 + 1;
      Pango.Font_Map'Elab_Spec;
      Pango.Font_Map'Elab_Body;
      E511 := E511 + 1;
      Pango.Tabs'Elab_Spec;
      Pango.Tabs'Elab_Body;
      E443 := E443 + 1;
      Pango.Layout'Elab_Spec;
      Pango.Layout'Elab_Body;
      E439 := E439 + 1;
      Gtk.Print_Context'Elab_Spec;
      Gtk.Print_Context'Elab_Body;
      E509 := E509 + 1;
      Gdk.Display'Elab_Spec;
      Gtk.Tree_Model'Elab_Spec;
      Gtk.Entry_Buffer'Elab_Spec;
      Gtk.Cell_Editable'Elab_Spec;
      Gtk.Adjustment'Elab_Spec;
      Gtk.Style'Elab_Spec;
      Gtk.Accel_Group'Elab_Spec;
      Gdk.Frame_Clock'Elab_Spec;
      Gdk.Pixbuf'Elab_Spec;
      Gtk.Icon_Source'Elab_Spec;
      Gtk.Icon_Source'Elab_Body;
      E491 := E491 + 1;
      E404 := E404 + 1;
      Gdk.Screen'Elab_Spec;
      Gdk.Screen'Elab_Body;
      E380 := E380 + 1;
      E519 := E519 + 1;
      Gtk.Selection_Data'Elab_Spec;
      Gtk.Selection_Data'Elab_Body;
      E412 := E412 + 1;
      Gdk.Device'Elab_Spec;
      Gdk.Drag_Contexts'Elab_Spec;
      Gdk.Drag_Contexts'Elab_Body;
      E398 := E398 + 1;
      Gdk.Device'Elab_Body;
      E396 := E396 + 1;
      Gtk.Widget'Elab_Spec;
      Gtk.Misc'Elab_Spec;
      Gtk.Misc'Elab_Body;
      E497 := E497 + 1;
      E390 := E390 + 1;
      Gtk.Settings'Elab_Spec;
      Gtk.Settings'Elab_Body;
      E378 := E378 + 1;
      Gdk.Window'Elab_Spec;
      E457 := E457 + 1;
      Gtk.Style_Context'Elab_Spec;
      Gtk.Icon_Set'Elab_Spec;
      Gtk.Icon_Set'Elab_Body;
      E489 := E489 + 1;
      Gtk.Image'Elab_Spec;
      Gtk.Image'Elab_Body;
      E487 := E487 + 1;
      Gtk.Cell_Renderer'Elab_Spec;
      Gtk.Container'Elab_Spec;
      Gtk.Bin'Elab_Spec;
      Gtk.Bin'Elab_Body;
      E459 := E459 + 1;
      Gtk.Box'Elab_Spec;
      Gtk.Box'Elab_Body;
      E445 := E445 + 1;
      Gtk.Status_Bar'Elab_Spec;
      Gtk.Notebook'Elab_Spec;
      E481 := E481 + 1;
      Gtk.Cell_Area'Elab_Spec;
      Gtk.Entry_Completion'Elab_Spec;
      Gtk.Window'Elab_Spec;
      Gtk.Dialog'Elab_Spec;
      Gtk.Print_Operation'Elab_Spec;
      Gtk.Gentry'Elab_Spec;
      Gtk.Status_Bar'Elab_Body;
      E517 := E517 + 1;
      E513 := E513 + 1;
      Gtk.Print_Operation'Elab_Body;
      E501 := E501 + 1;
      Gtk.Notebook'Elab_Body;
      E499 := E499 + 1;
      Gtk.Style_Context'Elab_Body;
      E493 := E493 + 1;
      Gtk.Gentry'Elab_Body;
      E461 := E461 + 1;
      Gtk.Tree_Model'Elab_Body;
      E485 := E485 + 1;
      Gtk.Cell_Area'Elab_Body;
      E477 := E477 + 1;
      Gtk.Entry_Completion'Elab_Body;
      E475 := E475 + 1;
      Gtk.Cell_Renderer'Elab_Body;
      E483 := E483 + 1;
      Gtk.Entry_Buffer'Elab_Body;
      E473 := E473 + 1;
      E471 := E471 + 1;
      E469 := E469 + 1;
      Gtk.Window'Elab_Body;
      E455 := E455 + 1;
      Gtk.Dialog'Elab_Body;
      E376 := E376 + 1;
      Gtk.Adjustment'Elab_Body;
      E451 := E451 + 1;
      Gtk.Container'Elab_Body;
      E449 := E449 + 1;
      Gtk.Style'Elab_Body;
      E414 := E414 + 1;
      Gtk.Widget'Elab_Body;
      E392 := E392 + 1;
      Gtk.Accel_Group'Elab_Body;
      E408 := E408 + 1;
      Gdk.Frame_Clock'Elab_Body;
      E400 := E400 + 1;
      Gdk.Display'Elab_Body;
      E382 := E382 + 1;
      E365 := E365 + 1;
      Glib.Menu_Model'Elab_Spec;
      Glib.Menu_Model'Elab_Body;
      E549 := E549 + 1;
      Gtk.Action'Elab_Spec;
      Gtk.Action'Elab_Body;
      E525 := E525 + 1;
      Gtk.Activatable'Elab_Spec;
      E529 := E529 + 1;
      Gtk.Button'Elab_Spec;
      Gtk.Button'Elab_Body;
      E363 := E363 + 1;
      Gtk.Color_Chooser'Elab_Spec;
      E543 := E543 + 1;
      Gtk.Color_Button'Elab_Spec;
      Gtk.Color_Button'Elab_Body;
      E541 := E541 + 1;
      Gtk.Grid'Elab_Spec;
      Gtk.Grid'Elab_Body;
      E557 := E557 + 1;
      E559 := E559 + 1;
      Gtk.Menu_Item'Elab_Spec;
      Gtk.Menu_Item'Elab_Body;
      E551 := E551 + 1;
      Gtk.Menu_Shell'Elab_Spec;
      Gtk.Menu_Shell'Elab_Body;
      E553 := E553 + 1;
      Gtk.Menu'Elab_Spec;
      Gtk.Menu'Elab_Body;
      E547 := E547 + 1;
      Gtk.Label'Elab_Spec;
      Gtk.Label'Elab_Body;
      E545 := E545 + 1;
      Gtk.Progress_Bar'Elab_Spec;
      Gtk.Progress_Bar'Elab_Body;
      E555 := E555 + 1;
      controllers'elab_body;
      E535 := E535 + 1;
      pump_station'elab_body;
      E575 := E575 + 1;
      environment_station'elab_body;
      E565 := E565 + 1;
      mine_water_level_control_system'elab_body;
      E561 := E561 + 1;
      gui'elab_spec;
      gui'elab_body;
      E531 := E531 + 1;
   end adainit;

   procedure Ada_Main_Program;
   pragma Import (Ada, Ada_Main_Program, "_ada_main");

   function main
     (argc : Integer;
      argv : System.Address;
      envp : System.Address)
      return Integer
   is
      procedure Initialize (Addr : System.Address);
      pragma Import (C, Initialize, "__gnat_initialize");

      procedure Finalize;
      pragma Import (C, Finalize, "__gnat_finalize");
      SEH : aliased array (1 .. 2) of Integer;

      Ensure_Reference : aliased System.Address := Ada_Main_Program_Name'Address;
      pragma Volatile (Ensure_Reference);

   begin
      gnat_argc := argc;
      gnat_argv := argv;
      gnat_envp := envp;

      Initialize (SEH'Address);
      adainit;
      Ada_Main_Program;
      adafinal;
      Finalize;
      return (gnat_exit_status);
   end;

--  BEGIN Object file/option list
   --   D:\RealTimeProgramming\ADAGPSProject\obj\devices.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\constants.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\alarm_station.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\controllers.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\pump_station.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\environment_station.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\mine_water_level_control_system.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\gui.o
   --   D:\RealTimeProgramming\ADAGPSProject\obj\main.o
   --   -LD:\RealTimeProgramming\ADAGPSProject\obj\
   --   -LD:\RealTimeProgramming\ADAGPSProject\obj\
   --   -LC:\GNAT\2018\lib\gnatcoll.static\
   --   -LC:\GNAT\2018\lib\xmlada\xmlada_dom.static\
   --   -LC:\GNAT\2018\lib\xmlada\xmlada_sax.static\
   --   -LC:\GNAT\2018\lib\xmlada\xmlada_unicode.static\
   --   -LC:\GNAT\2018\lib\xmlada\xmlada_input.static\
   --   -LC:\GNAT\2018\lib\xmlada\xmlada_schema.static\
   --   -LC:\GNAT\2018\lib\gpr\static\gpr\
   --   -LC:\GtkAda\lib\gtkada\gtkada.static\gtkada\
   --   -LC:/gnat/2018/lib/gcc/x86_64-pc-mingw32/7.3.1/adalib/
   --   -static
   --   -shared-libgcc
   --   -shared-libgcc
   --   -shared-libgcc
   --   -lgnarl
   --   -lgnat
   --   -Xlinker
   --   --stack=0x200000,0x1000
   --   -mthreads
   --   -Wl,--stack=0x2000000
--  END Object file/option list   

end ada_main;
