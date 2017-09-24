package com.ulfric.plugin.locale;

import com.ulfric.conf4j.ConfigurationBean;

import java.util.Map;

public interface LocaleConfigurationBean extends ConfigurationBean {

	Map<String, Map<String, String>> messages();

}
