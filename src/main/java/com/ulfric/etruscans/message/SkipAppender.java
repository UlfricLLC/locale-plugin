package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum SkipAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		return to;
	}

}