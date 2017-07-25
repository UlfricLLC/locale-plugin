package com.ulfric.etruscans.placeholder;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Named;
import com.ulfric.etruscans.placeholder.defaults.ExpLevelPlaceholder;
import com.ulfric.etruscans.placeholder.defaults.NamePlaceholder;
import com.ulfric.etruscans.placeholder.defaults.UniqueIdPlaceholder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class Placeholder implements Named, Function<CommandSender, String> {

	private static final Map<String, Placeholder> PLACEHOLDERS = new HashMap<>();

	static {
		register(new NamePlaceholder());
		register(new UniqueIdPlaceholder());
		register(new ExpLevelPlaceholder());
	}

	public static void register(Placeholder placeholder) {
		Objects.requireNonNull(placeholder, "placeholder");

		PLACEHOLDERS.put(placeholder.getName(), placeholder);
	}

	public static void unregister(Placeholder placeholder) {
		Objects.requireNonNull(placeholder, "placeholder");

		PLACEHOLDERS.remove(placeholder.getName(), placeholder);
	}

	public static Placeholder get(String name) {
		return PLACEHOLDERS.get(name);
	}

	private final String name;

	public Placeholder(String name) {
		Objects.requireNonNull(name);

		this.name = name;
	}

	@Override
	public final String getName() {
		return this.name;
	}

}