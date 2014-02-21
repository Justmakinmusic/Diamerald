package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Ddusts extends Item{
	
	public Ddusts(){
		super();
		maxStackSize=64;
		
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		String unlocalizedName = this.getUnlocalizedName();
		
		if (unlocalizedName.equals("item.Diameralddust"))
			this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diameralddust");
		
		else
			
			if (unlocalizedName.equals("item.Golddust"))
				this.itemIcon = par1IconRegister.registerIcon("Diamerald:DustGold");
		
			else
				
				if (unlocalizedName.equals("item.Irondust"))
					this.itemIcon = par1IconRegister.registerIcon("Diamerald:DustIron");
		
				else
					
					if (unlocalizedName.equals("item.Emeralddust"))
						this.itemIcon = par1IconRegister.registerIcon("Diamerald:Emeralddust");
		
					else
						
						if (unlocalizedName.equals("item.EmeralddustTiny"))
							this.itemIcon = par1IconRegister.registerIcon("Diamerald:EmeralddustTiny");
				
				
			
		
	}

}
