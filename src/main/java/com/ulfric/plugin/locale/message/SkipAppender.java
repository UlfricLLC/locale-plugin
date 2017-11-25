package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

public enum SkipAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		return to;
	}

}
