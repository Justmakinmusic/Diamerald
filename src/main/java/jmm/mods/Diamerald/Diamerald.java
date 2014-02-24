package jmm.mods.Diamerald;

import jmm.mods.Diamerald.blocks.BlockDirtchest;
import jmm.mods.Diamerald.blocks.GSTorch;
import jmm.mods.Diamerald.blocks.Grinder;
import jmm.mods.Diamerald.blocks.oreDiamerald;
import jmm.mods.Diamerald.grinder.GuiHandler;
import jmm.mods.Diamerald.items.Ddusts;
import jmm.mods.Diamerald.items.Dgems;
import jmm.mods.Diamerald.items.Diameraldaxe;
import jmm.mods.Diamerald.items.Diameraldboots;
import jmm.mods.Diamerald.items.Diameraldbow;
import jmm.mods.Diamerald.items.Diameraldhelmet;
import jmm.mods.Diamerald.items.Diameraldhoe;
import jmm.mods.Diamerald.items.Diameraldlegs;
import jmm.mods.Diamerald.items.Diameraldpickaxe;
import jmm.mods.Diamerald.items.Diameraldplate;
import jmm.mods.Diamerald.items.Diameraldshovel;
import jmm.mods.Diamerald.items.Diameraldsword;
import jmm.mods.Diamerald.items.berylSlag;
import jmm.mods.Diamerald.items.blackDiameraldhelmet;
import jmm.mods.Diamerald.items.blackDiameraldpickaxe;
import jmm.mods.Diamerald.items.blackDiameraldsword;
import jmm.mods.Diamerald.proxy.DiameraldProxy;
import jmm.mods.Diamerald.tileentity.TileEntityChestDC;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "Diamerald", name = "Diamerald", version = "1.7.2_3")

public class Diamerald {

	@Instance("Diamerald")
	public static Diamerald instance;

	// Enum ToolMaterial//

	public static ToolMaterial DIAMERALD = EnumHelper.addToolMaterial(
			"DIAMERALD", 4, 2000, 12.0f, 6.0f, 10);
	public static ToolMaterial BLACKDIAMERALD = EnumHelper.addToolMaterial(
			"BLACKDIAMERALD", 4, 2500, 16.0f, 14.0f, 10);

	// Enum ArmorMaterial//

	public static ArmorMaterial diamerald = EnumHelper.addArmorMaterial(
			"diamerald", 33, new int[] { 4, 9, 7, 4 }, 10);
	public static ArmorMaterial blackdiamerald = EnumHelper.addArmorMaterial(
			"blackdiamerald", 33, new int[] { 5, 10, 8, 5 }, 10);

	// Creative Tab//

	public static CreativeTabs tabDiamerald = new CreativeTabs("tabDiamerald") {
		public Item getTabIconItem() {
			return Diamerald.Diameraldgem;
		}

	};

	// Blocks and Items //

	public static Block oreDiamerald;
	public static Block GSTorch;
	public static Block BlockDirtchest;
	public static Block Grinder;
	public static Block Grinder_on;
	public static Item Diameraldgem;
	public static Item Diameraldsword;
	public static Item Diameraldpickaxe;
	public static Item Diameraldaxe;
	public static Item Diameraldshovel;
	public static Item Diameraldhoe;
	public static Item Diameraldhelmet;
	public static Item Diameraldplate;
	public static Item Diameraldlegs;
	public static Item Diameraldboots;
	public static ItemBow Diameraldbow;
	public static Item Roughgem;
	public static Item blackRoughgem;
	public static Item blackDiameraldgem;
	public static Item blackDiameraldsword;
	public static Item blackDiameraldpickaxe;
	public static Item blackDiameraldhelmet;
	public static Item Diameralddust;
	public static Item EmeralddustTiny;
	public static Item Emeralddust;
	public static Item berylSlag;
	public static Item Golddust;
	public static Item Irondust;

	// Proxy and Preload//

	@SidedProxy(clientSide = "jmm.mods.Diamerald.proxy.DiameraldClient", serverSide = "jmm.mods.Diamerald.proxy.DiameraldProxy")
	public static DiameraldProxy proxy;

