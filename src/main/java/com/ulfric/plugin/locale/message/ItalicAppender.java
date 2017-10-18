package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

public enum ItalicAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getItalic() == null && !to.hasChildren()) {
			to.base.setItalic(Boolean.TRUE);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setItalic(Boolean.TRUE);
		return continuation;
	}

}