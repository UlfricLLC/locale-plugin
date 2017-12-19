package com.ulfric.plugin.locale.function;

import org.bukkit.command.CommandSender;

import com.ulfric.i18n.function.Function;

public abstract class CommandSenderFunction extends Function<CommandSender> {

	public CommandSenderFunction(String name) {
		super(name, CommandSender.class);
	}

}
