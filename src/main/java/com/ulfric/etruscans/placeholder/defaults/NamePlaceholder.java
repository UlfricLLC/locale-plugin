package com.ulfric.etruscans.placeholder.defaults;

import org.bukkit.command.CommandSender;

import com.ulfric.etruscans.placeholder.Placeholder;

public class NamePlaceholder extends Placeholder {

	public NamePlaceholder() {
		super("name");
	}

	@Override
	public String apply(CommandSender sender) {
		return sender.getName();
	}

}