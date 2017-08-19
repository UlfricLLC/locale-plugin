package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import org.bukkit.ChatColor;

import com.ulfric.commons.xml.XmlHelper;
import com.ulfric.fancymessage.Message;

import java.util.Objects;

public class NodeToColor implements NodeToMessage {

	private final ChatColor color;

	public NodeToColor(ChatColor color) {
		Objects.requireNonNull(color, "color");

		this.color = color;
	}

	@Override
	public CompiledMessage apply(Node node) {
		Message base = new Message();
		base.setColor(color);
		base.setText(XmlHelper.getNodeValue(node));
		return CompiledMessage.wrap(base);
	}

}