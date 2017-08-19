package com.ulfric.etruscans.placeholder.defaults;

import org.bukkit.entity.Player;

import com.ulfric.etruscans.placeholder.PlayerPlaceholder;
import com.ulfric.i18n.content.Detail;

public class UniqueIdPlaceholder extends PlayerPlaceholder {

	public UniqueIdPlaceholder() {
		super("uniqueId");
	}

	@Override
	public Detail applyToPlayer(Player target) {
		return detail(target.getUniqueId());
	}

}