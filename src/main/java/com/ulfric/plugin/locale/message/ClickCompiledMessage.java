package com.ulfric.plugin.locale.message;

import java.util.List;
import java.util.Objects;

import com.ulfric.fancymessage.ClickAction;
import com.ulfric.fancymessage.ClickEvent;
import com.ulfric.fancymessage.Message;

public abstract class ClickCompiledMessage extends EventCompiledMessage {

	private final ClickAction action;

	public ClickCompiledMessage(List<? extends MessagePart> hover, ClickAction action) {
		super(hover);

		Objects.requireNonNull(action, "action");
		this.action = action;
	}

	@Override
	protected void addEvent(Message message, List<Message> event) {
		Message urlUnflattened = new Message();
		urlUnflattened.setText("");
		urlUnflattened.setExtra(event);

		ClickEvent click = new ClickEvent();
		click.setValue(Message.toLegacy(urlUnflattened));
		click.setAction(action);
		message.setClickEvent(click);
	}

}