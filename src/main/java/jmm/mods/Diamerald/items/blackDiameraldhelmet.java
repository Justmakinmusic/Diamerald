package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class blackDiameraldhelmet extends ItemArmor {
	
	
	public blackDiameraldhelmet(ArmorMaterial par2ArmorMaterial,
			int par3, int par4) {
		super(par2ArmorMaterial, par3, par4);
		this.setCreativeTab(Diamerald.tabDiamerald);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
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
		return par2ItemStack.getItem() == Diamerald.blackDiameraldgem;
	}

	public String getArmorTexture(ItemStack par1, Entity entity, int slot, String type) {
		if (par1 == new ItemStack(Diamerald.blackDiameraldhelmet))
		{
			return "diamerald:textures/models/armor/blackdiamerald_layer_1.png";
		}
		
		return "diamerald:textures/models/armor/blackdiamerald_layer_1.png";
	}
	
}


