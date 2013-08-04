package jmm.mods.Diamerald;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
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
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.toolMaterial = par2EnumToolMaterial;

	}

	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par1ItemStack.addEnchantment(Enchantment.power, 4);
		par1ItemStack.addEnchantment(Enchantment.infinity, 1);
		par1ItemStack.addEnchantment(Enchantment.punch, 2);
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {
		int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer,
				par1ItemStack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return;
		}
		var6 = event.charge;

		boolean var5 = par3EntityPlayer.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(
						Enchantment.infinity.effectId, par1ItemStack) > 0;

		if (var5 || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID)) {
			float var7 = (float) var6 / 20.0F;
			var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

			if ((double) var7 < 0.1D) {
				return;
			}

			if (var7 > 1.0F) {
				var7 = 1.0F;
			}

			EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer,
					var7 * 2.0F);

			if (var7 == 1.0F) {
				var8.setIsCritical(true);
			}

			int var9 = EnchantmentHelper.getEnchantmentLevel(
					Enchantment.power.effectId, par1ItemStack);

			if (var9 > 0) {
				var8.setDamage(var8.getDamage() + (double) var9 * 0.5D + 0.5D);
			}

			int var10 = EnchantmentHelper.getEnchantmentLevel(
					Enchantment.punch.effectId, par1ItemStack);

			if (var10 > 0) {
				var8.setKnockbackStrength(var10);
			}

			if (EnchantmentHelper.getEnchantmentLevel(
					Enchantment.flame.effectId, par1ItemStack) > 0) {
				var8.setFire(100);
			}

			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F,
					1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

			if (var5) {
				var8.canBePickedUp = 2;
			} else {
				par3EntityPlayer.inventory
						.consumeInventoryItem(Item.arrow.itemID);
			}

			if (!par2World.isRemote) {
				par2World.spawnEntityInWorld(var8);
			}
		}
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer,
				par1ItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return event.result;
		}

		if (par3EntityPlayer.capabilities.isCreativeMode
				|| par3EntityPlayer.inventory.hasItem(Item.arrow.itemID)) {
			par3EntityPlayer.setItemInUse(par1ItemStack,
					this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
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
		return  par2ItemStack.itemID == Diamerald.Diameraldgem.itemID;
				
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
