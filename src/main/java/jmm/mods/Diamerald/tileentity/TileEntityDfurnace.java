package jmm.mods.Diamerald.tileentity;

import jmm.mods.Diamerald.Diamerald;
import jmm.mods.Diamerald.blocks.Dfurnace;
import jmm.mods.Diamerald.machines.DfurnaceRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityDfurnace extends TileEntity implements ISidedInventory {

	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 0, 1 };
	private ItemStack[] dfurnaceItemStacks = new ItemStack[5];
	private static final int Dfurnace_MAX_FUEL = 30000;
	public int dfurnaceBurnTime;
	public int dfurnaceCookTime;
	public int itemCookTime;
	private String name;
	// private static SoundHandler sndHandler = FMLClientHandler.instance()
	// .getClient().getSoundHandler();
	// private GrinderSound sound = null;
	private boolean soundIsOn = false;

	public int getSizeInventory() {
		return this.dfurnaceItemStacks.length;
	}

	public ItemStack getStackInSlot(int par1) {
		return this.dfurnaceItemStacks[par1];
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if (this.dfurnaceItemStacks[par1] != null) {
			ItemStack itemstack;

			if (this.dfurnaceItemStacks[par1].stackSize <= par2) {
				itemstack = this.dfurnaceItemStacks[par1];
				this.dfurnaceItemStacks[par1] = null;
				return itemstack;
			} else {
				itemstack = this.dfurnaceItemStacks[par1].splitStack(par2);

				if (this.dfurnaceItemStacks[par1].stackSize == 0) {
					this.dfurnaceItemStacks[par1] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench
	 * GUI.
	 */
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.dfurnaceItemStacks[par1] != null) {
			ItemStack itemstack = this.dfurnaceItemStacks[par1];
			this.dfurnaceItemStacks[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.dfurnaceItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null
				&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	public String getInventoryName() {

		return String.valueOf(this.dfurnaceBurnTime);
		// return this.hasCustomInventoryName() ? this.name : "Grinder";
	}

	public boolean hasCustomInventoryName() {
		return this.name != null && this.name.length() > 0;
	}

	public void setGuiDisplayName(String par1Str) {
		this.name = par1Str;
	}

	public void readFromNBT(NBTTagCompound tileEntityTag) {
		super.readFromNBT(tileEntityTag);
		NBTTagList nbttaglist = tileEntityTag.getTagList("Items", 10);
		this.dfurnaceItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.dfurnaceItemStacks.length) {
				this.dfurnaceItemStacks[b0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.dfurnaceBurnTime = tileEntityTag.getInteger("BurnTime");
		this.dfurnaceCookTime = tileEntityTag.getInteger("CookTime");

		if (tileEntityTag.hasKey("CustomName", 8)) {
			this.name = tileEntityTag.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound tileEntityTag) {
		super.writeToNBT(tileEntityTag);
		tileEntityTag.setInteger("BurnTime", (int) this.dfurnaceBurnTime);
		tileEntityTag.setInteger("CookTime", (int) this.dfurnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.dfurnaceItemStacks.length; ++i) {
			if (this.dfurnaceItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.dfurnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tileEntityTag.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()) {
			tileEntityTag.setString("CustomName", this.name);
		}
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) {
		return this.dfurnaceCookTime * par1 / 100;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {

		return this.dfurnaceBurnTime * par1 / this.Dfurnace_MAX_FUEL;
	}

	@SideOnly(Side.CLIENT)
	public int getItemTimeScaled(int par1) {
		return this.itemCookTime;
	}

	public boolean isBurning() {
		return this.dfurnaceBurnTime > 0;
	}

	public boolean isCooking() {
		return this.dfurnaceCookTime > 0;
	}

	public void updateEntity() {
		boolean flag = this.dfurnaceBurnTime > 0;
		boolean flag1 = false;
		boolean soundWasOn = soundIsOn;

		if (this.dfurnaceItemStacks[0] != null || this.dfurnaceItemStacks[3] != null && this.isBurning()
				&& this.isCooking()) {
			soundIsOn = true;
			this.worldObj.playSoundEffect((double) this.xCoord + 0.5D,
					(double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D,
					"Diamerald:dfurnace", 0.5F, 0.5F);
			--this.dfurnaceBurnTime;
		}

		else {
			soundIsOn = false;
		}

		if (!this.worldObj.isRemote) {

			int moreFuelToBurn = getItemBurnTime(this.dfurnaceItemStacks[1]);

			if (moreFuelToBurn > 0) {

				if (this.dfurnaceBurnTime + moreFuelToBurn <= Dfurnace_MAX_FUEL) {
					this.dfurnaceBurnTime += moreFuelToBurn;

					if (this.dfurnaceBurnTime > 0) {
						flag1 = true;

						if (this.dfurnaceItemStacks[1] != null) {
							--this.dfurnaceItemStacks[1].stackSize;

							if (this.dfurnaceItemStacks[1].stackSize == 0) {
								this.dfurnaceItemStacks[1] = dfurnaceItemStacks[1]
										.getItem().getContainerItem(
												dfurnaceItemStacks[1]);
							}
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt() || this.canSmelt2()) {
				++this.dfurnaceCookTime;

				if (this.dfurnaceCookTime == 100) {
					this.dfurnaceCookTime = 0;
					this.smeltItem();
					this.smeltItem2();
					flag1 = true;
				}
			} else {
				this.dfurnaceCookTime = 0;
			}

			if (soundWasOn != soundIsOn) {
				flag1 = true;
				Dfurnace.updateDfurnaceBlockState(soundIsOn, this.worldObj,
						this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1) {
			this.markDirty();
		}

	}

	/*private boolean canSmelt() {
		if (this.dfurnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = DfurnaceRecipes.smelting().getSmeltingResult(
					this.dfurnaceItemStacks[0]);

			if (itemstack == null)

				return false;

			if (this.dfurnaceItemStacks[2] == null)

				return true;

			if (!this.dfurnaceItemStacks[2].isItemEqual(itemstack))

				return false;

			int result = dfurnaceItemStacks[2].stackSize + itemstack.stackSize;

			return result <= getInventoryStackLimit()
					&& result <= this.dfurnaceItemStacks[2].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = DfurnaceRecipes.smelting().getSmeltingResult(
					this.dfurnaceItemStacks[0]);

			if (this.dfurnaceItemStacks[2] == null) {
				this.dfurnaceItemStacks[2] = itemstack.copy();

			} else if (this.dfurnaceItemStacks[2].getItem() == itemstack
					.getItem()) {
				this.dfurnaceItemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.dfurnaceItemStacks[0].stackSize;

			if (this.dfurnaceItemStacks[0].stackSize <= 0) {
				this.dfurnaceItemStacks[0] = null;
			}
		}
	}*/
	
	private boolean canSmelt() {
		if (this.dfurnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = DfurnaceRecipes.smelting().getSmeltingResult(
					this.dfurnaceItemStacks[0]);

			if (itemstack == null)

				return false;

			if (this.dfurnaceItemStacks[2] == null)

				return true;

			if (!this.dfurnaceItemStacks[2].isItemEqual(itemstack))

				return false;

			int result = dfurnaceItemStacks[2].stackSize + itemstack.stackSize;

			return result <= getInventoryStackLimit()
					&& result <= this.dfurnaceItemStacks[2].getMaxStackSize();
		}
	}
	
	private boolean canSmelt2() {
		if (this.dfurnaceItemStacks[3] == null) {
			return false;
		} else {
			ItemStack itemstack = DfurnaceRecipes.smelting().getSmeltingResult2(
					this.dfurnaceItemStacks[3]);

			if (itemstack == null)

				return false;

			if (this.dfurnaceItemStacks[4] == null)

				return true;

			if (!this.dfurnaceItemStacks[4].isItemEqual(itemstack))

				return false;

			int result = dfurnaceItemStacks[4].stackSize + itemstack.stackSize;

			return result <= getInventoryStackLimit()
					&& result <= this.dfurnaceItemStacks[4].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = DfurnaceRecipes.smelting().getSmeltingResult(
					this.dfurnaceItemStacks[0]);

			if (this.dfurnaceItemStacks[2] == null) {
				this.dfurnaceItemStacks[2] = itemstack.copy();

			} else if (this.dfurnaceItemStacks[2].getItem() == itemstack
					.getItem()) {
				this.dfurnaceItemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.dfurnaceItemStacks[0].stackSize;

			if (this.dfurnaceItemStacks[0].stackSize <= 0) {
				this.dfurnaceItemStacks[0] = null;
			}
		}
	}
	
	public void smeltItem2() {
		if (this.canSmelt2()) {
			ItemStack itemstack = DfurnaceRecipes.smelting().getSmeltingResult2(
					this.dfurnaceItemStacks[3]);

			if (this.dfurnaceItemStacks[4] == null) {
				this.dfurnaceItemStacks[4] = itemstack.copy();

			} else if (this.dfurnaceItemStacks[4].getItem() == itemstack
					.getItem()) {
				this.dfurnaceItemStacks[4].stackSize += itemstack.stackSize;
			}

			--this.dfurnaceItemStacks[3].stackSize;

			if (this.dfurnaceItemStacks[3].stackSize <= 0) {
				this.dfurnaceItemStacks[3] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack par1ItemStack) {
		if (par1ItemStack == null) {
			return 0;
		} else {
			Item item = par1ItemStack.getItem();
			if (item == Diamerald.gem_Diamerald)
				return 20000;
			if (item == Diamerald.gem_BlackDiamerald)
				return 30000;
			if (item == Items.coal)
				return 200;
			if (item == Item.getItemFromBlock(Blocks.coal_block))
				return 1800;
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

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack)
				: true);
	}

	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
	}

	/**
	 * Returns true if automation can insert the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return par3 != 0 || par1 != 1
				|| par2ItemStack.getItem() == Items.bucket;
	}

}
