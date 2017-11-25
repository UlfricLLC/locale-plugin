package com.ulfric.plugin.locale.message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import com.ulfric.commons.value.Bean;
import com.ulfric.i18n.content.Content;

public final class VariableSequence extends Bean {

	private static final Pattern PERIOD = Pattern.compile(".", Pattern.LITERAL);

	public static VariableSequence of(String variable) {
		String[] split = PERIOD.split(variable);

		if (split.length == 1) {
			return new VariableSequence(variable, Collections.emptyList());
		}

		List<String> functions = Collections.unmodifiableList(Arrays.asList(Arrays.copyOfRange(split, 1, split.length)));
		return new VariableSequence(split[0], functions);
	}

	private final String variable;
	private final List<String> functions;

	private VariableSequence(String variable, List<String> functions) {
		this.variable = variable;
		this.functions = functions;
	}

	public String getVariable() {
		return variable;
	}

	public List<String> getFunctions() {
		return functions;
	}

	public Content transform(Content content) {
		for (String function : functions) {
			content = content.invoke(function);
		}

		return content;
	}

}