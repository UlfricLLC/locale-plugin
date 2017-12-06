package com.ulfric.plugin.locale.message;

import java.util.Arrays;
import java.util.Collections;

import org.bukkit.ChatColor;
import org.w3c.dom.Node;

import com.ulfric.commons.xml.XmlHelper;
import com.ulfric.fancymessage.ClickAction;
import com.ulfric.fancymessage.ClickEvent;
import com.ulfric.fancymessage.HoverAction;
import com.ulfric.fancymessage.HoverEvent;
import com.ulfric.fancymessage.Message;

public class LinkAppender implements Appender { // TODO use locale for most of this

	@Override
	public CompiledMessage apply(Node append, CompiledMessage to) {
		CompiledMessage use;
		if (to.base.getColor() == null && isStandalone(append, to)) {
			use = to;
		} else {
			use = to.createChild();
		}

		String url = XmlHelper.getNodeValue(append.getAttributes().getNamedItem("url"));

		use.base.setUnderlined(true);

		ClickEvent click = new ClickEvent();
		click.setAction(ClickAction.OPEN_URL);
		click.setValue(url);
		use.base.setClickEvent(click);

		HoverEvent hover = new HoverEvent();
		hover.setAction(HoverAction.SHOW_TEXT);
		Message hoverText = new Message();
		hoverText.setText("");

		String title = XmlHelper.getNodeValue(append.getAttributes().getNamedItem("title"));
		if (title == null) {
			clickToGoTo(hoverText, url);
		} else {
			hoverText.setColor(ChatColor.YELLOW);
			hoverText.setText(title);

			Message newLine = new Message();
			newLine.setText("\n");

			Message clickToGoTo = new Message();
			clickToGoTo(clickToGoTo, url);

			hoverText.setExtra(Arrays.asList(newLine, clickToGoTo));
		}

		hover.setValue(Collections.singletonList(hoverText));
		use.base.setHoverEvent(hover);

		return use;
	}

	private void clickToGoTo(Message message, String url) {
		message.setText("Click to open ");
		message.setColor(ChatColor.WHITE);
		message.setItalic(true);

		Message urlExtra = new Message();
		urlExtra.setText(url);
		urlExtra.setUnderlined(true);

		message.setExtra(Collections.singletonList(urlExtra));
	}

}
