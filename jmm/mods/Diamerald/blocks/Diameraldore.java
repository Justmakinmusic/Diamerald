package jmm.mods.Diamerald.blocks;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Diameraldore extends Block {

	public Diameraldore(int i) {

		super(i, Material.rock);
		this.setCreativeTab(Diamerald.tabDiamerald);
		setHardness(5f).setResistance(10f);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldore");
	}

}