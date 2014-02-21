package jmm.mods.Diamerald.tileentity;

import java.util.Iterator;
import java.util.List;

import jmm.mods.Diamerald.blocks.BlockDirtchest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityChestDC extends TileEntity implements IInventory {
	private ItemStack[] DirtchestContents = new ItemStack[36];
	public boolean adjacentDirtchestChecked = false;
	public TileEntityChestDC adjacentDirtchestZNeg;
	public TileEntityChestDC adjacentDirtchestXPos;
	public TileEntityChestDC adjacentDirtchestXNeg;
	public TileEntityChestDC adjacentDirtchestZPos;
	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;
	public int cachedChestType = -1;
	private String customName;
	private int blockID;

	public int getSizeInventory() {
		return 27;
	}

	public ItemStack getStackInSlot(int par1) {
		return this.DirtchestContents[par1];
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if (this.DirtchestContents[par1] != null) {
			ItemStack itemstack;

			if (this.DirtchestContents[par1].stackSize <= par2) {
				itemstack = this.DirtchestContents[par1];
				this.DirtchestContents[par1] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.DirtchestContents[par1].splitStack(par2);

				if (this.DirtchestContents[par1].stackSize == 0) {
					this.DirtchestContents[par1] = null;
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.DirtchestContents[par1] != null) {
			ItemStack itemstack = this.DirtchestContents[par1];
			this.DirtchestContents[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.DirtchestContents[par1] = par2ItemStack;

		if (par2ItemStack != null
				&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "Dirtchest";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setGuiDisplayName(String par1Str) {
		this.customName = par1Str;
	}

	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		NBTTagList nbttaglist = par1.getTagList("Items", 10);
		this.DirtchestContents = new ItemStack[this.getSizeInventory()];

		if (par1.hasKey("CustomName", 8)) {
			this.customName = par1.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.DirtchestContents.length) {
				this.DirtchestContents[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.DirtchestContents.length; ++i) {
			if (this.DirtchestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.DirtchestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()) {
			par1.setString("CustomName", this.customName);
		}
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		this.adjacentDirtchestChecked = false;
	}

	private void func_145978_a(TileEntityChestDC par1TileEntity, int par2) {
		if (par1TileEntity.isInvalid()) {
			this.adjacentDirtchestChecked = false;
		} else if (this.adjacentDirtchestChecked) {
			switch (par2) {
			case 0:
				if (this.adjacentDirtchestZPos != par1TileEntity) {
					this.adjacentDirtchestChecked = false;
				}

				break;
			case 1:
				if (this.adjacentDirtchestXNeg != par1TileEntity) {
					this.adjacentDirtchestChecked = false;
				}

				break;
			case 2:
				if (this.adjacentDirtchestZNeg != par1TileEntity) {
					this.adjacentDirtchestChecked = false;
				}

				break;
			case 3:
				if (this.adjacentDirtchestXPos != par1TileEntity) {
					this.adjacentDirtchestChecked = false;
				}
			}
		}
	}

	public void checkForAdjacentDirtchests() {
		if (!this.adjacentDirtchestChecked) {
			this.adjacentDirtchestChecked = true;
			this.adjacentDirtchestZNeg = null;
			this.adjacentDirtchestXPos = null;
			this.adjacentDirtchestXNeg = null;
			this.adjacentDirtchestZPos = null;

			if (this.func_145977_a(this.xCoord - 1, this.yCoord, this.zCoord)) {
				this.adjacentDirtchestXNeg = (TileEntityChestDC) this.worldObj
						.getTileEntity(this.xCoord - 1, this.yCoord,
								this.zCoord);
			}

			if (this.func_145977_a(this.xCoord + 1, this.yCoord, this.zCoord)) {
				this.adjacentDirtchestXPos = (TileEntityChestDC) this.worldObj
						.getTileEntity(this.xCoord + 1, this.yCoord,
								this.zCoord);
			}

			if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord - 1)) {
				this.adjacentDirtchestZNeg = (TileEntityChestDC) this.worldObj
						.getTileEntity(this.xCoord, this.yCoord,
								this.zCoord - 1);
			}

			if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord + 1)) {
				this.adjacentDirtchestZPos = (TileEntityChestDC) this.worldObj
						.getTileEntity(this.xCoord, this.yCoord,
								this.zCoord + 1);
			}

			if (this.adjacentDirtchestZNeg != null) {
				this.adjacentDirtchestZNeg.func_145978_a(this, 0);
			}

			if (this.adjacentDirtchestZPos != null) {
				this.adjacentDirtchestZPos.func_145978_a(this, 2);
			}

			if (this.adjacentDirtchestXPos != null) {
				this.adjacentDirtchestXPos.func_145978_a(this, 1);
			}

			if (this.adjacentDirtchestXNeg != null) {
				this.adjacentDirtchestXNeg.func_145978_a(this, 3);
			}
		}
	}

	private boolean func_145977_a(int par1, int par2,
			int par3) {
		Block block = this.worldObj.getBlock(par1, par2,
				par3);
		return block instanceof BlockDirtchest
				&& ((BlockDirtchest) block).field_149956_a == this
						.func_145980_j();
	}

	public void updateEntity() {
		super.updateEntity();
		this.checkForAdjacentDirtchests();
		++this.ticksSinceSync;
		float f;

		if (!this.worldObj.isRemote
				&& this.numPlayersUsing != 0
				&& (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
			this.numPlayersUsing = 0;
			f = 5.0F;
			List list = this.worldObj.getEntitiesWithinAABB(
					EntityPlayer.class,
					AxisAlignedBB.getAABBPool().getAABB(
							(double) ((float) this.xCoord - f),
							(double) ((float) this.yCoord - f),
							(double) ((float) this.zCoord - f),
							(double) ((float) (this.xCoord + 1) + f),
							(double) ((float) (this.yCoord + 1) + f),
							(double) ((float) (this.zCoord + 1) + f)));
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();

				if (entityplayer.openContainer instanceof ContainerChest) {
					IInventory iinventory = ((ContainerChest) entityplayer.openContainer)
							.getLowerChestInventory();

					if (iinventory == this
							|| iinventory instanceof InventoryLargeChest
							&& ((InventoryLargeChest) iinventory)
									.isPartOfLargeChest(this)) {
						++this.numPlayersUsing;
					}
				}
			}
		}

		this.prevLidAngle = this.lidAngle;
		f = 0.1F;
		double d2;

		if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F
				&& this.adjacentDirtchestZNeg == null
				&& this.adjacentDirtchestXNeg == null) {
			double d1 = (double) this.xCoord + 0.5D;
			d2 = (double) this.zCoord + 0.5D;

			if (this.adjacentDirtchestZPos != null) {
				d2 += 0.5D;
			}

			if (this.adjacentDirtchestXPos != null) {
				d1 += 0.5D;
			}

		}

		if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F
				|| this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
			float f1 = this.lidAngle;

			if (this.numPlayersUsing > 0) {
				this.lidAngle += f;
			} else {
				this.lidAngle -= f;
			}

			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			float f2 = 0.5F;

			if (this.lidAngle < f2 && f1 >= f2
					&& this.adjacentDirtchestZPos == null
					&& this.adjacentDirtchestZNeg == null) {
				d2 = (double) this.xCoord + 0.5D;
				double d0 = (double) this.zCoord + 0.5D;

				if (this.adjacentDirtchestXPos != null) {
					d0 += 0.5D;
				}

				if (this.adjacentDirtchestXNeg != null) {
					d2 += 0.5D;
				}

			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}

	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 1) {
			this.numPlayersUsing = par2;
			return true;
		} else {
			return super.receiveClientEvent(par1, par2);
		}
	}

	public void openInventory() {
		if (this.numPlayersUsing < 0) {
			this.numPlayersUsing = 0;
		}

		++this.numPlayersUsing;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
				this.getBlockType(), 1, this.numPlayersUsing);
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord,
				this.zCoord, this.getBlockType());
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
				this.yCoord - 1, this.zCoord, this.getBlockType());
	}

	public void closeInventory() {
		if (this.getBlockType() instanceof BlockDirtchest) {
			--this.numPlayersUsing;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 1, this.numPlayersUsing);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord, this.zCoord, this.getBlockType());
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord - 1, this.zCoord, this.getBlockType());
		}
	}

	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return true;
	}

	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
		this.checkForAdjacentDirtchests();
	}

	public int func_145980_j() {
		if (this.cachedChestType == -1) {
			if (this.worldObj == null
					|| !(this.getBlockType() instanceof BlockDirtchest)) {
				return 0;
			}

			this.cachedChestType = ((BlockDirtchest) this.getBlockType()).field_149956_a;
		}

		return this.cachedChestType;
	}

}
