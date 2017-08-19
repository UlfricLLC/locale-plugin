package com.ulfric.etruscans.message;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.HoverAction;
import com.ulfric.fancymessage.HoverEvent;
import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HoverCompiledMessage extends CompiledMessage {

	private final CompiledMessage hover;
	private final List<CompiledMessage> affected;

	public HoverCompiledMessage(CompiledMessage hover, List<CompiledMessage> affected) {
		super(CompiledMessage.emptyMessage());

		Objects.requireNonNull(hover, "hover");
		Objects.requireNonNull(affected, "affected");

		this.hover = hover;
		this.affected = affected;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) {
		Message base = createNewBase();
		HoverEvent hover = new HoverEvent();
		hover.setAction(HoverAction.SHOW_TEXT);

		Message text = this.hover.toMessage(display, details);
		hover.setValue(Collections.singletonList(text));
		base.setHoverEvent(hover);

		List<Message> extra = new ArrayList<>(affected.size());
		for (CompiledMessage affectedMessage : affected) {
			extra.add(affectedMessage.toMessage(display, details));
		}
		base.setExtra(extra);

		return base;
	}

	@Override
	protected Message createNewBase() {
		return CompiledMessage.emptyMessage();
	}

}