package com.ulfric.etruscans.locale;

import com.ulfric.commons.value.Bean;

import java.util.Map;

public class Locale extends Bean {

	private static Locale locale; // TODO extract to services

	public static String lookup(String code) {
		return locale.getMessage(code);
	}

	private Map<String, String> messages;

	private Locale() {
		locale = this; // TODO extract to services
	}

	public String getMessage(String key) {
		return messages == null ? key : messages.getOrDefault(key, key);
	}

}