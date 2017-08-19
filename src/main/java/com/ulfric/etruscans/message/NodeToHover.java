package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

public enum NodeToHover implements NodeToMessage {

	INSTANCE;

	@Override
	public final CompiledMessage apply(Node node) {
		Node valueNode = node.getAttributes().getNamedItem("value");

		if (valueNode == null) {
			return null;
		}

		String value = XmlHelper.getNodeValue(valueNode);

		if (value == null) {
			return null;
		}

		CompiledMessage message = CompiledMessage.wrap(CompiledMessage.emptyMessage());
		message.parts.add(new HoverMessagePart(CompiledMessage.compile(value)));
		return message;
	}

}