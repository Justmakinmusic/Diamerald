package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Diameraldbow extends ItemBow {

	private final ToolMaterial toolMaterial;

	public static final String[] bowPullIconNameArray = new String[] {
			"Dbow_0", "Dbow_1", "Dbow_2" };
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public Diameraldbow(ToolMaterial par2ToolMaterial) {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(2000);
		this.toolMaterial = par2ToolMaterial;

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
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:Dbow");
		this.iconArray = new IIcon[bowPullIconNameArray.length];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("Diamerald:"
					+ bowPullIconNameArray[i]);
		}

	}

	@SideOnly(Side.CLIENT)
	public IIcon getItemIconForUseDuration(int par1) {
		return this.iconArray[par1];
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Diamerald.Diameraldgem;

	}

	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player,
			ItemStack usingItem, int useRemaining) {
		if (usingItem != null
				&& usingItem.getItem() == Diamerald.Diameraldbow) {
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
	
