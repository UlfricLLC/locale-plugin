package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum StrikethroughAppender implements Appender {

	INSTANCE;

	@Override
	public Result apply(Node append, CompiledMessage to) {
		if (to.base.getStrikethrough() == null) {
			to.base.setStrikethrough(Boolean.TRUE);
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setStrikethrough(Boolean.TRUE);

		return new Result.Continue(continuation);
	}

}