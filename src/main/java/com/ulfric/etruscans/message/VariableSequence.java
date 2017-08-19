package com.ulfric.etruscans.message;

import com.ulfric.commons.value.Bean;
import com.ulfric.i18n.content.Content;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class VariableSequence extends Bean {

	private static final Pattern PERIOD = Pattern.compile(".", Pattern.LITERAL);

	public static VariableSequence of(String variable) {
		String[] split = PERIOD.split(variable);

		if (split.length == 1) {
			return new VariableSequence(variable, Collections.emptyList());
		}

		List<String> invokers = Arrays.asList(Arrays.copyOfRange(split, 1, split.length));
		return new VariableSequence(split[0], invokers);
	}

	private final String variable;
	private final List<String> invokers;

	private VariableSequence(String variable, List<String> invokers) {
		this.variable = variable;
		this.invokers = invokers;
	}

	public String getVariable() {
		return variable;
	}

	public List<String> getInvokers() {
		return invokers; // TODO exposing mutable from Arrays.asList
	}

	public Content transform(Content content) {
		for (String invoker : invokers) {
			content = content.invoke(invoker);
		}

		return content;
	}

}