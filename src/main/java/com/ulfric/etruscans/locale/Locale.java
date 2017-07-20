package com.ulfric.etruscans.locale;

import com.ulfric.commons.value.Bean;

import java.util.Map;

public class Locale extends Bean {

	private Map<String, String> messages;

	private Locale() {
	}

	public String getMessage(String key) {
		return messages.getOrDefault(key, key);
	}

}