package com.ulfric.etruscans.placeholder;

import com.ulfric.dragoon.application.Application;

import java.util.Objects;

public class PlaceholderApplication extends Application {

	private final Placeholder placeholder;

	public PlaceholderApplication(Placeholder placeholder) {
		Objects.requireNonNull(placeholder, "placeholder");

		this.placeholder = placeholder;
		addBootHook(this::register);
		addShutdownHook(this::unregister);
	}

	private void register() {
		com.ulfric.i18n.placeholder.Placeholder.register(placeholder);
	}

	private void unregister() {
		com.ulfric.i18n.placeholder.Placeholder.unregister(placeholder);
	}

}