package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

public enum NewLineAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		CompiledMessage continuation = to.createChild();
		continuation.base.setText("\n");
		return continuation;
	}

}
