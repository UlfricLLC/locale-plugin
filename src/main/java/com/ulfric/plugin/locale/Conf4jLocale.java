package com.ulfric.plugin.locale;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.dragoon.conf4j.Settings;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.services.ServiceApplication;

public class Conf4jLocale extends ServiceApplication implements LocaleService { // TODO different language support

	public static final String DEFAULT_LOCALE_CODE = Locale.getDefault().toLanguageTag().replace('-', '_');

	@Settings(extension = "locale")
	private LocaleConfigurationBean messages;

	@Inject(optional = true)
	private Logger logger;

	private final Map<String, BukkitMessageLocale> compiledLocales = MapHelper.newConcurrentMap(4);

	public Conf4jLocale() {
		addShutdownHook(compiledLocales::clear);
		addBootHook(() -> {
			if (logger != null) {
				int messages = this.messages.messages().values().stream().mapToInt(Map::size).sum();
				if (messages == 0) {
					logger.warning("No locale messages loaded");
				} else if (messages == 1) {
					logger.info("Loaded 1 locale message");
				} else {
					logger.info("Loaded " + messages + " locale messages");
				}
			}
		});
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