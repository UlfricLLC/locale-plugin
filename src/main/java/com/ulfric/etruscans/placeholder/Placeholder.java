package com.ulfric.etruscans.placeholder;

import org.bukkit.command.CommandSender;

public abstract class Placeholder extends com.ulfric.i18n.placeholder.Placeholder<CommandSender> {

	public static Placeholder get(String name) {
		com.ulfric.i18n.placeholder.Placeholder<?> placeholder =
				com.ulfric.i18n.placeholder.Placeholder.get(name);

		return placeholder instanceof Placeholder ? null : (Placeholder) placeholder;
	}

	public Placeholder(String name) {
		super(name);
	}

}