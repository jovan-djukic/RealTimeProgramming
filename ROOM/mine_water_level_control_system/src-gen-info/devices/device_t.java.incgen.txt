package devices;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class device_t implements Serializable {

	private static final long serialVersionUID = 549285032L;


	/*--------------------- attributes ---------------------*/
	public  int state;

	/* --------------------- attribute setters and getters */
	public void setState(int state) {
		 this.state = state;
	}
	public int getState() {
		return this.state;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public device_t() {
		super();

		// initialize attributes

		/* user defined constructor body */
	}

	// constructor using fields
	public device_t(int state) {
		super();

		this.state = state;

		/* user defined constructor body */
	}

	// deep copy
	public device_t deepCopy() {
		device_t copy = new device_t();
		copy.state = state;
		return copy;
	}
};
