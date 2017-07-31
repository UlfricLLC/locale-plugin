package com.ulfric.etruscans.locale;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import com.ulfric.commons.value.Bean;
import com.ulfric.servix.services.locale.Locale;

import java.util.Map;

public class IgnoreCaseLocale extends Bean implements Locale {

	private final Map<String, String> messages = new CaseInsensitiveMap<>();

	@Override
	public String getMessage(String key) {
		return messages.getOrDefault(key, key);
	}

}