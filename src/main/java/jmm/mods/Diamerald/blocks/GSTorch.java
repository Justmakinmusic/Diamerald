package jmm.mods.Diamerald.blocks;

import java.util.Iterator;
import java.util.Random;

import com.google.common.base.Predicate;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GSTorch extends BlockGlowstone {
	
	public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing", new Predicate()
    {
        public boolean func_176601_a(EnumFacing p_176601_1_)
        {
            return p_176601_1_ != EnumFacing.DOWN;
        }
        public boolean apply(Object p_apply_1_)
        {
            return this.func_176601_a((EnumFacing)p_apply_1_);
        }
    });

	public GSTorch(Material i) {
		super(i);
		setHardness(0.0f).setResistance(1.0f);
		setLightLevel(1.0f);
		
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2,
			int par3, int par4) {
		return null;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 2;
	}

	private boolean canPlaceOn(World par1World, BlockPos pos) {
		if (World.doesBlockHaveSolidTopSurface(par1World, pos)) {
			return true;
		} else {
			Block block = par1World.getBlockState(pos).getBlock();
			return block.canPlaceTorchOnTop(par1World, pos);
		}
	}

	/*public boolean canPlaceBlockAt(World par1World, int par2,
			int par3, int par4) {
		return par1World.isSideSolid(par2 - 1, par3,
				par4, EAST, true)
				|| par1World.isSideSolid(par2 + 1, par3,
						par4, WEST, true)
				|| par1World.isSideSolid(par2, par3,
						par4 - 1, SOUTH, true)
				|| par1World.isSideSolid(par2, par3,
						par4 + 1, NORTH, true)
				|| canPlaceOn(par1World, par2, par3 - 1,
						par4);
	}*/
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        Iterator iterator = FACING_PROP.getAllowedValues().iterator();
        EnumFacing enumfacing;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            enumfacing = (EnumFacing)iterator.next();
        }
        while (!this.func_176595_b(worldIn, pos, enumfacing));

        return true;
    }
	
	private boolean func_176595_b(World worldIn, BlockPos p_176595_2_, EnumFacing p_176595_3_)
    {
        BlockPos blockpos1 = p_176595_2_.offset(p_176595_3_.getOpposite());
        boolean flag = p_176595_3_.getAxis().isHorizontal();
        return flag && worldIn.isSideSolid(blockpos1, p_176595_3_, true) || p_176595_3_.equals(EnumFacing.UP) && this.canPlaceOn(worldIn, blockpos1);
    }

	/*public int onBlockPlaced(World par1World, int par2,
			int par3, int par4, int par5,
			float par6, float par7, float par8,
			int par9) {
		int j1 = par9;

		if (par5 == 1
				&& this.canPlaceOn(par1World, par2,
						par3 - 1, par4)) {
			j1 = 5;
		}

		if (par5 == 2
				&& par1World.isSideSolid(par2, par3,
						par4 + 1, NORTH, true)) {
			j1 = 4;
		}

		if (par5 == 3
				&& par1World.isSideSolid(par2, par3,
						par4 - 1, SOUTH, true)) {
			j1 = 3;
		}

		if (par5 == 4
				&& par1World.isSideSolid(par2 + 1, par3,
						par4, WEST, true)) {
			j1 = 2;
		}

		if (par5 == 5
				&& par1World.isSideSolid(par2 - 1, par3,
						par4, EAST, true)) {
			j1 = 1;
		}

		return j1;
	}*/
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (this.func_176595_b(worldIn, pos, facing))
        {
            return this.getDefaultState().withProperty(FACING_PROP, facing);
        }
        else
        {
            Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();
            EnumFacing enumfacing1;

            do
            {
                if (!iterator.hasNext())
                {
                    return this.getDefaultState();
                }

                enumfacing1 = (EnumFacing)iterator.next();
            }
            while (!worldIn.isSideSolid(pos.offset(enumfacing1.getOpposite()), enumfacing1, true));

            return this.getDefaultState().withProperty(FACING_PROP, enumfacing1);
        }
    }

	public int quantityDropped(Random random) {
		return 1;
	}

	 public Item getItemDropped(int par1, Random random, int par3)
	    {
	        return Item.getItemFromBlock(Diamerald.GSTorch);
	    }

	/*protected boolean dropIfCantStay(World par1World, int par2,
			int par3, int par4) {
		if (!this.canPlaceBlockAt(par1World, par2, par3,
				par4)) {
			if (par1World
					.getBlock(par2, par3, par4) == this) {
				this.dropBlockAsItem(par1World, par2, par3,
						par4, par1World.getBlockMetadata(par2,
								par3, par4), 0);
				par1World
						.setBlockToAir(par2, par3, par4);
			}

			return false;
		} else {
			return true;
		}
	}

	public void onNeighborBlockChange(World par1World, int par2,
			int par3, int par4, Block par5Block) {
		this.func_150108_b(par1World, par2, par3, par4,
				par5Block);
	}

	protected boolean func_150108_b(World par1World, int par2,
			int par3, int par4, Block par5Block) {
		if (this.dropIfCantStay(par1World, par2, par3,
				par4)) {
			int l = par1World.getBlockMetadata(par2, par3,
					par4);
			boolean flag = false;

			if (!par1World.isSideSolid(par2 - 1, par3,
					par4, EAST, true) && l == 1) {
				flag = true;
			}

			if (!par1World.isSideSolid(par2 + 1, par3,
					par4, WEST, true) && l == 2) {
				flag = true;
			}

			if (!par1World.isSideSolid(par2, par3,
					par4 - 1, SOUTH, true) && l == 3) {
				flag = true;
			}

			if (!par1World.isSideSolid(par2, par3,
					par4 + 1, NORTH, true) && l == 4) {
				flag = true;
			}

			if (!this.canPlaceOn(par1World, par2, par3 - 1,
					par4) && l == 5) {
				flag = true;
			}

			if (flag) {
				this.dropBlockAsItem(par1World, par2, par3,
						par4, par1World.getBlockMetadata(par2,
								par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
				
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}*/
	 
	 public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	    {
	        this.func_176592_e(worldIn, pos, state);
	    }
	
	protected boolean func_176592_e(World worldIn, BlockPos p_176592_2_, IBlockState p_176592_3_)
    {
        if (!this.func_176593_f(worldIn, p_176592_2_, p_176592_3_))
        {
            return true;
        }
        else
        {
            EnumFacing enumfacing = (EnumFacing)p_176592_3_.getValue(FACING_PROP);
            EnumFacing.Axis axis = enumfacing.getAxis();
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            boolean flag = false;

            if (axis.isHorizontal() && !worldIn.isSideSolid(p_176592_2_.offset(enumfacing1), enumfacing1, true))
            {
                flag = true;
            }
            else if (axis.isVertical() && !this.canPlaceOn(worldIn, p_176592_2_.offset(enumfacing1)))
            {
                flag = true;
            }

            if (flag)
            {
                this.dropBlockAsItem(worldIn, p_176592_2_, p_176592_3_, 0);
                worldIn.setBlockToAir(p_176592_2_);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    protected boolean func_176593_f(World worldIn, BlockPos p_176593_2_, IBlockState p_176593_3_)
    {
        if (p_176593_3_.getBlock() == this && this.func_176595_b(worldIn, p_176593_2_, (EnumFacing)p_176593_3_.getValue(FACING_PROP)))
        {
            return true;
        }
        else
        {
            if (worldIn.getBlockState(p_176593_2_).getBlock() == this)
            {
                this.dropBlockAsItem(worldIn, p_176593_2_, p_176593_3_, 0);
                worldIn.setBlockToAir(p_176593_2_);
            }

            return false;
        }
    }

	/*public MovingObjectPosition collisionRayTrace(World par1World,
			int par2, int par3, int par4,
			Vec3 par5, Vec3 par6) {
		int l = par1World.getBlockMetadata(par2, par3,
				par4) & 7;
		float f = 0.15F;

		if (l == 1) {
			this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
		} else if (l == 2) {
			this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F,
					0.5F + f);
		} else if (l == 3) {
			this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
		} else if (l == 4) {
			this.setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F,
					1.0F);
		} else {
			f = 0.1F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F,
					0.5F + f);
		}

		return super.collisionRayTrace(par1World, par2, par3,
				par4, par5, par6);
	}*/
    
    public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end)
    {
        EnumFacing enumfacing = (EnumFacing)worldIn.getBlockState(pos).getValue(FACING_PROP);
        float f = 0.15F;

        if (enumfacing == EnumFacing.EAST)
        {
            this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        }
        else if (enumfacing == EnumFacing.WEST)
        {
            this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        }
        else if (enumfacing == EnumFacing.SOUTH)
        {
            this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        }
        else if (enumfacing == EnumFacing.NORTH)
        {
            this.setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        }
        else
        {
            f = 0.1F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }

        return super.collisionRayTrace(worldIn, pos, start, end);
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta)
        {
            case 1:
                iblockstate = iblockstate.withProperty(FACING_PROP, EnumFacing.EAST);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(FACING_PROP, EnumFacing.WEST);
                break;
            case 3:
                iblockstate = iblockstate.withProperty(FACING_PROP, EnumFacing.SOUTH);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(FACING_PROP, EnumFacing.NORTH);
                break;
            case 5:
            default:
                iblockstate = iblockstate.withProperty(FACING_PROP, EnumFacing.UP);
        }

        return iblockstate;
    }

    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i;

        switch (GSTorch.SwitchEnumFacing.field_176609_a[((EnumFacing)state.getValue(FACING_PROP)).ordinal()])
        {
            case 1:
                i = b0 | 1;
                break;
            case 2:
                i = b0 | 2;
                break;
            case 3:
                i = b0 | 3;
                break;
            case 4:
                i = b0 | 4;
                break;
            case 5:
            case 6:
            default:
                i = b0 | 5;
        }

        return i;
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING_PROP});
    }
    
    static final class SwitchEnumFacing
    {
        static final int[] field_176609_a = new int[EnumFacing.values().length];

        static
        {
            try
            {
                field_176609_a[EnumFacing.EAST.ordinal()] = 1;
            }
            catch (NoSuchFieldError var6)
            {
                ;
            }

            try
            {
                field_176609_a[EnumFacing.WEST.ordinal()] = 2;
            }
            catch (NoSuchFieldError var5)
            {
                ;
            }

            try
            {
                field_176609_a[EnumFacing.SOUTH.ordinal()] = 3;
            }
            catch (NoSuchFieldError var4)
            {
                ;
            }

            try
            {
                field_176609_a[EnumFacing.NORTH.ordinal()] = 4;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try
            {
                field_176609_a[EnumFacing.DOWN.ordinal()] = 5;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                field_176609_a[EnumFacing.UP.ordinal()] = 6;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }

	/*@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("Diamerald:GStorch");
	}*/
}
