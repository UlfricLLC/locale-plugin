package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum BoldAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getBold() == null) {
			to.base.setBold(Boolean.TRUE);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setBold(Boolean.TRUE);
		return continuation;
	}

}