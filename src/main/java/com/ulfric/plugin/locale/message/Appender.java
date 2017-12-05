package com.ulfric.plugin.locale.message;

import java.util.function.BiFunction;

import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;

public interface Appender extends BiFunction<Node, CompiledMessage, CompiledMessage> {

	default boolean isStandalone(Node append, CompiledMessage to) {
		return !to.hasChildren() && !XmlHelper.hasSiblings(append);
	}

}