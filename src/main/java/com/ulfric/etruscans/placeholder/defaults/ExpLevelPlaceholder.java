package com.ulfric.etruscans.placeholder.defaults;

import org.bukkit.entity.Player;

import com.ulfric.etruscans.placeholder.PlayerPlaceholder;
import com.ulfric.i18n.content.Detail;

public class ExpLevelPlaceholder extends PlayerPlaceholder {

	public ExpLevelPlaceholder() {
		super("expLevel");
	}

	@Override
	public Detail applyToPlayer(Player target) {
		return Detail.of(getName(), target.getLevel());
	}

}