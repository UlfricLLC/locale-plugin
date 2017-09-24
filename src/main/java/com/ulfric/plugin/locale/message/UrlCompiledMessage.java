package com.ulfric.plugin.locale.message;

import com.ulfric.fancymessage.ClickAction;

import java.util.List;

public class UrlCompiledMessage extends ClickCompiledMessage {

	public UrlCompiledMessage(List<? extends MessagePart> hover) {
		super(hover, ClickAction.OPEN_URL);
	}

}