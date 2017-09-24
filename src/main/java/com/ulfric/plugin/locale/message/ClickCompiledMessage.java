package com.ulfric.plugin.locale.message;

import com.ulfric.fancymessage.ClickAction;
import com.ulfric.fancymessage.ClickEvent;
import com.ulfric.fancymessage.Message;

import java.util.List;
import java.util.Objects;

public abstract class ClickCompiledMessage extends EventCompiledMessage {

	private final ClickAction action;

	public ClickCompiledMessage(List<? extends MessagePart> hover, ClickAction action) {
		super(hover);

		Objects.requireNonNull(action, "action");
		this.action = action;
	}

	@Override
	protected void addEvent(Message message, List<Message> event) {
		ClickEvent click = new ClickEvent();
		click.setValue(event);
		click.setAction(action);
		message.setClickEvent(click);
	}

}