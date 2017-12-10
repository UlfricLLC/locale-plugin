package com.ulfric.plugin.locale.message;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

public class VarAppender extends ComplexAppender {

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		NamedNodeMap nodes = append.getAttributes();
		String variable = XmlHelper.getNodeValue(nodes.getNamedItem("name"));
		VariableSequence function = getIterableVariable(nodes.getNamedItem("function"));

		CompiledMessage continuation = new VarCompiledMessage(variable, function);
		to.addChild(continuation);
		return continuation;
	}

}