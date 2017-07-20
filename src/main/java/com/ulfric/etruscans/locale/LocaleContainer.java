package com.ulfric.etruscans.locale;

import com.ulfric.data.config.Configured;
import com.ulfric.data.config.Settings;
import com.ulfric.dragoon.application.Container;

@Configured
public class LocaleContainer extends Container { // TODO different language support

	@Settings
	private Locale english;

}