package jmm.mods.Diamerald.machines;

import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GUIDfurnace extends GuiContainer
{
	private static final ResourceLocation dfurnaceGuiTextures = new ResourceLocation("diamerald", "textures/gui/dfurnacegui2.png");
    public TileEntityDfurnace tileDfurnace;
    

    public GUIDfurnace(InventoryPlayer par1InventoryPlayer, TileEntityDfurnace par2TileEntityDfurnace)
    {
        super(new ContainerDfurnace(par1InventoryPlayer, par2TileEntityDfurnace));
        this.tileDfurnace = par2TileEntityDfurnace;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.tileDfurnace.hasCustomInventoryName() ? this.tileDfurnace.getInventoryName() : I18n.format(this.tileDfurnace.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(I18n.format("Diamerald Furnace", new Object[0]), this.xSize / 2 - this.fontRendererObj.getStringWidth("Diamerald Furnace") / 2, 4, 4210752);
        this.fontRendererObj.drawString(s, this.xSize / 6 - this.fontRendererObj.getStringWidth(s) / 6, 28, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(dfurnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        int i2;
        
        if (this.tileDfurnace.isCooking() || this.tileDfurnace.isCooking2())
        {
            i1 = this.tileDfurnace.getItemTimeScaled(12);
            i2 = this.tileDfurnace.getItemTimeScaled2(12);
            this.drawTexturedModalRect(k + 62, l + 52 + 12 - i1, 176, 11 - i1, 16, i1 + 2);
            this.drawTexturedModalRect(k + 62, l + 52 + 12 - i2, 176, 11 - i2, 16, i2 + 2);
        }
      
        if (this.tileDfurnace.isBurning())
        {
        	 i1 = this.tileDfurnace.getBurnTimeRemainingScaled(45);
             this.drawTexturedModalRect(k + 10, l + 36 + 13 - i1, 176, 93 - i1, 12, i1 + 2); // i1 + 2);
        }
        
        i1 = this.tileDfurnace.getCookProgressScaled(24, 0);
        i2 = this.tileDfurnace.getCookProgressScaled(24, 3);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        this.drawTexturedModalRect(k + 79, l + 12, 176, 14, i2 + 1, 16);
        
        //i1 = this.tileDfurnace.getCookProgressScaled2(24, 3);
        //this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}
