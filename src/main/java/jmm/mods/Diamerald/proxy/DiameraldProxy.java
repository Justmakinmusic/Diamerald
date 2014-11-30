package jmm.mods.Diamerald.proxy;

import jmm.mods.Diamerald.machines.ContainerGrinder;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class DiameraldProxy {

	public void registerRenderInformation() {

	}

	public World getClientWorld() {
		return null;
	}

	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileEntityGrinder) {
			return new ContainerGrinder(player.inventory,
					(TileEntityGrinder) tileEntity);
		}
		return null;
	}

}
