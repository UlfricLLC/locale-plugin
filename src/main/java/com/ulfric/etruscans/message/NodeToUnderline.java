package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

public enum NodeToUnderline implements NodeToMessage {

	INSTANCE;

	@Override
	public Result apply(Node node, CompiledMessage base) {
		base.base.setUnderlined(Boolean.TRUE);
		return Result.CONTINUE;
	}

}