package com.ulfric.plugin.locale.internal.dragoon;

import com.ulfric.dragoon.ObjectFactory;
import com.ulfric.dragoon.application.Application;
import com.ulfric.dragoon.application.Feature;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.i18n.function.Function;

public class FunctionFeature extends Feature {

	@Inject
	private ObjectFactory factory;

	@Override
	public Application apply(Object object) {
		if (object instanceof Function) {
			return factory.request(FunctionApplication.class, object);
		}
		return null;
	}

}
