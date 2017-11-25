package com.ulfric.plugin.locale.message;

import java.util.List;
import java.util.Objects;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Content;
import com.ulfric.i18n.content.ContentHelper;
import com.ulfric.i18n.content.Detail;
import com.ulfric.i18n.content.Details;

public class IfCompiledMessage extends CompiledMessage {

	private final VariableSequence condition;
	private final MessagePart then;
	private final MessagePart otherwise;

	public IfCompiledMessage(VariableSequence condition, MessagePart then, MessagePart otherwise) {
		Objects.requireNonNull(condition, "condition");

		this.condition = condition;
		this.then = then;
		this.otherwise = otherwise;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) { // TODO break method up, much too complex
		Detail existing = details.get(condition.getVariable());
		if (existing == null) {
			return super.toMessage(display, details);
		}

		List<Content> contents = existing.getAllContent();
		if (contents.isEmpty()) {
			return super.toMessage(display, details);
		}

		for (Content content : contents) {
			content = condition.transform(content);

			if (!ContentHelper.isTrue(content)) {
				if (otherwise != null) {
					return otherwise.toMessage(display, details);
				}

				return super.toMessage(display, details);
			}
		}

		if (then != null) {
			return then.toMessage(display, details);
		}

		return super.toMessage(display, details);
	}

}