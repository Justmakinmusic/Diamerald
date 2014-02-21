package jmm.mods.Diamerald.blocks;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import jmm.mods.Diamerald.Diamerald;
import jmm.mods.Diamerald.tileentity.TileEntityChestDC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDirtchest extends BlockContainer {

	private final Random random = new Random();
	public final int field_149956_a;

	public BlockDirtchest(int i, Material material) {
		super(material);
		this.field_149956_a = i;
		setHardness(2.5f);
		setResistance(5.0f);

	}

	 public boolean func_149662_c()
	    {
	        return false;
	    }

	    public boolean func_149686_d()
	    {
	        return false;
	    }

	public void onBlockAdded(World par1World, int par2,
			int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		this.unifyAdjacentDirtchests(par1World, par2, par3,
				par4);
		Block block = par1World.getBlock(par2, par3,
				par4 - 1);
		Block block1 = par1World.getBlock(par2, par3,
				par4 + 1);
		Block block2 = par1World.getBlock(par2 - 1, par3,
				par4);
		Block block3 = par1World.getBlock(par2 + 1, par3,
				par4);

		if (block == this) {
			this.unifyAdjacentDirtchests(par1World, par2, par3,
					par4 - 1);
		}

		if (block1 == this) {
			this.unifyAdjacentDirtchests(par1World, par2, par3,
					par4 + 1);
		}

		if (block2 == this) {
			this.unifyAdjacentDirtchests(par1World, par2 - 1,
					par3, par4);
		}

		if (block3 == this) {
			this.unifyAdjacentDirtchests(par1World, par2 + 1,
					par3, par4);
		}
	}

	public void onBlockPlacedBy(World par1World, int par2,
			int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		Block block = par1World.getBlock(par2, par3,
				par4 - 1);
		Block block1 = par1World.getBlock(par2, par3,
				par4 + 1);
		Block block2 = par1World.getBlock(par2 - 1, par3,
				par4);
		Block block3 = par1World.getBlock(par2 + 1, par3,
				par4);
		byte b0 = 0;
		int l = MathHelper
				.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			b0 = 2;
		}

		if (l == 1) {
			b0 = 5;
		}

		if (l == 2) {
			b0 = 3;
		}

		if (l == 3) {
			b0 = 4;
		}

		if (block != this && block1 != this && block2 != this && block3 != this) {
			par1World.setBlockMetadataWithNotify(par2, par3,
					par4, b0, 3);
		} else {
			if ((block == this || block1 == this) && (b0 == 4 || b0 == 5)) {
				if (block == this) {
					par1World.setBlockMetadataWithNotify(par2,
							par3, par4 - 1, b0, 3);
				} else {
					par1World.setBlockMetadataWithNotify(par2,
							par3, par4 + 1, b0, 3);
				}

				par1World.setBlockMetadataWithNotify(par2,
						par3, par4, b0, 3);
			}

			if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3)) {
				if (block2 == this) {
					par1World.setBlockMetadataWithNotify(par2 - 1,
							par3, par4, b0, 3);
				} else {
					par1World.setBlockMetadataWithNotify(par2 + 1,
							par3, par4, b0, 3);
				}

				par1World.setBlockMetadataWithNotify(par2,
						par3, par4, b0, 3);
			}
		}

		if (par6ItemStack.hasDisplayName()) {
			((TileEntityChestDC) par1World.getTileEntity(par2,
					par3, par4)).setGuiDisplayName(par6ItemStack
					.getDisplayName());
		}
	}

	public void unifyAdjacentDirtchests(World par1World, int par2,
			int par3, int par4) {
		if (!par1World.isRemote) {
			Block block = par1World.getBlock(par2, par3,
					par4 - 1);
			Block block1 = par1World.getBlock(par2, par3,
					par4 + 1);
			Block block2 = par1World.getBlock(par2 - 1,
					par3, par4);
			Block block3 = par1World.getBlock(par2 + 1,
					par3, par4);
			boolean flag = true;
			int l;
			Block block4;
			int i1;
			Block block5;
			boolean flag1;
			byte b0;
			int j1;

			if (block != this && block1 != this) {
				if (block2 != this && block3 != this) {
					b0 = 3;

					if (block.func_149730_j() && !block1.func_149730_j()) {
						b0 = 3;
					}

					if (block1.func_149730_j() && !block.func_149730_j()) {
						b0 = 2;
					}

					if (block2.func_149730_j() && !block3.func_149730_j()) {
						b0 = 5;
					}

					if (block3.func_149730_j() && !block2.func_149730_j()) {
						b0 = 4;
					}
				} else {
					l = block2 == this ? par2 - 1 : par2 + 1;
					block4 = par1World.getBlock(l, par3,
							par4 - 1);
					i1 = block2 == this ? par2 - 1 : par2 + 1;
					block5 = par1World.getBlock(i1, par3,
							par4 + 1);
					b0 = 3;
					flag1 = true;

					if (block2 == this) {
						j1 = par1World.getBlockMetadata(par2 - 1,
								par3, par4);
					} else {
						j1 = par1World.getBlockMetadata(par2 + 1,
								par3, par4);
					}

					if (j1 == 2) {
						b0 = 2;
					}

					if ((block.func_149730_j() || block4.func_149730_j())
							&& !block1.func_149730_j()
							&& !block5.func_149730_j()) {
						b0 = 3;
					}

					if ((block1.func_149730_j() || block5.func_149730_j())
							&& !block.func_149730_j()
							&& !block4.func_149730_j()) {
						b0 = 2;
					}
				}
			} else {
				l = block == this ? par4 - 1 : par4 + 1;
				block4 = par1World.getBlock(par2 - 1,
						par3, l);
				i1 = block == this ? par4 - 1 : par4 + 1;
				block5 = par1World.getBlock(par2 + 1,
						par3, i1);
				b0 = 5;
				flag1 = true;

				if (block == this) {
					j1 = par1World.getBlockMetadata(par2, par3,
							par4 - 1);
				} else {
					j1 = par1World.getBlockMetadata(par2, par3,
							par4 + 1);
				}

				if (j1 == 4) {
					b0 = 4;
				}

				if ((block2.func_149730_j() || block4.func_149730_j())
						&& !block3.func_149730_j() && !block5.func_149730_j()) {
					b0 = 5;
				}

				if ((block3.func_149730_j() || block5.func_149730_j())
						&& !block2.func_149730_j() && !block4.func_149730_j()) {
					b0 = 4;
				}
			}

			par1World.setBlockMetadataWithNotify(par2, par3,
					par4, b0, 3);
		}
	}

	public boolean canPlaceBlockAt(World par1World, int par2,
			int par3, int par4) {
		int l = 0;

		if (par1World.getBlock(par2 - 1, par3, par4) == this) {
			++l;
		}

		if (par1World.getBlock(par2 + 1, par3, par4) == this) {
			++l;
		}

		if (par1World.getBlock(par2, par3, par4 - 1) == this) {
			++l;
		}

		if (par1World.getBlock(par2, par3, par4 + 1) == this) {
			++l;
		}

		return l > 1 ? false : (this.isThereANeighborDirtchest(par1World,
				par2 - 1, par3, par4) ? false : (this
				.isThereANeighborDirtchest(par1World, par2 + 1,
						par3, par4) ? false : (this
				.isThereANeighborDirtchest(par1World, par2,
						par3, par4 - 1) ? false : !this
				.isThereANeighborDirtchest(par1World, par2,
						par3, par4 + 1))));
	}

	private boolean isThereANeighborDirtchest(World par1World,
			int par2, int par3, int par4) {
		return par1World.getBlock(par2, par3, par4) != this ? false
				: (par1World.getBlock(par2 - 1, par3,
						par4) == this ? true : (par1World
						.getBlock(par2 + 1, par3,
								par4) == this ? true : (par1World
						.getBlock(par2, par3,
								par4 - 1) == this ? true : par1World
						.getBlock(par2, par3,
								par4 + 1) == this)));
	}

	public void onNeighborBlockChange(World par1World, int par2,
			int par3, int par4, Block par5Block) {
		super.onNeighborBlockChange(par1World, par2, par3, par4,
				par5Block);
		TileEntityChestDC tileentitychestdc = (TileEntityChestDC) par1World
				.getTileEntity(par2, par3, par4);

		if (tileentitychestdc != null) {
			tileentitychestdc.updateContainingBlockInfo();
		}
	}

	public void breakBlock(World par1World, int par2, int par3,
			int par4, Block par5Block, int par6) {
		TileEntityChestDC tileentitychestdc = (TileEntityChestDC) par1World
				.getTileEntity(par2, par3, par4);

		if (tileentitychestdc != null) {
			for (int j1 = 0; j1 < tileentitychestdc.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentitychestdc.getStackInSlot(j1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World
							.spawnEntityInWorld(entityitem)) {
						int k1 = this.random.nextInt(21) + 10;

						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						entityitem = new EntityItem(par1World,
								(double) ((float) par2 + f),
								(double) ((float) par3 + f1),
								(double) ((float) par4 + f2),
								new ItemStack(itemstack.getItem(), k1,
										itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random
								.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random
								.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random
								.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound(
									(NBTTagCompound) itemstack.getTagCompound()
											.copy());
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4,
				par5Block, par6);
	}

	 public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5Player, int par6, float par7, float par8, float par9)
	    {
	        if (par1World.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	            IInventory iinventory = this.func_149951_m(par1World, par2, par3, par4);

	            if (iinventory != null)
	            {
	                par5Player.displayGUIChest(iinventory);
	            }

	            return true;
	        }
	}

	public IInventory func_149951_m(World par1World, int par2, int par3, int par4) {
		Object object = (TileEntityChestDC) par1World.getTileEntity(par2, par3, par4);

		if (object == null) {
			return null;
		} else if (par1World.isSideSolid(par2, par3 + 1, par4, DOWN)) {
			return null;
		} else if (isOcelotBlockingDirtchest(par1World, par2, par3, par4)) {
			return null;
		} else if (par1World.getBlock(par2 - 1, par3, par4) == this && (par1World.isSideSolid(par2 - 1, par3 + 1, par4, DOWN) || isOcelotBlockingDirtchest(
				par1World, par2 - 1, par3, par4))) {
			return null;
		} else if (par1World.getBlock(par2 + 1, par3, par4) == this && (par1World.isSideSolid(par2 + 1, par3 + 1, par4, DOWN) || isOcelotBlockingDirtchest(
				par1World, par2 + 1, par3, par4))) {
			return null;
		} else if (par1World.getBlock(par2, par3, par4 - 1) == this && (par1World.isSideSolid(par2, par3 + 1, par4 - 1, DOWN) || isOcelotBlockingDirtchest(
				par1World, par2, par3, par4 - 1))) {
			return null;
		} else if (par1World.getBlock(par2, par3, par4 + 1) == this && (par1World.isSideSolid(par2, par3 + 1, par4 + 1, DOWN) || isOcelotBlockingDirtchest(
						par1World, par2, par3, par4 + 1))) {
			return null;
		} else {
			if (par1World.getBlock(par2 - 1, par3, par4) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(TileEntityChestDC) par1World.getTileEntity(par2 - 1, par3, par4), (IInventory)object);
			}

			if (par1World.getBlock(par2 + 1, par3, par4) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(IInventory) object,
						(TileEntityChestDC) par1World.getTileEntity(par2 + 1, par3, par4));
			}

			if (par1World.getBlock(par2, par3, par4 - 1) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(TileEntityChestDC) par1World.getTileEntity(par2, par3, par4 - 1), (IInventory) object);
			}

			if (par1World.getBlock(par2, par3, par4 + 1) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(IInventory) object,
						(TileEntityChestDC) par1World.getTileEntity(par2, par3, par4 + 1));
			}

			return (IInventory) object;
		}
	}

	 public TileEntity createNewTileEntity(World par1World, int par2)
	    {
	        TileEntityChestDC tileentitychestdc = new TileEntityChestDC();
	        return tileentitychestdc;
	    }


	public static boolean isOcelotBlockingDirtchest(World par0World, int par1,
			int par2, int par3) {
		Iterator iterator = par0World.getEntitiesWithinAABB(
				EntityOcelot.class,
				AxisAlignedBB.getAABBPool().getAABB((double) par1,
						(double) (par2 + 1), (double) par3,
						(double) (par1 + 1), (double) (par2 + 2),
						(double) (par3 + 1))).iterator();
		EntityOcelot entityocelot;

		do {
			if (!iterator.hasNext()) {
				return false;
			}

			EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();
			entityocelot = (EntityOcelot) entityocelot1;
		} while (!entityocelot.isSitting());

		return true;
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World par1World, int par2, int par3,
			int par4, int par5) {
		return Container.calcRedstoneFromInventory(this.func_149951_m(par1World,
				par2, par3, par4));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("dirt");

	}

}
