package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import org.bukkit.ChatColor;

import java.util.Objects;

public class NodeToColor implements NodeToMessage {

	private final ChatColor color;

	public NodeToColor(ChatColor color) {
		Objects.requireNonNull(color, "color");

		this.color = color;
	}

	@Override
	public Result apply(Node node, CompiledMessage base) {
		base.base.setColor(color);
		return Result.CONTINUE;
	}

}