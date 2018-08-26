package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class gas_sensor_t implements Serializable {

	private static final long serialVersionUID = 691136818L;


	/*--------------------- attributes ---------------------*/
	public  int value;
	public  boolean error_occurred;

	/* --------------------- attribute setters and getters */
	public void setValue(int value) {
		 this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	public void setError_occurred(boolean error_occurred) {
		 this.error_occurred = error_occurred;
	}
	public boolean getError_occurred() {
		return this.error_occurred;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public gas_sensor_t() {
		super();

		// initialize attributes

		/* user defined constructor body */
	}

	// constructor using fields
	public gas_sensor_t(int value, boolean error_occurred) {
		super();

		this.value = value;
		this.error_occurred = error_occurred;

		/* user defined constructor body */
	}

	// deep copy
	public gas_sensor_t deepCopy() {
		gas_sensor_t copy = new gas_sensor_t();
		copy.value = value;
		copy.error_occurred = error_occurred;
		return copy;
	}
};
