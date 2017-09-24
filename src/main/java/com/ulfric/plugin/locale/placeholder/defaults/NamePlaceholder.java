package com.ulfric.plugin.locale.placeholder.defaults;

import org.bukkit.command.CommandSender;

import com.ulfric.i18n.content.Detail;
import com.ulfric.plugin.locale.placeholder.Placeholder;

public class NamePlaceholder extends Placeholder {

	public NamePlaceholder() {
		super("name");
	}

	@Override
	public Detail apply(CommandSender sender) {
		return detail(sender.getName());
	}

}