package com.ulfric.etruscans.message;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class EventCompiledMessage extends CompiledMessage {

	private final List<? extends MessagePart> event;

	public EventCompiledMessage(List<? extends MessagePart> event) {
		Objects.requireNonNull(event, "event");

		this.event = event;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) {
		Message message = super.toMessage(display, details);

		if (event.isEmpty()) {
			return message;
		}

		List<Message> event = createEventText(display, details);
		if (event.isEmpty()) {
			return message;
		}

		addEvent(message, event);

		return message;
	}

	private List<Message> createEventText(CommandSender display, Details details) {
		List<Message> event = new ArrayList<>(this.event.size());

		for (MessagePart part : this.event) {
			Message message = part.toMessage(display, details);

			if (message != null) {
				event.add(message);
			}
		}

		return event;
	}

	protected abstract void addEvent(Message message, List<Message> event);

}