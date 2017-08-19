package com.ulfric.etruscans.locale;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.value.Bean;
import com.ulfric.etruscans.message.Messages;
import com.ulfric.i18n.content.Details;
import com.ulfric.servix.services.locale.BukkitLocale;

import java.util.Map;

public class SimpleLocale extends Bean implements BukkitLocale { // TODO better name

	private Map<String, String> messages;

	@Override
	public String getMessage(String key) {
		return messages == null ? key : messages.getOrDefault(key, key);
	}

	@Override
	public String getMessage(CommandSender target, String key) {
		return getMessage(target, key, Details.none());
	}

	@Override
	public String getMessage(CommandSender target, String key, Details details) {
		return Messages.format(getMessage(key), target, details);
	}
	
}