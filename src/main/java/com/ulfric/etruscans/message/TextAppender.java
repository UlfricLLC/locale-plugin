package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.xml.XmlHelper;
import com.ulfric.fancymessage.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TextAppender implements Appender {

	INSTANCE;

	private final Pattern placeholder = Pattern.compile("\\$\\{[a-zA-Z0-9_.]+\\}");

	@Override
	public Result apply(Node append, CompiledMessage to) {
		String message = XmlHelper.getNodeValue(append);
		if (StringUtils.isEmpty(message)) {
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		Matcher variables = placeholder.matcher(message);
		if (variables.find()) {
			

			int textStart = 0;
			do {
				String text = message.substring(textStart, variables.start());

				if (!text.isEmpty()) {
					Message child = new Message();
					child.setText(text);
					to.addChild(new PlainMessagePart(child));
				}
				textStart = variables.end();

				String variable = variables.group();
				to.addChild(new PlaceholderMessagePart(variable));
			} while (variables.find());

			return new Result.Continue();
		}

		return applyRaw(message, to);
	}

	private Result applyRaw(String raw, CompiledMessage to) {
		if (StringUtils.isEmpty(to.base.getText())) {
			to.base.setText(raw);
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setText(raw);

		return new Result.Continue(continuation);
	}

}