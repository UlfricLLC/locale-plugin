package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.xml.XmlHelper;

public enum TextAppender implements Appender {

	INSTANCE;

	@Override
	public Result apply(Node append, CompiledMessage to) {
		String value = XmlHelper.getNodeValue(append);
		if (StringUtils.isEmpty(value)) {
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		if (StringUtils.isEmpty(to.base.getText())) {
			to.base.setText(value);
			return new Result.Continue(); // TODO optimize, remove object creation
		}

		CompiledMessage continuation = to.createChild();
		continuation.base.setText(value);

		return new Result.Continue(continuation);
	}

}