package jmm.mods.Diamerald.PacketHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.network.NetworkManager;

public class PacketGrinder extends AbstractPacket {
	
	int grinderBurnTime, x, y, z;
	
	public PacketGrinder(){
		
	}
	
	public PacketGrinder(int grinderBurnTime, int x, int y, int z){
		
		this.grinderBurnTime = grinderBurnTime;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		
		buffer.writeInt(grinderBurnTime);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		
		grinderBurnTime = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub
		System.out.println("ran");
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		System.out.println("ranPacket");
		World world = player.worldObj;
		TileEntity te = world.getTileEntity(x, y, z);
		
		if (te instanceof TileEntityGrinder)
		{			
			TileEntityGrinder teg = (TileEntityGrinder) te;
			
			teg.grinderBurnTime = this.grinderBurnTime;
			
			FMLCommonHandler.instance().getClientToServerNetworkManager().scheduleOutboundPacket(teg.getDescriptionPacket());
		}
	}
}
