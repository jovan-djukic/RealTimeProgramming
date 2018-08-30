package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class water_flow_sensor_t implements Serializable {

	private static final long serialVersionUID = -1378139141L;


	/*--------------------- attributes ---------------------*/
	public  int value;

	/* --------------------- attribute setters and getters */
	public void setValue(int value) {
		 this.value = value;
	}
	public int getValue() {
		return this.value;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_flow_sensor_t() {
		super();

		// initialize attributes

		/* user defined constructor body */
	}

	// constructor using fields
	public water_flow_sensor_t(int value) {
		super();

		this.value = value;

		/* user defined constructor body */
	}

	// deep copy
	public water_flow_sensor_t deepCopy() {
		water_flow_sensor_t copy = new water_flow_sensor_t();
		copy.value = value;
		return copy;
	}
};
