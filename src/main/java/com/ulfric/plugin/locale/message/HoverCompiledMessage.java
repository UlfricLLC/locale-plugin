package com.ulfric.plugin.locale.message;

import com.ulfric.fancymessage.HoverAction;
import com.ulfric.fancymessage.HoverEvent;
import com.ulfric.fancymessage.Message;

import java.util.List;

public class HoverCompiledMessage extends EventCompiledMessage {

	public HoverCompiledMessage(List<? extends MessagePart> hover) {
		super(hover);
	}

	@Override
	protected void addEvent(Message message, List<Message> event) {
		HoverEvent hover = new HoverEvent();
		hover.setValue(event);
		hover.setAction(HoverAction.SHOW_TEXT);
		message.setHoverEvent(hover);
	}

}