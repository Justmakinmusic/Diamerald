package jmm.mods.Diamerald.grinder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jmm.mods.Diamerald.Diamerald;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class GrinderRecipes
{
	private static final GrinderRecipes grinderBase = new GrinderRecipes();
	 Block input;
	 ItemStack output1;
	 ItemStack output2;
	 int chance;
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
    	 this.addGrinderRecipe(Diamerald.oreDiamerald, new ItemStack(Diamerald.Diameralddust,2), 1.0F);
         this.addGrinderRecipe(Blocks.iron_ore, new ItemStack(Diamerald.Irondust,2), 0.7F);
         this.addGrinderRecipe(Blocks.gold_ore, new ItemStack(Diamerald.Golddust,2), 1.0F);
         
    }
    
    public void addGrinderRecipe(Block input, ItemStack output1, float xp)
    {
        this.addSmelting(Item.getItemFromBlock(input), output1, xp);
    }

    public void addSmelting(Item item, ItemStack itemstack, float xp)
    {
        this.addSmelting(new ItemStack(item, 1, 32767), itemstack, xp);
    }

    public void addSmelting(ItemStack itemstack, ItemStack itemstack2, float xp)
    {
        this.grindingList.put(itemstack, itemstack2);
        this.experienceList.put(itemstack2, Float.valueOf(xp));
    }
    
       //Not Implemented Yet//
    public void addGrinderRecipe(Block input, ItemStack output1, ItemStack output2, int chance, float xp)
    {
    	this.input = input;
    	this.output1 = output1;
    	this.output2 = output2;
        this.addSmelting(Item.getItemFromBlock(input), output1, output2, chance, xp);
    }

    public void addSmelting(Item item, ItemStack output1, ItemStack output2, int chance, float xp)
    {
        this.addSmelting(new ItemStack(item, 1, 32767), output1, output2, chance, xp);
    }

    public void addSmelting(ItemStack input, ItemStack output1, ItemStack output2, int chance, float xp)
    {
        this.grindingList.put(input, output2);
        this.experienceList.put(output2, Float.valueOf(xp));
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
