package jmm.mods.Diamerald.tileentity;

import jmm.mods.Diamerald.blocks.Grinder;
import jmm.mods.Diamerald.machines.ContainerGrinder;
import jmm.mods.Diamerald.machines.GrinderRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityGrinder extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory {
	private static final int[] slotsTop = new int[] { 0, 1, 2 };
	private static final int[] slotsBottom = new int[] { 0, 1, 2 };
	private static final int[] slotsSides = new int[] { 0, 1, 2 };
	private ItemStack[] grinderItemStacks = new ItemStack[3];
	public static final int GRINDER_MAX_FUEL = 30000;
	public int grinderBurnTime;
	public int grinderCookTime;
	public int itemCookTime;
	private int field_174905_l;
	private String name;
	private boolean soundIsOn = false;

	public int getSizeInventory() {
		return this.grinderItemStacks.length;
	}

	public ItemStack getStackInSlot(int par1) {
		return this.grinderItemStacks[par1];
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if (this.grinderItemStacks[par1] != null) {
			ItemStack itemstack;

			if (this.grinderItemStacks[par1].stackSize <= par2) {
				itemstack = this.grinderItemStacks[par1];
				this.grinderItemStacks[par1] = null;
				return itemstack;
			} else {
				itemstack = this.grinderItemStacks[par1].splitStack(par2);

				if (this.grinderItemStacks[par1].stackSize == 0) {
					this.grinderItemStacks[par1] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.grinderItemStacks[par1] != null) {
			ItemStack itemstack = this.grinderItemStacks[par1];
			this.grinderItemStacks[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}
	
	public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.grinderItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.grinderItemStacks[index]);
        this.grinderItemStacks[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.field_174905_l = this.func_174904_a(stack);
            this.grinderCookTime = 0;
            this.markDirty();
        }
    }

	public String getName() {

		return String.valueOf(this.grinderBurnTime);
	}

	public boolean hasCustomName() {
		return this.name != null && this.name.length() > 0;
	}

	public void setGuiDisplayName(String par1Str) {
		this.name = par1Str;
	}
	
	@Override
	public String getGuiID() {
		return "diamerald:Grinder";
	}

	public void readFromNBT(NBTTagCompound tileEntityTag) {
		super.readFromNBT(tileEntityTag);
		NBTTagList nbttaglist = tileEntityTag.getTagList("Items", 10);
		this.grinderItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.grinderItemStacks.length) {
				this.grinderItemStacks[b0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.grinderBurnTime = tileEntityTag.getInteger("BurnTime");
		this.grinderCookTime = tileEntityTag.getInteger("CookTime");

		if (tileEntityTag.hasKey("CustomName", 8)) {
			this.name = tileEntityTag.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound tileEntityTag) {
		super.writeToNBT(tileEntityTag);
		tileEntityTag.setInteger("BurnTime", (int) this.grinderBurnTime);
		tileEntityTag.setInteger("CookTime", (int) this.grinderCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.grinderItemStacks.length; ++i) {
			if (this.grinderItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.grinderItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tileEntityTag.setTag("Items", nbttaglist);

		if (this.hasCustomName()) {
			tileEntityTag.setString("CustomName", this.name);
		}
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	/*@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) {
		return this.grinderCookTime * par1 / 325;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {

		return this.grinderBurnTime * par1 / this.GRINDER_MAX_FUEL;
	}

	@SideOnly(Side.CLIENT)
	public int getItemTimeScaled(int par1) {
		return this.itemCookTime;
	}*/

	public boolean isBurning() {
		return this.grinderBurnTime > 0;
	}

	public boolean isGrinding() {
		return this.grinderCookTime > 0;
	}

	public void update() {
		boolean flag = this.grinderBurnTime > 0;
		boolean flag1 = false;
		boolean soundWasOn = soundIsOn;
	
		if (this.grinderItemStacks[0] != null && this.isBurning()
				&& this.isGrinding()) {
			
			soundIsOn = true;
			this.worldObj.playSoundEffect((double) this.pos.getX() + 0.5D,
					(double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D,
					"Diamerald:Grindersnd", 0.5F, 0.5F);
			--this.grinderBurnTime;
		}

		else {
			soundIsOn = false;
		}

		if (!this.worldObj.isRemote) {

			int moreFuelToBurn = getItemBurnTime(this.grinderItemStacks[1]);

			if (moreFuelToBurn > 0) {

				if (this.grinderBurnTime + moreFuelToBurn <= GRINDER_MAX_FUEL) {
					this.grinderBurnTime += moreFuelToBurn;

					if (this.grinderBurnTime > 0) {
						flag1 = true;

						if (this.grinderItemStacks[1] != null) {
							--this.grinderItemStacks[1].stackSize;

							if (this.grinderItemStacks[1].stackSize == 0) {
								this.grinderItemStacks[1] = grinderItemStacks[1]
										.getItem().getContainerItem(
												grinderItemStacks[1]);
							}
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt()) {
				++this.grinderCookTime;

				if (this.grinderCookTime == this.field_174905_l) {
					this.grinderCookTime = 0;
					this.field_174905_l = this.func_174904_a(this.grinderItemStacks[0]);
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.grinderCookTime = 0;
			}

			if (soundWasOn != soundIsOn) {
				flag1 = true;
				Grinder.updateGrinderBlockState(soundIsOn, this.worldObj,
						this.pos);
			}
		}

		if (flag1) {
			this.markDirty();
		}

	}

	public int func_174904_a(ItemStack p_174904_1_)
    {
        return 325;
    }

	private boolean canSmelt() {
		if (this.grinderItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = GrinderRecipes.instance().getSmeltingResult(
					this.grinderItemStacks[0]);

			if (itemstack == null)

				return false;
			
			if (this.grinderItemStacks[2] == null)

				return true;

			if (!this.grinderItemStacks[2].isItemEqual(itemstack))

				return false;

			int result = grinderItemStacks[2].stackSize + itemstack.stackSize *2;

			return result <= getInventoryStackLimit()
					&& result <= this.grinderItemStacks[2].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = GrinderRecipes.instance().getSmeltingResult(
					this.grinderItemStacks[0]);

			if (this.grinderItemStacks[2] == null) {
				this.grinderItemStacks[2] = itemstack.copy();
				this.grinderItemStacks[2].stackSize *= 2;

			} else if (this.grinderItemStacks[2].getItem() == itemstack
					.getItem()) {
				this.grinderItemStacks[2].stackSize += itemstack.stackSize *2;
			}

			--this.grinderItemStacks[0].stackSize;

			if (this.grinderItemStacks[0].stackSize <= 0) {
				this.grinderItemStacks[0] = null;
			}
		}
	}
	
	public static int getItemBurnTime(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return 0;
		} else {
			Item item = par1ItemStack.getItem();
			if (item == Items.lava_bucket)
				return 10000;
			if (item == Items.blaze_rod)
				return 2000;
			return GameRegistry.getFuelValue(par1ItemStack);
		}

	}

	public static boolean isItemFuel(ItemStack par1ItemStack) {

		return getItemBurnTime(par1ItemStack) > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer playerIn) {
		return this.worldObj.getTileEntity(this.pos) != this ? false : playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public void openInventory(EntityPlayer playerIn) {
	}

	public void closeInventory(EntityPlayer playerIn) {
	}

	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack)
				: true);
	}

	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
	}

	 public boolean canInsertItem(int slotIn, ItemStack itemStackIn, EnumFacing direction)
	    {
	        return this.isItemValidForSlot(slotIn, itemStackIn);
	    }

	    public boolean canExtractItem(int slotId, ItemStack stack, EnumFacing direction)
	    {
	        if (direction == EnumFacing.DOWN && slotId == 1)
	        {
	            Item item = stack.getItem();

	            if (item != Items.water_bucket && item != Items.bucket)
	            {
	                return false;
	            }
	        }

	        return true;
	    }

	public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.grinderBurnTime;
            case 1:
                return this.itemCookTime;
            case 2:
                return this.grinderCookTime;
            case 3:
                return this.field_174905_l;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.grinderBurnTime = value;
                break;
            case 1:
                this.itemCookTime = value;
                break;
            case 2:
                this.grinderCookTime = value;
                break;
            case 3:
                this.field_174905_l = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
    }

    public void clearInventory()
    {
        for (int i = 0; i < this.grinderItemStacks.length; ++i)
        {
            this.grinderItemStacks[i] = null;
        }
    }

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	 public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerGrinder(playerInventory, this);
    }

}
