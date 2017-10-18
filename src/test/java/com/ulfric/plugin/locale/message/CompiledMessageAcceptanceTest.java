package com.ulfric.plugin.locale.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.truth.Truth;
import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Details;

class CompiledMessageAcceptanceTest {

	@Test
	void testCompiledMessageSimple() {
		String text = "Hello, world!";
		Truth.assertThat(compiled(text)).isEqualTo("Hello, world!");
	}

	@Test
	void testCompiledMessageBold() {
		String text = "<bold>hello world</bold>";
		Truth.assertThat(compiled(text)).isEqualTo("&lhello world");
	}

	@Test
	void testCompiledMessageBoldRed() {
		String text = "<bold><red>hello world</red></bold>";
		Truth.assertThat(compiled(text)).isEqualTo("&c&lhello world");
	}

	@Test
	void testCompiledMessageRedBold() {
		String text = "<bold><red>hello world</red></bold>";
		Truth.assertThat(compiled(text)).isEqualTo("&c&lhello world");
	}

	/*
	@Test
	void testCompiledMessageRedInBlue() {
		String text = "<blue>put the <red>red</red> in the blue</blue>";
		System.out.println(compiled(text));
		Truth.assertThat(compiled(text)).isEqualTo("&9put the &cred&9 in the blue");
	}
	*/

	private String compiled(String text) {
		return Message.toLegacy(CompiledMessage.compile(text).toMessage(Mockito.mock(CommandSender.class), Details.none())).replace(ChatColor.COLOR_CHAR, '&');
	}

}
