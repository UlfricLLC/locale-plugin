package com.ulfric.plugin.locale.message;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

public class IfAppender extends ComplexAppender {

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		NamedNodeMap nodes = append.getAttributes();
		VariableSequence iterableVariable = getIterableVariable(nodes.getNamedItem("condition"));

		MessagePart then = null;
		MessagePart otherwise = null;

		List<Node> children = XmlHelper.getChildren(append);
		for (Node child : children) {
			String name = child.getNodeName();
			if (name.equals("then")) {
				then = CompiledMessage.compile(child);
			} else if (name.equals("else")) {
				otherwise = CompiledMessage.compile(child);
			}
		}

		if (then == null) {
			String message = XmlHelper.getNodeValue(append);
			if (!StringUtils.isEmpty(message)) {
				then = CompiledMessage.compile(message);
			}
		}

		CompiledMessage continuation = new IfCompiledMessage(iterableVariable, then, otherwise);
		to.addChild(continuation);
		return continuation;
	}

}