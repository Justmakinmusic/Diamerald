package jmm.mods.Diamerald.blocks;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import jmm.mods.Diamerald.Diamerald;
import jmm.mods.Diamerald.tedc.TileEntityChestDC;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDirtchest extends BlockContainer {

	private final Random random = new Random();
	public final int field_149956_a;

	public BlockDirtchest(int i, Material material) {
		super(material);
		this.field_149956_a = i;
		setCreativeTab(Diamerald.tabDiamerald);
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

	public void onBlockAdded(World p_149726_1_, int p_149726_2_,
			int p_149726_3_, int p_149726_4_) {
		super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
		this.unifyAdjacentDirtchests(p_149726_1_, p_149726_2_, p_149726_3_,
				p_149726_4_);
		Block block = p_149726_1_.getBlock(p_149726_2_, p_149726_3_,
				p_149726_4_ - 1);
		Block block1 = p_149726_1_.getBlock(p_149726_2_, p_149726_3_,
				p_149726_4_ + 1);
		Block block2 = p_149726_1_.getBlock(p_149726_2_ - 1, p_149726_3_,
				p_149726_4_);
		Block block3 = p_149726_1_.getBlock(p_149726_2_ + 1, p_149726_3_,
				p_149726_4_);

		if (block == this) {
			this.unifyAdjacentDirtchests(p_149726_1_, p_149726_2_, p_149726_3_,
					p_149726_4_ - 1);
		}

		if (block1 == this) {
			this.unifyAdjacentDirtchests(p_149726_1_, p_149726_2_, p_149726_3_,
					p_149726_4_ + 1);
		}

		if (block2 == this) {
			this.unifyAdjacentDirtchests(p_149726_1_, p_149726_2_ - 1,
					p_149726_3_, p_149726_4_);
		}

		if (block3 == this) {
			this.unifyAdjacentDirtchests(p_149726_1_, p_149726_2_ + 1,
					p_149726_3_, p_149726_4_);
		}
	}

	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_,
			int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_,
			ItemStack p_149689_6_) {
		Block block = p_149689_1_.getBlock(p_149689_2_, p_149689_3_,
				p_149689_4_ - 1);
		Block block1 = p_149689_1_.getBlock(p_149689_2_, p_149689_3_,
				p_149689_4_ + 1);
		Block block2 = p_149689_1_.getBlock(p_149689_2_ - 1, p_149689_3_,
				p_149689_4_);
		Block block3 = p_149689_1_.getBlock(p_149689_2_ + 1, p_149689_3_,
				p_149689_4_);
		byte b0 = 0;
		int l = MathHelper
				.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

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
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_,
					p_149689_4_, b0, 3);
		} else {
			if ((block == this || block1 == this) && (b0 == 4 || b0 == 5)) {
				if (block == this) {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_,
							p_149689_3_, p_149689_4_ - 1, b0, 3);
				} else {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_,
							p_149689_3_, p_149689_4_ + 1, b0, 3);
				}

				p_149689_1_.setBlockMetadataWithNotify(p_149689_2_,
						p_149689_3_, p_149689_4_, b0, 3);
			}

			if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3)) {
				if (block2 == this) {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_ - 1,
							p_149689_3_, p_149689_4_, b0, 3);
				} else {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_ + 1,
							p_149689_3_, p_149689_4_, b0, 3);
				}

				p_149689_1_.setBlockMetadataWithNotify(p_149689_2_,
						p_149689_3_, p_149689_4_, b0, 3);
			}
		}

		if (p_149689_6_.hasDisplayName()) {
			((TileEntityChestDC) p_149689_1_.getTileEntity(p_149689_2_,
					p_149689_3_, p_149689_4_)).func_145976_a(p_149689_6_
					.getDisplayName());
		}
	}

	public void unifyAdjacentDirtchests(World p_149954_1_, int p_149954_2_,
			int p_149954_3_, int p_149954_4_) {
		if (!p_149954_1_.isRemote) {
			Block block = p_149954_1_.getBlock(p_149954_2_, p_149954_3_,
					p_149954_4_ - 1);
			Block block1 = p_149954_1_.getBlock(p_149954_2_, p_149954_3_,
					p_149954_4_ + 1);
			Block block2 = p_149954_1_.getBlock(p_149954_2_ - 1,
					p_149954_3_, p_149954_4_);
			Block block3 = p_149954_1_.getBlock(p_149954_2_ + 1,
					p_149954_3_, p_149954_4_);
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
					l = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
					block4 = p_149954_1_.getBlock(l, p_149954_3_,
							p_149954_4_ - 1);
					i1 = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
					block5 = p_149954_1_.getBlock(i1, p_149954_3_,
							p_149954_4_ + 1);
					b0 = 3;
					flag1 = true;

					if (block2 == this) {
						j1 = p_149954_1_.getBlockMetadata(p_149954_2_ - 1,
								p_149954_3_, p_149954_4_);
					} else {
						j1 = p_149954_1_.getBlockMetadata(p_149954_2_ + 1,
								p_149954_3_, p_149954_4_);
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
				l = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
				block4 = p_149954_1_.getBlock(p_149954_2_ - 1,
						p_149954_3_, l);
				i1 = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
				block5 = p_149954_1_.getBlock(p_149954_2_ + 1,
						p_149954_3_, i1);
				b0 = 5;
				flag1 = true;

				if (block == this) {
					j1 = p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_,
							p_149954_4_ - 1);
				} else {
					j1 = p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_,
							p_149954_4_ + 1);
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

			p_149954_1_.setBlockMetadataWithNotify(p_149954_2_, p_149954_3_,
					p_149954_4_, b0, 3);
		}
	}

	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_,
			int p_149742_3_, int p_149742_4_) {
		int l = 0;

		if (p_149742_1_
				.getBlock(p_149742_2_ - 1, p_149742_3_, p_149742_4_) == this) {
			++l;
		}

		if (p_149742_1_
				.getBlock(p_149742_2_ + 1, p_149742_3_, p_149742_4_) == this) {
			++l;
		}

		if (p_149742_1_
				.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ - 1) == this) {
			++l;
		}

		if (p_149742_1_
				.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ + 1) == this) {
			++l;
		}

		return l > 1 ? false : (this.isThereANeighborDirtchest(p_149742_1_,
				p_149742_2_ - 1, p_149742_3_, p_149742_4_) ? false : (this
				.isThereANeighborDirtchest(p_149742_1_, p_149742_2_ + 1,
						p_149742_3_, p_149742_4_) ? false : (this
				.isThereANeighborDirtchest(p_149742_1_, p_149742_2_,
						p_149742_3_, p_149742_4_ - 1) ? false : !this
				.isThereANeighborDirtchest(p_149742_1_, p_149742_2_,
						p_149742_3_, p_149742_4_ + 1))));
	}

	private boolean isThereANeighborDirtchest(World p_149952_1_,
			int p_149952_2_, int p_149952_3_, int p_149952_4_) {
		return p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_) != this ? false
				: (p_149952_1_.getBlock(p_149952_2_ - 1, p_149952_3_,
						p_149952_4_) == this ? true : (p_149952_1_
						.getBlock(p_149952_2_ + 1, p_149952_3_,
								p_149952_4_) == this ? true : (p_149952_1_
						.getBlock(p_149952_2_, p_149952_3_,
								p_149952_4_ - 1) == this ? true : p_149952_1_
						.getBlock(p_149952_2_, p_149952_3_,
								p_149952_4_ + 1) == this)));
	}

	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_,
			int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_,
				p_149695_5_);
		TileEntityChestDC tileentitychestdc = (TileEntityChestDC) p_149695_1_
				.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);

		if (tileentitychestdc != null) {
			tileentitychestdc.updateContainingBlockInfo();
		}
	}

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_,
			int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		TileEntityChestDC tileentitychestdc = (TileEntityChestDC) p_149749_1_
				.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

		if (tileentitychestdc != null) {
			for (int j1 = 0; j1 < tileentitychestdc.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentitychestdc.getStackInSlot(j1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_
							.spawnEntityInWorld(entityitem)) {
						int k1 = this.random.nextInt(21) + 10;

						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						entityitem = new EntityItem(p_149749_1_,
								(double) ((float) p_149749_2_ + f),
								(double) ((float) p_149749_3_ + f1),
								(double) ((float) p_149749_4_ + f2),
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

		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_,
				p_149749_5_, p_149749_6_);
	}

	 public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	    {
	        if (p_149727_1_.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	            IInventory iinventory = this.func_149951_m(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);

	            if (iinventory != null)
	            {
	                p_149727_5_.displayGUIChest(iinventory);
	            }

	            return true;
	        }
	}

	public IInventory func_149951_m(World p_149951_1_, int p_149951_2_, int p_149951_3_, int p_149951_4_) {
		Object object = (TileEntityChestDC) p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_);

		if (object == null) {
			return null;
		} else if (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_, DOWN)) {
			return null;
		} else if (isOcelotBlockingDirtchest(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_)) {
			return null;
		} else if (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.isSideSolid(p_149951_2_ - 1, p_149951_3_ + 1, p_149951_4_, DOWN) || isOcelotBlockingDirtchest(
				p_149951_1_, p_149951_2_ - 1, p_149951_3_, p_149951_4_))) {
			return null;
		} else if (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.isSideSolid(p_149951_2_ + 1, p_149951_3_ + 1, p_149951_4_, DOWN) || isOcelotBlockingDirtchest(
				p_149951_1_, p_149951_2_ + 1, p_149951_3_, p_149951_4_))) {
			return null;
		} else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this && (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ - 1, DOWN) || isOcelotBlockingDirtchest(
				p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ - 1))) {
			return null;
		} else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this && (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ + 1, DOWN) || isOcelotBlockingDirtchest(
						p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ + 1))) {
			return null;
		} else {
			if (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(TileEntityChestDC) p_149951_1_.getTileEntity(p_149951_2_ - 1, p_149951_3_, p_149951_4_), (IInventory)object);
			}

			if (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(IInventory) object,
						(TileEntityChestDC) p_149951_1_.getTileEntity(p_149951_2_ + 1, p_149951_3_, p_149951_4_));
			}

			if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(TileEntityChestDC) p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ - 1), (IInventory) object);
			}

			if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this) {
				object = new InventoryLargeChest("Large Dirtchest",
						(IInventory) object,
						(TileEntityChestDC) p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ + 1));
			}

			return (IInventory) object;
		}
	}

	 public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
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
