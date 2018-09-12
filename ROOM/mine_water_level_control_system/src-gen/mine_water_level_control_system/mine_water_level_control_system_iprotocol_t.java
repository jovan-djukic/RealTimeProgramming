package mine_water_level_control_system;

import org.eclipse.etrice.runtime.java.messaging.Message;
import org.eclipse.etrice.runtime.java.modelbase.EventMessage;
import org.eclipse.etrice.runtime.java.modelbase.EventWithDataMessage;
import org.eclipse.etrice.runtime.java.modelbase.IInterfaceItemOwner;
import org.eclipse.etrice.runtime.java.modelbase.InterfaceItemBase;
import org.eclipse.etrice.runtime.java.modelbase.PortBase;
import org.eclipse.etrice.runtime.java.modelbase.ReplicatedPortBase;
import org.eclipse.etrice.runtime.java.debugging.DebuggingService;
import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;


import devices.*;
import logger.*;

public class mine_water_level_control_system_iprotocol_t {
	// message IDs
	public static final int MSG_MIN = 0;
	public static final int IN_initialize = 1;
	public static final int MSG_MAX = 2;


	private static String messageStrings[] = {"MIN",  "initialize","MAX"};

	public String getMessageString(int msg_id) {
		if (msg_id<MSG_MIN || msg_id>MSG_MAX+1){
			// id out of range
			return "Message ID out of range";
		}
		else{
			return messageStrings[msg_id];
		}
	}

	
	// port class
	static public class mine_water_level_control_system_iprotocol_tPort extends PortBase {
		// constructors
		public mine_water_level_control_system_iprotocol_tPort(IInterfaceItemOwner actor, String name, int localId) {
			this(actor, name, localId, 0);
		}
		public mine_water_level_control_system_iprotocol_tPort(IInterfaceItemOwner actor, String name, int localId, int idx) {
			super(actor, name, localId, idx);
			DebuggingService.getInstance().addPortInstance(this);
		}
	
		public void destroy() {
			DebuggingService.getInstance().removePortInstance(this);
			super.destroy();
		}
	
		@Override
		public void receive(Message m) {
			if (!(m instanceof EventMessage))
				return;
			EventMessage msg = (EventMessage) m;
			if (0 < msg.getEvtId() && msg.getEvtId() < MSG_MAX) {
				DebuggingService.getInstance().addMessageAsyncIn(getPeerAddress(), getAddress(), messageStrings[msg.getEvtId()]);
				if (msg instanceof EventWithDataMessage)
					getActor().receiveEvent(this, msg.getEvtId(), ((EventWithDataMessage)msg).getData());
				else
					getActor().receiveEvent(this, msg.getEvtId(), null);
			}
	}
	
	
		// sent messages
	}
	
	// replicated port class
	static public class mine_water_level_control_system_iprotocol_tReplPort extends ReplicatedPortBase {
	
		public mine_water_level_control_system_iprotocol_tReplPort(IInterfaceItemOwner actor, String name, int localId) {
			super(actor, name, localId);
		}
	
		public int getReplication() {
			return getNInterfaceItems();
		}
	
		public int getIndexOf(InterfaceItemBase ifitem){
				return ifitem.getIdx();
		}
	
		public mine_water_level_control_system_iprotocol_tPort get(int idx) {
			return (mine_water_level_control_system_iprotocol_tPort) getInterfaceItem(idx);
		}
	
		protected InterfaceItemBase createInterfaceItem(IInterfaceItemOwner rcv, String name, int lid, int idx) {
			return new mine_water_level_control_system_iprotocol_tPort(rcv, name, lid, idx);
		}
	
		// outgoing messages
	}
	
	
	// port class
	static public class mine_water_level_control_system_iprotocol_tConjPort extends PortBase {
		// constructors
		public mine_water_level_control_system_iprotocol_tConjPort(IInterfaceItemOwner actor, String name, int localId) {
			this(actor, name, localId, 0);
		}
		public mine_water_level_control_system_iprotocol_tConjPort(IInterfaceItemOwner actor, String name, int localId, int idx) {
			super(actor, name, localId, idx);
			DebuggingService.getInstance().addPortInstance(this);
		}
	
		public void destroy() {
			DebuggingService.getInstance().removePortInstance(this);
			super.destroy();
		}
	