	@EventHandler
	public void PreLoad(FMLPreInitializationEvent event) {
		
		// Init Blocks and Items//

		oreDiamerald = GameRegistry.registerBlock(new oreDiamerald(), "oreDiamerald").setBlockName("oreDiamerald")
				.setBlockTextureName("oreDiamerald").setCreativeTab(tabDiamerald);
		GSTorch = GameRegistry.registerBlock(new GSTorch(Material.glass), "GSTorch").setBlockName("GSTorch")
				.setBlockTextureName("GStorch").setCreativeTab(tabDiamerald);
		BlockDirtchest = GameRegistry.registerBlock(new BlockDirtchest(0, Material.ground), "Dirtchest").setBlockName(
				"Dirtchest").setBlockTextureName("dirt").setCreativeTab(tabDiamerald);
		Grinder = GameRegistry.registerBlock(new Grinder(false), "Grinder").setBlockName("Grinder").setCreativeTab(tabDiamerald);
		Grinder_on = GameRegistry.registerBlock(new Grinder(true), "Grinder_on").setBlockName("Grinder").setLightLevel(0.5f);
		Diameraldgem = GameRegistry.registerItem(new Dgems(), "Diameraldgem", "Diamerald").setUnlocalizedName("Diameraldgem").setCreativeTab(tabDiamerald);
		Roughgem = GameRegistry.registerItem(new Dgems(), "Roughgem", "Diamerald").setUnlocalizedName("Roughgem").setCreativeTab(tabDiamerald);
		Diameraldsword = GameRegistry.registerItem(new Diameraldsword(DIAMERALD), "Diameraldsword", "Diamerald")
				.setUnlocalizedName("Diameraldsword").setCreativeTab(tabDiamerald);
		Diameraldpickaxe = GameRegistry.registerItem(new Diameraldpickaxe(DIAMERALD), "Diameraldpickaxe", "Diamerald")
				.setUnlocalizedName("Diameraldpickaxe").setCreativeTab(tabDiamerald);
		Diameraldaxe = GameRegistry.registerItem(new Diameraldaxe(DIAMERALD), "Diameraldaxe", "Diamerald")
				.setUnlocalizedName("Diameraldaxe").setCreativeTab(tabDiamerald);
		Diameraldshovel = GameRegistry.registerItem(new Diameraldshovel(DIAMERALD), "Diameraldshovel", "Diamerald")
				.setUnlocalizedName("Diameraldshovel").setCreativeTab(tabDiamerald);
		Diameraldhoe = GameRegistry.registerItem(new Diameraldhoe(DIAMERALD), "Diameraldhoe", "Diamerald")
				.setUnlocalizedName("Diameraldhoe").setCreativeTab(tabDiamerald);
		Diameraldbow = (ItemBow) GameRegistry.registerItem(new Diameraldbow(DIAMERALD), "Diameraldbow", "Diamerald")
				.setUnlocalizedName("Dbow").setCreativeTab(tabDiamerald);
		Diameraldhelmet = GameRegistry.registerItem(new Diameraldhelmet(diamerald, 3, 0), "Diameraldhelmet", "Diamerald")
				.setUnlocalizedName("Diameraldhelmet").setCreativeTab(tabDiamerald);
		Diameraldplate = GameRegistry.registerItem(new Diameraldplate(diamerald, 3, 1), "Diameraldplate", "Diamerald")
				.setUnlocalizedName("Diameraldplate").setCreativeTab(tabDiamerald);
		Diameraldlegs = GameRegistry.registerItem(new Diameraldlegs(diamerald, 3, 2), "Diameraldlegs", "Diamerald")
				.setUnlocalizedName("Diameraldlegs").setCreativeTab(tabDiamerald);
		Diameraldboots = GameRegistry.registerItem(new Diameraldboots(diamerald, 3, 3), "Diameraldboots", "Diamerald")
				.setUnlocalizedName("Diameraldboots").setCreativeTab(tabDiamerald);
		blackDiameraldgem = GameRegistry.registerItem(new Dgems(), "blackDiameraldgem", "Diamerald")
				.setUnlocalizedName("blackDiameraldgem").setCreativeTab(tabDiamerald);
		blackRoughgem = GameRegistry.registerItem(new Dgems(), "blackRoughgem", "Diamerald").setUnlocalizedName("blackRoughgem").setCreativeTab(tabDiamerald);
		blackDiameraldsword = GameRegistry.registerItem(new blackDiameraldsword(BLACKDIAMERALD), "blackDiameraldsword", "Diamerald")
				.setUnlocalizedName("blackDiameraldsword").setCreativeTab(tabDiamerald);
		blackDiameraldpickaxe = GameRegistry.registerItem(new blackDiameraldpickaxe(BLACKDIAMERALD), "blackDiameraldpickaxe", "Diamerald")
				.setUnlocalizedName("blackDiameraldpickaxe").setCreativeTab(tabDiamerald);
		blackDiameraldhelmet = GameRegistry.registerItem(new blackDiameraldhelmet(blackdiamerald, 3, 0), "blackDiameraldhelmet", "Diamerald")
				.setUnlocalizedName("blackDiameraldhelmet").setCreativeTab(tabDiamerald);
		Diameralddust = GameRegistry.registerItem(new Ddusts(), "Diameralddust", "Diamerald").setUnlocalizedName("Diameralddust").setCreativeTab(tabDiamerald);
//		EmeralddustTiny = GameRegistry.registerItem(new Ddusts(), "EmeralddustTiny", "Diamerald").setUnlocalizedName("EmeralddustTiny").setCreativeTab(tabDiamerald);;
//		Emeralddust = GameRegistry.registerItem(new Ddusts(), "Emeralddust", "Diamerald").setUnlocalizedName("Emeralddust").setCreativeTab(tabDiamerald);
		Golddust = GameRegistry.registerItem(new Ddusts(), "Golddust", "Diamerald").setUnlocalizedName("Golddust").setCreativeTab(tabDiamerald);
		Irondust = GameRegistry.registerItem(new Ddusts(), "Irondust", "Diamerald").setUnlocalizedName("Irondust").setCreativeTab(tabDiamerald);

		// Registering Blocks and Items //

		GameRegistry.registerWorldGenerator(new WorldGeneratorDiamerald(), 1);
/*		GameRegistry.registerBlock(oreDiamerald, "oreDiamerald");
		GameRegistry.registerBlock(GSTorch, "GSTorch");
		GameRegistry.registerBlock(BlockDirtchest, "Dirtchest");
		GameRegistry.registerBlock(Grinder, "Grinder");
		GameRegistry.registerBlock(Grinder_on, "Grinder_on");
		GameRegistry.registerItem(Diameraldgem, "Diameraldgem");
		GameRegistry.registerItem(blackDiameraldgem, "blackDiameraldgem");
		GameRegistry.registerItem(Roughgem, "Roughgem");
		GameRegistry.registerItem(blackRoughgem, "blackRoughgem");
		GameRegistry.registerItem(Diameraldsword, "Diameraldsword");
		GameRegistry.registerItem(Diameraldpickaxe, "Diameraldpickaxe");
		GameRegistry.registerItem(blackDiameraldsword, "blackDiameraldsword");
		GameRegistry.registerItem(blackDiameraldpickaxe,
				"blackDiameraldpickaxe");
		GameRegistry.registerItem(Diameraldhoe, "Diameraldhoe");
		GameRegistry.registerItem(blackDiameraldhelmet, "blackDiameraldhelmet");
		GameRegistry.registerItem(Diameraldaxe, "Diameraldaxe");
		GameRegistry.registerItem(Diameraldshovel, "Diameraldshovel");
		GameRegistry.registerItem(Diameraldhelmet, "Diameraldhelmet");
		GameRegistry.registerItem(Diameraldplate, "Diameraldplate");
		GameRegistry.registerItem(Diameraldlegs, "Diameraldlegs");
		GameRegistry.registerItem(Diameraldboots, "Diameraldboots");
		GameRegistry.registerItem(Diameraldbow, "Diameraldbow");
		GameRegistry.registerItem(Diameralddust, "Diameralddust");
		GameRegistry.registerItem(EmeralddustTiny, "EmeralddustTiny");
		GameRegistry.registerItem(Emeralddust, "Emeralddust");
		GameRegistry.registerItem(Golddust, "Golddust");
		GameRegistry.registerItem(Irondust,"Irondust");
*/		GameRegistry.registerTileEntity(TileEntityChestDC.class,
				"TileEntityChestDC");
		GameRegistry.registerTileEntity(TileEntityGrinder.class,
				"TileEntityGrinder");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		

	}

