package com.ulfric.plugin.locale.internal;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;

public enum PermissionlessSender implements CommandSender {

	INSTANCE;

	@Override
	public boolean hasPermission(String permission) {
		return false;
	}

	@Override
	public boolean isPermissionSet(String permission) {
		return false;
	}

	@Override
	public void recalculatePermissions() {		
	}

	@Override
	public String getName() {
		return "???";
	}

	@Override
	public Server getServer() {
		return Bukkit.getServer();
	}

	@Override
	public void sendMessage(String message) {		
	}

	@Override
	public void sendMessage(String[] messages) {		
	}

	@Override
	public void sendMessage(Message message) {		
	}

	@Override
	public void sendMessage(Message... messages) {		
	}

}