		@Override
		public void receive(Message m) {
			if (!(m instanceof EventMessage))
				return;
			EventMessage msg = (EventMessage) m;
			if (0 < msg.getEvtId() && msg.getEvtId() < MSG_MAX) {
				DebuggingService.getInstance().addMessageAsyncIn(getPeerAddress(), getAddress(), messageStrings[msg.getEvtId()]);
				if (msg instanceof EventWithDataMessage)
					getActor().receiveEvent(this, msg.getEvtId(), ((EventWithDataMessage)msg).getData());
				else
					getActor().receiveEvent(this, msg.getEvtId(), null);
			}
	}
	
	
		// sent messages
		public void initialize(mine_water_level_control_system_idata_t data) {
			DebuggingService.getInstance().addMessageAsyncOut(getAddress(), getPeerAddress(), messageStrings[IN_initialize]);
			if (getPeerAddress()!=null)
				getPeerMsgReceiver().receive(new EventWithDataMessage(getPeerAddress(), IN_initialize, data.deepCopy()));
		}
		public void initialize(int water_level, int water_level_sensors_low_water_level_threshold, int water_level_sensors_high_water_level_threshold, device_t alarm, device_t pump, gas_sensor_t co_sensor, int co_sensor_controller_deadline_in_ms, int co_sensor_controller_period_in_ms, int co_sensor_controller_threshold, int co_sensor_controller_error_count_threshold, gas_sensor_t o_sensor, int o_sensor_controller_deadline_in_ms, int o_sensor_controller_period_in_ms, int o_sensor_controller_threshold, int o_sensor_controller_error_count_threshold, gas_sensor_t ch4_sensor, int ch4_sensor_controller_deadline_in_ms, int ch4_sensor_controller_period_in_ms, int ch4_sensor_controller_threshold, int ch4_sensor_controller_error_count_threshold, int water_level_sensors_controller_deadline_in_ms, sensor_t water_flow_sensor, int water_flow_sensor_controller_deadline_in_ms, int water_flow_sensor_controller_period_in_ms, int water_flow_sensor_controller_number_of_activations) {
			initialize(new mine_water_level_control_system_idata_t(water_level, water_level_sensors_low_water_level_threshold, water_level_sensors_high_water_level_threshold, alarm, pump, co_sensor, co_sensor_controller_deadline_in_ms, co_sensor_controller_period_in_ms, co_sensor_controller_threshold, co_sensor_controller_error_count_threshold, o_sensor, o_sensor_controller_deadline_in_ms, o_sensor_controller_period_in_ms, o_sensor_controller_threshold, o_sensor_controller_error_count_threshold, ch4_sensor, ch4_sensor_controller_deadline_in_ms, ch4_sensor_controller_period_in_ms, ch4_sensor_controller_threshold, ch4_sensor_controller_error_count_threshold, water_level_sensors_controller_deadline_in_ms, water_flow_sensor, water_flow_sensor_controller_deadline_in_ms, water_flow_sensor_controller_period_in_ms, water_flow_sensor_controller_number_of_activations));
		}
	}
	
	// replicated port class
	static public class mine_water_level_control_system_iprotocol_tConjReplPort extends ReplicatedPortBase {
	
		public mine_water_level_control_system_iprotocol_tConjReplPort(IInterfaceItemOwner actor, String name, int localId) {
			super(actor, name, localId);
		}
	
		public int getReplication() {
			return getNInterfaceItems();
		}
	
		public int getIndexOf(InterfaceItemBase ifitem){
				return ifitem.getIdx();
		}
	
		public mine_water_level_control_system_iprotocol_tConjPort get(int idx) {
			return (mine_water_level_control_system_iprotocol_tConjPort) getInterfaceItem(idx);
		}
	
		protected InterfaceItemBase createInterfaceItem(IInterfaceItemOwner rcv, String name, int lid, int idx) {
			return new mine_water_level_control_system_iprotocol_tConjPort(rcv, name, lid, idx);
		}
	
		// incoming messages
		public void initialize(mine_water_level_control_system_idata_t data){
			for (InterfaceItemBase item : getItems()) {
				((mine_water_level_control_system_iprotocol_tConjPort)item).initialize( data);
			}
		}
	}
	
}
