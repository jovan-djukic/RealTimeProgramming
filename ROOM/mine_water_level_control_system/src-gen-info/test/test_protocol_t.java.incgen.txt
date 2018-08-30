package test;

import org.eclipse.etrice.runtime.java.messaging.Message;
import org.eclipse.etrice.runtime.java.modelbase.EventMessage;
import org.eclipse.etrice.runtime.java.modelbase.EventWithDataMessage;
import org.eclipse.etrice.runtime.java.modelbase.IInterfaceItemOwner;
import org.eclipse.etrice.runtime.java.modelbase.InterfaceItemBase;
import org.eclipse.etrice.runtime.java.modelbase.PortBase;
import org.eclipse.etrice.runtime.java.modelbase.ReplicatedPortBase;
import org.eclipse.etrice.runtime.java.debugging.DebuggingService;
import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;



public class test_protocol_t {
	// message IDs
	public static final int MSG_MIN = 0;
	public static final int OUT_activated = 1;
	public static final int MSG_MAX = 2;


	private static String messageStrings[] = {"MIN", "activated", "MAX"};

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
	static public class test_protocol_tPort extends PortBase {
		// constructors
		public test_protocol_tPort(IInterfaceItemOwner actor, String name, int localId) {
			this(actor, name, localId, 0);
		}
		public test_protocol_tPort(IInterfaceItemOwner actor, String name, int localId, int idx) {
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
		public void activated() {
			DebuggingService.getInstance().addMessageAsyncOut(getAddress(), getPeerAddress(), messageStrings[OUT_activated]);
			if (getPeerAddress()!=null)
				getPeerMsgReceiver().receive(new EventMessage(getPeerAddress(), OUT_activated));
		}
	}
	
	// replicated port class
	static public class test_protocol_tReplPort extends ReplicatedPortBase {
	
		public test_protocol_tReplPort(IInterfaceItemOwner actor, String name, int localId) {
			super(actor, name, localId);
		}
	
		public int getReplication() {
			return getNInterfaceItems();
		}
	
		public int getIndexOf(InterfaceItemBase ifitem){
				return ifitem.getIdx();
		}
	
		public test_protocol_tPort get(int idx) {
			return (test_protocol_tPort) getInterfaceItem(idx);
		}
	
		protected InterfaceItemBase createInterfaceItem(IInterfaceItemOwner rcv, String name, int lid, int idx) {
			return new test_protocol_tPort(rcv, name, lid, idx);
		}
	
		// outgoing messages
		public void activated(){
			for (InterfaceItemBase item : getItems()) {
				((test_protocol_tPort)item).activated();
			}
		}
	}
	
	
	// port class
	static public class test_protocol_tConjPort extends PortBase {
		// constructors
		public test_protocol_tConjPort(IInterfaceItemOwner actor, String name, int localId) {
			this(actor, name, localId, 0);
		}
		public test_protocol_tConjPort(IInterfaceItemOwner actor, String name, int localId, int idx) {
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
	static public class test_protocol_tConjReplPort extends ReplicatedPortBase {
	
		public test_protocol_tConjReplPort(IInterfaceItemOwner actor, String name, int localId) {
			super(actor, name, localId);
		}
	
		public int getReplication() {
			return getNInterfaceItems();
		}
	
		public int getIndexOf(InterfaceItemBase ifitem){
				return ifitem.getIdx();
		}
	
		public test_protocol_tConjPort get(int idx) {
			return (test_protocol_tConjPort) getInterfaceItem(idx);
		}
	
		protected InterfaceItemBase createInterfaceItem(IInterfaceItemOwner rcv, String name, int lid, int idx) {
			return new test_protocol_tConjPort(rcv, name, lid, idx);
		}
	
		// incoming messages
	}
	
}
