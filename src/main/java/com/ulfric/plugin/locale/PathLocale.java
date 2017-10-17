package com.ulfric.plugin.locale;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.dragoon.conf4j.Settings;
import com.ulfric.plugin.services.ServiceApplication;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class PathLocale extends ServiceApplication implements LocaleService { // TODO different language support

	public static final String DEFAULT_LOCALE_CODE = Locale.getDefault().toLanguageTag().replace('-', '_');

	@Settings
	private LocaleConfigurationBean messages;

	private final Map<String, BukkitMessageLocale> compiledLocales = MapHelper.newConcurrentMap(4);

	public PathLocale() {
		addShutdownHook(compiledLocales::clear);
	}

	@Override
	public Class<LocaleService> getService() {
		return LocaleService.class;
	}

	@Override
	public BukkitMessageLocale getLocale(String code) {
		return compiledLocales.computeIfAbsent(code.replace('-', '_'), localeName -> {
			Map<String, String> messages = this.messages.messages().get(localeName);

			if (messages == null) {
				if (DEFAULT_LOCALE_CODE.equals(localeName)) {
					return new SimpleLocale(Collections.emptyMap());
				}

				return defaultLocale();
			}

			return new SimpleLocale(messages);
		});
	}

	@Override
	public BukkitMessageLocale defaultLocale() {
		return getLocale(DEFAULT_LOCALE_CODE);
	}

}