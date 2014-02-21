package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class blackDiameraldpickaxe extends ItemPickaxe {

	public blackDiameraldpickaxe(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
	}

	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par1ItemStack.addEnchantment(Enchantment.efficiency, 3);
		par1ItemStack.addEnchantment(Enchantment.unbreaking, 5);
		par1ItemStack.addEnchantment(Enchantment.fortune, 3);
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
			Block par3Block, int par4, int par5,
			int par6, EntityLivingBase par7EntityLivingBase) {
		int direction = MathHelper
				.floor_double((double) ((par7EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int dir = MathHelper
				.floor_double((double) ((par7EntityLivingBase.rotationPitch * 4F) / 360F) + 0.5D) & 3;
		int[] offsetY = new int[] { 0, -1, 0, 1 };
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = -1; k < 2; k++) {
					if (offsetY[dir] == 0) {
						Block blockToTest = par2World.getBlock(
								par4 + k + Direction.offsetX[direction],
								par5 + i, par6 + j
										+ Direction.offsetZ[direction]);
						if (blockToTest != null
								&& blockToTest != Blocks.bedrock
								&& func_150897_b(blockToTest))
							par2World.func_147480_a(par4 + k
									+ Direction.offsetX[direction], par5
									+ i, par6 + j
									+ Direction.offsetZ[direction], true);
					} else {
						Block blockToTest = par2World
								.getBlock(par4 + k, par5 + i
										+ offsetY[dir], par6 + j);
						if (blockToTest != null
								&& blockToTest != Blocks.bedrock
								&& func_150897_b(blockToTest))
							par2World.func_147480_a(par4 + k,
									par5 + i + offsetY[dir], par6
											+ j, true);
					}

				}

			}

		}
		par1ItemStack.damageItem(1, par7EntityLivingBase);
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Diamerald.blackDiameraldgem;

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:blackDiameraldpick");

	}

}
