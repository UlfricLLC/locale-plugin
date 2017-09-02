package com.ulfric.etruscans.locale;

import org.bukkit.entity.Player;

import com.ulfric.commons.text.Patterns;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;
import com.ulfric.servix.services.locale.InputService;
import com.ulfric.servix.services.locale.LocaleService;

import java.util.function.Consumer;

public class LocalizedInput implements InputService {

	@Inject
	private LocaleService locale;

	@Override
	public Class<InputService> getService() {
		return InputService.class;
	}

	@Override
	public void request(Player target, String message, Consumer<String[]> callback) {
		String formatted = Message.toLegacy(LocaleService.getMessage(target, message));
		requestInternal(target, formatted, callback);
	}

	@Override
	public void request(Player target, String message, Details details, Consumer<String[]> callback) {
		String formatted = Message.toLegacy(LocaleService.getMessage(target, message, details));
		requestInternal(target, formatted, callback);
	}

	private void requestInternal(Player target, String message, Consumer<String[]> callback) {
		String[] messageLines = Patterns.NEW_LINE.split(message);
		String[] lines = new String[messageLines.length + 1];
		for (int x = 1; x < lines.length; x++) {
			lines[x] = messageLines[x - 1];
		}
		target.sendSign(lines, callback);
	}

}
