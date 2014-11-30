package jmm.mods.Diamerald.blocks;

import java.util.Random;

import jmm.mods.Diamerald.Diamerald;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Grinder extends BlockContainer
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private final boolean isBurning;
    private static boolean keepInventory;
    //private static boolean field_149934_M;
    private static final String __OBFID = "CL_00000248";
    
    

    public Grinder(boolean p_i45407_1_)
    {
        super(Material.rock);
        this.isBurning = p_i45407_1_;
        setHardness(3.5f).setResistance(5.0f);
    }

    public Item getItemDropped(int par1, Random random, int par3)
    {
        return Item.getItemFromBlock(Diamerald.Grinder);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	 this.setDefaultDirection(worldIn, pos, state);
    }

    private void setDefaultDirection(World worldIn, BlockPos p_176445_2_, IBlockState p_176445_3_)
    {
        if (!worldIn.isRemote)
        {
        	Block block = worldIn.getBlockState(p_176445_2_.offsetNorth()).getBlock();
            Block block1 = worldIn.getBlockState(p_176445_2_.offsetSouth()).getBlock();
            Block block2 = worldIn.getBlockState(p_176445_2_.offsetWest()).getBlock();
            Block block3 = worldIn.getBlockState(p_176445_2_.offsetEast()).getBlock();
            EnumFacing enumfacing = (EnumFacing)p_176445_3_.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(p_176445_2_, p_176445_3_.withProperty(FACING, enumfacing), 2);
        }
    }
    
    /*@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1side, int par2meta)
    {
        return par1side == 1 ? this.top : (par1side == 0 ? this.bottom : (par1side != par2meta ? this.blockIcon : this.front));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1)
    {
        this.blockIcon = par1.registerIcon("Diamerald:grinder_side");
        this.front = par1.registerIcon(this.isActive ? "Diamerald:grinder_front_on" : "Diamerald:grinder_front_off");
        this.top = par1.registerIcon("Diamerald:grinder_top");
        this.bottom = par1.registerIcon("Diamerald:grinder_bottom");
    }*/

    public boolean onBlockActivated(World par1World, int xCoord, int yCoord, int zCoord, EntityPlayer par5Player, int par6, float par7, float par8, float par9)
    {
    	
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityGrinder tileentitygrinder = (TileEntityGrinder)par1World.getTileEntity(new BlockPos(xCoord, yCoord, zCoord));

            if (tileentitygrinder != null)
            {
                par5Player.openGui(Diamerald.instance, 0, par1World, xCoord, yCoord, zCoord);
            }
            return true;
        }
    }

   public static void updateGrinderBlockState(boolean par0, World par2World, BlockPos pos)/*(int xCoord, int yCoord, int zCoord)*/
    {
	   IBlockState iblockstate = par2World.getBlockState(pos);
        TileEntity tileentity = par2World.getTileEntity(pos);
        keepInventory = true;

        if (par0)
        {
            //par2World.setBlock(pos, Diamerald.Grinder_on);
        	par2World.setBlockState(pos, Diamerald.Grinder_on.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            par2World.setBlockState(pos, Diamerald.Grinder_on.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else
        {
            //par2World.setBlock(xCoord, yCoord, zCoord, Diamerald.Grinder);
        	par2World.setBlockState(pos, Diamerald.Grinder.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            par2World.setBlockState(pos, Diamerald.Grinder.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;
        //par2World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            par2World.setTileEntity(pos, tileentity);
        }
    }

    public TileEntity createNewTileEntity(World par1World, int par2)
    {
        return new TileEntityGrinder();
    }
    
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.func_174811_aO().getOpposite());
    }

    /*public void onBlockPlacedBy(World par1World, int xCoord, int yCoord, int zCoord, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 4, 2);
        }

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityGrinder)par1World.getTileEntity(new BlockPos(xCoord, yCoord, zCoord))).setGuiDisplayName(par6ItemStack.getDisplayName());
        }
    }*/
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.func_174811_aO().getOpposite()), 2);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityGrinder)
            {
                ((TileEntityGrinder)tileentity).setGuiDisplayName(stack.getDisplayName());
            }
        }
    }

    /*public void breakBlock(World par1World, int xCoord, int yCoord, int zCoord, Block par5Block, int par6)
    {
        if (!keepInventory)
        {
            TileEntityGrinder tileentitygrinder = (TileEntityGrinder)par1World.getTileEntity(xCoord, yCoord, zCoord);

            if (tileentitygrinder != null)
            {
                for (int i1 = 0; i1 < tileentitygrinder.getSizeInventory(); ++i1)
                {
                    ItemStack itemstack = tileentitygrinder.getStackInSlot(i1);

                    if (itemstack != null)
                    {
                        float f = this.random.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int j1 = this.random.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize)
                            {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(par1World, (double)((float)xCoord + f), (double)((float)yCoord + f1), (double)((float)zCoord + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);
                            par1World.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                par1World.func_147453_f(xCoord, yCoord, zCoord, par5Block);
            }
        }

        super.breakBlock(par1World, xCoord, yCoord, zCoord, par5Block, par6);
    }*/
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepInventory)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityGrinder)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityGrinder)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World par1World, int xCoord, int yCoord, int zCoord)
    {
        return Item.getItemFromBlock(Diamerald.Grinder);
    }
}

