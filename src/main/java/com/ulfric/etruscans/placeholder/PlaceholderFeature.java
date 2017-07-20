package com.ulfric.etruscans.placeholder;

import com.ulfric.dragoon.application.Application;
import com.ulfric.dragoon.application.Feature;

public class PlaceholderFeature extends Feature {

	@Override
	public Application apply(Object object) {
		if (object instanceof Placeholder) {
			return new PlaceholderApplication((Placeholder) object);
		}
		return null;
	}

}