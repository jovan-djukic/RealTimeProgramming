package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class water_level_detector_t implements Serializable {

	private static final long serialVersionUID = -245829349L;


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
	public water_level_detector_t() {
		super();

		// initialize attributes
		this.setValue(0);

		/* user defined constructor body */
	}

	// constructor using fields
	public water_level_detector_t(int value) {
		super();

		this.value = value;

		/* user defined constructor body */
	}

	// deep copy
	public water_level_detector_t deepCopy() {
		water_level_detector_t copy = new water_level_detector_t();
		copy.value = value;
		return copy;
	}
};
