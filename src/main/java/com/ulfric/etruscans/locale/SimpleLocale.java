package com.ulfric.etruscans.locale;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.value.Bean;
import com.ulfric.etruscans.Messages;
import com.ulfric.servix.services.locale.Locale;

import java.util.Collections;
import java.util.Map;

public class SimpleLocale extends Bean implements Locale { // TODO better name

	private Map<String, String> messages;

	@Override
	public String getMessage(String key) {
		return messages == null ? key : messages.getOrDefault(key, key);
	}

	@Override
	public String getMessage(CommandSender target, String key) {
		return getMessage(target, key, Collections.emptyMap());
	}

	@Override
	public String getMessage(CommandSender target, String key, Map<String, String> context) {
		return Messages.getMessage(target, key, context);
	}

}