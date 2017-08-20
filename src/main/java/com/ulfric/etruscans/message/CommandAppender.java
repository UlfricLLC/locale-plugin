package com.ulfric.etruscans.message;

import java.util.List;

public class CommandAppender extends EventAppender {

	@Override
	protected CompiledMessage createEventMessage(List<CompiledMessage> eventValue) {
		return new CommandCompiledMessage(eventValue);
	}

}
