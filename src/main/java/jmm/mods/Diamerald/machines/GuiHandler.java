package jmm.mods.Diamerald.machines;

import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	// returns an instance of container for server
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileEntityGrinder) {
			return new ContainerGrinder(player.inventory,
					(TileEntityGrinder) tileEntity);
		}

		if (tileEntity instanceof TileEntityDfurnace) {
			return new ContainerDfurnace(player.inventory,
					(TileEntityDfurnace) tileEntity);
		}
		return null;
	}

	// returns an instance of the Gui you made earlier
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileEntityGrinder) {
			return new GUIGrinder(player.inventory,
					(TileEntityGrinder) tileEntity);
		}

		if (tileEntity instanceof TileEntityDfurnace) {
			return new GUIDfurnace(player.inventory,
					(TileEntityDfurnace) tileEntity);
		}
		return null;
	}

}
