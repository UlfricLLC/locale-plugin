package com.ulfric.plugin.locale.message;

import java.util.Objects;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Detail;
import com.ulfric.i18n.content.Details;
import com.ulfric.i18n.content.IterableDetail;

public class VarCompiledMessage extends CompiledMessage {

	private final String name;
	private final VariableSequence function;

	public VarCompiledMessage(String name, VariableSequence function) {
		Objects.requireNonNull(name, "name");
		Objects.requireNonNull(function, "function");

		this.name = name;
		this.function = function;
	}

	@Override
	public Message toMessage(CommandSender display, Details details) { // TODO break method up, much too complex
		Detail existing = details.get(function.getVariable());
		if (existing == null) {
			return super.toMessage(display, details);
		}

		Detail variable = new IterableDetail(name, existing.getAllContent());
		details.add(variable);

		Message message = super.toMessage(display, details);

		details.remove(variable);

		return message;
	}

}