package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum ObfuscatedAppender implements Appender {

	INSTANCE;

	@Override
	public Result apply(Node append, CompiledMessage to) {
		if (to.base.getObfuscated() == null) {
			to.base.setObfuscated(Boolean.TRUE);
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setObfuscated(Boolean.TRUE);

		return new Result.Continue(continuation);
	}

}