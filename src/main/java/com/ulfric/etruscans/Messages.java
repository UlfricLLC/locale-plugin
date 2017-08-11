package com.ulfric.etruscans;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ulfric.andrew.Sender;
import com.ulfric.servix.services.locale.LocaleService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Messages {

	private static final Map<String, CompiledMessage> COMPILED_TELLRAW = new HashMap<>();

	public static void send(Sender sender, String message) {
		send(sender(sender), message);
	}

	public static void send(Sender sender, String message, Map<String, String> context) {
		send(sender(sender), message, context);
	}

	private static CommandSender sender(Sender sender) {
		Object handle = sender.handle();
		if (handle instanceof CommandSender) {
			return (CommandSender) handle;
		}
		throw new IllegalArgumentException("Not a BukkitSender: " + sender);
	}

	public static void send(CommandSender sender, String message) {
		send(sender, message, Collections.emptyMap());
	}

	public static void send(CommandSender sender, String message, Map<String, String> context) {
		message = getMessage(sender, message, context);
		BaseComponent[] endMessage = ComponentSerializer.parse(message);
		if (sender instanceof Player) { // TODO not required in new spigot versions -- backport
			Player player = (Player) sender;
			player.spigot().sendMessage(endMessage);
			return;
		}
		sender.sendMessage(BaseComponent.toLegacyText(endMessage));
	}

	public static String getMessage(CommandSender sender, String message, Map<String, String> context) {
		message = LocaleService.defaultMessage(message);
		CompiledMessage send = getCompiledTellrawMessage(message);
		return send.apply(sender, context);
	}

	private static CompiledMessage getCompiledTellrawMessage(String message) {
		return COMPILED_TELLRAW.computeIfAbsent(message, CompiledMessage::compileRaw);
	}

}