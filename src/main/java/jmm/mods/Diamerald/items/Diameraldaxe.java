package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class Diameraldaxe extends ItemAxe {

	private final ToolMaterial toolMaterial;

	public Diameraldaxe(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
		this.toolMaterial = par2ToolMaterial;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Diamerald.Diameraldgem;

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diameraldaxe");
	}

}
