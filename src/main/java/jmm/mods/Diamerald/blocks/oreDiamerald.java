package jmm.mods.Diamerald.blocks;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class oreDiamerald extends Block {

	public oreDiamerald() {

		super(Material.rock);
		this.setHarvestLevel("pickaxe", 3);
		setHardness(5f).setResistance(10f);

	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("Diamerald:oreDiamerald");
	}

}