package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Diameraldbow extends ItemBow {

	private final EnumToolMaterial toolMaterial;

	public static final String[] bowPullIconNameArray = new String[] {
			"Dbow_0", "Dbow_1", "Dbow_2" };
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public Diameraldbow(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(2000);
		this.setCreativeTab(Diamerald.tabDiamerald);
		this.toolMaterial = par2EnumToolMaterial;

	}

	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par1ItemStack.addEnchantment(Enchantment.power, 4);
		par1ItemStack.addEnchantment(Enchantment.infinity, 1);
		par1ItemStack.addEnchantment(Enchantment.punch, 2);
	}

	public int getItemEnchantability() {
		return 10;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:Dbow");
		this.iconArray = new Icon[bowPullIconNameArray.length];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("Diamerald:"
					+ bowPullIconNameArray[i]);
		}

	}

	@SideOnly(Side.CLIENT)
	public Icon getItemIconForUseDuration(int par1) {
		return this.iconArray[par1];
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Diamerald.Diameraldgem.itemID;

	}

	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player,
			ItemStack usingItem, int useRemaining) {
		if (usingItem != null
				&& usingItem.getItem().itemID == Diamerald.Diameraldbow.itemID) {
			int k = usingItem.getMaxItemUseDuration() - useRemaining;
			if (k >= 18) {
				return Diamerald.Diameraldbow.getItemIconForUseDuration(2);
			}

			if (k > 13) {
				return Diamerald.Diameraldbow.getItemIconForUseDuration(1);
			}

			if (k > 0) {
				return Diamerald.Diameraldbow.getItemIconForUseDuration(0);
			}

		}
		return getIcon(stack, renderPass);
	}
}
