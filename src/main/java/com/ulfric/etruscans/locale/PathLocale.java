package com.ulfric.etruscans.locale;

import com.ulfric.data.config.Configured;
import com.ulfric.data.config.Settings;
import com.ulfric.servix.services.locale.BukkitLocale;
import com.ulfric.servix.services.locale.LocaleService;

@Configured
public class PathLocale implements LocaleService { // TODO different language support

	@Settings
	private SimpleLocale english;

	@Override
	public Class<LocaleService> getService() {
		return LocaleService.class;
	}

	@Override
	public BukkitLocale getLocale(String code) {
		return english; // TODO
	}

	@Override
	public BukkitLocale defaultLocale() {
		return english;
	}

}