package jmm.mods.Diamerald.proxy;

import jmm.mods.Diamerald.machines.GUIGrinder;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class DiameraldClient extends DiameraldProxy {

	@Override
	public void registerRenderInformation() {

	}

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileEntityGrinder) {
			return new GUIGrinder(player.inventory,
					(TileEntityGrinder) tileEntity);
		}
		return null;

	}

}
