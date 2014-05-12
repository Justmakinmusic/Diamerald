package jmm.mods.Diamerald.grinder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;


public class GrinderRecipes
{
	private static final GrinderRecipes grinderBase = new GrinderRecipes();
	
    /**
     * The list of grinding results.
     */
    private Map grindingList = new HashMap();
    private Map experienceList = new HashMap();
    private Map secondaryOutput = new HashMap();
   
    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static GrinderRecipes smelting()
    {
        return grinderBase;
    }
    
    private GrinderRecipes()
    {
    	
    	 this.addGrinderRecipe(Diamerald.oreDiamerald, new ItemStack(Diamerald.Diameralddust));
         this.addGrinderRecipe(Blocks.iron_ore, new ItemStack(Diamerald.Irondust));
         this.addGrinderRecipe(Blocks.gold_ore, new ItemStack(Diamerald.Golddust));
         this.addGrinderRecipe(Blocks.emerald_ore, new ItemStack(Diamerald.Emeralddust));
         //this.addGrinderRecipe(Diamerald.oreDiamerald, new ItemStack(Diamerald.Diameralddust), new ItemStack(Diamerald.EmeralddustTiny));
    }
    
    public void func_151393_a(Block input, ItemStack output)
    {
        this.func_151396_a(Item.getItemFromBlock(input), output);
    }

    public void func_151396_a(Item input, ItemStack output)
    {
        this.func_151394_a(new ItemStack(input, 1, 32767), output);
    }

    public void func_151394_a(ItemStack input, ItemStack output)
    {
        this.grindingList.put(input, output);
    }
    
    public void addGrinderRecipe (Block input, ItemStack output)
    {
    	this.func_151393_a(input, output);
    }
    
    public void addGrinderRecipe (Item input, ItemStack output)
    {
    	this.func_151396_a(input, output);
    }
    
    public void addGrinderRecipe (ItemStack input, ItemStack output)
    {
        this.func_151394_a(input, output);
    }
    
    // Trying new recipes //
 /*   
    public void func_151393_b(Block input, ItemStack output, ItemStack output2)
    {
        this.func_151396_b(Item.getItemFromBlock(input), output, output2);
    }

    public void func_151396_b(Item input, ItemStack output, ItemStack output2)
    {
        this.func_151394_b(new ItemStack(input, 1, 32767), output, output2);
    }

    public void func_151394_b(ItemStack input, ItemStack output, ItemStack output2)
    {
        this.grindingList.put(input, output);
        this.secondaryOutput.put(input, output2);
    }
    
    public void addGrinderRecipe (Block input, ItemStack output, ItemStack output2)
    {
    	this.func_151393_b(input, output, output2);
    }
*/  
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
/*    
    public ItemStack getOutput2(ItemStack par1ItemStack)
    {
        Iterator iterator = this.secondaryOutput.entrySet().iterator();
        Entry entry;
        
          do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
           
        }
        
        while (!this.func_151397_b(par1ItemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();       
    }
    
    private boolean func_151397_b(ItemStack par1Item, ItemStack par2Item)
    {
        return par2Item.getItem() == par1Item.getItem() && (par2Item.getItemDamage() == 32767 || par2Item.getItemDamage() == par1Item.getItemDamage());
    }
*/
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
