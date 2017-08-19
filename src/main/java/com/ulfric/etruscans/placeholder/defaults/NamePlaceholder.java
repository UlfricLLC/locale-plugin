package com.ulfric.etruscans.placeholder.defaults;

import org.bukkit.command.CommandSender;

import com.ulfric.etruscans.placeholder.Placeholder;
import com.ulfric.i18n.content.Detail;

public class NamePlaceholder extends Placeholder {

	public NamePlaceholder() {
		super("name");
	}

	@Override
	public Detail apply(CommandSender sender) {
		return detail(sender.getName());
	}

}