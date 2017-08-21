package com.ulfric.etruscans.message;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

import java.util.Objects;

public enum ForEachAppender implements Appender { // TODO support invokers

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		NamedNodeMap nodes = append.getAttributes();
		VariableSequence iterableVariable = getIterableVariable(nodes.getNamedItem("value"));
		String elementVariable = getNewDetailName(nodes, iterableVariable);
		CompiledMessage delimiter = delimiter(nodes);

		CompiledMessage continuation = new ForEachCompiledMessage(iterableVariable, elementVariable, delimiter);
		to.addChild(continuation);
		return continuation;
	}

	private VariableSequence getIterableVariable(Node node) {
		Objects.requireNonNull(node, "node");

		String value = XmlHelper.getNodeValue(node);
		Objects.requireNonNull(value, "value");

		return VariableSequence.of(value);
	}

	private String getNewDetailName(NamedNodeMap map, VariableSequence variable) {
		Node element = map.getNamedItem("element");
		if (element == null) {
			return elementNameFromVariable(variable);
		}

		String newDetailName = XmlHelper.getNodeValue(element);
		return newDetailName == null ? elementNameFromVariable(variable) : newDetailName;
	}

	private String elementNameFromVariable(VariableSequence variableSequence) {
		String variable = variableSequence.getVariable();
		int size = variable.length();
		return size > 1 && variable.endsWith("s") ? variable.substring(0, size - 1) : variable;
	}

	private CompiledMessage delimiter(NamedNodeMap nodes) {
		Node delimiter = nodes.getNamedItem("delimiter");

		if (delimiter == null) {
			return null;
		}

		String value = XmlHelper.getNodeValue(delimiter);
		return value == null ? null : CompiledMessage.compile(value);
	}

}