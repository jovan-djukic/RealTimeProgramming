package periodic_task;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class periodic_task_idata_t implements Serializable {

	private static final long serialVersionUID = 266151977L;


	/*--------------------- attributes ---------------------*/
	public  int period;

	/* --------------------- attribute setters and getters */
	public void setPeriod(int period) {
		 this.period = period;
	}
	public int getPeriod() {
		return this.period;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public periodic_task_idata_t() {
		super();

		// initialize attributes
		this.setPeriod(0);

		/* user defined constructor body */
	}

	// constructor using fields
	public periodic_task_idata_t(int period) {
		super();

		this.period = period;

		/* user defined constructor body */
	}

	// deep copy
	public periodic_task_idata_t deepCopy() {
		periodic_task_idata_t copy = new periodic_task_idata_t();
		copy.period = period;
		return copy;
	}
};
