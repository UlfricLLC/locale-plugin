package com.ulfric.etruscans.locale;

import com.ulfric.commons.value.Bean;
import com.ulfric.servix.services.locale.Locale;

import java.util.Map;

public class SimpleLocale extends Bean implements Locale { // TODO better name

	private Map<String, String> messages;

	@Override
	public String getMessage(String key) {
		return messages == null ? key : messages.getOrDefault(key, key);
	}

}