package jmm.mods.Diamerald.grinder;

import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler 
{
	 @Override
     public Object getServerGuiElement(int id, EntityPlayer player, World world,
                     int x, int y, int z) {
             TileEntity tileEntity = world.getTileEntity(x, y, z);
             if(tileEntity instanceof TileEntityGrinder){
                     return new ContainerGrinder(player.inventory, (TileEntityGrinder) tileEntity);
             }
             return null;
     }

     //returns an instance of the Gui you made earlier
     @Override
     public Object getClientGuiElement(int id, EntityPlayer player, World world,
                     int x, int y, int z) {
             TileEntity tileEntity = world.getTileEntity(x, y, z);
             if(tileEntity instanceof TileEntityGrinder){
                     return new GUIGrinder(player.inventory, (TileEntityGrinder) tileEntity);
             }
             return null;

     }
}
