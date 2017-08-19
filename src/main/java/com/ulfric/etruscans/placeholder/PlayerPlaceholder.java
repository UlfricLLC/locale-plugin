package com.ulfric.etruscans.placeholder;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ulfric.i18n.content.Detail;

public abstract class PlayerPlaceholder extends Placeholder {

	public PlayerPlaceholder(String name) {
		super(name);
	}

	@Override
	public final Detail apply(CommandSender target) {
		return target instanceof Player ? applyToPlayer((Player) target) : null; // TODO is it appropriate to return null?
	}

	public abstract Detail applyToPlayer(Player target);

}