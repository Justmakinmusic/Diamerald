package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Diameraldboots extends ItemArmor {

	public Diameraldboots(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		this.setCreativeTab(Diamerald.tabDiamerald);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldboots");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return  par2ItemStack.itemID == Diamerald.Diameraldgem.itemID;
				
	}

	public String getArmorTexture(ItemStack par1, Entity entity, int slot, int layer) {
		if (par1.itemID == Diamerald.Diameraldhelmet.itemID
				|| par1.itemID == Diamerald.Diameraldplate.itemID
				|| par1.itemID == Diamerald.Diameraldboots.itemID) {
			return "diamerald:textures/models/armor/diamerald_layer_1.png";
		}

		if (par1.itemID == Diamerald.Diameraldlegs.itemID) {
			return "diamerald:textures/models/armor/diamerald_layer_2.png";
		}

		return "diamerald:textures/models/armor/diamerald_layer_2.png";
	}

}
