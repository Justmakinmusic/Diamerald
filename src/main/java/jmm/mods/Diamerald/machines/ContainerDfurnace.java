package jmm.mods.Diamerald.machines;

import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerDfurnace extends Container {

	private TileEntityDfurnace tileDfurnace;
    private int lastCookTime;
    private int lastCookTime2;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private int lastItemCookTime;
    private int lastItemCookTime2;
   
       
    public ContainerDfurnace(InventoryPlayer par1InventoryPlayer, TileEntityDfurnace par2TileEntityDfurnace)
    {
        this.tileDfurnace = par2TileEntityDfurnace;
        this.addSlotToContainer(new Slot(par2TileEntityDfurnace, 0, 56, 34));
        this.addSlotToContainer(new Slot(par2TileEntityDfurnace, 1, 8, 52));
        this.addSlotToContainer(new SlotMachine(par1InventoryPlayer.player, par2TileEntityDfurnace, 2, 116, 35));
        this.addSlotToContainer(new Slot(par2TileEntityDfurnace, 3, 56, 13));
        this.addSlotToContainer(new SlotMachine(par1InventoryPlayer.player, par2TileEntityDfurnace, 4, 147, 35));
        int i;
        
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }
    
    public void onCraftGuiOpened(ICrafting icrafting)
    {
        super.onCraftGuiOpened(icrafting);
        icrafting.func_175173_a(this, this.tileDfurnace);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileDfurnace.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileDfurnace.getField(0));
            }
            
            if (this.lastCookTime2 != this.tileDfurnace.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileDfurnace.getField(3));
            }
            
            if (this.lastBurnTime != this.tileDfurnace.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileDfurnace.getField(1));
            }
            
            if (this.lastItemCookTime != this.tileDfurnace.getField(2))
            {
            	icrafting.sendProgressBarUpdate(this, 2, this.tileDfurnace.getField(2));
            }
            
            if (this.lastItemCookTime2 != this.tileDfurnace.getField(4))
            {
            	icrafting.sendProgressBarUpdate(this, 4, this.tileDfurnace.getField(4));
            }
           
        }

        this.lastCookTime = this.tileDfurnace.getField(0);
        this.lastBurnTime = this.tileDfurnace.getField(1);
        this.lastItemCookTime = this.tileDfurnace.getField(2);
        this.lastCookTime2 = this.tileDfurnace.getField(3);
        this.lastItemCookTime2 = this.tileDfurnace.getField(4);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int value)
    {
        this.tileDfurnace.setField(slot, value);        
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tileDfurnace.isUseableByPlayer(par1EntityPlayer);
    }
    
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
           
            else if (par2 != 1 && par2 != 0)
            {
                if (DfurnaceRecipes.instance().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }                
                
                else if (TileEntityDfurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        
        return itemstack;
    }   
}