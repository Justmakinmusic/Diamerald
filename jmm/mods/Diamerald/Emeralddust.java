package jmm.mods.Diamerald;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class Emeralddust extends Item {
	
	public Emeralddust(int i){
		super(i);
		maxStackSize=64;
		this.setCreativeTab(Diamerald.tabDiamerald);
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("Diamerald:Emeralddust");
	}

}
