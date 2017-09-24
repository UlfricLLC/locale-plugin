package com.ulfric.plugin.locale.placeholder.defaults;

import org.bukkit.entity.Player;

import com.ulfric.i18n.content.Detail;
import com.ulfric.plugin.locale.placeholder.PlayerPlaceholder;

public class UniqueIdPlaceholder extends PlayerPlaceholder {

	public UniqueIdPlaceholder() {
		super("uniqueId");
	}

	@Override
	public Detail applyToPlayer(Player target) {
		return detail(target.getUniqueId());
	}

}