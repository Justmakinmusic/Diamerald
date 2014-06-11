package jmm.mods.Diamerald.machines;

import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

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
        //this.addSlotToContainer(new SlotMachine(par1InventoryPlayer.player, par2TileEntityGrinder, 3, 143, 35));
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

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileDfurnace.dfurnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tileDfurnace.dfurnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileDfurnace.itemCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.tileDfurnace.dfurnaceCookTime2);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.tileDfurnace.itemCookTime2);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileDfurnace.dfurnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileDfurnace.dfurnaceCookTime);
            }
            
            if (this.lastCookTime2 != this.tileDfurnace.dfurnaceCookTime2)
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileDfurnace.dfurnaceCookTime2);
            }
            
            if (this.lastBurnTime != this.tileDfurnace.dfurnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileDfurnace.dfurnaceBurnTime);
            }

/*            if (this.lastBurnTime != this.tileGrinder.grinderBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileGrinder.grinderBurnTime);
            }

            if (this.lastItemBurnTime != this.tileGrinder.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileGrinder.currentItemBurnTime);
            }
*/            
            if (this.lastItemCookTime != this.tileDfurnace.itemCookTime)
            {
            	icrafting.sendProgressBarUpdate(this, 0, this.tileDfurnace.itemCookTime);
            }
            
            if (this.lastItemCookTime2 != this.tileDfurnace.itemCookTime2)
            {
            	icrafting.sendProgressBarUpdate(this, 3, this.tileDfurnace.itemCookTime2);
            }
           
        }

        this.lastCookTime = this.tileDfurnace.dfurnaceCookTime;
        this.lastBurnTime = this.tileDfurnace.dfurnaceBurnTime;
        this.lastItemCookTime = this.tileDfurnace.itemCookTime;
        this.lastCookTime2 = this.tileDfurnace.dfurnaceCookTime2;
        this.lastItemCookTime2 = this.tileDfurnace.itemCookTime2;
    }

	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int value)
    {
        if (slot == 0)
        {
            this.tileDfurnace.dfurnaceCookTime = value;
        }

        if (slot == 1)
        {
            this.tileDfurnace.dfurnaceBurnTime = value;
        }
        if (slot == 3)
        {
        	this.tileDfurnace.dfurnaceCookTime2 = value;
        }

        /*if (slot == 2)
        {
            this.tileDfurnace.currentItemBurnTime = value;
        }*/
        
        if (slot == 0)
        {
        	this.tileDfurnace.itemCookTime = value;
        }
        if (slot == 3)
        {
        	this.tileDfurnace.itemCookTime2 = value;
        }
        
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
            if (par2 == 4)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (DfurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (par2 != 1 && par2 != 3)
                {
                    if (DfurnaceRecipes.smelting().getSmeltingResult2(itemstack1) != null)
                    {
                        if (!this.mergeItemStack(itemstack1, 3, 1, false))
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
