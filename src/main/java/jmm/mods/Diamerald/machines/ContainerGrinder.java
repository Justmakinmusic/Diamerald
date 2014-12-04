package jmm.mods.Diamerald.machines;

import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerGrinder extends Container
{
	private TileEntityGrinder tileGrinder;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private int lastItemCookTime;
   
       

    public ContainerGrinder(InventoryPlayer par1InventoryPlayer, TileEntityGrinder par2TileEntityGrinder)
    {
        this.tileGrinder = par2TileEntityGrinder;
        this.addSlotToContainer(new Slot(par2TileEntityGrinder, 0, 56, 34));
        this.addSlotToContainer(new Slot(par2TileEntityGrinder, 1, 8, 52));
        this.addSlotToContainer(new SlotMachine(par1InventoryPlayer.player, par2TileEntityGrinder, 2, 116, 35));
        
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
        icrafting.func_175173_a(this, this.tileGrinder);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileGrinder.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileGrinder.getField(0));
            }
            
            if (this.lastBurnTime != this.tileGrinder.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileGrinder.getField(1));
            }

            if (this.lastItemCookTime != this.tileGrinder.getField(2))
            {
            	icrafting.sendProgressBarUpdate(this, 2, this.tileGrinder.getField(2));
            }
           
        }

        this.lastCookTime = this.tileGrinder.getField(0);
        this.lastBurnTime = this.tileGrinder.getField(1);
        this.lastItemCookTime = this.tileGrinder.getField(2);
    }

	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int value)
    {
        this.tileGrinder.setField(slot, value);
        
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tileGrinder.isUseableByPlayer(par1EntityPlayer);
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
                if (GrinderRecipes.instance().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityGrinder.isItemFuel(itemstack1))
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
