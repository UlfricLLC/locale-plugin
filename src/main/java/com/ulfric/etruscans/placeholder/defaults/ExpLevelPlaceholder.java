package com.ulfric.etruscans.placeholder.defaults;

import org.bukkit.entity.Player;

import com.ulfric.etruscans.placeholder.PlayerPlaceholder;

import java.text.NumberFormat;

public class ExpLevelPlaceholder extends PlayerPlaceholder {

	public ExpLevelPlaceholder() {
		super("expLevel");
	}

	@Override
	public String applyToPlayer(Player target) {
		return NumberFormat.getInstance().format(target.getLevel());
	}

}