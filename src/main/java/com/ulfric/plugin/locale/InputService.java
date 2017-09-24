package com.ulfric.plugin.locale;

import org.bukkit.entity.Player;

import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.services.Service;

import java.util.function.Consumer;

public interface InputService extends Service<InputService> {

	static InputService get() {
		return Service.get(InputService.class);
	}

	static void requestOnSign(Player target, String message, Consumer<String[]> callback) {
		InputService input = InputService.get();

		if (input != null) {
			input.request(target, message, callback);
		}
	}

	static void requestOnSign(Player target, String message, Details details, Consumer<String[]> callback) {
		InputService input = InputService.get();

		if (input != null) {
			input.request(target, message, details, callback);
		}
	}

	void request(Player target, String message, Consumer<String[]> callback);

	void request(Player target, String message, Details details, Consumer<String[]> callback);

}
