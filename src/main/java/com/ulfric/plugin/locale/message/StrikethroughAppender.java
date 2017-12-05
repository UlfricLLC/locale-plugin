package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

public enum StrikethroughAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getStrikethrough() == null && isStandalone(append, to)) {
			to.base.setStrikethrough(Boolean.TRUE);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setStrikethrough(Boolean.TRUE);
		return continuation;
	}

}