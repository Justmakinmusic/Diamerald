package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class blackRoughgem extends Item {
	
	
	public blackRoughgem(){
		super();
		maxStackSize=64;
		this.setCreativeTab(Diamerald.tabDiamerald);
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:blackRoughgem");
	}

}
