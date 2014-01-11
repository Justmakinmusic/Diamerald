package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

;

public class Diameraldplate extends ItemArmor {

	public Diameraldplate(ArmorMaterial par2ArmorMaterial,
			int par3, int par4) {
		super(par2ArmorMaterial, par3, par4);
		this.setCreativeTab(Diamerald.tabDiamerald);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldplate");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return  par2ItemStack.getItem() == Diamerald.Diameraldgem;
				
	}

	public String getArmorTexture(ItemStack par1, Entity entity, int slot, int layer) {
		if (par1 == new ItemStack(Diamerald.Diameraldhelmet)
				|| par1 == new ItemStack(Diamerald.Diameraldplate)
				|| par1 == new ItemStack(Diamerald.Diameraldboots)) {
			return "diamerald:textures/models/armor/diamerald_layer_1.png";
		}
		if (par1 == new ItemStack(Diamerald.Diameraldlegs)) {
			return "diamerald:textures/models/armor/diamerald_layer_2.png";
		}
		return "diamerald:textures/models/armor/diamerald_layer_2.png";
	}

}
