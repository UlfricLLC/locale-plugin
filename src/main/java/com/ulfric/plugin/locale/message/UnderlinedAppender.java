package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

public enum UnderlinedAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getUnderlined() == null && isStandalone(append, to)) {
			to.base.setUnderlined(Boolean.TRUE);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setUnderlined(Boolean.TRUE);
		return continuation;
	}

}