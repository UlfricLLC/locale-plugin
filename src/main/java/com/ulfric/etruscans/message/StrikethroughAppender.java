package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum StrikethroughAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getStrikethrough() == null) {
			to.base.setStrikethrough(Boolean.TRUE);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setStrikethrough(Boolean.TRUE);
		return continuation;
	}

}