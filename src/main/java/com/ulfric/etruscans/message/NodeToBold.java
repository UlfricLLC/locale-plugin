package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum NodeToBold implements NodeToMessage {

	INSTANCE;

	@Override
	public final Result apply(Node node, CompiledMessage base) {
		base.base.setBold(Boolean.TRUE);
		return Result.CONTINUE;
	}

}