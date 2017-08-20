package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum ItalicAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getItalic() == null) {
			to.base.setItalic(Boolean.TRUE);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setItalic(Boolean.TRUE);
		return continuation;
	}

}