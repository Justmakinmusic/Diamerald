package jmm.mods.Diamerald.tileentity;

import jmm.mods.Diamerald.blocks.Grinder;
import jmm.mods.Diamerald.grinder.GrinderRecipes;
import jmm.mods.Diamerald.sounds.GrinderSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGrinder extends TileEntity implements ISidedInventory {
	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1, 2 };
	private ItemStack[] grinderItemStacks = new ItemStack[4];
	private static final int GRINDER_MAX_FUEL = 60000;
	public int grinderBurnTime;
	public int currentItemBurnTime;
	public int grinderCookTime;
	public int itemCookTime;
	private String name;
//	private static SoundHandler sndHandler = FMLClientHandler.instance().getClient().getSoundHandler();
	private boolean soundIsOn = false;
//	private GrinderSound sound = null;

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory() 
	{
		return this.grinderItemStacks.length;
	}

	/**
	 * Returns the stack in slot i
	 */
	public ItemStack getStackInSlot(int par1) 
	{
		return this.grinderItemStacks[par1];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number
	 * (second arg) of items and returns them in a new stack.
	 */
	public ItemStack decrStackSize(int par1, int par2) 
	{
		if (this.grinderItemStacks[par1] != null) 
		{
			ItemStack itemstack;

			if (this.grinderItemStacks[par1].stackSize <= par2) 
			{
				itemstack = this.grinderItemStacks[par1];
				this.grinderItemStacks[par1] = null;
				return itemstack;
			} 
			else 
			{
				itemstack = this.grinderItemStacks[par1].splitStack(par2);

				if (this.grinderItemStacks[par1].stackSize == 0) 
				{
					this.grinderItemStacks[par1] = null;
				}

				return itemstack;
			}
		} 
		else 
		{
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench
	 * GUI.
	 */
	public ItemStack getStackInSlotOnClosing(int par1) 
	{
		if (this.grinderItemStacks[par1] != null) 
		{
			ItemStack itemstack = this.grinderItemStacks[par1];
			this.grinderItemStacks[par1] = null;
			return itemstack;
		} 
		else 
		{
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) 
	{
		this.grinderItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null
				&& par2ItemStack.stackSize > this.getInventoryStackLimit()) 
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory
	 */
	public String getInventoryName()
	{
//		return Integer.toString(this.grinderBurnTime);
		return this.hasCustomInventoryName() ? this.name : "Grinder";
	}

	/**
	 * Returns if the inventory is named
	 */
	public boolean hasCustomInventoryName() 
	{
		return this.name != null && this.name.length() > 0;
	}

	public void setGuiDisplayName(String par1Str) 
	{
		this.name = par1Str;
	}

	public void readFromNBT(NBTTagCompound tileEntityTag) 
	{
		super.readFromNBT(tileEntityTag);
		NBTTagList nbttaglist = tileEntityTag.getTagList("Items", 10);
		this.grinderItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) 
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.grinderItemStacks.length) 
			{
				this.grinderItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.grinderBurnTime = tileEntityTag.getShort("BurnTime");
		this.grinderCookTime = tileEntityTag.getShort("CookTime");
		this.currentItemBurnTime = this.grinderBurnTime; // getItemBurnTime(this.grinderItemStacks[1]);
		
		 // Read info for GrinderSound location
//    	NBTTagCompound soundTag = tileEntityTag.getCompoundTag("GrinderSound");
//    	this.soundIsOn = tileEntityTag.getBoolean("SoundIsOn");
//    	sound = new GrinderSound(0,0,0);
//    	sound.readFromNBT(soundTag);

		if (tileEntityTag.hasKey("CustomName", 8)) 
		{
			this.name = tileEntityTag.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound tileEntityTag) 
	{
		super.writeToNBT(tileEntityTag);
		tileEntityTag.setShort("BurnTime", (short) this.grinderBurnTime);
		tileEntityTag.setShort("CookTime", (short) this.grinderCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.grinderItemStacks.length; ++i) 
		{
			if (this.grinderItemStacks[i] != null) 
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.grinderItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tileEntityTag.setTag("Items", nbttaglist);
		
		// Write info for GrinderSound location
//    	NBTTagCompound soundTag = new NBTTagCompound();
//    	sound.writeToNBT(soundTag);	        
//    	tileEntityTag.setTag("GrinderSound", soundTag);
//    	tileEntityTag.setBoolean("SoundIsOn",false);

		if (this.hasCustomInventoryName()) 
		{
			tileEntityTag.setString("CustomName", this.name);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how close
	 * the current item is to being completely cooked
	 */
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) 
	{
		return this.grinderCookTime * par1 / 650;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how much
	 * burn time is left on the current fuel item, where 0 means that the item
	 * is exhausted and the passed value means that the item is fresh
	 */
	@SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1)
    {
        //if (this.currentItemBurnTime == 0)
        //{
        //    this.currentItemBurnTime = 800;
        //}

        return (int)(this.grinderBurnTime * (double)par1 / GRINDER_MAX_FUEL); //this.currentItemBurnTime;
    }
	
	@SideOnly(Side.CLIENT)
	public int getItemTimeScaled(int par1)
	{
		return this.itemCookTime;
	}
	
	/**
	 * Furnace isBurning
	 */
	public boolean isBurning() 
	{
		return this.grinderBurnTime > 0;
	}

	// startSound being tested against worldObj.playSoundEffect//
/*    
	public void startSound() 
	{

		if (!this.worldObj.isRemote) 
		{

			if (!sndHandler.isSoundPlaying(sound)) 
			{
				System.out.println("Playing: "+ sound.getPositionedSoundLocation());
				sndHandler.stopSound(sound);
				sndHandler.playSound(sound);
				soundIsOn = true;
			}
		}
	}

	// stopSound being tested against worldObj.playSoundEffect//
    
	public void stopSound() 
	{
		if (!this.worldObj.isRemote) 
		{
			if (soundIsOn) 
			{
				System.out.println("Stopping: "+ sound.getPositionedSoundLocation());
				sndHandler.stopSound(sound);
				soundIsOn = false;
			}
		}
	}
*/    
	public void updateEntity() 
	{
		boolean flag = this.grinderBurnTime > 0;
		boolean flag1 = false;
		boolean soundWasOn = soundIsOn;
/*		
		if (!this.worldObj.isRemote && sound == null) 
		{
			System.out.println("TileEntityGrinder " + this.toString() + " is");
			sound = new GrinderSound(this.xCoord, this.yCoord, this.zCoord);
		}
*/
		if (!this.worldObj.isRemote && this.grinderItemStacks[0] != null && this.grinderBurnTime > 0 && this.canSmelt()) 
		{
//			this.startSound();
			soundIsOn = true;
			this.worldObj.playSoundEffect((double)this.xCoord + 0.5D,(double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D,"Diamerald:Grindersnd", 0.5F, 0.5F);
			--this.grinderBurnTime;
		}

		else 
		{
			soundIsOn = false;
//			this.stopSound();
		}

		if (!this.worldObj.isRemote) 
		{
			
			if (this.grinderItemStacks[1] != null )
            {
        		int moreFuelToBurn = getItemBurnTime(this.grinderItemStacks[1]);
        		        		
        		if(this.grinderBurnTime + moreFuelToBurn <= GRINDER_MAX_FUEL) {
        			
        			this.currentItemBurnTime = this.grinderBurnTime += moreFuelToBurn;
                
                   --this.grinderItemStacks[1].stackSize;
                
                	if (this.grinderItemStacks[1].stackSize == 0)
                	{
                    	this.grinderItemStacks[1] = grinderItemStacks[1].getItem().getContainerItem(grinderItemStacks[1]);                   
                	}
        		}

            }

			if (this.grinderBurnTime == 0)
			{
				this.currentItemBurnTime = this.grinderBurnTime = getItemBurnTime(this.grinderItemStacks[1]);

				if (this.grinderBurnTime > 0) 
				{
					flag1 = true;

					if (this.grinderItemStacks[1] != null) 
					{
						--this.grinderItemStacks[1].stackSize;

						if (this.grinderItemStacks[1].stackSize == 0) 
						{
							this.grinderItemStacks[1] = grinderItemStacks[1].getItem().getContainerItem(grinderItemStacks[1]);
						}
					}
				}

			}

			if (this.isBurning() && this.canSmelt()) 
			{
				++this.grinderCookTime;

				if (this.grinderCookTime == 599) 
				{
					this.grinderCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} 
			else 
			{
				this.grinderCookTime = 0;
			}

//			if (flag != this.grinderBurnTime > 0)
			if (soundWasOn != soundIsOn) 
			{
				flag1 = true;
				Grinder.updateGrinderBlockState(soundIsOn, this.worldObj,this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1) 
		{
			this.markDirty();
		}

	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	public boolean canSmelt() 
	{
		if (this.grinderItemStacks[0] == null) 
		{
			return false;
		} 
		else 
		{
			ItemStack itemstack = GrinderRecipes.smelting().getSmeltingResult(this.grinderItemStacks[0]);
			
			if (itemstack == null)
				
				return false;
			
			if (this.grinderItemStacks[2] == null)
				
				return true;
			if (!this.grinderItemStacks[2].isItemEqual(itemstack))
				
				return false;
			
			int result = grinderItemStacks[2].stackSize + itemstack.stackSize;
			
			return result <= getInventoryStackLimit()&& result <= this.grinderItemStacks[2].getMaxStackSize();
		}
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted
	 * item in the furnace result stack
	 */
	public void smeltItem() 
	{
		if (this.canSmelt()) 
		{
			ItemStack itemstack = GrinderRecipes.smelting().getSmeltingResult(this.grinderItemStacks[0]);

			if (this.grinderItemStacks[2] == null) 
			{
				this.grinderItemStacks[2] = itemstack.copy();
			} 
			else 
				if (this.grinderItemStacks[2].getItem() == itemstack.getItem()) 
				{
				this.grinderItemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.grinderItemStacks[0].stackSize;

			if (this.grinderItemStacks[0].stackSize <= 0) 
			{
				this.grinderItemStacks[0] = null;
			}
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the
	 * furnace burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack par1ItemStack) 
	{
		if (par1ItemStack == null) 
		{
			return 0;
		} 
		else 
		{
			Item item = par1ItemStack.getItem();
			if (item == Items.lava_bucket)return 20000;
			if (item == Items.blaze_rod)return 2400;
			return GameRegistry.getFuelValue(par1ItemStack);
		}

	}

	public static boolean isItemFuel(ItemStack par1ItemStack) 
	{
		/**
		 * Returns the number of ticks that the supplied fuel item will keep the
		 * furnace burning, or 0 if the item isn't fuel
		 */
		return getItemBurnTime(par1ItemStack) > 0;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) 
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,(double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() 
	{
	}

	public void closeInventory() 
	{
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) 
	{
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack): true);
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed
	 * by automation on the given side of this block.
	 */
	public int[] getAccessibleSlotsFromSide(int par1) 
	{
		return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
	}

	/**
	 * Returns true if automation can insert the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) 
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) 
	{
		return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
	}

}
