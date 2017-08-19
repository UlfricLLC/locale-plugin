package com.ulfric.etruscans.message;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

public enum NodeToForEach implements NodeToMessage {

	INSTANCE;

	@Override
	public final Result apply(Node node, CompiledMessage base) {
		NamedNodeMap nodes = node.getAttributes();
		String listVariable = XmlHelper.getNodeValue(nodes.getNamedItem("value"));
		String elementVariable = getNewDetailName(nodes, listVariable);
		CompiledMessage delimiter = delimiter(nodes);

		CompiledMessage foreach = new ForEachCompiledMessage(listVariable, elementVariable, delimiter);
		base.parts.add(foreach)

		return Result.FINISHED;
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