package environment_monitoring_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;




public class environment_station_actor_base_idata_t implements Serializable {

	private static final long serialVersionUID = 578271012L;


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
	public environment_station_actor_base_idata_t() {
		super();

		// initialize attributes
		this.setPeriod(0);

		/* user defined constructor body */
	}

	// constructor using fields
	public environment_station_actor_base_idata_t(int period) {
		super();

		this.period = period;

		/* user defined constructor body */
	}

	// deep copy
	public environment_station_actor_base_idata_t deepCopy() {
		environment_station_actor_base_idata_t copy = new environment_station_actor_base_idata_t();
		copy.period = period;
		return copy;
	}
};
