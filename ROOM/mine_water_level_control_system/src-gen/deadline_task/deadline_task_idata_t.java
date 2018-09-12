package deadline_task;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class deadline_task_idata_t implements Serializable {

	private static final long serialVersionUID = -1771339511L;


	/*--------------------- attributes ---------------------*/
	public  int deadline;

	/* --------------------- attribute setters and getters */
	public void setDeadline(int deadline) {
		 this.deadline = deadline;
	}
	public int getDeadline() {
		return this.deadline;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public deadline_task_idata_t() {
		super();

		// initialize attributes
		this.setDeadline(0);

		/* user defined constructor body */
	}

	// constructor using fields
	public deadline_task_idata_t(int deadline) {
		super();

		this.deadline = deadline;

		/* user defined constructor body */
	}

	// deep copy
	public deadline_task_idata_t deepCopy() {
		deadline_task_idata_t copy = new deadline_task_idata_t();
		copy.deadline = deadline;
		return copy;
	}
};
