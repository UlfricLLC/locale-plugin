package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum SkipAppender implements Appender {

	INSTANCE;

	@Override
	public Result apply(Node append, CompiledMessage to) {
		return new Result.Continue();
	}

}