package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum UnderlinedAppender implements Appender {

	INSTANCE;

	@Override
	public Result apply(Node append, CompiledMessage to) {
		if (to.base.getUnderlined() == null) {
			to.base.setUnderlined(Boolean.TRUE);
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setUnderlined(Boolean.TRUE);

		return new Result.Continue(continuation);
	}

}