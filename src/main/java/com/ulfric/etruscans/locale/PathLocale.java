package com.ulfric.etruscans.locale;

import com.ulfric.data.config.ConfigType;
import com.ulfric.data.config.Configuration;
import com.ulfric.data.config.Configured;
import com.ulfric.data.config.Settings;
import com.ulfric.servix.ServiceApplication;
import com.ulfric.servix.services.locale.BukkitMessageLocale;
import com.ulfric.servix.services.locale.LocaleService;

@Configured
public class PathLocale extends ServiceApplication implements LocaleService { // TODO different language support

	@Settings(type = ConfigType.PROPERTIES, handleField = "handle")
	private SimpleLocale english;

	private Configuration handle;

	public PathLocale() {
		addBootHook(() -> handle.subscribe(english::clearCompiled)); // TODO currently reloading this container will cause this subscription to be added twice

		addShutdownHook(english::clearCompiled);
	}

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