package jmm.mods.Diamerald.blocks;

import java.util.Random;

import jmm.mods.Diamerald.Diamerald;
import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Dfurnace extends BlockContainer {
	
		private final Random random = new Random();
	    private final boolean isActive;
	    private static boolean keepInventory;
	    @SideOnly(Side.CLIENT)
	    private IIcon top;
	    @SideOnly(Side.CLIENT)
	    private IIcon front;
	    @SideOnly(Side.CLIENT)
	    private IIcon bottom;
	    
	    

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

	    public void onBlockAdded(World par1World, int x, int y, int z)
	    {
	        super.onBlockAdded(par1World, x, y, z);
	        this.setDefaultDirection(par1World, x, y, z);
	    }

	    private void setDefaultDirection(World par1World, int xCoord, int yCoord, int zCoord)
	    {
	        if (!par1World.isRemote)
	        {
	            Block block = par1World.getBlock(xCoord, yCoord, zCoord - 1);
	            Block block1 = par1World.getBlock(xCoord, yCoord, zCoord + 1);
	            Block block2 = par1World.getBlock(xCoord - 1, yCoord, zCoord);
	            Block block3 = par1World.getBlock(xCoord + 1, yCoord, zCoord);
	            byte b0 = 3;

	            if (block.func_149730_j() && !block1.func_149730_j())
	            {
	                b0 = 3;
	            }

	            if (block1.func_149730_j() && !block.func_149730_j())
	            {
	                b0 = 2;
	            }

	            if (block2.func_149730_j() && !block3.func_149730_j())
	            {
	                b0 = 5;
	            }

	            if (block3.func_149730_j() && !block2.func_149730_j())
	            {
	                b0 = 4;
	            }

	            par1World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, b0, 2);
	        }
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int par1side, int par2meta)
	    {
	        return par1side == 1 ? this.top : (par1side == 0 ? this.bottom : (par1side != par2meta ? this.blockIcon : this.front));
	    }

	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister par1)
	    {
	        this.blockIcon = par1.registerIcon("Diamerald:Dfurnace_side");
	        this.front = par1.registerIcon(this.isActive ? "Diamerald:Dfurnace_front_on" : "Diamerald:Dfurnace_front_off");
	        this.top = par1.registerIcon("Diamerald:Dfurnace_top");
	        this.bottom = par1.registerIcon("Diamerald:Dfurnace_top");
	    }

	    public boolean onBlockActivated(World par1World, int xCoord, int yCoord, int zCoord, EntityPlayer par5Player, int par6, float par7, float par8, float par9)
	    {
	    	
	        if (par1World.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	            TileEntityDfurnace tileentitydfurnace = (TileEntityDfurnace)par1World.getTileEntity(xCoord, yCoord, zCoord);

	            if (tileentitydfurnace != null)
	            {
	                par5Player.openGui(Diamerald.instance, 0, par1World, xCoord, yCoord, zCoord);
	            }
	            return true;
	        }
	    }

	   public static void updateDfurnaceBlockState(boolean par0, World par2World, int xCoord, int yCoord, int zCoord)
	    {
	        int l = par2World.getBlockMetadata(xCoord, yCoord, zCoord);
	        TileEntity tileentity = par2World.getTileEntity(xCoord, yCoord, zCoord);
	        keepInventory = true;

	        if (par0)
	        {
	            par2World.setBlock(xCoord, yCoord, zCoord, Diamerald.Dfurnace_on);
	        }
	        else
	        {
	            par2World.setBlock(xCoord, yCoord, zCoord, Diamerald.Dfurnace);
	        }

	        keepInventory = false;
	        par2World.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l, 2);

	        if (tileentity != null)
	        {
	            tileentity.validate();
	            par2World.setTileEntity(xCoord, yCoord, zCoord, tileentity);
	        }
	    }

	    public TileEntity createNewTileEntity(World par1World, int par2)
	    {
	        return new TileEntityDfurnace();
	    }

	    public void onBlockPlacedBy(World par1World, int xCoord, int yCoord, int zCoord, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
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
	            ((TileEntityGrinder)par1World.getTileEntity(xCoord, yCoord, zCoord)).setGuiDisplayName(par6ItemStack.getDisplayName());
	        }
	    }

	    public void breakBlock(World par1World, int xCoord, int yCoord, int zCoord, Block par5Block, int par6)
	    {
	        if (!keepInventory)
	        {
	            TileEntityDfurnace tileentitydfurnace = (TileEntityDfurnace)par1World.getTileEntity(xCoord, yCoord, zCoord);

	            if (tileentitydfurnace != null)
	            {
	                for (int i1 = 0; i1 < tileentitydfurnace.getSizeInventory(); ++i1)
	                {
	                    ItemStack itemstack = tileentitydfurnace.getStackInSlot(i1);

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
	    }

	    @SideOnly(Side.CLIENT)
	    public Item getItem(World par1World, int xCoord, int yCoord, int zCoord)
	    {
	        return Item.getItemFromBlock(Diamerald.Dfurnace);
	    }
	}

