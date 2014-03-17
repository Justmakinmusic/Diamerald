package jmm.mods.Diamerald.grinder;

import jmm.mods.Diamerald.Diamerald;
import jmm.mods.Diamerald.packethandler.PacketGrinder;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;


public class GUIGrinder extends GuiContainer
{
	private static final ResourceLocation grinderGuiTextures = new ResourceLocation("diamerald", "textures/gui/grindergui.png");
    public TileEntityGrinder tileGrinder;
    

    public GUIGrinder(InventoryPlayer par1InventoryPlayer, TileEntityGrinder par2TileEntityGrinder)
    {
        super(new ContainerGrinder(par1InventoryPlayer, par2TileEntityGrinder));
        this.tileGrinder = par2TileEntityGrinder;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.tileGrinder.hasCustomInventoryName() ? this.tileGrinder.getInventoryName() : I18n.format(this.tileGrinder.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(I18n.format("Grinder", new Object[0]), this.xSize / 2 - this.fontRendererObj.getStringWidth("Grinder") / 2, 6, 4210752);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 15, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(grinderGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        
        if (this.tileGrinder.isGrinding())
        {
            i1 = this.tileGrinder.getItemTimeScaled(12);
            this.drawTexturedModalRect(k + 62, l + 52 + 12 - i1, 176, 11 - i1, 16, i1 + 2);
        }
      
        if (this.tileGrinder.isBurning())
        {
        	 i1 = this.tileGrinder.getBurnTimeRemainingScaled(45);
             this.drawTexturedModalRect(k + 10, l + 36 + 13 - i1, 176, 93 - i1, 12, i1 + 2); // i1 + 2);
             //Diamerald.packetPipeline.sendToAll(new PacketGrinder(tileGrinder.grinderBurnTime, tileGrinder.xCoord, tileGrinder.yCoord, tileGrinder.zCoord));
        }

        i1 = this.tileGrinder.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        
        //Diamerald.packetPipeline.sendToServer(new PacketGrinder(tileGrinder.grinderBurnTime, tileGrinder.xCoord, tileGrinder.yCoord, tileGrinder.zCoord));
    }
}
