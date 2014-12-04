package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Diameraldhoe extends ItemHoe {

	protected ToolMaterial theToolMaterial;
	static final int[] dirttype = new int[BlockDirt.DirtType.values().length];

	public Diameraldhoe(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
		this.theToolMaterial = par2ToolMaterial;
		this.maxStackSize = 1;
		this.setMaxDamage(par2ToolMaterial.getMaxUses());
		
	}
	
	@Override
	public boolean onItemUse(ItemStack stack,EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!playerIn.func_175151_a(pos.offset(side), side, stack)) 
		{
			return false;
		} else {
			UseHoeEvent event = new UseHoeEvent(playerIn,
					stack, worldIn, pos);
			if (MinecraftForge.EVENT_BUS.post(event)) 
			{
				return false;
			}

			if (event.getResult() == Result.ALLOW) 
			{
				stack.damageItem(1, playerIn);
				return true;
			}
			for (int i = -2; i < 3; i++) {
				for (int j = -2; j < 3; j++) {
		            Block block = worldIn.getBlockState(pos.add(j, 0, i)).getBlock();
					boolean block1 = worldIn.isAirBlock(pos.add(j, 1, i));

					if (!((side == EnumFacing.DOWN || block1 != true || block != Blocks.grass) && block != Blocks.dirt)) 
					{						
						this.func_179232_a(stack, playerIn, worldIn, (pos.add(j, 0, j)), Blocks.farmland.getDefaultState());
						
						if (!worldIn.isRemote) {
							
							worldIn.setBlockState((pos.add(j, 0, i)), Blocks.farmland.getDefaultState());
												
						}
					}
				}
			}
			stack.damageItem(1, playerIn);
			return true;
		}
	}

    protected boolean func_179232_a(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), state.getBlock().stepSound.getStepSound(), (state.getBlock().stepSound.getVolume() + 1.0F) / 2.0F, state.getBlock().stepSound.getFrequency() * 0.8F);

        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            worldIn.setBlockState(pos, state);
            stack.damageItem(1, playerIn);
            return true;
        }
    }

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() 
	{
		return true;
	}

	public String getMaterialName() 
	{
		return this.theToolMaterial.toString();
	}

}
