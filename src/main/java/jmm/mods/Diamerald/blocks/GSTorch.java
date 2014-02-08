package jmm.mods.Diamerald.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.Random;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GSTorch extends BlockGlowstone {

	public GSTorch(Material i) {
		super(i);
		this.setCreativeTab(Diamerald.tabDiamerald);
		setHardness(0.0f).setResistance(1.0f);
		setLightLevel(1.0f);
		
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_,
			int p_149668_3_, int p_149668_4_) {
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

	private boolean func_150107_m(World p_150107_1_, int p_150107_2_,
			int p_150107_3_, int p_150107_4_) {
		if (World.doesBlockHaveSolidTopSurface(p_150107_1_, p_150107_2_, p_150107_3_,
				p_150107_4_)) {
			return true;
		} else {
			Block block = p_150107_1_.getBlock(p_150107_2_, p_150107_3_,
					p_150107_4_);
			return block.canPlaceTorchOnTop(p_150107_1_, p_150107_2_,
					p_150107_3_, p_150107_4_);
		}
	}

	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_,
			int p_149742_3_, int p_149742_4_) {
		return p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_,
				p_149742_4_, EAST, true)
				|| p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_,
						p_149742_4_, WEST, true)
				|| p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_,
						p_149742_4_ - 1, SOUTH, true)
				|| p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_,
						p_149742_4_ + 1, NORTH, true)
				|| func_150107_m(p_149742_1_, p_149742_2_, p_149742_3_ - 1,
						p_149742_4_);
	}

	public int onBlockPlaced(World p_149660_1_, int p_149660_2_,
			int p_149660_3_, int p_149660_4_, int p_149660_5_,
			float p_149660_6_, float p_149660_7_, float p_149660_8_,
			int p_149660_9_) {
		int j1 = p_149660_9_;

		if (p_149660_5_ == 1
				&& this.func_150107_m(p_149660_1_, p_149660_2_,
						p_149660_3_ - 1, p_149660_4_)) {
			j1 = 5;
		}

		if (p_149660_5_ == 2
				&& p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_,
						p_149660_4_ + 1, NORTH, true)) {
			j1 = 4;
		}

		if (p_149660_5_ == 3
				&& p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_,
						p_149660_4_ - 1, SOUTH, true)) {
			j1 = 3;
		}

		if (p_149660_5_ == 4
				&& p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_,
						p_149660_4_, WEST, true)) {
			j1 = 2;
		}

		if (p_149660_5_ == 5
				&& p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_,
						p_149660_4_, EAST, true)) {
			j1 = 1;
		}

		return j1;
	}

	public int quantityDropped(Random p_149745_1_) {
		return 1;
	}

	 public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	    {
	        return Item.getItemFromBlock(Diamerald.GSTorch);
	    }

	protected boolean func_150109_e(World p_150109_1_, int p_150109_2_,
			int p_150109_3_, int p_150109_4_) {
		if (!this.canPlaceBlockAt(p_150109_1_, p_150109_2_, p_150109_3_,
				p_150109_4_)) {
			if (p_150109_1_
					.getBlock(p_150109_2_, p_150109_3_, p_150109_4_) == this) {
				this.dropBlockAsItem(p_150109_1_, p_150109_2_, p_150109_3_,
						p_150109_4_, p_150109_1_.getBlockMetadata(p_150109_2_,
								p_150109_3_, p_150109_4_), 0);
				p_150109_1_
						.setBlockToAir(p_150109_2_, p_150109_3_, p_150109_4_);
			}

			return false;
		} else {
			return true;
		}
	}

	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_,
			int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
		this.func_150108_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_,
				p_149695_5_);
	}

	protected boolean func_150108_b(World p_150108_1_, int p_150108_2_,
			int p_150108_3_, int p_150108_4_, Block p_150108_5_) {
		if (this.func_150109_e(p_150108_1_, p_150108_2_, p_150108_3_,
				p_150108_4_)) {
			int l = p_150108_1_.getBlockMetadata(p_150108_2_, p_150108_3_,
					p_150108_4_);
			boolean flag = false;

			if (!p_150108_1_.isSideSolid(p_150108_2_ - 1, p_150108_3_,
					p_150108_4_, EAST, true) && l == 1) {
				flag = true;
			}

			if (!p_150108_1_.isSideSolid(p_150108_2_ + 1, p_150108_3_,
					p_150108_4_, WEST, true) && l == 2) {
				flag = true;
			}

			if (!p_150108_1_.isSideSolid(p_150108_2_, p_150108_3_,
					p_150108_4_ - 1, SOUTH, true) && l == 3) {
				flag = true;
			}

			if (!p_150108_1_.isSideSolid(p_150108_2_, p_150108_3_,
					p_150108_4_ + 1, NORTH, true) && l == 4) {
				flag = true;
			}

			if (!this.func_150107_m(p_150108_1_, p_150108_2_, p_150108_3_ - 1,
					p_150108_4_) && l == 5) {
				flag = true;
			}

			if (flag) {
				this.dropBlockAsItem(p_150108_1_, p_150108_2_, p_150108_3_,
						p_150108_4_, p_150108_1_.getBlockMetadata(p_150108_2_,
								p_150108_3_, p_150108_4_), 0);
				p_150108_1_
						.setBlockToAir(p_150108_2_, p_150108_3_, p_150108_4_);
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public MovingObjectPosition collisionRayTrace(World p_149731_1_,
			int p_149731_2_, int p_149731_3_, int p_149731_4_,
			Vec3 p_149731_5_, Vec3 p_149731_6_) {
		int l = p_149731_1_.getBlockMetadata(p_149731_2_, p_149731_3_,
				p_149731_4_) & 7;
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

		return super.collisionRayTrace(p_149731_1_, p_149731_2_, p_149731_3_,
				p_149731_4_, p_149731_5_, p_149731_6_);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("Diamerald:GStorch");
	}
}
