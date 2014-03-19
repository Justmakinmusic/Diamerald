package jmm.mods.Diamerald.grinder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class GrinderRecipes
{
	private static final GrinderRecipes grinderBase = new GrinderRecipes();
	
    /**
     * The list of grinding results.
     */
    private Map grindingList = new HashMap();
    private Map experienceList = new HashMap();
   
    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static GrinderRecipes smelting()
    {
        return grinderBase;
    }
    
    private GrinderRecipes()
    {	
    	 this.addRecipe(Diamerald.oreDiamerald, new ItemStack(Diamerald.Diameralddust));
         this.addRecipe(Blocks.iron_ore, new ItemStack(Diamerald.Irondust));
         this.addRecipe(Blocks.gold_ore, new ItemStack(Diamerald.Golddust));
         this.addRecipe(Blocks.emerald_ore, new ItemStack(Diamerald.Emeralddust));
    }
    
    public void func_151393_a(Block input, ItemStack output)
    {
        this.func_151396_a(Item.getItemFromBlock(input), output);
    }

    public void func_151396_a(Item item, ItemStack itemstack)
    {
        this.func_151394_a(new ItemStack(item, 1, 32767), itemstack);
    }

    public void func_151394_a(ItemStack itemstack, ItemStack itemstack2)
    {
        this.grindingList.put(itemstack, itemstack2);
        //this.experienceList.put(itemstack2, Float.valueOf(xp));
    }
    
    public void addRecipe (Block input, ItemStack output)
    {
    	this.func_151393_a(input, output);
    }
    
    public void addRecipe (Item input, ItemStack output)
    {
    	this.func_151396_a(input, output);
    }
    
    public void addRecipe(ItemStack input, ItemStack output)
    {
        this.func_151394_a(input, output);
    }
  
    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack par1ItemStack)
    {
        Iterator iterator = this.grindingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
       
        }
        
        while (!this.func_151397_a(par1ItemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
        
    }

    private boolean func_151397_a(ItemStack par1Item, ItemStack par2Item)
    {
        return par2Item.getItem() == par1Item.getItem() && (par2Item.getItemDamage() == 32767 || par2Item.getItemDamage() == par1Item.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return this.grindingList;
    }

    public float getExperience(ItemStack item)
    {
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(item, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}
