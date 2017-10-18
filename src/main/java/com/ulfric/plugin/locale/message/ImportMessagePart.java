package com.ulfric.plugin.locale.message;

import java.util.Objects;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.locale.LocaleService;

public final class ImportMessagePart implements MessagePart {

	private final String variable;

	public ImportMessagePart(String variable) {
		Objects.requireNonNull(variable, "variable");

		this.variable = variable;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) {
		return LocaleService.getMessage(display, variable, details);
	}

}