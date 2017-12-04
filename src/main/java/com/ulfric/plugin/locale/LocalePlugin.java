package com.ulfric.plugin.locale;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.locale.internal.dragoon.FunctionFeature;
import com.ulfric.plugin.locale.placeholder.PlaceholderFeature;

public class LocalePlugin extends Plugin {

	public LocalePlugin() {
		install(PlaceholderFeature.class);
		install(Conf4jLocale.class);
		install(LocalizedTell.class);
		install(LocalizedInput.class);
		install(FunctionFeature.class);
	}

}
