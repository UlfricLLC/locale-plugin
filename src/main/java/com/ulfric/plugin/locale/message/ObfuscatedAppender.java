package com.ulfric.plugin.locale.message;

import org.w3c.dom.Node;

public enum ObfuscatedAppender implements Appender {

	INSTANCE;

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		if (to.base.getObfuscated() == null) {
			to.base.setObfuscated(Boolean.TRUE);
			return to;
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setObfuscated(Boolean.TRUE);

		return continuation;
	}

}