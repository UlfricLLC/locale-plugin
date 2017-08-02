package com.ulfric.etruscans.locale;

import org.bukkit.entity.Player;

import com.ulfric.andrew.Sender;
import com.ulfric.etruscans.Messages;
import com.ulfric.servix.services.locale.TellService;

import java.util.Map;

public class MessagesTell implements TellService {

	@Override
	public Class<TellService> getService() {
		return TellService.class;
	}

	@Override
	public void send(Sender target, String message) {
		Messages.send(target, message);
	}

	@Override
	public void send(Player target, String message) {
		Messages.send(target, message);
	}

	@Override
	public void send(Sender target, String message, Map<String, String> context) {
		Messages.send(target, message, context);
	}

	@Override
	public void send(Player target, String message, Map<String, String> context) {
		Messages.send(target, message, context);
	}

}
