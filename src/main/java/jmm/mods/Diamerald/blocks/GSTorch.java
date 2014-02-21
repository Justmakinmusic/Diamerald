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

	private boolean canPlaceOn(World par1World, int par2,
			int par3, int par4) {
		if (World.doesBlockHaveSolidTopSurface(par1World, par2, par3,
				par4)) {
			return true;
		} else {
			Block block = par1World.getBlock(par2, par3,
					par4);
			return block.canPlaceTorchOnTop(par1World, par2,
					par3, par4);
		}
	}

	public boolean canPlaceBlockAt(World par1World, int par2,
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
	}

	public int onBlockPlaced(World par1World, int par2,
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
	}

	public int quantityDropped(Random random) {
		return 1;
	}

	 public Item getItemDropped(int par1, Random random, int par3)
	    {
	        return Item.getItemFromBlock(Diamerald.GSTorch);
	    }

	protected boolean dropIfCantStay(World par1World, int par2,
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
	}

	public MovingObjectPosition collisionRayTrace(World par1World,
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
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("Diamerald:GStorch");
	}
}
