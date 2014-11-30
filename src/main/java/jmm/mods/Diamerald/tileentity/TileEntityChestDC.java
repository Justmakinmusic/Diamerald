package jmm.mods.Diamerald.tileentity;

import java.util.Iterator;
import java.util.List;

import jmm.mods.Diamerald.blocks.BlockDirtchest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityChestDC extends TileEntityLockable implements IUpdatePlayerListBox, IInventory
{
    private ItemStack[] DirtchestContents = new ItemStack[27];
    public boolean adjacentDirtchestChecked;
    public TileEntityChestDC adjacentDirtchestZNeg;
    public TileEntityChestDC adjacentDirtchestXPos;
    public TileEntityChestDC adjacentDirtchestXNeg;
    public TileEntityChestDC adjacentDirtchestZPos;
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private int cachedChestType;
    private String customName;
    private static final String __OBFID = "CL_00000346";

    public TileEntityChestDC()
    {
        this.cachedChestType = -1;
    }

    @SideOnly(Side.CLIENT)
    public TileEntityChestDC(int p_i2350_1_)
    {
        this.cachedChestType = p_i2350_1_;
    }

    public int getSizeInventory()
    {
        return 27;
    }

    public ItemStack getStackInSlot(int slotIn)
    {
        return this.DirtchestContents[slotIn];
    }

    public ItemStack decrStackSize(int index, int count)
    {
        if (this.DirtchestContents[index] != null)
        {
            ItemStack itemstack;

            if (this.DirtchestContents[index].stackSize <= count)
            {
                itemstack = this.DirtchestContents[index];
                this.DirtchestContents[index] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.DirtchestContents[index].splitStack(count);

                if (this.DirtchestContents[index].stackSize == 0)
                {
                    this.DirtchestContents[index] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.DirtchestContents[index] != null)
        {
            ItemStack itemstack = this.DirtchestContents[index];
            this.DirtchestContents[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.DirtchestContents[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    public String getName()
    {
        return this.hasCustomName() ? this.customName : "Dirtchest";
    }

    public boolean hasCustomName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomName(String p_145976_1_)
    {
        this.customName = p_145976_1_;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.DirtchestContents = new ItemStack[this.getSizeInventory()];

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.DirtchestContents.length)
            {
                this.DirtchestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.DirtchestContents.length; ++i)
        {
            if (this.DirtchestContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.DirtchestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer playerIn)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
        this.adjacentDirtchestChecked = false;
    }

    private void func_174910_a(TileEntityChestDC p_174910_1_, EnumFacing p_174910_2_)
    {
        if (p_174910_1_.isInvalid())
        {
            this.adjacentDirtchestChecked = false;
        }
        else if (this.adjacentDirtchestChecked)
        {
            switch (TileEntityChestDC.SwitchEnumFacing.field_177366_a[p_174910_2_.ordinal()])
            {
                case 1:
                    if (this.adjacentDirtchestZNeg != p_174910_1_)
                    {
                        this.adjacentDirtchestChecked = false;
                    }

                    break;
                case 2:
                    if (this.adjacentDirtchestZPos != p_174910_1_)
                    {
                        this.adjacentDirtchestChecked = false;
                    }

                    break;
                case 3:
                    if (this.adjacentDirtchestXPos != p_174910_1_)
                    {
                        this.adjacentDirtchestChecked = false;
                    }

                    break;
                case 4:
                    if (this.adjacentDirtchestXNeg != p_174910_1_)
                    {
                        this.adjacentDirtchestChecked = false;
                    }
            }
        }
    }

    public void checkForAdjacentDirtchests()
    {
        if (!this.adjacentDirtchestChecked)
        {
            this.adjacentDirtchestChecked = true;
            this.adjacentDirtchestXNeg = this.func_174911_a(EnumFacing.WEST);
            this.adjacentDirtchestXPos = this.func_174911_a(EnumFacing.EAST);
            this.adjacentDirtchestZNeg = this.func_174911_a(EnumFacing.NORTH);
            this.adjacentDirtchestZPos = this.func_174911_a(EnumFacing.SOUTH);
        }
    }

    protected TileEntityChestDC func_174911_a(EnumFacing p_174911_1_)
    {
        BlockPos blockpos = this.pos.offset(p_174911_1_);

        if (this.func_174912_b(blockpos))
        {
            TileEntity tileentity = this.worldObj.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityChestDC)
            {
                TileEntityChestDC tileentitychestdc = (TileEntityChestDC)tileentity;
                tileentitychestdc.func_174910_a(this, p_174911_1_.getOpposite());
                return tileentitychestdc;
            }
        }

        return null;
    }

    private boolean func_174912_b(BlockPos p_174912_1_)
    {
        if (this.worldObj == null)
        {
            return false;
        }
        else
        {
            Block block = this.worldObj.getBlockState(p_174912_1_).getBlock();
            return block instanceof BlockDirtchest && ((BlockDirtchest)block).dchestType == this.getChestType();
        }
    }

    public void update()
    {
        this.checkForAdjacentDirtchests();
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;
        float f;

        if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            f = 5.0F;
            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)i - f), (double)((float)j - f), (double)((float)k - f), (double)((float)(i + 1) + f), (double)((float)(j + 1) + f), (double)((float)(k + 1) + f)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if (entityplayer.openContainer instanceof ContainerChest)
                {
                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        f = 0.1F;
        double d2;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentDirtchestZNeg == null && this.adjacentDirtchestXNeg == null)
        {
            double d1 = (double)i + 0.5D;
            d2 = (double)k + 0.5D;

            if (this.adjacentDirtchestZPos != null)
            {
                d2 += 0.5D;
            }

            if (this.adjacentDirtchestXPos != null)
            {
                d1 += 0.5D;
            }

            this.worldObj.playSoundEffect(d1, (double)j + 0.5D, d2, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f1 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += f;
            }
            else
            {
                this.lidAngle -= f;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < f2 && f1 >= f2 && this.adjacentDirtchestZNeg == null && this.adjacentDirtchestXNeg == null)
            {
                d2 = (double)i + 0.5D;
                double d0 = (double)k + 0.5D;

                if (this.adjacentDirtchestZPos != null)
                {
                    d0 += 0.5D;
                }

                if (this.adjacentDirtchestXPos != null)
                {
                    d2 += 0.5D;
                }

                this.worldObj.playSoundEffect(d2, (double)j + 0.5D, d0, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    public boolean receiveClientEvent(int id, int type)
    {
        if (id == 1)
        {
            this.numPlayersUsing = type;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, type);
        }
    }

    public void openInventory(EntityPlayer playerIn)
    {
        if (!playerIn.func_175149_v())
        {
            if (this.numPlayersUsing < 0)
            {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
            this.worldObj.notifyNeighborsOfStateChange(this.pos.offsetDown(), this.getBlockType());
        }
    }

    public void closeInventory(EntityPlayer playerIn)
    {
        if (!playerIn.func_175149_v() && this.getBlockType() instanceof BlockDirtchest)
        {
            --this.numPlayersUsing;
            this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
            this.worldObj.notifyNeighborsOfStateChange(this.pos.offsetDown(), this.getBlockType());
        }
    }

    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    public void invalidate()
    {
        super.invalidate();
        this.updateContainingBlockInfo();
        this.checkForAdjacentDirtchests();
    }

    public int getChestType()
    {
        if (this.cachedChestType == -1)
        {
            if (this.worldObj == null || !(this.getBlockType() instanceof BlockDirtchest))
            {
                return 0;
            }

            this.cachedChestType = ((BlockDirtchest)this.getBlockType()).dchestType;
        }

        return this.cachedChestType;
    }

    public String getGuiID()
    {
        return "minecraft:chest";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerChest(playerInventory, this, playerIn);
    }

    public int getField(int id)
    {
        return 0;
    }

    public void setField(int id, int value) {}

    public int getFieldCount()
    {
        return 0;
    }

    public void clearInventory()
    {
        for (int i = 0; i < this.DirtchestContents.length; ++i)
        {
            this.DirtchestContents[i] = null;
        }
    }

    static final class SwitchEnumFacing
        {
            static final int[] field_177366_a = new int[EnumFacing.values().length];
            private static final String __OBFID = "CL_00002041";

            static
            {
                try
                {
                    field_177366_a[EnumFacing.NORTH.ordinal()] = 1;
                }
                catch (NoSuchFieldError var4)
                {
                    ;
                }

                try
                {
                    field_177366_a[EnumFacing.SOUTH.ordinal()] = 2;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    field_177366_a[EnumFacing.EAST.ordinal()] = 3;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    field_177366_a[EnumFacing.WEST.ordinal()] = 4;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}
