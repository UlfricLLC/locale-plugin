package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum ItalicAppender implements Appender {

	INSTANCE;

	@Override
	public Result apply(Node append, CompiledMessage to) {
		if (to.base.getItalic() == null) {
			to.base.setItalic(Boolean.TRUE);
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setItalic(Boolean.TRUE);

		return new Result.Continue(continuation);
	}

}