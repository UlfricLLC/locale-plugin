package com.ulfric.etruscans.internal;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import com.ulfric.fancymessage.Message;

import java.util.Collections;
import java.util.Set;

public enum PermissionlessSender implements CommandSender {

	INSTANCE;

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int delay) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int delay) {
		return null;
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return Collections.emptySet();
	}

	@Override
	public boolean hasPermission(String permission) {
		return false;
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return false;
	}

	@Override
	public boolean isPermissionSet(String permission) {
		return false;
	}

	@Override
	public boolean isPermissionSet(Permission permission) {
		return false;
	}

	@Override
	public void recalculatePermissions() {		
	}

	@Override
	public void removeAttachment(PermissionAttachment attachment) {		
	}

	@Override
	public boolean isOp() {
		return false;
	}

	@Override
	public void setOp(boolean op) {		
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
