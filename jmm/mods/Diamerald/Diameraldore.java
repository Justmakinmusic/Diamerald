package jmm.mods.Diamerald;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class Diameraldore extends Block {

	public Diameraldore(int i) {

		super(i, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		setHardness(5f).setResistance(10f);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("Diamerald:Diameraldore");
	}

}