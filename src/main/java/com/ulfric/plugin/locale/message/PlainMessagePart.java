package com.ulfric.plugin.locale.message;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

import java.util.Objects;

public class PlainMessagePart implements MessagePart {

	private final Message base;

	public PlainMessagePart(Message base) {
		Objects.requireNonNull(base, "base");

		this.base = base;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) {
		return new Message(base);
	}

}