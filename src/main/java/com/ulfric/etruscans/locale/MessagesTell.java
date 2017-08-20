package com.ulfric.etruscans.locale;

import org.bukkit.command.CommandSender;

import com.ulfric.andrew.Sender;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.i18n.content.Details;
import com.ulfric.servix.services.locale.BukkitLocale;
import com.ulfric.servix.services.locale.LocaleService;
import com.ulfric.servix.services.locale.TellService;

public class MessagesTell implements TellService {

	@Inject
	private LocaleService locale;

	@Override
	public Class<TellService> getService() {
		return TellService.class;
	}

	@Override
	public void send(Sender target, String message) {
		send(target, message, Details.none());
	}

	@Override
	public void send(Sender target, String message, Details context) {
		CommandSender display;

		if (target instanceof CommandSender) {
			display = (CommandSender) target;
		} else {
			Object handle = target.handle();

			if (handle instanceof CommandSender) {
				display = (CommandSender) handle;
			} else {
				return; // TODO log
			}
		}

		send(display, message, context);
	}

	@Override
	public void send(CommandSender display, String message) {
		send(display, message, Details.none());
	}

	@Override
	public void send(CommandSender display, String message, Details details) {
		BukkitLocale locale = this.locale.defaultLocale(); // TODO using default locale
		display.sendMessage(locale.getMessage(display, message, details));
	}

}
