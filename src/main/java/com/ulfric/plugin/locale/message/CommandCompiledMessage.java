package com.ulfric.plugin.locale.message;

import com.ulfric.fancymessage.ClickAction;

import java.util.List;

public class CommandCompiledMessage extends ClickCompiledMessage {

	public CommandCompiledMessage(List<? extends MessagePart> hover) {
		super(hover, ClickAction.RUN_COMMAND);
	}

}