	// Load//

	@EventHandler
	public void load(FMLInitializationEvent event) {

		proxy.registerRenderInformation();

		// Loot generation//

		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.Diameraldgem), 1, 3, 20));

		// Recipes//

		GameRegistry.addSmelting(Diamerald.Roughgem, new ItemStack(
				Diamerald.Diameraldgem, 1), 500.0f);
		GameRegistry.addSmelting(Diamerald.blackRoughgem, new ItemStack(
				Diamerald.blackDiameraldgem, 1), 500.0f);
		GameRegistry.addSmelting(Diamerald.oreDiamerald, new ItemStack(
				Diamerald.Diameraldgem, 1), 500.0f);
		GameRegistry.addRecipe(new ItemStack(Diameraldsword, 1), new Object[] {
				" D ", " D ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldpickaxe, 1),
				new Object[] { "DDD", " S ", " S ", 'D',
						Diamerald.Diameraldgem, 'S', Items.stick });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldsword, 1), new Object[] {
						" B ", " S ", " R ", 'B', Diamerald.blackDiameraldgem,
						'S', new ItemStack(Items.skull, 1, 1), 'R',
						Items.blaze_rod });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldpickaxe, 1),
						new Object[] { "BSB", " R ", " R ", 'B',
								Diamerald.blackDiameraldgem, 'S',
								new ItemStack(Items.skull, 1, 1), 'R',
								Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(Diameraldaxe, 1), new Object[] {
				"DD ", "DS ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldshovel, 1), new Object[] {
				" D ", " S ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldhoe, 1), new Object[] {
				"DD ", " S ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldhelmet, 1), new Object[] {
				"DDD", "D D", 'D', Diamerald.Diameraldgem });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldhelmet, 1),
						new Object[] { "BBB", "B B", 'B',
								Diamerald.blackDiameraldgem });
		GameRegistry.addRecipe(new ItemStack(Diameraldplate, 1), new Object[] {
				"D D", "DDD", "DDD", 'D', Diamerald.Diameraldgem });
		GameRegistry.addRecipe(new ItemStack(Diameraldlegs, 1), new Object[] {
				"DDD", "D D", "D D", 'D', Diamerald.Diameraldgem });
		GameRegistry.addRecipe(new ItemStack(Diameraldboots, 1), new Object[] {
				"D D", "D D", 'D', Diamerald.Diameraldgem });
		GameRegistry.addRecipe(new ItemStack(Diameraldbow, 1), new Object[] {
				" DW", "B W", " DW", 'D', Diamerald.Diameraldgem, 'W',
				Items.string, 'B', Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(GSTorch, 16), new Object[] { " G",
				" G", 'G', Blocks.glowstone });
		GameRegistry.addShapelessRecipe(new ItemStack(Roughgem, 1),
				new Object[] { Items.diamond, Items.emerald });
		GameRegistry.addRecipe(new ItemStack(blackRoughgem, 1), new Object[] {
				" I ", "IRI", " I ", 'R', Diamerald.Roughgem, 'I', Items.dye });
		GameRegistry.addRecipe(new ItemStack(BlockDirtchest, 1), new Object[] {
				"AAA", "ACA", "AAA", 'A', Blocks.dirt, 'C', Blocks.chest });
		GameRegistry.addSmelting(Diamerald.Golddust, new ItemStack(Items.gold_ingot, 1), 0.7f);
		GameRegistry.addSmelting(Diamerald.Irondust, new ItemStack(Items.iron_ingot, 1), 0.7f);
		GameRegistry.addSmelting(Diamerald.Diameralddust, new ItemStack(Diamerald.Diameraldgem, 1), 1.0f);
		GameRegistry.addRecipe(new ItemStack(Grinder, 1), new Object[] { "IGI",
				"RFR", "XPX", 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'X', Blocks.stone, 'R', Items.redstone, 'F',
				Items.flint, 'P', Blocks.piston });

		// Recipes for IC2//

		/*
		 * if (Loader.isModLoaded("IC2")) {
		 * 
		 * Diameralddust = (new Diameralddust(DiameralddustID))
		 * .setUnlocalizedName("Diameralddust");
		 * LanguageRegistry.addName(Diameralddust, "Diamerald Dust");
		 * Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(
		 * oreDiamerald, 1)), null, (new ItemStack(Diameralddust, 2)));
		 * Recipes.compressor.addRecipe(new RecipeInputItemStack( new
		 * ItemStack(Diameralddust, 1)), null, (new ItemStack( Diameraldgem,
		 * 1))); Recipes.compressor.addRecipe(new RecipeInputItemStack( new
		 * ItemStack(Item.skull, 1, 1)), null, (new ItemStack(
		 * blackDiameraldgem, 1)));
		 * 
		 * }
		 */

		// Recipes for ThermalExpansion//

		if (Loader.isModLoaded("ThermalExpansion")) {

			Diameralddust = new Ddusts()
					.setUnlocalizedName("Diameralddust");
			EmeralddustTiny = new Ddusts()
					.setUnlocalizedName("EmeralddustTiny");
			Emeralddust = new Ddusts().setUnlocalizedName("Emeralddust");
			berylSlag = new berylSlag().setUnlocalizedName("berylSlag");
			GameRegistry.registerItem(berylSlag, "berylSlag");
			GameRegistry.addRecipe(new ItemStack(Emeralddust, 1), new Object[] {
					"EE", "EE", 'E', Diamerald.EmeralddustTiny });

			{
				NBTTagCompound toSend = new NBTTagCompound();

				toSend.setInteger("energy", 10000);
				toSend.setTag("primaryInput", new NBTTagCompound());
				toSend.setTag("secondaryInput", new NBTTagCompound());
				toSend.setTag("primaryOutput", new NBTTagCompound());
				toSend.setTag("secondaryOutput", new NBTTagCompound());
				new ItemStack(Emeralddust, 2).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(Blocks.sand, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(Items.emerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				new ItemStack(berylSlag, 1).writeToNBT(toSend
						.getCompoundTag("secondaryOutput"));
				toSend.setInteger("secondaryChance", 5);
				FMLInterModComms.sendMessage("ThermalExpansion",
						"SmelterRecipe", toSend);
			}

			{
				NBTTagCompound toSend = new NBTTagCompound();
				toSend.setInteger("energy", 6000);
				toSend.setTag("primaryInput", new NBTTagCompound());
				toSend.setTag("secondaryInput", new NBTTagCompound());
				toSend.setTag("primaryOutput", new NBTTagCompound());
				new ItemStack(Emeralddust, 1).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(berylSlag, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(Items.emerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				FMLInterModComms.sendMessage("ThermalExpansion",
						"SmelterRecipe", toSend);
			}

			{
				NBTTagCompound toSend = new NBTTagCompound();
				toSend.setInteger("energy", 10000);
				toSend.setTag("primaryInput", new NBTTagCompound());
				toSend.setTag("secondaryInput", new NBTTagCompound());
				toSend.setTag("primaryOutput", new NBTTagCompound());
				toSend.setTag("secondaryOutput", new NBTTagCompound());
				new ItemStack(Diameralddust, 2).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(Blocks.sand, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(Diameraldgem, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				new ItemStack(berylSlag, 1).writeToNBT(toSend
						.getCompoundTag("secondaryOutput"));
				toSend.setInteger("secondaryChance", 5);
				FMLInterModComms.sendMessage("ThermalExpansion",
						"SmelterRecipe", toSend);
			}

			{
				NBTTagCompound toSend = new NBTTagCompound();
				toSend.setInteger("energy", 8000);
				toSend.setTag("input", new NBTTagCompound());
				toSend.setTag("primaryOutput", new NBTTagCompound());
				toSend.setTag("secondaryOutput", new NBTTagCompound());
				new ItemStack(oreDiamerald, 1).writeToNBT(toSend
						.getCompoundTag("input"));
				new ItemStack(Diameralddust, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				new ItemStack(EmeralddustTiny, 1).writeToNBT(toSend
						.getCompoundTag("secondaryOutput"));
				toSend.setInteger("secondaryChance", 10);
				FMLInterModComms.sendMessage("ThermalExpansion",
						"PulverizerRecipe", toSend);
			}

		}

	}

}
