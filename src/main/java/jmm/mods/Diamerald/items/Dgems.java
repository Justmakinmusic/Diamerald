package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dgems extends Item {


	public Dgems(){
		super();
		maxStackSize=64;
		
	}


	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		String unlocalizedName = this.getUnlocalizedName();
		
		if (unlocalizedName.equals("item.gemDiamerald"))
			this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diameraldgem");
		
		else
			
			if (unlocalizedName.equals("item.gemBlackDiamerald"))
				this.itemIcon = par1IconRegister.registerIcon("Diamerald:blackDiameraldgem");
	}*/


}
