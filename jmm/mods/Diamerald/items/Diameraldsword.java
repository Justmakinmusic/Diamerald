package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Diameraldsword extends ItemSword {

	private float weaponDamage;
	private final EnumToolMaterial toolMaterial;

	public Diameraldsword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.toolMaterial = par2EnumToolMaterial;
		this.maxStackSize = 1;
		this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
		this.setCreativeTab(Diamerald.tabDiamerald);
		this.weaponDamage = 4.0f + par2EnumToolMaterial.getDamageVsEntity();

	}

	public float func_82803_g() {
		return this.toolMaterial.getDamageVsEntity();
	}

	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
		par1ItemStack.damageItem(1, par3EntityLiving);
		return true;
	}

	public float getDamageVsEntity(Entity par1Entity) {
		return this.weaponDamage;
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	public int getItemEnchantability() {
		return this.toolMaterial.getEnchantability();
	}

	public String getToolMaterialName() {
		return this.toolMaterial.toString();
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Diamerald.Diameraldgem.itemID;

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldsword");
	}

}
