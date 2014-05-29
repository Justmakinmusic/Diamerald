package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Diameraldboots extends ItemArmor {

	public Diameraldboots(ArmorMaterial par2ArmorMaterial, int par3, int par4) {
		super(par2ArmorMaterial, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldboots");
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Diamerald.gem_Diamerald;

	}

	public String getArmorTexture(ItemStack par1, Entity entity, int slot,
			int layer) {

		return "diamerald:textures/models/armor/diamerald_layer_1.png";
	}

}
