package com.ulfric.plugin.locale.function;

import org.bukkit.block.Block;

import com.ulfric.i18n.function.Function;

public class BlockToMaterialFunction extends Function<Block> {

	public BlockToMaterialFunction() {
		super("material", Block.class);
	}

	@Override
	public Object apply(Block block) {
		return block.getType();
	}

}
