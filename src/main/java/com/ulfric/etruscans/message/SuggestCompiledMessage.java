package com.ulfric.etruscans.message;

import com.ulfric.fancymessage.ClickAction;

import java.util.List;

public class SuggestCompiledMessage extends ClickCompiledMessage {

	public SuggestCompiledMessage(List<? extends MessagePart> hover) {
		super(hover, ClickAction.SUGGEST_COMMAND);
	}

}