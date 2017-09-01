package com.ulfric.etruscans.locale;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.value.Bean;
import com.ulfric.etruscans.internal.PermissionlessSender;
import com.ulfric.etruscans.message.CompiledMessage;
import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;
import com.ulfric.servix.services.locale.BukkitMessageLocale;

import java.util.HashMap;
import java.util.Map;

public class SimpleLocale extends Bean implements BukkitMessageLocale { // TODO better name

	private transient final Map<String, CompiledMessage> compiled = new HashMap<>();

	private Map<String, String> messages;

	private CompiledMessage lookup(String key) {
		return compiled.computeIfAbsent(key, this::compileKey);
	}

	private CompiledMessage compileKey(String key) {
		return CompiledMessage.compile(lookupRaw(key));
	}

	private String lookupRaw(String key) {
		return messages == null ? key : messages.getOrDefault(key, key);
	}

	@Override
	public Message getMessage(String key) {
		return getMessage(PermissionlessSender.INSTANCE, key);
	}

	@Override
	public Message getMessage(CommandSender display, String key) {
		return getMessage(display, key, Details.none());
	}

	@Override
	public Message getMessage(CommandSender display, String key, Details details) {
		return lookup(key).toMessage(display, details);
	}

	public void clearCompiled() {
		compiled.clear();
	}

}