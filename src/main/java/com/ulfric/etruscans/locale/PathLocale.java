package com.ulfric.etruscans.locale;

import com.ulfric.data.config.ConfigType;
import com.ulfric.data.config.Configured;
import com.ulfric.data.config.Settings;
import com.ulfric.servix.services.locale.BukkitMessageLocale;
import com.ulfric.servix.services.locale.LocaleService;

@Configured
public class PathLocale implements LocaleService { // TODO different language support

	@Settings(type = ConfigType.PROPERTIES)
	private SimpleLocale english;

	@Override
	public Class<LocaleService> getService() {
		return LocaleService.class;
	}

	@Override
	public BukkitMessageLocale getLocale(String code) {
		return english; // TODO
	}

	@Override
	public BukkitMessageLocale defaultLocale() {
		return english;
	}

}