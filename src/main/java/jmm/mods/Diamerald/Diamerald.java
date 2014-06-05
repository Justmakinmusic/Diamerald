package jmm.mods.Diamerald;

import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import jmm.mods.Diamerald.blocks.BlockDirtchest;
import jmm.mods.Diamerald.blocks.Dfurnace;
import jmm.mods.Diamerald.blocks.GSTorch;
import jmm.mods.Diamerald.blocks.Grinder;
import jmm.mods.Diamerald.blocks.oreDiamerald;
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
import jmm.mods.Diamerald.items.blackDiameraldhelmet;
import jmm.mods.Diamerald.items.blackDiameraldpickaxe;
import jmm.mods.Diamerald.items.blackDiameraldsword;
import jmm.mods.Diamerald.machines.GrinderRecipes;
import jmm.mods.Diamerald.machines.GuiHandler;
import jmm.mods.Diamerald.packethandler.PacketPipeline;
import jmm.mods.Diamerald.proxy.DiameraldProxy;
import jmm.mods.Diamerald.tileentity.TileEntityChestDC;
import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
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
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "Diamerald", name = "Diamerald", version = "1.7.2_4")
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
			return Diamerald.gem_Diamerald;
		}
	};

	// Blocks and Items //

	public static Block oreDiamerald;
	public static Block GSTorch;
	public static Block BlockDirtchest;
	public static Block Grinder;
	public static Block Grinder_on;
	public static Block Dfurnace;
	public static Block Dfurnace_on;
	public static Item gem_Diamerald;
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
	// public static Item Roughgem;
	// public static Item blackRoughgem;
	public static Item gem_BlackDiamerald;
	public static Item blackDiameraldsword;
	public static Item blackDiameraldpickaxe;
	public static Item blackDiameraldhelmet;
	public static Item dust_Diamerald;
	public static Item dust_BlackDiamerald;
	public static Item dust_EmeraldTiny;
	public static Item dust_Emerald;
	public static Item dust_Diamond;
	public static Item berylSlag;
	public static Item dust_Gold;
	public static Item dust_Iron;

	// Proxy and Preload//

	@SidedProxy(clientSide = "jmm.mods.Diamerald.proxy.DiameraldClient", serverSide = "jmm.mods.Diamerald.proxy.DiameraldProxy")
	public static DiameraldProxy proxy;
	public static final PacketPipeline packetPipeline = new PacketPipeline();

	@EventHandler
	public void PreLoad(FMLPreInitializationEvent event) {

		// Init Blocks and Items//

		oreDiamerald = GameRegistry
				.registerBlock(new oreDiamerald(), "oreDiamerald")
				.setBlockName("oreDiamerald")
				.setBlockTextureName("oreDiamerald")
				.setCreativeTab(tabDiamerald);
		GSTorch = GameRegistry
				.registerBlock(new GSTorch(Material.glass), "GSTorch")
				.setBlockName("GSTorch").setBlockTextureName("GStorch")
				.setCreativeTab(tabDiamerald);
		BlockDirtchest = GameRegistry
				.registerBlock(new BlockDirtchest(0, Material.ground),
						"Dirtchest").setBlockName("Dirtchest")
				.setBlockTextureName("dirt").setCreativeTab(tabDiamerald);
		Grinder = GameRegistry.registerBlock(new Grinder(false), "Grinder")
				.setBlockName("Grinder").setCreativeTab(tabDiamerald);
		Grinder_on = GameRegistry
				.registerBlock(new Grinder(true), "Grinder_on")
				.setBlockName("Grinder").setLightLevel(0.5f);
		Dfurnace = GameRegistry.registerBlock(new Dfurnace(false), "Dfurnace")
				.setBlockName("Dfurnace").setCreativeTab(tabDiamerald);
		Dfurnace_on = GameRegistry
				.registerBlock(new Dfurnace(true), "Dfurnace_on")
				.setBlockName("Dfurnace").setLightLevel(0.5f);
		gem_Diamerald = GameRegistry
				.registerItem(new Dgems(), "gem_Diamerald", "Diamerald")
				.setUnlocalizedName("gem_Diamerald")
				.setCreativeTab(tabDiamerald);
		/*
		 * Roughgem = GameRegistry .registerItem(new Dgems(), "Roughgem",
		 * "Diamerald")
		 * .setUnlocalizedName("Roughgem").setCreativeTab(tabDiamerald);
		 */
		Diameraldsword = GameRegistry
				.registerItem(new Diameraldsword(DIAMERALD), "Diameraldsword",
						"Diamerald").setUnlocalizedName("Diameraldsword")
				.setCreativeTab(tabDiamerald);
		Diameraldpickaxe = GameRegistry
				.registerItem(new Diameraldpickaxe(DIAMERALD),
						"Diameraldpickaxe", "Diamerald")
				.setUnlocalizedName("Diameraldpickaxe")
				.setCreativeTab(tabDiamerald);
		Diameraldaxe = GameRegistry
				.registerItem(new Diameraldaxe(DIAMERALD), "Diameraldaxe",
						"Diamerald").setUnlocalizedName("Diameraldaxe")
				.setCreativeTab(tabDiamerald);
		Diameraldshovel = GameRegistry
				.registerItem(new Diameraldshovel(DIAMERALD),
						"Diameraldshovel", "Diamerald")
				.setUnlocalizedName("Diameraldshovel")
				.setCreativeTab(tabDiamerald);
		Diameraldhoe = GameRegistry
				.registerItem(new Diameraldhoe(DIAMERALD), "Diameraldhoe",
						"Diamerald").setUnlocalizedName("Diameraldhoe")
				.setCreativeTab(tabDiamerald);
		Diameraldbow = (ItemBow) GameRegistry
				.registerItem(new Diameraldbow(DIAMERALD), "Diameraldbow",
						"Diamerald").setUnlocalizedName("Dbow")
				.setCreativeTab(tabDiamerald);
		Diameraldhelmet = GameRegistry
				.registerItem(new Diameraldhelmet(diamerald, 3, 0),
						"Diameraldhelmet", "Diamerald")
				.setUnlocalizedName("Diameraldhelmet")
				.setCreativeTab(tabDiamerald);
		Diameraldplate = GameRegistry
				.registerItem(new Diameraldplate(diamerald, 3, 1),
						"Diameraldplate", "Diamerald")
				.setUnlocalizedName("Diameraldplate")
				.setCreativeTab(tabDiamerald);
		Diameraldlegs = GameRegistry
				.registerItem(new Diameraldlegs(diamerald, 3, 2),
						"Diameraldlegs", "Diamerald")
				.setUnlocalizedName("Diameraldlegs")
				.setCreativeTab(tabDiamerald);
		Diameraldboots = GameRegistry
				.registerItem(new Diameraldboots(diamerald, 3, 3),
						"Diameraldboots", "Diamerald")
				.setUnlocalizedName("Diameraldboots")
				.setCreativeTab(tabDiamerald);
		gem_BlackDiamerald = GameRegistry
				.registerItem(new Dgems(), "gem_BlackDiamerald", "Diamerald")
				.setUnlocalizedName("gem_BlackDiamerald")
				.setCreativeTab(tabDiamerald);
		/*
		 * blackRoughgem = GameRegistry .registerItem(new Dgems(),
		 * "blackRoughgem", "Diamerald") .setUnlocalizedName("blackRoughgem")
		 * .setCreativeTab(tabDiamerald);
		 */
		blackDiameraldsword = GameRegistry
				.registerItem(new blackDiameraldsword(BLACKDIAMERALD),
						"blackDiameraldsword", "Diamerald")
				.setUnlocalizedName("blackDiameraldsword")
				.setCreativeTab(tabDiamerald);
		blackDiameraldpickaxe = GameRegistry
				.registerItem(new blackDiameraldpickaxe(BLACKDIAMERALD),
						"blackDiameraldpickaxe", "Diamerald")
				.setUnlocalizedName("blackDiameraldpickaxe")
				.setCreativeTab(tabDiamerald);
		blackDiameraldhelmet = GameRegistry
				.registerItem(new blackDiameraldhelmet(blackdiamerald, 3, 0),
						"blackDiameraldhelmet", "Diamerald")
				.setUnlocalizedName("blackDiameraldhelmet")
				.setCreativeTab(tabDiamerald);
		dust_Diamerald = GameRegistry
				.registerItem(new Ddusts(), "dust_Diamerald", "Diamerald")
				.setUnlocalizedName("dust_Diamerald")
				.setCreativeTab(tabDiamerald);
		dust_BlackDiamerald = GameRegistry
				.registerItem(new Ddusts(), "dust_BlackDiamerald", "Diamerald")
				.setUnlocalizedName("dust_BlackDiamerald")
				.setCreativeTab(tabDiamerald);
		/*
		 * EmeralddustTiny = GameRegistry .registerItem(new Ddusts(),
		 * "EmeralddustTiny", "Diamerald")
		 * .setUnlocalizedName("EmeralddustTiny") .setCreativeTab(tabDiamerald);
		 */
		dust_Emerald = GameRegistry
				.registerItem(new Ddusts(), "dust_Emerald", "Diamerald")
				.setUnlocalizedName("dust_Emerald")
				.setCreativeTab(tabDiamerald);
		dust_Diamond = GameRegistry
				.registerItem(new Ddusts(), "dust_Diamond", "Diamerald")
				.setUnlocalizedName("dust_Diamond")
				.setCreativeTab(tabDiamerald);
		/*
		 * berylSlag = GameRegistry .registerItem(new Ddusts(), "berylSlag",
		 * "Diamerald")
		 * .setUnlocalizedName("berylSlag").setCreativeTab(tabDiamerald);
		 */
		dust_Gold = GameRegistry
				.registerItem(new Ddusts(), "dust_Gold", "Diamerald")
				.setUnlocalizedName("dust_Gold").setCreativeTab(tabDiamerald);
		dust_Iron = GameRegistry
				.registerItem(new Ddusts(), "dust_Iron", "Diamerald")
				.setUnlocalizedName("dust_Iron").setCreativeTab(tabDiamerald);

		// Registering WorldGenerator, Ore, TileEntity, Gui //

		OreDictionary.registerOre("oreDiamerald", oreDiamerald);
		OreDictionary.registerOre("gem_Diamerald", gem_Diamerald);
		OreDictionary.registerOre("dust_Diamerald", dust_Diamerald);
		OreDictionary.registerOre("gem_BlackDiamerald", gem_BlackDiamerald);
		OreDictionary.registerOre("dust_BlackDiamerald", dust_BlackDiamerald);
		OreDictionary.registerOre("dust_Emerald", dust_Emerald);
		OreDictionary.registerOre("dust_Diamond", dust_Diamond);
		OreDictionary.registerOre("dust_Gold", dust_Gold);
		OreDictionary.registerOre("dust_Iron", dust_Iron);
		GameRegistry.registerWorldGenerator(new WorldGeneratorDiamerald(), 1);
		GameRegistry.registerTileEntity(TileEntityChestDC.class,
				"TileEntityChestDC");
		GameRegistry.registerTileEntity(TileEntityGrinder.class,
				"TileEntityGrinder");
		GameRegistry.registerTileEntity(TileEntityDfurnace.class,
				"TileEntityDfrunace");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	}

	// Load//

	@EventHandler
	public void load(FMLInitializationEvent event) {

		proxy.registerRenderInformation();
		packetPipeline.initalise();

		// Loot generation//

		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gem_Diamerald), 1, 3, 20));

		// Recipes//

		/*
		 * GameRegistry.addSmelting(Diamerald.Roughgem, new ItemStack(
		 * Diamerald.Diameraldgem, 1), 500.0f);
		 * GameRegistry.addSmelting(Diamerald.blackRoughgem, new ItemStack(
		 * Diamerald.blackDiameraldgem, 1), 500.0f);
		 */
		GameRegistry.addSmelting(Diamerald.oreDiamerald, new ItemStack(
				Diamerald.gem_Diamerald, 1), 500.0f);
		GameRegistry.addRecipe(new ItemStack(Diameraldsword, 1), new Object[] {
				" D ", " D ", " S ", 'D', Diamerald.gem_Diamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldpickaxe, 1),
				new Object[] { "DDD", " S ", " S ", 'D',
						Diamerald.gem_Diamerald, 'S', Items.stick });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldsword, 1), new Object[] {
						" B ", " S ", " R ", 'B', Diamerald.gem_BlackDiamerald,
						'S', new ItemStack(Items.skull, 1, 1), 'R',
						Items.blaze_rod });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldpickaxe, 1),
						new Object[] { "BSB", " R ", " R ", 'B',
								Diamerald.gem_BlackDiamerald, 'S',
								new ItemStack(Items.skull, 1, 1), 'R',
								Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(Diameraldaxe, 1), new Object[] {
				"DD ", "DS ", " S ", 'D', Diamerald.gem_Diamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldshovel, 1), new Object[] {
				" D ", " S ", " S ", 'D', Diamerald.gem_Diamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldhoe, 1), new Object[] {
				"DD ", " S ", " S ", 'D', Diamerald.gem_Diamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldhelmet, 1), new Object[] {
				"DDD", "D D", 'D', Diamerald.gem_Diamerald });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldhelmet, 1),
						new Object[] { "BBB", "B B", 'B',
								Diamerald.gem_BlackDiamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldplate, 1), new Object[] {
				"D D", "DDD", "DDD", 'D', Diamerald.gem_Diamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldlegs, 1), new Object[] {
				"DDD", "D D", "D D", 'D', Diamerald.gem_Diamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldboots, 1), new Object[] {
				"D D", "D D", 'D', Diamerald.gem_Diamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldbow, 1), new Object[] {
				" DW", "B W", " DW", 'D', Diamerald.gem_Diamerald, 'W',
				Items.string, 'B', Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(GSTorch, 4), new Object[] { " G",
				" S", 'G', Items.glowstone_dust, 'S', Items.stick });
		// GameRegistry.addShapelessRecipe(new ItemStack(Roughgem, 1),
		// new Object[] { Items.diamond, Items.emerald });
		// GameRegistry.addRecipe(new ItemStack(blackRoughgem, 1), new Object[]
		// {
		// " I ", "IRI", " I ", 'R', Diamerald.Roughgem, 'I', Items.dye });
		GameRegistry.addRecipe(new ItemStack(BlockDirtchest, 1), new Object[] {
				"AAA", "ACA", "AAA", 'A', Blocks.dirt, 'C', Blocks.chest });
		GameRegistry.addSmelting(Diamerald.dust_Gold, new ItemStack(
				Items.gold_ingot, 1), 0.7f);
		GameRegistry.addSmelting(Diamerald.dust_Iron, new ItemStack(
				Items.iron_ingot, 1), 0.7f);
		GameRegistry.addSmelting(Diamerald.dust_Diamerald, new ItemStack(
				Diamerald.gem_Diamerald, 1), 1.0f);
		GameRegistry.addSmelting(Diamerald.dust_BlackDiamerald, new ItemStack(
				Diamerald.gem_BlackDiamerald, 1), 1.0f);
		GameRegistry.addSmelting(Diamerald.dust_Emerald, new ItemStack(
				Items.emerald, 1), 0.8f);
		GameRegistry.addSmelting(Diamerald.dust_Diamond, new ItemStack(
				Items.diamond, 1), 1.0f);
		GameRegistry.addShapelessRecipe(new ItemStack(dust_BlackDiamerald, 2),
				new Object[] { Diamerald.dust_Diamerald, Items.blaze_powder,
						Items.dye });
		GameRegistry
				.addShapelessRecipe(new ItemStack(dust_Diamerald, 2),
						new Object[] { Diamerald.dust_Diamond,
								Diamerald.dust_Emerald });
		GameRegistry.addRecipe(new ItemStack(Grinder, 1), new Object[] { "IGI",
				"RFR", "XPX", 'I', Items.iron_ingot, 'G', Items.gold_ingot,
				'X', Blocks.stone, 'R', Items.redstone, 'F', Items.flint, 'P',
				Blocks.piston });
		GameRegistry.addRecipe(new ItemStack(Dfurnace, 1), new Object[] { "IGI",
			"RFR", "XXX", 'I', Items.iron_ingot, 'G', Items.gold_ingot,
			'X', Blocks.stone, 'R', Items.redstone, 'F',
			Blocks.furnace });

		// IC2 Integration//

		if (Loader.isModLoaded("IC2")) {

			GrinderRecipes.smelting().addGrinderRecipe(
					IC2Items.getItem("tinOre"), IC2Items.getItem("tinDust"));
			GrinderRecipes.smelting().addGrinderRecipe(
					IC2Items.getItem("leadOre"), IC2Items.getItem("leadDust"));
			GrinderRecipes.smelting().addGrinderRecipe(
					IC2Items.getItem("copperOre"),
					IC2Items.getItem("copperDust"));
			Recipes.macerator
					.addRecipe(new RecipeInputItemStack(new ItemStack(
							oreDiamerald, 1)), null, (new ItemStack(
							dust_Diamerald, 2)));
			Recipes.compressor.addRecipe(new RecipeInputItemStack(
					new ItemStack(dust_Diamerald, 1)), null, (new ItemStack(
					gem_Diamerald, 1)));
			Recipes.compressor.addRecipe(new RecipeInputItemStack(
					new ItemStack(dust_BlackDiamerald, 1)), null,
					(new ItemStack(gem_BlackDiamerald)));
			Recipes.compressor.addRecipe(new RecipeInputItemStack(
					new ItemStack(Items.skull, 1, 1)), null, (new ItemStack(
					gem_BlackDiamerald, 1)));
		}

		// ThermalExpansion Integration//

		if (Loader.isModLoaded("ThermalExpansion")) {

			dust_EmeraldTiny = GameRegistry
					.registerItem(new Ddusts(), "EmeralddustTiny", "Diamerald")
					.setUnlocalizedName("EmeralddustTiny")
					.setCreativeTab(tabDiamerald);
			/*
			 * Emeralddust = GameRegistry .registerItem(new Ddusts(),
			 * "Emeralddust", "Diamerald")
			 * .setUnlocalizedName("Emeralddust").setCreativeTab(tabDiamerald);
			 */
			berylSlag = GameRegistry
					.registerItem(new Ddusts(), "berylSlag", "Diamerald")
					.setUnlocalizedName("berylSlag")
					.setCreativeTab(tabDiamerald);
			GameRegistry.registerItem(berylSlag, "berylSlag");
			GameRegistry
					.addRecipe(new ItemStack(dust_Emerald, 1), new Object[] {
							"EE", "EE", 'E', Diamerald.dust_EmeraldTiny });

			{
				NBTTagCompound toSend = new NBTTagCompound();

				toSend.setInteger("energy", 10000);
				toSend.setTag("primaryInput", new NBTTagCompound());
				toSend.setTag("secondaryInput", new NBTTagCompound());
				toSend.setTag("primaryOutput", new NBTTagCompound());
				toSend.setTag("secondaryOutput", new NBTTagCompound());
				new ItemStack(dust_Emerald, 2).writeToNBT(toSend
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
				new ItemStack(dust_Emerald, 1).writeToNBT(toSend
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
				new ItemStack(dust_Diamerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(Blocks.sand, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(gem_Diamerald, 2).writeToNBT(toSend
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
				new ItemStack(dust_Diamerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				new ItemStack(dust_EmeraldTiny, 1).writeToNBT(toSend
						.getCompoundTag("secondaryOutput"));
				toSend.setInteger("secondaryChance", 10);
				FMLInterModComms.sendMessage("ThermalExpansion",
						"PulverizerRecipe", toSend);
			}

		}

	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent evt) {

		packetPipeline.postInitialise();

	}

}
