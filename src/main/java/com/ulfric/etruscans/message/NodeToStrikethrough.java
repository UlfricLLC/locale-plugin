package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum NodeToStrikethrough implements NodeToMessage {

	INSTANCE;

	@Override
	public Result apply(Node node, CompiledMessage base) {
		base.base.setStrikethrough(Boolean.TRUE);
		return Result.CONTINUE;
	}

}