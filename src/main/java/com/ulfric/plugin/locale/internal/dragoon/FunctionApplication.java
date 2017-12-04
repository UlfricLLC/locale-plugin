package com.ulfric.plugin.locale.internal.dragoon;

import java.util.Objects;

import com.ulfric.dragoon.ObjectFactory;
import com.ulfric.dragoon.application.Application;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.i18n.function.Function;

public class FunctionApplication extends Application {

	private final Function<?> function;

	@Inject
	private ObjectFactory factory;

	public FunctionApplication(Function<?> function) {
		Objects.requireNonNull(function, "function");

		this.function = function;

		addBootHook(this::register);
		addShutdownHook(this::unregister);
	}

	private void register() {
		Function.register(function);
	}

	private void unregister() {
		Function.unregister(function);
	}

}
