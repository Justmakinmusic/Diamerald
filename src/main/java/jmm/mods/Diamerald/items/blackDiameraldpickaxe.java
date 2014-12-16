package jmm.mods.Diamerald.items;


import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



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
	
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) 
	{		
		int direction = MathHelper
				.floor_double((double) ((playerIn.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int dir = MathHelper
				.floor_double((double) ((playerIn.rotationPitch * 4F) / 360F) + 0.5D) & 3;
		
		int offsetX = EnumFacing.getHorizontal(direction).getFrontOffsetX();
		int[] offsetY = new int[] { 0, -1, 0, 1 };
	    int offsetZ = EnumFacing.getHorizontal(direction).getFrontOffsetZ();
		
		for (int i = -1; i < 2; i++) 
		{
			for (int j = -1; j < 2; j++) 
			{
				for (int k = -1; k < 2; k++) 
				{
					if (offsetY[dir] == 0)
					{
						Block blockToTest = worldIn.getBlockState(pos.add(k + offsetX, i, j + offsetZ)).getBlock();
						if (blockToTest != null
								&& blockToTest != Blocks.bedrock
								&& canHarvestBlock(blockToTest))
							worldIn.destroyBlock(pos.add(k + offsetX, i, j + offsetZ), true);
					} 
					else 
					{
						Block blockToTest = worldIn
								.getBlockState(pos.add(k, i + offsetY[dir], j)).getBlock();
						if (blockToTest != null
								&& blockToTest != Blocks.bedrock
								&& canHarvestBlock(blockToTest))
							worldIn.destroyBlock(pos.add(k,	i + offsetY[dir], j), true);
					}

				}

			}

		}
		stack.damageItem(1, playerIn);
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) 
	{
		return par2ItemStack.getItem() == Diamerald.gemBlackDiamerald;

	}

}
