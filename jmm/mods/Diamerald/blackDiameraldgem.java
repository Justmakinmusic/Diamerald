package jmm.mods.Diamerald;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class blackDiameraldgem extends Item {
	
	
	public blackDiameraldgem(int i){
		super(i);
		maxStackSize=64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:blackDiameraldgem");
	}

}
