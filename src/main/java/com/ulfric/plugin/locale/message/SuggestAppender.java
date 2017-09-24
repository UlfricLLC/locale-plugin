package com.ulfric.plugin.locale.message;

import java.util.List;

public class SuggestAppender extends EventAppender {

	@Override
	protected CompiledMessage createEventMessage(List<CompiledMessage> eventValue) {
		return new SuggestCompiledMessage(eventValue);
	}

}
