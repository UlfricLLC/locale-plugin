package com.ulfric.plugin.locale;

import org.bukkit.command.CommandSender;

import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.services.Service;

public interface TellService extends Service<TellService> {

	static TellService get() {
		return Service.get(TellService.class);
	}

	static void sendMessage(CommandSender target, String message) {
		TellService tell = TellService.get();

		if (tell != null) {
			tell.send(target, message);
		}
	}

	static void sendMessage(CommandSender target, String message, Details details) {
		TellService tell = TellService.get();

		if (tell != null) {
			tell.send(target, message, details);
		}
	}

	void send(CommandSender target, String message);

	void send(CommandSender target, String message, Details details);

}
