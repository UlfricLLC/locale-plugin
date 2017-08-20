package com.ulfric.etruscans.message;

import java.util.List;

public class HoverAppender extends EventAppender {

	@Override
	protected CompiledMessage createEventMessage(List<CompiledMessage> eventValue) {
		return new HoverCompiledMessage(eventValue);
	}

}
