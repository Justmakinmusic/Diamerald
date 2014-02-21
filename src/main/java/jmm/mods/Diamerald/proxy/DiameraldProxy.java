package jmm.mods.Diamerald.proxy;


import jmm.mods.Diamerald.grinder.ContainerGrinder;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DiameraldProxy {

	public void registerRenderInformation()
	{

	}
	
	 public Object getServerGuiElement(int id, EntityPlayer player, World world,
             int x, int y, int z) {
     TileEntity tileEntity = world.getTileEntity(x, y, z);
     if(tileEntity instanceof TileEntityGrinder){
             return new ContainerGrinder(player.inventory, (TileEntityGrinder) tileEntity);
     }
     return null;
}

}
