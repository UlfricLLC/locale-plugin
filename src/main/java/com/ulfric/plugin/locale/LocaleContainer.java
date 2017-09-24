package com.ulfric.plugin.locale;

import com.ulfric.dragoon.application.Container;

public class LocaleContainer extends Container {

	public LocaleContainer() {
		install(PathLocale.class);
		install(LocalizedTell.class);
		install(LocalizedInput.class);
	}

}