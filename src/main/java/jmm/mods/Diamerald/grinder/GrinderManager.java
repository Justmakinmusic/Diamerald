package jmm.mods.Diamerald.grinder;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GrinderManager {

	public static void addGrinderRecipe(Block input, ItemStack output1, float xp) {
		GrinderRecipes.smelting().addGrinderRecipe(input, output1, xp);
	}

}
