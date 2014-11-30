package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

;

public class Diameraldplate extends ItemArmor {

	public Diameraldplate(ArmorMaterial par2ArmorMaterial, int par3, int par4) {
		super(par2ArmorMaterial, par3, par4);
		
	}

	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldplate");
	}*/

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Diamerald.gemDiamerald;

	}

	public String getArmorTexture(ItemStack par1, Entity entity, int slot,
			String type) {

		return "diamerald:textures/models/armor/diamerald_layer_1.png";
	}

}
