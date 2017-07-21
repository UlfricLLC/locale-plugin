package com.ulfric.etruscans;

import net.md_5.bungee.chat.ComponentSerializer;

import org.bukkit.command.CommandSender;

import com.ulfric.etruscans.placeholder.Placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class CompiledMessage implements BiFunction<CommandSender, Map<String, String>, String> {

	private static final double EXPECTED_LENGTH_MULTIPLIER = 0.2;
	private static final Pattern VARIABLE = Pattern.compile(CompiledMessage.escape("(?i)${[A-Z0-9_]+}"));

	private static String escape(String pattern) { // TODO replace with proper regex
		return pattern.replace("$", Pattern.quote("$")).replace("{", Pattern.quote("{")).replace("}",
		        Pattern.quote("}"));
	}

	static CompiledMessage compileLegacy(String message) {
		Objects.requireNonNull(message);

		String legacy = FancyMessage.parse(message).toLegacyText();
		return CompiledMessage.compile(legacy);
	}

	static CompiledMessage compileRaw(String message) {
		Objects.requireNonNull(message);

		String raw = ComponentSerializer.toString(FancyMessage.parse(message));
		return CompiledMessage.compile(raw);
	}

	private static CompiledMessage compile(String message) {
		List<BiFunction<CommandSender, Map<String, String>, String>> compiledMessageParts = CompiledMessage.getCompiledMessageParts(message);
		int expectedLength = CompiledMessage.getExpectedLength(message);
		return new CompiledMessage(compiledMessageParts, expectedLength);
	}

	private static List<BiFunction<CommandSender, Map<String, String>, String>> getCompiledMessageParts(String message) {
		List<BiFunction<CommandSender, Map<String, String>, String>> messages = new ArrayList<>();

		Matcher variables = CompiledMessage.VARIABLE.matcher(message);
		int textStart = 0;
		while (variables.find()) {
			String text = message.substring(textStart, variables.start());
			messages.add((sender, context) -> text);
			textStart = variables.end();

			String variable = CompiledMessage.stripVariableBoilerplate(variables.group());
			Placeholder placeholder = Placeholder.get(variable);
			if (placeholder == null) {
				messages.add((sender, context) -> context.getOrDefault(variable, variable)); // TODO stop leaking matcher object
			} else {
				messages.add((sender, context) -> placeholder.apply(sender));
			}
		}
		String text = message.substring(textStart, message.length());
		if (!text.isEmpty()) {
			messages.add((sender, context) -> text);
		}

		return messages;
	}

	private static String stripVariableBoilerplate(String variable) { // TODO proper regex
		return variable.substring("${".length(), variable.length() - "}".length());
	}

	private static int getExpectedLength(String precompile) {
		return (int) (precompile.length() + (CompiledMessage.EXPECTED_LENGTH_MULTIPLIER * precompile.length()));
	}

	private final List<BiFunction<CommandSender, Map<String, String>, String>> compiled;
	private final int expectedLength;

	private CompiledMessage(List<BiFunction<CommandSender, Map<String, String>, String>> compiled, int expectedLength) {
		this.compiled = compiled;
		this.expectedLength = expectedLength;
	}

	@Override
	public String apply(CommandSender target, Map<String, String> context) {
		StringBuilder message = new StringBuilder(this.expectedLength);
		for (BiFunction<CommandSender, Map<String, String>, String> messagePart : this.compiled) {
			message.append(messagePart.apply(target, context));
		}
		return message.toString();
	}

}
