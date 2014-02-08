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
		this.setCreativeTab(Diamerald.tabDiamerald);
	}

	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par1ItemStack.addEnchantment(Enchantment.efficiency, 3);
		par1ItemStack.addEnchantment(Enchantment.unbreaking, 5);
		par1ItemStack.addEnchantment(Enchantment.fortune, 3);
	}

	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_,
			Block p_150894_3_, int p_150894_4_, int p_150894_5_,
			int p_150894_6_, EntityLivingBase p_150894_7_) {
		int direction = MathHelper
				.floor_double((double) ((p_150894_7_.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int dir = MathHelper
				.floor_double((double) ((p_150894_7_.rotationPitch * 4F) / 360F) + 0.5D) & 3;
		int[] offsetY = new int[] { 0, -1, 0, 1 };
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = -1; k < 2; k++) {
					if (offsetY[dir] == 0) {
						Block blockToTest = p_150894_2_.getBlock(
								p_150894_4_ + k + Direction.offsetX[direction],
								p_150894_5_ + i, p_150894_6_ + j
										+ Direction.offsetZ[direction]);
						if (blockToTest != null
								&& blockToTest != Blocks.bedrock
								&& func_150897_b(blockToTest))
							p_150894_2_.func_147480_a(p_150894_4_ + k
									+ Direction.offsetX[direction], p_150894_5_
									+ i, p_150894_6_ + j
									+ Direction.offsetZ[direction], true);
					} else {
						Block blockToTest = p_150894_2_
								.getBlock(p_150894_4_ + k, p_150894_5_ + i
										+ offsetY[dir], p_150894_6_ + j);
						if (blockToTest != null
								&& blockToTest != Blocks.bedrock
								&& func_150897_b(blockToTest))
							p_150894_2_.func_147480_a(p_150894_4_ + k,
									p_150894_5_ + i + offsetY[dir], p_150894_6_
											+ j, true);
					}

				}

			}

		}
		p_150894_1_.damageItem(1, p_150894_7_);
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
