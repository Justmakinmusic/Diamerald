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
		
		if (unlocalizedName.equals("item.dust_Diamerald"))
			this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diameralddust");
		
		else
			
			if (unlocalizedName.equals("item.dust_Gold"))
				this.itemIcon = par1IconRegister.registerIcon("Diamerald:DustGold");
		
			else
				
				if (unlocalizedName.equals("item.dust_Iron"))
					this.itemIcon = par1IconRegister.registerIcon("Diamerald:DustIron");
		
				else
					
					if (unlocalizedName.equals("item.dust_Emerald"))
						this.itemIcon = par1IconRegister.registerIcon("Diamerald:Emeralddust");
		
					else
						
						if (unlocalizedName.equals("item.dust_EmeraldTiny"))
							this.itemIcon = par1IconRegister.registerIcon("Diamerald:EmeralddustTiny");
						else
							
							if (unlocalizedName.equals("item.berylSlag"))
								this.itemIcon = par1IconRegister.registerIcon("Diamerald:berylSlag");
		
							else
								
								if (unlocalizedName.equals("item.dust_BlackDiamerald"))
									this.itemIcon = par1IconRegister.registerIcon("Diamerald:blackDiameralddust");
		
								else
									
									if (unlocalizedName.equals("item.dust_Diamond"))
										this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diamonddust");
						
	}

}
