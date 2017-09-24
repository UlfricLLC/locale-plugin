package com.ulfric.plugin.locale.message;

import java.util.List;

public class HoverAppender extends EventAppender {

	@Override
	protected CompiledMessage createEventMessage(List<CompiledMessage> eventValue) {
		return new HoverCompiledMessage(eventValue);
	}

}
