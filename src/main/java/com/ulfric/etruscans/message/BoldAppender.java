package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum BoldAppender implements Appender {

	INSTANCE;

	@Override
	public Result apply(Node append, CompiledMessage to) {
		if (to.base.getBold() == null) {
			to.base.setBold(Boolean.TRUE);
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setBold(Boolean.TRUE);

		return new Result.Continue(continuation);
	}

}