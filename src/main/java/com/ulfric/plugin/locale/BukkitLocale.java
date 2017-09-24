package com.ulfric.plugin.locale;

import org.bukkit.command.CommandSender;

import com.ulfric.i18n.content.Details;
import com.ulfric.i18n.locale.Locale;

public interface BukkitLocale<T> extends Locale<T> {

	T getMessage(CommandSender display, String key);

	T getMessage(CommandSender display, String key, Details details);

}