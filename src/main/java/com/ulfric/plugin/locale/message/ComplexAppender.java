package com.ulfric.plugin.locale.message;

import java.util.Objects;

import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

public abstract class ComplexAppender implements Appender {

	protected final VariableSequence getIterableVariable(Node node) {
		Objects.requireNonNull(node, "node");

		String value = XmlHelper.getNodeValue(node);
		Objects.requireNonNull(value, "value");

		return VariableSequence.of(value);
	}

}
