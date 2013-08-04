package jmm.mods.Diamerald;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class Diameraldpickaxe extends ItemPickaxe {

	public static final Block[] blocksEffectiveAgainst = new Block[] {
			Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab,
			Block.stone, Block.sandStone, Block.cobblestoneMossy,
			Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold,
			Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice,
			Block.netherrack, Block.oreLapis, Block.blockLapis,
			Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail,
			Block.railDetector, Block.railPowered };

	public Diameraldpickaxe(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);

	}

	public boolean canHarvestBlock(Block par1Block) {
		return par1Block == Block.obsidian ? this.toolMaterial
				.getHarvestLevel() == 3
				: (par1Block != Block.blockDiamond
						&& par1Block != Block.oreDiamond ? (par1Block != Block.oreEmerald
						&& par1Block != Block.blockEmerald ? (par1Block != Block.blockGold
						&& par1Block != Block.oreGold ? (par1Block != Block.blockIron
						&& par1Block != Block.oreIron ? (par1Block != Block.blockLapis
						&& par1Block != Block.oreLapis ? (par1Block != Block.oreRedstone
						&& par1Block != Block.oreRedstoneGlowing ? (par1Block.blockMaterial == Material.rock ? true
						: (par1Block.blockMaterial == Material.iron ? true
								: par1Block.blockMaterial == Material.anvil))
						: this.toolMaterial.getHarvestLevel() >= 2)
						: this.toolMaterial.getHarvestLevel() >= 1)
						: this.toolMaterial.getHarvestLevel() >= 1)
						: this.toolMaterial.getHarvestLevel() >= 2)
						: this.toolMaterial.getHarvestLevel() >= 2)
						: this.toolMaterial.getHarvestLevel() >= 2);
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return par2Block != null
				&& (par2Block.blockMaterial == Material.iron
						|| par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial
				: super.getStrVsBlock(par1ItemStack, par2Block);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return  par2ItemStack.itemID == Diamerald.Diameraldgem.itemID;
				
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldpick");

	}

}
