package com.ulfric.etruscans.message;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AggregatingMessagePart implements MessagePart { // TODO make CompiledMessage extend from this

	protected final List<MessagePart> parts;

	public AggregatingMessagePart(List<MessagePart> extra) { // TODO ensure this is 'flattened'
		Objects.requireNonNull(extra, "extra");

		this.parts = extra;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) {
		Message holder = createNewBase();
		if (parts.isEmpty()) {
			return holder;
		}

		List<Message> contents = createNewContentsWithAnticipatedSize();

		for (MessagePart part : parts) {
			Message displayable = part.toMessage(display, details);

			if (displayable == null) {
				continue;
			}

			contents.add(displayable);
		}

		if (!contents.isEmpty()) {
			holder.setExtra(contents);
		}

		return holder;
	}

	protected Message createNewBase() {
		return CompiledMessage.emptyMessage();
	}

	private List<Message> createNewContentsWithAnticipatedSize() {
		return new ArrayList<>(parts.size());
	}

}