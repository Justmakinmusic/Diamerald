package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Diameraldsword extends ItemSword {

	private float field_150934_a;
	private final ToolMaterial toolMaterial;

	public Diameraldsword(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
		this.toolMaterial = par2ToolMaterial;
		this.maxStackSize = 1;
		this.setMaxDamage(par2ToolMaterial.getMaxUses());
		this.field_150934_a = 4.0f + par2ToolMaterial.getDamageVsEntity();

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
		return this.field_150934_a;
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
		return par2ItemStack.getItem() == Diamerald.gemDiamerald;

	}

	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldsword");
	}*/

}
