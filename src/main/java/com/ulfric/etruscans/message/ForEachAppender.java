package com.ulfric.etruscans.message;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

public enum ForEachAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		NamedNodeMap nodes = append.getAttributes();
		String listVariable = XmlHelper.getNodeValue(nodes.getNamedItem("value"));
		String elementVariable = getNewDetailName(nodes, listVariable);
		CompiledMessage delimiter = delimiter(nodes);

		CompiledMessage continuation = new ForEachCompiledMessage(listVariable, elementVariable, delimiter);
		to.addChild(continuation);
		return continuation;
	}

	private String getNewDetailName(NamedNodeMap map, String variable) {
		Node element = map.getNamedItem("element");
		if (element == null) {
			return elementNameFromVariable(variable);
		}

		String newDetailName = XmlHelper.getNodeValue(element);
		return newDetailName == null ? elementNameFromVariable(variable) : newDetailName;
	}

	private String elementNameFromVariable(String variable) {
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