package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum NodeToItalic implements NodeToMessage {

	INSTANCE;

	@Override
	public Result apply(Node node, CompiledMessage base) {
		base.base.setItalic(Boolean.TRUE);
		return Result.CONTINUE;
	}

}