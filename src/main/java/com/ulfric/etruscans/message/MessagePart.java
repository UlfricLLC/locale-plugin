package com.ulfric.etruscans.message;

import org.bukkit.command.CommandSender;

import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

public interface MessagePart {

	Message toMessage(CommandSender display, Details details);

}