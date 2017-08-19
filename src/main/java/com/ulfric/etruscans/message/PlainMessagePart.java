package com.ulfric.etruscans.message;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

import java.util.Objects;

public final class PlainMessagePart implements MessagePart {

	private final Message message;

	public PlainMessagePart(Message message) { // TODO ensure this is 'flattened'
		Objects.requireNonNull(message, "message");

		this.message = message;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) { // TODO do we care enough to clone these?
		return message;
	}

}