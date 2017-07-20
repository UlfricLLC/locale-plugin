package com.ulfric.etruscans.placeholder;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Named;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class Placeholder implements Named, Function<CommandSender, String> {

	private static final Map<String, Placeholder> PLACEHOLDERS = new HashMap<>();

	public static void register(Placeholder placeholder) {
		Objects.requireNonNull(placeholder, "placeholder");

		PLACEHOLDERS.put(placeholder.getName(), placeholder);
		placeholder.active = true;
	}

	public static void unregister(Placeholder placeholder) {
		Objects.requireNonNull(placeholder, "placeholder");

		if (PLACEHOLDERS.remove(placeholder.getName(), placeholder)) {
			placeholder.active = false;
		}
	}

	public static Placeholder get(String name) {
		return PLACEHOLDERS.get(name);
	}

	private final String name;
	private volatile boolean active;

	public Placeholder(String name) {
		Objects.requireNonNull(name);

		this.name = name;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	public final boolean isActive() {
		return active;
	}

}