package com.ulfric.plugin.locale;

import org.bukkit.entity.Player;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.services.Service;

public interface LocaleService extends Service<LocaleService> {

	static LocaleService get() {
		return Service.get(LocaleService.class);
	}

	static BukkitMessageLocale getLocale(Player player) {
		LocaleService service = get();

		if (service == null) {
			return null;
		}

		return service.getLocale(player.getLocale());
	}

	static Message getMessage(Player target, String key) {
		BukkitMessageLocale locale = getLocale(target);

		if (locale == null) {
			return null;
		}

		return locale.getMessage(target, key);
	}

	static Message getMessage(Player target, String key, Details details) {
		BukkitMessageLocale locale = getLocale(target);

		if (locale == null) {
			return null;
		}

		return locale.getMessage(target, key, details);
	}

	BukkitMessageLocale getLocale(String code);

	BukkitMessageLocale defaultLocale();

}