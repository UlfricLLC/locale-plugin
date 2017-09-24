package com.ulfric.plugin.locale.placeholder.defaults;

import org.bukkit.entity.Player;

import com.ulfric.i18n.content.Detail;
import com.ulfric.plugin.locale.placeholder.PlayerPlaceholder;

public class ExpLevelPlaceholder extends PlayerPlaceholder {

	public ExpLevelPlaceholder() {
		super("expLevel");
	}

	@Override
	public Detail applyToPlayer(Player target) {
		return Detail.of(getName(), target.getLevel());
	}

}