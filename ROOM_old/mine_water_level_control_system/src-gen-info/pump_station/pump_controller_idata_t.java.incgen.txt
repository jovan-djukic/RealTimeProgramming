package pump_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;



public class pump_controller_idata_t implements Serializable {

	private static final long serialVersionUID = 1928224095L;


	/*--------------------- attributes ---------------------*/
	public  device_t pump;

	/* --------------------- attribute setters and getters */
	public void setPump(device_t pump) {
		 this.pump = pump;
	}
	public device_t getPump() {
		return this.pump;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public pump_controller_idata_t() {
		super();

		// initialize attributes
		this.setPump(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public pump_controller_idata_t(device_t pump) {
		super();

		this.pump = pump;

		/* user defined constructor body */
	}

	// deep copy
	public pump_controller_idata_t deepCopy() {
		pump_controller_idata_t copy = new pump_controller_idata_t();
		copy.pump = pump;
		return copy;
	}
};
