package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class berylSlag extends Item {

	public berylSlag(int i) {
		super(i);
		maxStackSize = 64;
		this.setCreativeTab(Diamerald.tabDiamerald);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:berylSlag");
	}

}
