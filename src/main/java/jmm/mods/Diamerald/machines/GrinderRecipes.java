package jmm.mods.Diamerald.machines;

//import ic2.api.item.IC2Items;

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
    private Map grindingList2 = new HashMap();
    private Map experienceList = new HashMap();
    private Map secondaryOutput = new HashMap();
    private Map chance = new HashMap();
   
    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static GrinderRecipes instance()
    {
        return grinderBase;
    }
    
    private GrinderRecipes()
    {
    
    	 this.addGrinderRecipeForBlock(Diamerald.oreDiamerald, new ItemStack(Diamerald.dustDiamerald));
         this.addGrinderRecipeForBlock(Blocks.iron_ore, new ItemStack(Diamerald.dustIron));
         this.addGrinderRecipeForBlock(Blocks.gold_ore, new ItemStack(Diamerald.dustGold));
         this.addGrinderRecipeForBlock(Blocks.emerald_ore, new ItemStack(Diamerald.dustEmerald));
         this.addGrinderRecipeForBlock(Blocks.diamond_ore, new ItemStack(Diamerald.dustDiamond));
    }
    
    public void addGrinderRecipeForBlock (Block input, ItemStack output)
    {
    	this.addGrinding(Item.getItemFromBlock(input), output);
    }
    
    public void addGrinding (Item input, ItemStack output)
    {
    	this.addGrinderRecipe(new ItemStack(input, 1, 32767), output);
    }
    
    public void addGrinderRecipe (ItemStack input, ItemStack output)
    {
    	this.grindingList.put(input, output);      
    }
   
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
        return par2Item.getItem() == par1Item.getItem() && (par2Item.getMetadata() == 32767 || par2Item.getMetadata() == par1Item.getMetadata());
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
