package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

import java.util.List;
import java.util.stream.Collectors;

public enum NodeToHover implements NodeToMessage {

	INSTANCE;

	@Override
	public final Result apply(Node node, CompiledMessage base) {
		Node valueNode = node.getAttributes().getNamedItem("value");

		if (valueNode == null) {
			return null;
		}

		String value = XmlHelper.getNodeValue(valueNode);

		if (value == null) {
			return null;
		}

		List<CompiledMessage> extra = XmlHelper.asList(node.getChildNodes()).stream()
				.map(CompiledMessage::compile)
				.collect(Collectors.toList());

		base.parts.add(new HoverCompiledMessage(CompiledMessage.compile(value), extra));

		return Result.FINISHED;
	}

}