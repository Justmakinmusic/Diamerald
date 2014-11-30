package jmm.mods.Diamerald.items;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Ddusts extends Item{
	
	public Ddusts(){
		super();
		maxStackSize=64;
		
	}


	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		String unlocalizedName = this.getUnlocalizedName();
		
		if (unlocalizedName.equals("item.dustDiamerald"))
			this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diameralddust");
		
		else
			
			if (unlocalizedName.equals("item.dustGold"))
				this.itemIcon = par1IconRegister.registerIcon("Diamerald:DustGold");
		
			else
				
				if (unlocalizedName.equals("item.dustIron"))
					this.itemIcon = par1IconRegister.registerIcon("Diamerald:DustIron");
		
				else
					
					if (unlocalizedName.equals("item.dustEmerald"))
						this.itemIcon = par1IconRegister.registerIcon("Diamerald:Emeralddust");
		
					else
						
						if (unlocalizedName.equals("item.dustEmeraldTiny"))
							this.itemIcon = par1IconRegister.registerIcon("Diamerald:EmeralddustTiny");
						else
							
							if (unlocalizedName.equals("item.berylSlag"))
								this.itemIcon = par1IconRegister.registerIcon("Diamerald:berylSlag");
		
							else
								
								if (unlocalizedName.equals("item.dustBlackDiamerald"))
									this.itemIcon = par1IconRegister.registerIcon("Diamerald:blackDiameralddust");
		
								else
									
									if (unlocalizedName.equals("item.dustDiamond"))
										this.itemIcon = par1IconRegister.registerIcon("Diamerald:Diamonddust");
						
	}*/

}
