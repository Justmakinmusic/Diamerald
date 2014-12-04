package jmm.mods.Diamerald.blocks;

import java.util.Random;

import jmm.mods.Diamerald.Diamerald;
import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dfurnace extends BlockContainer {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private final Random random = new Random();
	private final boolean isActive;
	private static boolean keepInventory;
	    

	    public Dfurnace(boolean p_i45407_1_)
	    {
	        super(Material.rock);
	        this.isActive = p_i45407_1_;
	        setHardness(3.5f).setResistance(5.0f);
	    }

	    public Item getItemDropped(int par1, Random random, int par3)
	    {
	        return Item.getItemFromBlock(Diamerald.Dfurnace);
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

	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	    {
	        if (worldIn.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityDfurnace)
	            {
	                playerIn.openGui(Diamerald.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
	            }

	            return true;
	        }
	    }
	    
	    public static void updateDfurnaceBlockState(boolean par0, World par2World, BlockPos pos)
	    {
		   IBlockState iblockstate = par2World.getBlockState(pos);
	        TileEntity tileentity = par2World.getTileEntity(pos);
	        keepInventory = true;

	        if (par0)
	        {
	        	par2World.setBlockState(pos, Diamerald.Dfurnace_on.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	            par2World.setBlockState(pos, Diamerald.Dfurnace_on.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	        }
	        else
	        {
	        	par2World.setBlockState(pos, Diamerald.Dfurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	            par2World.setBlockState(pos, Diamerald.Dfurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	        }

	        keepInventory = false;

	        if (tileentity != null)
	        {
	            tileentity.validate();
	            par2World.setTileEntity(pos, tileentity);
	        }
	    }

	    public TileEntity createNewTileEntity(World par1World, int par2)
	    {
	        return new TileEntityDfurnace();
	    }
	    
	    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	        return this.getDefaultState().withProperty(FACING, placer.func_174811_aO().getOpposite());
	    }
	    
	    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	    {
	        worldIn.setBlockState(pos, state.withProperty(FACING, placer.func_174811_aO().getOpposite()), 2);

	        if (stack.hasDisplayName())
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityDfurnace)
	            {
	                ((TileEntityDfurnace)tileentity).setGuiDisplayName(stack.getDisplayName());
	            }
	        }
	    }
	    
	    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        if (!keepInventory)
	        {
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntityDfurnace)
	            {
	                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityDfurnace)tileentity);
	                worldIn.updateComparatorOutputLevel(pos, this);
	            }
	        }

	        super.breakBlock(worldIn, pos, state);
	    }

	    @SideOnly(Side.CLIENT)
	    public Item getItem(World par1World, int xCoord, int yCoord, int zCoord)
	    {
	        return Item.getItemFromBlock(Diamerald.Dfurnace);
	    }
	    public int getRenderType()
	    {
	        return 3;
	    }

	    @SideOnly(Side.CLIENT)
	    public IBlockState getStateForEntityRender(IBlockState state)
	    {
	        return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	    }

	    public IBlockState getStateFromMeta(int meta)
	    {
	        EnumFacing enumfacing = EnumFacing.getFront(meta);

	        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
	        {
	            enumfacing = EnumFacing.NORTH;
	        }

	        return this.getDefaultState().withProperty(FACING, enumfacing);
	    }

	    public int getMetaFromState(IBlockState state)
	    {
	        return ((EnumFacing)state.getValue(FACING)).getIndex();
	    }

	    protected BlockState createBlockState()
	    {
	        return new BlockState(this, new IProperty[] {FACING});
	    }
	}

