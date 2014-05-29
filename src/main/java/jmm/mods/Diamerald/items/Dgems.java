package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Dgems extends Item {


	public Dgems(){
		super();
		maxStackSize=64;
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		String unlocalizedName = this.getUnlocalizedName();
		
		if (unlocalizedName.equals("item.Diameraldgem"))
			this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diameraldgem");
		
		else
			
			if (unlocalizedName.equals("item.blackDiameraldgem"))
				this.itemIcon = par1IconRegister.registerIcon("Diamerald:blackDiameraldgem");
		
			/*else
				
				if (unlocalizedName.equals("item.Roughgem"))
					this.itemIcon = par1IconRegister.registerIcon("Diamerald:Roughgem");
		
				else
					
					if (unlocalizedName.equals("item.blackRoughgem"))
						this.itemIcon = par1IconRegister.registerIcon("Diamerald:blackRoughgem");
		*/
	}


}
