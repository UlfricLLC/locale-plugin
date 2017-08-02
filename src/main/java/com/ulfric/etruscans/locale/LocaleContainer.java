package com.ulfric.etruscans.locale;

import com.ulfric.dragoon.application.Container;

public class LocaleContainer extends Container {

	public LocaleContainer() {
		install(PathLocale.class);
		install(MessagesTell.class);
	}

}