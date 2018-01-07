package com.ulfric.plugin.locale.function;

import org.bukkit.World;

import com.ulfric.i18n.function.Function;

public class WorldToNameFunction extends Function<World> {

	public WorldToNameFunction() {
		super("name", World.class);
	}

	@Override
	public Object apply(World world) {
		return world.getName();
	}

}
