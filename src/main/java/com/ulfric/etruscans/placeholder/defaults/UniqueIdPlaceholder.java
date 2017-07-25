package com.ulfric.etruscans.placeholder.defaults;

import org.bukkit.entity.Player;

import com.ulfric.etruscans.placeholder.PlayerPlaceholder;

public class UniqueIdPlaceholder extends PlayerPlaceholder {

	public UniqueIdPlaceholder() {
		super("uniqueId");
	}

	@Override
	public String applyToPlayer(Player target) {
		return target.getUniqueId().toString();
	}

}