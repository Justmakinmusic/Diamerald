package jmm.mods.Diamerald.tedc;

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
import net.minecraft.util.AxisAlignedBB;

public class TileEntityChestDC extends TileEntity implements IInventory {
	private ItemStack[] DirtchestContents = new ItemStack[36];
	public boolean adjacentDirtchestChecked = false;
	public TileEntityChestDC field_145992_i;
	public TileEntityChestDC field_145990_j;
	public TileEntityChestDC field_145991_k;
	public TileEntityChestDC field_145988_l;
	public float field_145989_m;
	public float field_145986_n;
	public int field_145987_o;
	private int field_145983_q;
	public int field_145982_r = -1;
	private String field_145981_s;
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
				this.onInventoryChanged();
				return itemstack;
			} else {
				itemstack = this.DirtchestContents[par1].splitStack(par2);

				if (this.DirtchestContents[par1].stackSize == 0) {
					this.DirtchestContents[par1] = null;
				}

				this.onInventoryChanged();
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

		this.onInventoryChanged();
	}

	public String func_145825_b() {
		return this.func_145818_k_() ? this.field_145981_s : "Dirtchest";
	}

	public boolean func_145818_k_() {
		return this.field_145981_s != null && this.field_145981_s.length() > 0;
	}

	public void func_145976_a(String p_145976_1_) {
		this.field_145981_s = p_145976_1_;
	}

	public void readFromNBT(NBTTagCompound p_145839_1_) {
		super.func_145839_a(p_145839_1_);
		NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
		this.DirtchestContents = new ItemStack[this.getSizeInventory()];

		if (p_145839_1_.func_150297_b("CustomName", 8)) {
			this.field_145981_s = p_145839_1_.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.func_150305_b(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.DirtchestContents.length) {
				this.DirtchestContents[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound p_145841_1_) {
		super.func_145841_b(p_145841_1_);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.DirtchestContents.length; ++i) {
			if (this.DirtchestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.DirtchestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		p_145841_1_.setTag("Items", nbttaglist);

		if (this.func_145818_k_()) {
			p_145841_1_.setString("CustomName", this.field_145981_s);
		}
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.field_145850_b.func_147438_o(this.field_145851_c,
				this.field_145848_d, this.field_145849_e) != this ? false
				: par1EntityPlayer.getDistanceSq(
						(double) this.field_145851_c + 0.5D,
						(double) this.field_145848_d + 0.5D,
						(double) this.field_145849_e + 0.5D) <= 64.0D;
	}

	public void func_145836_u() {
		super.func_145836_u();
		this.adjacentDirtchestChecked = false;
	}

	private void func_145978_a(TileEntityChestDC p_145978_1_, int p_145978_2_) {
		if (p_145978_1_.func_145837_r()) {
			this.adjacentDirtchestChecked = false;
		} else if (this.adjacentDirtchestChecked) {
			switch (p_145978_2_) {
			case 0:
				if (this.field_145992_i != p_145978_1_) {
					this.adjacentDirtchestChecked = false;
				}

				break;
			case 1:
				if (this.field_145990_j != p_145978_1_) {
					this.adjacentDirtchestChecked = false;
				}

				break;
			case 2:
				if (this.field_145991_k != p_145978_1_) {
					this.adjacentDirtchestChecked = false;
				}

				break;
			case 3:
				if (this.field_145988_l != p_145978_1_) {
					this.adjacentDirtchestChecked = false;
				}
			}
		}
	}

	public void checkForAdjacentDirtchests() {
		if (!this.adjacentDirtchestChecked) {
			this.adjacentDirtchestChecked = true;
			this.field_145992_i = null;
			this.field_145990_j = null;
			this.field_145991_k = null;
			this.field_145988_l = null;

			if (this.func_145977_a(this.field_145851_c - 1,
					this.field_145848_d, this.field_145849_e)) {
				this.field_145991_k = (TileEntityChestDC) this.field_145850_b
						.func_147438_o(this.field_145851_c - 1,
								this.field_145848_d, this.field_145849_e);
			}

			if (this.func_145977_a(this.field_145851_c + 1,
					this.field_145848_d, this.field_145849_e)) {
				this.field_145990_j = (TileEntityChestDC) this.field_145850_b
						.func_147438_o(this.field_145851_c + 1,
								this.field_145848_d, this.field_145849_e);
			}

			if (this.func_145977_a(this.field_145851_c, this.field_145848_d,
					this.field_145849_e - 1)) {
				this.field_145992_i = (TileEntityChestDC) this.field_145850_b
						.func_147438_o(this.field_145851_c,
								this.field_145848_d, this.field_145849_e - 1);
			}

			if (this.func_145977_a(this.field_145851_c, this.field_145848_d,
					this.field_145849_e + 1)) {
				this.field_145988_l = (TileEntityChestDC) this.field_145850_b
						.func_147438_o(this.field_145851_c,
								this.field_145848_d, this.field_145849_e + 1);
			}

			if (this.field_145992_i != null) {
				this.field_145992_i.func_145978_a(this, 0);
			}

			if (this.field_145988_l != null) {
				this.field_145988_l.func_145978_a(this, 2);
			}

			if (this.field_145990_j != null) {
				this.field_145990_j.func_145978_a(this, 1);
			}

			if (this.field_145991_k != null) {
				this.field_145991_k.func_145978_a(this, 3);
			}
		}
	}

	private boolean func_145977_a(int p_145977_1_, int p_145977_2_,
			int p_145977_3_) {
		Block block = this.field_145850_b.func_147439_a(p_145977_1_,
				p_145977_2_, p_145977_3_);
		return block instanceof BlockDirtchest
				&& ((BlockDirtchest) block).field_149956_a == this
						.func_145980_j();
	}

	public void func_145845_h() {
		super.func_145845_h();
		this.checkForAdjacentDirtchests();
		++this.field_145983_q;
		float f;

		if (!this.field_145850_b.isRemote
				&& this.field_145987_o != 0
				&& (this.field_145983_q + this.field_145851_c
						+ this.field_145848_d + this.field_145849_e) % 200 == 0) {
			this.field_145987_o = 0;
			f = 5.0F;
			List list = this.field_145850_b.getEntitiesWithinAABB(
					EntityPlayer.class,
					AxisAlignedBB.getAABBPool().getAABB(
							(double) ((float) this.field_145851_c - f),
							(double) ((float) this.field_145848_d - f),
							(double) ((float) this.field_145849_e - f),
							(double) ((float) (this.field_145851_c + 1) + f),
							(double) ((float) (this.field_145848_d + 1) + f),
							(double) ((float) (this.field_145849_e + 1) + f)));
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
						++this.field_145987_o;
					}
				}
			}
		}

		this.field_145986_n = this.field_145989_m;
		f = 0.1F;
		double d2;

		if (this.field_145987_o > 0 && this.field_145989_m == 0.0F
				&& this.field_145992_i == null && this.field_145991_k == null) {
			double d1 = (double) this.field_145851_c + 0.5D;
			d2 = (double) this.field_145849_e + 0.5D;

			if (this.field_145988_l != null) {
				d2 += 0.5D;
			}

			if (this.field_145990_j != null) {
				d1 += 0.5D;
			}

		}

		if (this.field_145987_o == 0 && this.field_145989_m > 0.0F
				|| this.field_145987_o > 0 && this.field_145989_m < 1.0F) {
			float f1 = this.field_145989_m;

			if (this.field_145987_o > 0) {
				this.field_145989_m += f;
			} else {
				this.field_145989_m -= f;
			}

			if (this.field_145989_m > 1.0F) {
				this.field_145989_m = 1.0F;
			}

			float f2 = 0.5F;

			if (this.field_145989_m < f2 && f1 >= f2
					&& this.field_145992_i == null
					&& this.field_145991_k == null) {
				d2 = (double) this.field_145851_c + 0.5D;
				double d0 = (double) this.field_145849_e + 0.5D;

				if (this.field_145988_l != null) {
					d0 += 0.5D;
				}

				if (this.field_145990_j != null) {
					d2 += 0.5D;
				}

			}

			if (this.field_145989_m < 0.0F) {
				this.field_145989_m = 0.0F;
			}
		}
	}

	public boolean func_145842_c(int p_145842_1_, int p_145842_2_) {
		if (p_145842_1_ == 1) {
			this.field_145987_o = p_145842_2_;
			return true;
		} else {
			return super.func_145842_c(p_145842_1_, p_145842_2_);
		}
	}

	public void openChest() {
		if (this.field_145987_o < 0) {
			this.field_145987_o = 0;
		}

		++this.field_145987_o;
		this.field_145850_b.func_147452_c(this.field_145851_c,
				this.field_145848_d, this.field_145849_e, this.func_145838_q(),
				1, this.field_145987_o);
		this.field_145850_b.func_147459_d(this.field_145851_c,
				this.field_145848_d, this.field_145849_e, this.func_145838_q());
		this.field_145850_b.func_147459_d(this.field_145851_c,
				this.field_145848_d - 1, this.field_145849_e,
				this.func_145838_q());
	}

	public void closeChest() {
		if (this.func_145838_q() instanceof BlockChest) {
			--this.field_145987_o;
			this.field_145850_b.func_147452_c(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q(), 1, this.field_145987_o);
			this.field_145850_b.func_147459_d(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q());
			this.field_145850_b.func_147459_d(this.field_145851_c,
					this.field_145848_d - 1, this.field_145849_e,
					this.func_145838_q());
		}
	}

	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return true;
	}

	public void func_145843_s() {
		super.func_145843_s();
		this.func_145836_u();
		this.checkForAdjacentDirtchests();
	}

	public int func_145980_j() {
		if (this.field_145982_r == -1) {
			if (this.field_145850_b == null
					|| !(this.func_145838_q() instanceof BlockDirtchest)) {
				return 0;
			}

			this.field_145982_r = ((BlockDirtchest) this.func_145838_q()).field_149956_a;
		}

		return this.field_145982_r;
	}

}
