package jmm.mods.Diamerald;

import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import jmm.mods.Diamerald.blocks.BlockDirtchest;
import jmm.mods.Diamerald.blocks.Diameraldore;
import jmm.mods.Diamerald.blocks.GSTorch;
import jmm.mods.Diamerald.items.Diameraldaxe;
import jmm.mods.Diamerald.items.Diameraldboots;
import jmm.mods.Diamerald.items.Diameraldbow;
import jmm.mods.Diamerald.items.Diameralddust;
import jmm.mods.Diamerald.items.Diameraldgem;
import jmm.mods.Diamerald.items.Diameraldhelmet;
import jmm.mods.Diamerald.items.Diameraldhoe;
import jmm.mods.Diamerald.items.Diameraldlegs;
import jmm.mods.Diamerald.items.Diameraldpickaxe;
import jmm.mods.Diamerald.items.Diameraldplate;
import jmm.mods.Diamerald.items.Diameraldshovel;
import jmm.mods.Diamerald.items.Diameraldsword;
import jmm.mods.Diamerald.items.Emeralddust;
import jmm.mods.Diamerald.items.EmeralddustTiny;
import jmm.mods.Diamerald.items.Roughgem;
import jmm.mods.Diamerald.items.berylSlag;
import jmm.mods.Diamerald.items.blackDiameraldgem;
import jmm.mods.Diamerald.items.blackDiameraldhelmet;
import jmm.mods.Diamerald.items.blackDiameraldpickaxe;
import jmm.mods.Diamerald.items.blackDiameraldsword;
import jmm.mods.Diamerald.items.blackRoughgem;
import jmm.mods.Diamerald.proxy.DiameraldProxy;
import jmm.mods.Diamerald.tedc.TileEntityChestDC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Diamerald", name = "Diamerald", version = "1.6.4_1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Diamerald {

	// Enum ToolMaterial//

	public static EnumToolMaterial DIAMERALD = EnumHelper.addToolMaterial(
			"DIAMERALD", 3, 2000, 12.0f, 6.0f, 10);
	public static EnumToolMaterial BLACKDIAMERALD = EnumHelper.addToolMaterial(
			"BLACKDIAMERALD", 3, 2500, 16.0f, 14.0f, 10);

	// Enum ArmorMaterial//

	public static EnumArmorMaterial diamerald = EnumHelper.addArmorMaterial(
			"diamerald", 33, new int[] { 4, 9, 7, 4 }, 10);
	public static EnumArmorMaterial blackdiamerald = EnumHelper
			.addArmorMaterial("blackdiamerald", 33, new int[] { 5, 10, 8, 5 },
					10);

	// Creative Tab//

	public static CreativeTabs tabDiamerald = new CreativeTabs("tabDiamerald") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Diamerald.Diameraldgem, 1, 0);
		}
	};

	// Blocks and Items //

	public static Block oreDiamerald;
	public static Block GSTorch;
	public static Block BlockDirtchest;
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

	// Config intIDs//

	public int oreDiameraldID;
	public int GSTorchID;
	public int RoughgemID;
	public int DiameraldgemID;
	public int DiameraldswordID;
	public int DiameraldpickaxeID;
	public int DiameraldaxeID;
	public int DiameraldshovelID;
	public int DiameraldhoeID;
	public int DiameraldbowID;
	public int DiameraldhelmetID;
	public int DiameraldplateID;
	public int DiameraldlegsID;
	public int DiameraldbootsID;
	public int DirtchestID;
	public int blackRoughgemID;
	public int blackDiameraldgemID;
	public int blackDiameraldswordID;
	public int blackDiameraldpickaxeID;
	public int blackDiameraldhelmetID;
	public int DiameralddustID;
	public int EmeralddustTinyID;
	public int EmeralddustID;
	public int berylSlagID;

	// Proxy and Preload//

	@SidedProxy(clientSide = "jmm.mods.Diamerald.proxy.DiameraldClient", serverSide = "jmm.mods.Diamerald.proxy.DiameraldProxy")
	public static DiameraldProxy proxy;

	@EventHandler
	public void PreLoad(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());

		config.load();

		oreDiameraldID = config.getBlock("oreDiamerald ID",
				Configuration.CATEGORY_BLOCK, 500).getInt();
		GSTorchID = config.getBlock("GSTorch ID", Configuration.CATEGORY_BLOCK,
				512).getInt();
		RoughgemID = config.getItem("Roughgem ID", Configuration.CATEGORY_ITEM,
				3852).getInt();
		DiameraldgemID = config.getItem("Diameraldgem ID",
				Configuration.CATEGORY_ITEM, 3841).getInt();
		DiameraldswordID = config.getItem("Diameraldsword ID",
				Configuration.CATEGORY_ITEM, 3842).getInt();
		DiameraldpickaxeID = config.getItem("Diameraldpickaxe ID",
				Configuration.CATEGORY_ITEM, 3843).getInt();
		DiameraldaxeID = config.getItem("Diameraldaxe ID",
				Configuration.CATEGORY_ITEM, 3844).getInt();
		DiameraldshovelID = config.getItem("Diameraldshovel ID",
				Configuration.CATEGORY_ITEM, 3845).getInt();
		DiameraldhoeID = config.getItem("Diameraldhoe ID",
				Configuration.CATEGORY_ITEM, 3846).getInt();
		DiameraldbowID = config.getItem("Diameraldbow ID",
				Configuration.CATEGORY_ITEM, 3851).getInt();
		DiameraldhelmetID = config.getItem("Diameraldhelmet ID",
				Configuration.CATEGORY_ITEM, 3847).getInt();
		DiameraldplateID = config.getItem("Diameraldplate ID",
				Configuration.CATEGORY_ITEM, 3848).getInt();
		DiameraldlegsID = config.getItem("Diameraldlegs ID",
				Configuration.CATEGORY_ITEM, 3849).getInt();
		DiameraldbootsID = config.getItem("Diameraldboots ID",
				Configuration.CATEGORY_ITEM, 3850).getInt();
		DirtchestID = config.getBlock("Dirtchest ID",
				Configuration.CATEGORY_BLOCK, 513).getInt();
		blackRoughgemID = config.getItem("blackRoughgem ID",
				Configuration.CATEGORY_ITEM, 3853).getInt();
		blackDiameraldgemID = config.getItem("blackDiameraldgem ID",
				Configuration.CATEGORY_ITEM, 3854).getInt();
		blackDiameraldswordID = config.getItem("blackDiameraldsword ID",
				Configuration.CATEGORY_ITEM, 3855).getInt();
		blackDiameraldpickaxeID = config.getItem("blackDiameraldpickaxe ID",
				Configuration.CATEGORY_ITEM, 3856).getInt();
		blackDiameraldhelmetID = config.getItem("blackDiameraldhelmet ID",
				Configuration.CATEGORY_ITEM, 3857).getInt();
		DiameralddustID = config.getItem("Diameralddust ID",
				Configuration.CATEGORY_ITEM, 3859).getInt();
		EmeralddustTinyID = config.getItem("EmeralddustTiny ID",
				Configuration.CATEGORY_ITEM, 3860).getInt();
		EmeralddustID = config.getItem("Emeralddust ID",
				Configuration.CATEGORY_ITEM, 3861).getInt();
		berylSlagID = config.getItem("berylSlag ID",
				Configuration.CATEGORY_ITEM, 3862).getInt();

		config.save();

	}

	// Load//

	@EventHandler
	public void load(FMLInitializationEvent event) {

		proxy.registerRenderInformation();

		oreDiamerald = (new Diameraldore(oreDiameraldID))
				.setUnlocalizedName("oreDiamerald");
		MinecraftForge.setBlockHarvestLevel(oreDiamerald, "pickaxe", 2);
		GSTorch = (new GSTorch(GSTorchID, Material.glass))
				.setUnlocalizedName("GStorch").setHardness(0.0f)
				.setLightValue(1.0f);
		Diameraldgem = (new Diameraldgem(DiameraldgemID))
				.setUnlocalizedName("Diameraldgem");
		Roughgem = (new Roughgem(RoughgemID)).setUnlocalizedName("Roughgem");
		Diameraldsword = (new Diameraldsword(DiameraldswordID, DIAMERALD))
				.setUnlocalizedName("Diameraldsword");
		Diameraldpickaxe = (new Diameraldpickaxe(DiameraldpickaxeID, DIAMERALD))
				.setUnlocalizedName("Diameraldpick");
		Diameraldaxe = (new Diameraldaxe(DiameraldaxeID, DIAMERALD))
				.setUnlocalizedName("Diameraldaxe");
		Diameraldshovel = (new Diameraldshovel(DiameraldshovelID, DIAMERALD))
				.setUnlocalizedName("Diameraldshovel");
		Diameraldhoe = (new Diameraldhoe(DiameraldhoeID, DIAMERALD))
				.setUnlocalizedName("Diameraldhoe");
		Diameraldbow = (ItemBow) (new Diameraldbow(DiameraldbowID, DIAMERALD))
				.setUnlocalizedName("Dbow");
		Diameraldhelmet = (new Diameraldhelmet(DiameraldhelmetID, diamerald, 3,
				0).setUnlocalizedName("Diameraldhelmet"));
		Diameraldplate = (new Diameraldplate(DiameraldplateID, diamerald, 3, 1)
				.setUnlocalizedName("Diameraldplate"));
		Diameraldlegs = (new Diameraldlegs(DiameraldlegsID, diamerald, 3, 2)
				.setUnlocalizedName("Diameraldlegs"));
		Diameraldboots = (new Diameraldboots(DiameraldbootsID, diamerald, 3, 3)
				.setUnlocalizedName("Diameraldboots"));
		BlockDirtchest = (new BlockDirtchest(DirtchestID, 0))
				.setUnlocalizedName("Dirtchest");
		blackDiameraldgem = (new blackDiameraldgem(blackDiameraldgemID))
				.setUnlocalizedName("blackDiameraldgem");
		blackRoughgem = (new blackRoughgem(blackRoughgemID))
				.setUnlocalizedName("blackRoughgem");
		blackDiameraldsword = (new blackDiameraldsword(blackDiameraldswordID,
				BLACKDIAMERALD)).setUnlocalizedName("blackDiameraldsword");
		blackDiameraldpickaxe = (new blackDiameraldpickaxe(
				blackDiameraldpickaxeID, BLACKDIAMERALD))
				.setUnlocalizedName("blackDiameraldpick");
		blackDiameraldhelmet = (new blackDiameraldhelmet(
				blackDiameraldhelmetID, blackdiamerald, 3, 0)
				.setUnlocalizedName("blackDiameraldhelmet"));

		// Registering things//

		OreDictionary.registerOre(DiameraldgemID, new ItemStack(Diameraldgem));
		OreDictionary.registerOre(oreDiameraldID, new ItemStack(oreDiamerald));
		GameRegistry.registerBlock(oreDiamerald, "oreDiamerald");

		GameRegistry.registerWorldGenerator(new WorldGeneratorDiamerald());
		LanguageRegistry.instance().addStringLocalization(
				"itemGroup.tabDiamerald", "en_US", "Diamerald");
		LanguageRegistry.addName(oreDiamerald, "Diamerald ore");
		LanguageRegistry.addName(Diameraldgem, "Diamerald");
		LanguageRegistry.addName(blackDiameraldgem, "Black Diamerald");
		LanguageRegistry.addName(Roughgem, "Rough Gem");
		LanguageRegistry.addName(blackRoughgem, "Black Rough Gem");
		LanguageRegistry.addName(Diameraldsword, "Diamerald sword");
		LanguageRegistry.addName(Diameraldpickaxe, "Diamerald pickaxe");
		LanguageRegistry.addName(blackDiameraldsword, "Black Diamerald sword");
		LanguageRegistry.addName(blackDiameraldpickaxe,
				"Black Diamerald pickaxe");
		LanguageRegistry.addName(Diameraldhoe, "Diamerald hoe");
		LanguageRegistry
				.addName(blackDiameraldhelmet, "Black Diamerald Helmet");
		LanguageRegistry.addName(Diameraldaxe, "Diamerald axe");
		LanguageRegistry.addName(Diameraldshovel, "Diamerald shovel");
		LanguageRegistry.addName(Diameraldhelmet, "Diamerald Helmet");
		LanguageRegistry.addName(Diameraldplate, "Diamerald Chestpiece");
		LanguageRegistry.addName(Diameraldlegs, "Diamerald Legs");
		LanguageRegistry.addName(Diameraldboots, "Diamerald Boots");
		LanguageRegistry.addName(Diameraldbow, "Diamerald Bow");
		GameRegistry.registerBlock(GSTorch, "Glowstone Torch");
		LanguageRegistry.addName(GSTorch, "Glowstone Torch");
		GameRegistry.registerBlock(BlockDirtchest, "Dirtchest");
		LanguageRegistry.addName(BlockDirtchest, "Dirtchest");
		GameRegistry.registerTileEntity(TileEntityChestDC.class,
				"TileEntityChestDC");

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

		GameRegistry.addSmelting(Diamerald.Roughgem.itemID, new ItemStack(
				Diamerald.Diameraldgem, 1), 500.0f);
		GameRegistry.addSmelting(Diamerald.blackRoughgem.itemID, new ItemStack(
				Diamerald.blackDiameraldgem, 1), 500.0f);
		GameRegistry.addSmelting(Diamerald.oreDiamerald.blockID, new ItemStack(
				Diamerald.Diameraldgem, 1), 500.0f);
		GameRegistry.addRecipe(new ItemStack(Diameraldsword, 1), new Object[] {
				" D ", " D ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Item.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldpickaxe, 1),
				new Object[] { "DDD", " S ", " S ", 'D',
						Diamerald.Diameraldgem, 'S', Item.stick });
		GameRegistry.addRecipe(new ItemStack(blackDiameraldsword, 1),
				new Object[] { " B ", " S ", " R ", 'B',
						Diamerald.blackDiameraldgem, 'S',
						new ItemStack(Item.skull, 1, 1), 'R', Item.blazeRod });
		GameRegistry.addRecipe(new ItemStack(blackDiameraldpickaxe, 1),
				new Object[] { "BSB", " R ", " R ", 'B',
						Diamerald.blackDiameraldgem, 'S',
						new ItemStack(Item.skull, 1, 1), 'R', Item.blazeRod });
		GameRegistry.addRecipe(new ItemStack(Diameraldaxe, 1), new Object[] {
				"DD ", "DS ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Item.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldshovel, 1), new Object[] {
				" D ", " S ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Item.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldhoe, 1), new Object[] {
				"DD ", " S ", " S ", 'D', Diamerald.Diameraldgem, 'S',
				Item.stick });
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
				Item.silk, 'B', Item.blazeRod });
		GameRegistry.addRecipe(new ItemStack(GSTorch, 16), new Object[] { " G",
				" G", 'G', Block.glowStone });
		GameRegistry.addShapelessRecipe(new ItemStack(Roughgem, 1),
				new Object[] { Item.diamond, Item.emerald });
		GameRegistry.addRecipe(new ItemStack(blackRoughgem, 1), new Object[] {
				" I ", "IRI", " I ", 'R', Diamerald.Roughgem, 'I',
				Item.dyePowder, });
		GameRegistry.addRecipe(new ItemStack(BlockDirtchest, 1), new Object[] {
				"AAA", "ACA", "AAA", 'A', Block.dirt, 'C', Block.chest });

		// Recipes for other IC2//

		if (Loader.isModLoaded("IC2")) {

			Diameralddust = (new Diameralddust(DiameralddustID))
					.setUnlocalizedName("Diameralddust");
			LanguageRegistry.addName(Diameralddust, "Diamerald Dust");
			Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(
					oreDiamerald, 1)), null, (new ItemStack(Diameralddust, 2)));
			Recipes.compressor.addRecipe(new RecipeInputItemStack(
					new ItemStack(Diameralddust, 1)), null, (new ItemStack(
					Diameraldgem, 1)));
			Recipes.compressor.addRecipe(new RecipeInputItemStack(
					new ItemStack(Item.skull, 1, 1)), null, (new ItemStack(
					blackDiameraldgem, 1)));

		}

		// Recipes for ThermalExpansion//

		if (Loader.isModLoaded("ThermalExpansion")) {

			// CraftingManagers.smelterManager.addRecipe(200, new ItemStack(
			// Emeralddust, 2), new ItemStack(Block.sand, 1),
			// new ItemStack(Item.emerald, 2), ItemRegistry.getItem(
			// "slagRich", 1), 5);

			Diameralddust = (new Diameralddust(DiameralddustID))
					.setUnlocalizedName("Diameralddust");
			EmeralddustTiny = (new EmeralddustTiny(EmeralddustTinyID))
					.setUnlocalizedName("EmeralddustTiny");
			Emeralddust = (new Emeralddust(EmeralddustID))
					.setUnlocalizedName("Emeralddust");
			berylSlag = (new berylSlag(berylSlagID))
					.setUnlocalizedName("berylSlag");
			LanguageRegistry.addName(Diameralddust, "Diamerald Dust");
			LanguageRegistry.addName(EmeralddustTiny, "Tiny Emerald Dust");
			LanguageRegistry.addName(Emeralddust, "Emerald Dust");
			LanguageRegistry.addName(berylSlag, "Beryl Slag");
			GameRegistry.addRecipe(new ItemStack(Emeralddust, 1), new Object[] {
					"EE", "EE", 'E', Diamerald.EmeralddustTiny });

			{
				NBTTagCompound toSend = new NBTTagCompound();

				toSend.setInteger("energy", 10000);
				toSend.setCompoundTag("primaryInput", new NBTTagCompound());
				toSend.setCompoundTag("secondaryInput", new NBTTagCompound());
				toSend.setCompoundTag("primaryOutput", new NBTTagCompound());
				toSend.setCompoundTag("secondaryOutput", new NBTTagCompound());
				new ItemStack(Emeralddust, 2).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(Block.sand, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(Item.emerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				new ItemStack(berylSlag, 1).writeToNBT(toSend
						.getCompoundTag("secondaryOutput"));
				toSend.setInteger("secondaryChance", 5);
				FMLInterModComms.sendMessage("ThermalExpansion",
						"SmelterRecipe", toSend);
			}

			// CraftingManagers.smelterManager.addRecipe(600, new ItemStack(
			// Emeralddust, 1), ItemRegistry.getItem("slagRich", 1),
			// new ItemStack(Item.emerald, 2));

			{
				NBTTagCompound toSend = new NBTTagCompound();
				toSend.setInteger("energy", 6000);
				toSend.setCompoundTag("primaryInput", new NBTTagCompound());
				toSend.setCompoundTag("secondaryInput", new NBTTagCompound());
				toSend.setCompoundTag("primaryOutput", new NBTTagCompound());
				new ItemStack(Emeralddust, 1).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(berylSlag, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(Item.emerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				FMLInterModComms.sendMessage("ThermalExpansion",
						"SmelterRecipe", toSend);
			}

			// CraftingManagers.smelterManager.addRecipe(400, new ItemStack(
			// Diameralddust, 2), new ItemStack(Block.sand, 1),
			// new ItemStack(Diamerald.Diameraldgem, 2), ItemRegistry.getItem(
			// "slagRich", 1), 5);

			{
				NBTTagCompound toSend = new NBTTagCompound();
				toSend.setInteger("energy", 10000);
				toSend.setCompoundTag("primaryInput", new NBTTagCompound());
				toSend.setCompoundTag("secondaryInput", new NBTTagCompound());
				toSend.setCompoundTag("primaryOutput", new NBTTagCompound());
				toSend.setCompoundTag("secondaryOutput", new NBTTagCompound());
				new ItemStack(Diameralddust, 2).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(Block.sand, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(Diameraldgem, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				new ItemStack(berylSlag, 1).writeToNBT(toSend
						.getCompoundTag("secondaryOutput"));
				toSend.setInteger("secondaryChance", 5);
				FMLInterModComms.sendMessage("ThermalExpansion",
						"SmelterRecipe", toSend);
			}

			// CraftingManagers.pulverizerManager.addRecipe(400, new ItemStack(
			// oreDiamerald, 1), new ItemStack(Diameralddust, 2),
			// new ItemStack(EmeralddustTiny, 1), 10);

			{
				NBTTagCompound toSend = new NBTTagCompound();
				toSend.setInteger("energy", 8000);
				toSend.setCompoundTag("input", new NBTTagCompound());
				toSend.setCompoundTag("primaryOutput", new NBTTagCompound());
				toSend.setCompoundTag("secondaryOutput", new NBTTagCompound());
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
