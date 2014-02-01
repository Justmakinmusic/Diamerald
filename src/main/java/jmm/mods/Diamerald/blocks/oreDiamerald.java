package jmm.mods.Diamerald.blocks;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class oreDiamerald extends Block {

	public oreDiamerald(int i) {

		super(Material.field_151576_e);
		this.setHarvestLevel("pickaxe", 3);
		this.func_149647_a(Diamerald.tabDiamerald);
		func_149711_c(5f).func_149752_b(10f);

	}

	@SideOnly(Side.CLIENT)
	public void func_149651_a(IIconRegister p_149651_1_) {
		this.field_149761_L = p_149651_1_
				.registerIcon("Diamerald:oreDiamerald");
	}

}