package jmm.mods.Diamerald.machines;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jmm.mods.Diamerald.Diamerald;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class DfurnaceRecipes {
	
private static final DfurnaceRecipes dfurnaceBase = new DfurnaceRecipes();
	
    /**
     * The list of Dfurnace results.
     */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private Map smeltingList2 = new HashMap();
   
    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static DfurnaceRecipes instance()
    {
        return dfurnaceBase;
    }
    
    private DfurnaceRecipes()
    {
    	
    	 this.addDfurnaceRecipeForBlock(Blocks.iron_ore, new ItemStack(Items.iron_ingot), 0.7F);
         this.addDfurnaceRecipeForBlock(Blocks.gold_ore, new ItemStack(Items.gold_ingot), 1.0F);
         this.addDfurnaceRecipeForBlock(Blocks.diamond_ore, new ItemStack(Items.diamond), 1.0F);
         this.addDfurnaceRecipeForBlock(Blocks.sand, new ItemStack(Blocks.glass), 0.1F);
         this.addSmelting(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.35F);
         this.addSmelting(Items.beef, new ItemStack(Items.cooked_beef), 0.35F);
         this.addSmelting(Items.chicken, new ItemStack(Items.cooked_chicken), 0.35F);
         this.addDfurnaceRecipeForBlock(Blocks.cobblestone, new ItemStack(Blocks.stone), 0.1F);
         this.addSmelting(Items.clay_ball, new ItemStack(Items.brick), 0.3F);
         this.addDfurnaceRecipeForBlock(Blocks.clay, new ItemStack(Blocks.hardened_clay), 0.35F);
         this.addDfurnaceRecipeForBlock(Blocks.cactus, new ItemStack(Items.dye, 1, 2), 0.2F);
         this.addDfurnaceRecipeForBlock(Blocks.log, new ItemStack(Items.coal, 1, 1), 0.15F);
         this.addDfurnaceRecipeForBlock(Blocks.log2, new ItemStack(Items.coal, 1, 1), 0.15F);
         this.addDfurnaceRecipeForBlock(Blocks.emerald_ore, new ItemStack(Items.emerald), 1.0F);
         this.addSmelting(Items.potato, new ItemStack(Items.baked_potato), 0.35F);
         this.addDfurnaceRecipeForBlock(Blocks.netherrack, new ItemStack(Items.netherbrick), 0.1F);
         ItemFishFood.FishType[] afishtype = ItemFishFood.FishType.values();
         int i = afishtype.length;

         for (int j = 0; j < i; ++j)
         {
             ItemFishFood.FishType fishtype = afishtype[j];

             if (fishtype.getCookable())
             {
                 this.addDfurnaceRecipe(new ItemStack(Items.fish, 1, fishtype.getItemDamage()), new ItemStack(Items.cooked_fish, 1, fishtype.getItemDamage()), 0.35F);
             }
         }

         this.addDfurnaceRecipeForBlock(Blocks.coal_ore, new ItemStack(Items.coal), 0.1F);
         this.addDfurnaceRecipeForBlock(Blocks.redstone_ore, new ItemStack(Items.redstone), 0.7F);
         this.addDfurnaceRecipeForBlock(Blocks.lapis_ore, new ItemStack(Items.dye, 1, 4), 0.2F);
         this.addDfurnaceRecipeForBlock(Blocks.quartz_ore, new ItemStack(Items.quartz), 0.2F);
         this.addDfurnaceRecipeForBlock(Diamerald.oreDiamerald, new ItemStack(Diamerald.gemDiamerald), 1.0f);
         this.addSmelting(Diamerald.dustBlackDiamerald, new ItemStack(Diamerald.gemBlackDiamerald), 1.0f);
         this.addSmelting(Diamerald.dustDiamerald, new ItemStack(Diamerald.gemDiamerald), 1.0f);
         this.addSmelting(Diamerald.dustDiamond, new ItemStack(Items.diamond), 1.0f);
         this.addSmelting(Diamerald.dustEmerald, new ItemStack(Items.emerald), 1.0f);
         this.addSmelting(Diamerald.dustGold, new ItemStack(Items.gold_ingot), 0.5f);
         this.addSmelting(Diamerald.dustIron, new ItemStack(Items.iron_ingot), 0.5f);
     }
    
    public void func_151393_a(Block input, ItemStack output, float xp)
    {
        this.func_151396_a(Item.getItemFromBlock(input), output, xp);
    }

    public void func_151396_a(Item input, ItemStack output, float xp)
    {
        this.func_151394_a(new ItemStack(input, 1, 32767), output, xp);
    }

    public void func_151394_a(ItemStack input, ItemStack output, float xp)
    {
        this.smeltingList.put(input, output);
        this.smeltingList2.put(input, output);
        this.experienceList.put(output, Float.valueOf(xp));
    }
    
    public void addDfurnaceRecipeForBlock (Block input, ItemStack output, float xp)
    {
    	this.addSmelting(Item.getItemFromBlock(input), output, xp);
    }
    
    public void addSmelting (Item input, ItemStack output,float xp)
    {
    	this.addDfurnaceRecipe(new ItemStack(input, 1, 32767), output, xp);
    }
    
    public void addDfurnaceRecipe (ItemStack input, ItemStack output, float xp)
    {
    	this.smeltingList.put(input, output);
        this.smeltingList2.put(input, output);
        this.experienceList.put(output, Float.valueOf(xp));      
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
        Iterator iterator = this.smeltingList.entrySet().iterator();
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
    
    public ItemStack getSmeltingResult2(ItemStack par1ItemStack)
    {
        Iterator iterator = this.smeltingList2.entrySet().iterator();
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
    
    /*public ItemStack getOutput2(ItemStack par1ItemStack)
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
    }*/

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }
    
    public Map getSmeltingList2()
    {
    	return this.smeltingList2;
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
