package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

import org.bukkit.ChatColor;

import java.util.Objects;

public class ColorAppender implements Appender {

	private final ChatColor color;

	public ColorAppender(ChatColor color) {
		Objects.requireNonNull(color, "color");

		this.color = color;
	}

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getColor() == null && isStandalone(append, to)) {
			to.base.setColor(color);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setColor(color);
		return continuation;
	}

}