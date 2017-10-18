package com.ulfric.plugin.locale.message;

import org.bukkit.command.CommandSender;

import com.ulfric.dragoon.value.Lazy;
import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Content;
import com.ulfric.i18n.content.Detail;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.locale.placeholder.Placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PlaceholderMessagePart implements MessagePart {

	private final VariableSequence variable;
	private final String raw;
	private final Lazy<Placeholder> placeholder;

	public PlaceholderMessagePart(String variable) {
		Objects.requireNonNull(variable, "variable");

		this.raw = variable;
		this.variable = VariableSequence.of(variable);
		this.placeholder = Lazy.ofRetrying(() -> Placeholder.get(this.variable.getVariable()));
	}

	@Override
	public Message toMessage(CommandSender display, Details details) { // TODO cleanup
		Detail detail = details.get(variable.getVariable());
		if (detail == null) {
			detail = detailFromPlaceholder(display);

			if (detail == null) {
				Message message = new Message();
				message.setText(raw);
				return message;
			}
		}

		if (detail.isMultiPart()) {
			Message message = new Message();
			message.setText("");
			message.setExtra(createExtra(detail));
			return message;
		}

		Content content = detail.getPrimaryContent();
		content = variable.transform(content);
		String text = content.getDisplayable();
		if (text == null) {
			return null;
		}

		Message message = new Message();
		message.setText(text);
		return message;
	}

	private Detail detailFromPlaceholder(CommandSender display) {
		Placeholder placeholder = this.placeholder.get();

		if (placeholder == null) {
			return null;
		}

		return placeholder.apply(display);
	}

	private List<Message> createExtra(Detail detail) { // TODO improve variable names
		List<Content> content = detail.getAllContent();
		List<Message> extra = new ArrayList<>(content.size());
		for (Content part : content) {
			part = variable.transform(part);
			String text = part.getDisplayable();
			if (text == null) {
				continue;
			}

			Message partOfMessage = new Message();
			partOfMessage.setText(text);
			extra.add(partOfMessage);
		}
		return extra;
	}

}