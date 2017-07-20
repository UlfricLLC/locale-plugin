package com.ulfric.etruscans.placeholder;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerPlaceholder extends Placeholder {

	public PlayerPlaceholder(String name) {
		super(name);
	}

	@Override
	public final String apply(CommandSender target) {
		return target instanceof Player ? applyToPlayer((Player) target) : null;
	}

	public abstract String applyToPlayer(Player target);

}