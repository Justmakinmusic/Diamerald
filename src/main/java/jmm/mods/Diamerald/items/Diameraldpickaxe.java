package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class Diameraldpickaxe extends ItemPickaxe {

	public Diameraldpickaxe(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Diamerald.gemDiamerald;

	}

	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldpick");

	}*/

}
