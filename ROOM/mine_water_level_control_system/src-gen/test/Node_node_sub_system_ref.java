package test;

import org.eclipse.etrice.runtime.java.config.IVariableService;
import org.eclipse.etrice.runtime.java.debugging.DebuggingService;
import org.eclipse.etrice.runtime.java.messaging.IRTObject;
import org.eclipse.etrice.runtime.java.messaging.IMessageService;
import org.eclipse.etrice.runtime.java.messaging.MessageService;
import org.eclipse.etrice.runtime.java.messaging.MessageServiceController;
import org.eclipse.etrice.runtime.java.messaging.RTServices;
import org.eclipse.etrice.runtime.java.modelbase.ActorClassBase;
import org.eclipse.etrice.runtime.java.modelbase.DataPortBase;
import org.eclipse.etrice.runtime.java.modelbase.OptionalActorInterfaceBase;
import org.eclipse.etrice.runtime.java.modelbase.IOptionalActorFactory;
import org.eclipse.etrice.runtime.java.modelbase.SubSystemClassBase;
import org.eclipse.etrice.runtime.java.modelbase.InterfaceItemBase;
import org.eclipse.etrice.runtime.java.modelbase.InterfaceItemBroker;

import devices.*;
import logger.*;
import pump_station.*;
import room.basic.service.timing.*;
import top_actor.*;


public class Node_node_sub_system_ref extends SubSystemClassBase {

	public static final int THREAD_PHYSICAL_THREAD = 0;


	public Node_node_sub_system_ref(IRTObject parent, String name) {
		super(parent, name);
	}

	@Override
	public void receiveEvent(InterfaceItemBase ifitem, int evt, Object data){
	}

	@Override
	public void instantiateMessageServices() {

		IMessageService msgService;
		msgService = new MessageService(this, MessageService.ExecMode.MIXED, 100000000L, 0, THREAD_PHYSICAL_THREAD, "MessageService_physical_thread" /*, thread_prio */);
		RTServices.getInstance().getMsgSvcCtrl().addMsgSvc(msgService);
	}

	@Override
	public void instantiateActors() {

		// thread mappings

		// sub actors
		DebuggingService.getInstance().addMessageActorCreate(this, "top_actor");
		new top_actor_t(this, "top_actor");
		DebuggingService.getInstance().addMessageActorCreate(this, "timing_service");
		new ATimingService(this, "timing_service");

		// create service brokers in optional actor interfaces

		// wiring
		InterfaceItemBase.connect(this, "timing_service/timer", "top_actor/timer_port");

		// apply instance attribute configurations
	}

	@Override
	public void init(){
		DebuggingService.getInstance().addVisibleComment("begin sub system initialization");
		super.init();
		DebuggingService.getInstance().addVisibleComment("done sub system initialization");
	}

	@Override
	public void stop(){
		super.stop();
	}

	@Override
	public boolean hasGeneratedMSCInstrumentation() {
		return true;
	}

	@Override
	public void destroy() {
		DebuggingService.getInstance().addVisibleComment("begin sub system destruction");
		super.destroy();
		DebuggingService.getInstance().addVisibleComment("done sub system destruction");
	}

	public IOptionalActorFactory getFactory(String optionalActorClass, String actorClass) {

		return null;
	}
};
