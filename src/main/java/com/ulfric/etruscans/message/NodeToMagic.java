package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum NodeToMagic implements NodeToMessage {

	INSTANCE;

	@Override
	public Result apply(Node node, CompiledMessage base) {
		base.base.setObfuscated(Boolean.TRUE);
		return Result.CONTINUE;
	}

}