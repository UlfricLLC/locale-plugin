package com.ulfric.etruscans;

import org.w3c.dom.Node;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.ulfric.andrew.Sender;
import com.ulfric.commons.xml.XmlHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class Message {

	private static final Pattern VARIABLE = Pattern.compile("[$][{]([a-zA-Z0-9_]+)[}]");
	private static final Map<String, Message> COMPILED = new HashMap<>();

	public static void send(Sender sender, String message) {
		
	}

	public static void send(Sender sender, String message, Map<String, String> context) {
		
	}

	public static void send(CommandSender sender, String message) {
		
	}

	public static void send(CommandSender sender, String message, Map<String, String> context) {
		
	}

	private final String raw;

	private Message(String raw) {
		this.raw = format(raw);
		compile();
	}

	private String format(String raw) {
		String formatted = VARIABLE.matcher(raw).replaceAll("<$1/>");
		formatted = ChatColor.translateAlternateColorCodes('&', formatted); // TODO static utility; ChatHelper
		return formatted;
	}

	private void compile() {
		Node root = XmlHelper.parseImcompleteDocument(raw).getDocumentElement();
	}

}