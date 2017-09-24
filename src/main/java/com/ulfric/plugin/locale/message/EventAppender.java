package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class EventAppender implements Appender {

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		List<CompiledMessage> eventValue = eventValue(append);

		if (eventValue == null) {
			return to;
		}

		CompiledMessage hover = createEventMessage(eventValue);
		to.addChild(hover);
		return hover;
	}

	private List<CompiledMessage> eventValue(Node base) {
		List<Node> children = XmlHelper.getAttributes(base);

		if (children.isEmpty()) {
			return null;
		}

		return children.stream()
			.map(XmlHelper::getNodeValue)
			.filter(Objects::nonNull)
			.map(CompiledMessage::compile)
			.collect(Collectors.toList());
	}

	protected abstract CompiledMessage createEventMessage(List<CompiledMessage> eventValue);

}
