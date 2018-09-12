package pump_station;

public interface pump_controller_state_t {
	static final int PUMP_TURNED_OFF = 0;
	static final int PUMP_TURNED_ON = 1;
	static final int METHANE_THRESHOLD_BREACHED = 2;
}
