package com.ulfric.etruscans.message;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.HoverAction;
import com.ulfric.fancymessage.HoverEvent;
import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

import java.util.Collections;
import java.util.Objects;

public class HoverMessagePart implements MessagePart {

	private final CompiledMessage hover;

	public HoverMessagePart(CompiledMessage hover) {
		Objects.requireNonNull(hover, "hover");

		this.hover = hover;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) {
		Message base = CompiledMessage.emptyMessage();
		HoverEvent hover = new HoverEvent();
		hover.setAction(HoverAction.SHOW_TEXT);

		Message text = this.hover.toMessage(display, details);
		hover.setValue(Collections.singletonList(text));
		base.setHoverEvent(hover);
		return base;
	}

}