package jmm.mods.Diamerald;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class blackDiameraldhelmet extends ItemArmor {
	
	
	public blackDiameraldhelmet(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:blackDiameraldhelmet");
		
	}
	
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par1ItemStack.addEnchantment(Enchantment.aquaAffinity, 1);
		par1ItemStack.addEnchantment(Enchantment.respiration, 3);
		
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Diamerald.blackDiameraldgem.itemID;
	}

	public String getArmorTexture(ItemStack par1, Entity entity, int slot, int layer) {
		if (par1.itemID == Diamerald.blackDiameraldhelmet.itemID)
		{
			return "diamerald:textures/models/armor/blackdiamerald_layer_1.png";
		}
		if (par1.itemID == Diamerald.Diameraldlegs.itemID) 
		{
			return "diamerald:textures/models/armor/diamerald_layer_2.png";
		}
		return "diamerald:textures/models/armor/diamerald_layer_2.png";
	}
	
}


