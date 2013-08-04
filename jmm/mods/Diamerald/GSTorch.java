package jmm.mods.Diamerald;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlowStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class GSTorch extends BlockGlowStone {

	public GSTorch(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
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

	private boolean canPlaceTorchOn(World par1World, int par2, int par3,
			int par4) {
		if (par1World.doesBlockHaveSolidTopSurface(par2, par3, par4)) {
			return true;
		} else {
			int var5 = par1World.getBlockId(par2, par3, par4);
			return (Block.blocksList[var5] != null && Block.blocksList[var5]
					.canPlaceTorchOnTop(par1World, par2, par3, par4));
		}
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST, true)
				|| par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST,
						true)
				|| par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH,
						true)
				|| par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH,
						true)
				|| canPlaceTorchOn(par1World, par2, par3 - 1, par4);
	}

	public int onBlockPlaced(World par1World, int par2, int par3, int par4,
			int par5, float par6, float par7, float par8, int par9) {
		int j1 = par9;

		if (par5 == 1 && this.canPlaceTorchOn(par1World, par2, par3 - 1, par4)) {
			j1 = 5;
		}

		if (par5 == 2
				&& par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH,
						true)) {
			j1 = 4;
		}

		if (par5 == 3
				&& par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH,
						true)) {
			j1 = 3;
		}

		if (par5 == 4
				&& par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST,
						true)) {
			j1 = 2;
		}

		if (par5 == 5
				&& par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST,
						true)) {
			j1 = 1;
		}

		return j1;
	}

	public int quantityDropped(Random par1Random) {
		return 1;
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

	protected boolean dropTorchIfCantStay(World par1World, int par2, int par3,
			int par4) {
		if (!this.canPlaceBlockAt(par1World, par2, par3, par4)) {
			if (par1World.getBlockId(par2, par3, par4) == this.blockID) {
				this.dropBlockAsItem(par1World, par2, par3, par4,
						par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}

			return false;
		} else {
			return true;
		}
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		this.func_94397_d(par1World, par2, par3, par4, par5);
	}

	protected boolean func_94397_d(World par1World, int par2, int par3,
			int par4, int par5) {
		if (this.dropTorchIfCantStay(par1World, par2, par3, par4)) {
			int i1 = par1World.getBlockMetadata(par2, par3, par4);
			boolean flag = false;

			if (!par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST, true)
					&& i1 == 1) {
				flag = true;
			}

			if (!par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST, true)
					&& i1 == 2) {
				flag = true;
			}

			if (!par1World
					.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH, true)
					&& i1 == 3) {
				flag = true;
			}

			if (!par1World
					.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH, true)
					&& i1 == 4) {
				flag = true;
			}

			if (!this.canPlaceTorchOn(par1World, par2, par3 - 1, par4)
					&& i1 == 5) {
				flag = true;
			}

			if (flag) {
				this.dropBlockAsItem(par1World, par2, par3, par4,
						par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public MovingObjectPosition collisionRayTrace(World par1World, int par2,
			int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
		int var7 = par1World.getBlockMetadata(par2, par3, par4) & 7;
		float var8 = 0.15F;

		if (var7 == 1) {
			this.setBlockBounds(0.0F, 0.2F, 0.5F - var8, var8 * 2.0F, 0.8F,
					0.5F + var8);
		} else if (var7 == 2) {
			this.setBlockBounds(1.0F - var8 * 2.0F, 0.2F, 0.5F - var8, 1.0F,
					0.8F, 0.5F + var8);
		} else if (var7 == 3) {
			this.setBlockBounds(0.5F - var8, 0.2F, 0.0F, 0.5F + var8, 0.8F,
					var8 * 2.0F);
		} else if (var7 == 4) {
			this.setBlockBounds(0.5F - var8, 0.2F, 1.0F - var8 * 2.0F,
					0.5F + var8, 0.8F, 1.0F);
		} else {
			var8 = 0.1F;
			this.setBlockBounds(0.5F - var8, 0.0F, 0.5F - var8, 0.5F + var8,
					0.6F, 0.5F + var8);
		}

		return super.collisionRayTrace(par1World, par2, par3, par4, par5Vec3,
				par6Vec3);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("Diamerald:GStorch");
	}
}
