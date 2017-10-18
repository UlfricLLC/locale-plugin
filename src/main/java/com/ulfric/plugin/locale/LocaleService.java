package com.ulfric.plugin.locale;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.services.Service;

public interface LocaleService extends Service<LocaleService> {

	static LocaleService get() {
		return Service.get(LocaleService.class);
	}

	static BukkitMessageLocale getLocale(CommandSender target) {
		LocaleService service = get();

		if (service == null) {
			return null;
		}

		if (target instanceof Player) {
			service.getLocale(((Player) target).getLocale());
		}
		return service.defaultLocale();
	}

	static Message getMessage(CommandSender target, String key) {
		BukkitMessageLocale locale = getLocale(target);

		if (locale == null) {
			return null;
		}

		return locale.getMessage(target, key);
	}

	static Message getMessage(CommandSender target, String key, Details details) {
		BukkitMessageLocale locale = getLocale(target);

		if (locale == null) {
			return null;
		}

		return locale.getMessage(target, key, details);
	}

	BukkitMessageLocale getLocale(String code);

	BukkitMessageLocale defaultLocale();

}