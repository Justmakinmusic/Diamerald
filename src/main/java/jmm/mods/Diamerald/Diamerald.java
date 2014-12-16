package jmm.mods.Diamerald;

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
import jmm.mods.Diamerald.machines.GuiHandler;
import jmm.mods.Diamerald.proxy.DiameraldProxy;
import jmm.mods.Diamerald.tileentity.TileEntityChestDC;
import jmm.mods.Diamerald.tileentity.TileEntityDfurnace;
import jmm.mods.Diamerald.tileentity.TileEntityGrinder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
//import ic2.api.item.IC2Items;
//import ic2.api.recipe.RecipeInputItemStack;
//import ic2.api.recipe.Recipes;

@Mod(modid = "Diamerald", name = "Diamerald", version = "1.8_0")
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
			"diamerald", "diamerald", 33, new int[] { 4, 9, 7, 4 }, 10);
	public static ArmorMaterial blackdiamerald = EnumHelper.addArmorMaterial(
			"blackdiamerald", "blackdiamerald", 33, new int[] { 5, 10, 8, 5 },
			10);

	// Creative Tab//

	public static CreativeTabs tabDiamerald = new CreativeTabs("tabDiamerald") {
		public Item getTabIconItem() {
			return Diamerald.gemDiamerald;
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
	public static Item gemDiamerald;
	public static Item Diameraldsword;
	public static Item Diameraldpickaxe;
	public static Item Diameraldaxe;
	public static Item Diameraldshovel;
	public static Item Diameraldhoe;
	public static ItemArmor Diameraldhelmet;
	public static ItemArmor Diameraldplate;
	public static ItemArmor Diameraldlegs;
	public static ItemArmor Diameraldboots;
	public static ItemBow Diameraldbow;
	public static Item gemBlackDiamerald;
	public static Item blackDiameraldsword;
	public static Item blackDiameraldpickaxe;
	public static ItemArmor blackDiameraldhelmet;
	public static Item dustDiamerald;
	public static Item dustBlackDiamerald;
	public static Item dustEmeraldTiny;
	public static Item dustEmerald;
	public static Item dustDiamond;
	public static Item berylSlag;
	public static Item dustGold;
	public static Item dustIron;

	// Proxy and Preload//

	@SidedProxy(clientSide = "jmm.mods.Diamerald.proxy.DiameraldClient", serverSide = "jmm.mods.Diamerald.proxy.DiameraldProxy")
	public static DiameraldProxy proxy;

	@EventHandler
	public void PreLoad(FMLPreInitializationEvent event) {

		// Init Blocks and Items//

		oreDiamerald = GameRegistry
				.registerBlock(new oreDiamerald(), "oreDiamerald")
				.setUnlocalizedName("oreDiamerald")
				.setCreativeTab(tabDiamerald);
		GSTorch = GameRegistry
				.registerBlock(new GSTorch(Material.circuits), "GSTorch")
				.setUnlocalizedName("GSTorch").setCreativeTab(tabDiamerald);
		BlockDirtchest = GameRegistry
				.registerBlock(new BlockDirtchest(0), "BlockDirtchest")
				.setUnlocalizedName("BlockDirtchest")
				.setHardness(2.5f)
				.setResistance(5.0f)
				.setCreativeTab(tabDiamerald);
		Grinder = GameRegistry.registerBlock(new Grinder(false), "Grinder")
				.setHardness(3.5f).setResistance(5.0f)
				.setUnlocalizedName("Grinder").setCreativeTab(tabDiamerald);
		Grinder_on = GameRegistry
				.registerBlock(new Grinder(true), "Grinder_on")
				.setHardness(3.5f).setResistance(5.0f)
				.setUnlocalizedName("Grinder").setLightLevel(0.5f);
		Dfurnace = GameRegistry.registerBlock(new Dfurnace(false), "Dfurnace")
				.setUnlocalizedName("Dfurnace").setCreativeTab(tabDiamerald);
		Dfurnace_on = GameRegistry
				.registerBlock(new Dfurnace(true), "Dfurnace_on")
				.setUnlocalizedName("Dfurnace").setLightLevel(0.5f);
		gemDiamerald = GameRegistry
				.registerItem(new Dgems(), "gemDiamerald", "Diamerald")
				.setUnlocalizedName("gemDiamerald")
				.setCreativeTab(tabDiamerald);
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
		Diameraldhelmet = (ItemArmor) GameRegistry
				.registerItem(new Diameraldhelmet(diamerald, 3, 0),
						"Diameraldhelmet", "Diamerald")
				.setUnlocalizedName("Diameraldhelmet")
				.setCreativeTab(tabDiamerald);
		Diameraldplate = (ItemArmor) GameRegistry
				.registerItem(new Diameraldplate(diamerald, 3, 1),
						"Diameraldplate", "Diamerald")
				.setUnlocalizedName("Diameraldplate")
				.setCreativeTab(tabDiamerald);
		Diameraldlegs = (ItemArmor) GameRegistry
				.registerItem(new Diameraldlegs(diamerald, 3, 2),
						"Diameraldlegs", "Diamerald")
				.setUnlocalizedName("Diameraldlegs")
				.setCreativeTab(tabDiamerald);
		Diameraldboots = (ItemArmor) GameRegistry
				.registerItem(new Diameraldboots(diamerald, 3, 3),
						"Diameraldboots", "Diamerald")
				.setUnlocalizedName("Diameraldboots")
				.setCreativeTab(tabDiamerald);
		gemBlackDiamerald = GameRegistry
				.registerItem(new Dgems(), "gemBlackDiamerald", "Diamerald")
				.setUnlocalizedName("gemBlackDiamerald")
				.setCreativeTab(tabDiamerald);
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
		blackDiameraldhelmet = (ItemArmor) GameRegistry
				.registerItem(new blackDiameraldhelmet(blackdiamerald, 3, 0),
						"blackDiameraldhelmet", "Diamerald")
				.setUnlocalizedName("blackDiameraldhelmet")
				.setCreativeTab(tabDiamerald);
		dustDiamerald = GameRegistry
				.registerItem(new Ddusts(), "dustDiamerald", "Diamerald")
				.setUnlocalizedName("dustDiamerald")
				.setCreativeTab(tabDiamerald);
		dustBlackDiamerald = GameRegistry
				.registerItem(new Ddusts(), "dustBlackDiamerald", "Diamerald")
				.setUnlocalizedName("dustBlackDiamerald")
				.setCreativeTab(tabDiamerald);
		/*
		 * EmeralddustTiny = GameRegistry .registerItem(new Ddusts(),
		 * "EmeralddustTiny", "Diamerald")
		 * .setUnlocalizedName("EmeralddustTiny") .setCreativeTab(tabDiamerald);
		 */
		dustEmerald = GameRegistry
				.registerItem(new Ddusts(), "dustEmerald", "Diamerald")
				.setUnlocalizedName("dustEmerald").setCreativeTab(tabDiamerald);
		dustDiamond = GameRegistry
				.registerItem(new Ddusts(), "dustDiamond", "Diamerald")
				.setUnlocalizedName("dustDiamond").setCreativeTab(tabDiamerald);
		/*
		 * berylSlag = GameRegistry .registerItem(new Ddusts(), "berylSlag",
		 * "Diamerald")
		 * .setUnlocalizedName("berylSlag").setCreativeTab(tabDiamerald);
		 */
		dustGold = GameRegistry
				.registerItem(new Ddusts(), "dustGold", "Diamerald")
				.setUnlocalizedName("dustGold").setCreativeTab(tabDiamerald);
		dustIron = GameRegistry
				.registerItem(new Ddusts(), "dustIron", "Diamerald")
				.setUnlocalizedName("dustIron").setCreativeTab(tabDiamerald);

		// Registering WorldGenerator, Ore, TileEntity, Gui //

		OreDictionary.registerOre("oreDiamerald", oreDiamerald);
		OreDictionary.registerOre("gemDiamerald", gemDiamerald);
		OreDictionary.registerOre("dustDiamerald", dustDiamerald);
		OreDictionary.registerOre("gemBlackDiamerald", gemBlackDiamerald);
		OreDictionary.registerOre("dustBlackDiamerald", dustBlackDiamerald);
		OreDictionary.registerOre("dustEmerald", dustEmerald);
		OreDictionary.registerOre("dustDiamond", dustDiamond);
		OreDictionary.registerOre("dustGold", dustGold);
		OreDictionary.registerOre("dustIron", dustIron);

		GameRegistry.registerWorldGenerator(new WorldGeneratorDiamerald(), 1);
		GameRegistry.registerTileEntity(TileEntityChestDC.class,
				"TileEntityChestDC");
		GameRegistry.registerTileEntity(TileEntityGrinder.class,
				"TileEntityGrinder");
		GameRegistry.registerTileEntity(TileEntityDfurnace.class,
				"TileEntityDfurnace");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	}

	// Load//

	@EventHandler
	public void load(FMLInitializationEvent event) {

		proxy.registerRenderInformation();

		// Loot generation//

		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(
				new WeightedRandomChestContent(new ItemStack(
						Diamerald.gemDiamerald), 1, 3, 20));

		// Recipes//

		GameRegistry.addSmelting(Diamerald.oreDiamerald, new ItemStack(
				Diamerald.gemDiamerald, 1), .7f);
		GameRegistry.addRecipe(new ItemStack(Diameraldsword, 1), new Object[] {
				" D ", " D ", " S ", 'D', Diamerald.gemDiamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldpickaxe, 1),
				new Object[] { "DDD", " S ", " S ", 'D',
						Diamerald.gemDiamerald, 'S', Items.stick });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldsword, 1), new Object[] {
						" B ", " S ", " R ", 'B', Diamerald.gemBlackDiamerald,
						'S', new ItemStack(Items.skull, 1, 1), 'R',
						Items.blaze_rod });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldpickaxe, 1),
						new Object[] { "BSB", " R ", " R ", 'B',
								Diamerald.gemBlackDiamerald, 'S',
								new ItemStack(Items.skull, 1, 1), 'R',
								Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(Diameraldaxe, 1), new Object[] {
				"DD ", "DS ", " S ", 'D', Diamerald.gemDiamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldshovel, 1), new Object[] {
				" D ", " S ", " S ", 'D', Diamerald.gemDiamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldhoe, 1), new Object[] {
				"DD ", " S ", " S ", 'D', Diamerald.gemDiamerald, 'S',
				Items.stick });
		GameRegistry.addRecipe(new ItemStack(Diameraldhelmet, 1), new Object[] {
				"DDD", "D D", 'D', Diamerald.gemDiamerald });
		GameRegistry
				.addRecipe(new ItemStack(blackDiameraldhelmet, 1),
						new Object[] { "BBB", "B B", 'B',
								Diamerald.gemBlackDiamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldplate, 1), new Object[] {
				"D D", "DDD", "DDD", 'D', Diamerald.gemDiamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldlegs, 1), new Object[] {
				"DDD", "D D", "D D", 'D', Diamerald.gemDiamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldboots, 1), new Object[] {
				"D D", "D D", 'D', Diamerald.gemDiamerald });
		GameRegistry.addRecipe(new ItemStack(Diameraldbow, 1), new Object[] {
				" DW", "B W", " DW", 'D', Diamerald.gemDiamerald, 'W',
				Items.string, 'B', Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(GSTorch, 4), new Object[] { " G",
				" S", 'G', Items.glowstone_dust, 'S', Items.stick });
		GameRegistry.addRecipe(new ItemStack(BlockDirtchest, 1), new Object[] {
				"AAA", "ACA", "AAA", 'A', Blocks.dirt, 'C', Blocks.chest });
		GameRegistry.addSmelting(Diamerald.dustGold, new ItemStack(
				Items.gold_ingot, 1), 0.5f);
		GameRegistry.addSmelting(Diamerald.dustIron, new ItemStack(
				Items.iron_ingot, 1), 0.5f);
		GameRegistry.addSmelting(Diamerald.dustDiamerald, new ItemStack(
				Diamerald.gemDiamerald, 1), 1.0f);
		GameRegistry.addSmelting(Diamerald.dustBlackDiamerald, new ItemStack(
				Diamerald.gemBlackDiamerald, 1), 1.0f);
		GameRegistry.addSmelting(Diamerald.dustEmerald, new ItemStack(
				Items.emerald, 1), 0.5f);
		GameRegistry.addSmelting(Diamerald.dustDiamond, new ItemStack(
				Items.diamond, 1), .05f);
		GameRegistry.addShapelessRecipe(new ItemStack(dustBlackDiamerald, 2),
				new Object[] { Diamerald.dustDiamerald, Items.blaze_powder,
						Items.dye });
		GameRegistry.addShapelessRecipe(new ItemStack(dustDiamerald, 2),
				new Object[] { Diamerald.dustDiamond, Diamerald.dustEmerald });
		GameRegistry.addRecipe(new ItemStack(Grinder, 1), new Object[] { "IGI",
				"RFR", "XPX", 'I', Items.iron_ingot, 'G', Items.gold_ingot,
				'X', Blocks.stone, 'R', Items.redstone, 'F', Items.flint, 'P',
				Blocks.piston });
		GameRegistry.addRecipe(new ItemStack(Dfurnace, 1), new Object[] {
				"IGI", "DFD", "XFX", 'I', Items.iron_ingot, 'G',
				Items.gold_ingot, 'X', Blocks.stone, 'D',
				Diamerald.dustDiamerald, 'F', Blocks.furnace });

		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Item.getItemFromBlock(oreDiamerald),
						0,
						new ModelResourceLocation("diamerald:oreDiamerald",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Item.getItemFromBlock(GSTorch),
						0,
						new ModelResourceLocation("diamerald:GSTorch",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Item.getItemFromBlock(BlockDirtchest),
						0,
						new ModelResourceLocation("diamerald:BlockDirtchest",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Item.getItemFromBlock(Grinder),
						0,
						new ModelResourceLocation("diamerald:Grinder",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Item.getItemFromBlock(Dfurnace),
						0,
						new ModelResourceLocation("diamerald:Dfurnace",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						gemDiamerald,
						0,
						new ModelResourceLocation("diamerald:gemDiamerald",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						gemBlackDiamerald,
						0,
						new ModelResourceLocation(
								"diamerald:gemBlackDiamerald", "inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldsword,
						0,
						new ModelResourceLocation("diamerald:Diameraldsword",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldpickaxe,
						0,
						new ModelResourceLocation("diamerald:Diameraldpickaxe",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldaxe,
						0,
						new ModelResourceLocation("diamerald:Diameraldaxe",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldshovel,
						0,
						new ModelResourceLocation("diamerald:Diameraldshovel",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldhoe,
						0,
						new ModelResourceLocation("diamerald:Diameraldhoe",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldhelmet,
						0,
						new ModelResourceLocation("diamerald:Diameraldhelmet",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldplate,
						0,
						new ModelResourceLocation("diamerald:Diameraldplate",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldlegs,
						0,
						new ModelResourceLocation("diamerald:Diameraldlegs",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldboots,
						0,
						new ModelResourceLocation("diamerald:Diameraldboots",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Diameraldbow,
						0,
						new ModelResourceLocation("diamerald:Diameraldbow",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						blackDiameraldsword,
						0,
						new ModelResourceLocation(
								"diamerald:blackDiameraldsword", "inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						blackDiameraldpickaxe,
						0,
						new ModelResourceLocation(
								"diamerald:blackDiameraldpickaxe", "inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						blackDiameraldhelmet,
						0,
						new ModelResourceLocation(
								"diamerald:blackDiameraldhelmet", "inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						dustDiamerald,
						0,
						new ModelResourceLocation("diamerald:dustDiamerald",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						dustGold,
						0,
						new ModelResourceLocation("diamerald:dustGold",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						dustIron,
						0,
						new ModelResourceLocation("diamerald:dustIron",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						dustEmerald,
						0,
						new ModelResourceLocation("diamerald:dustEmerald",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						dustEmeraldTiny,
						0,
						new ModelResourceLocation("diamerald:dustEmeraldTiny",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						berylSlag,
						0,
						new ModelResourceLocation("diamerald:berylSlag",
								"inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						dustBlackDiamerald,
						0,
						new ModelResourceLocation(
								"diamerald:dustBlackDiamerald", "inventory"));
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						dustDiamond,
						0,
						new ModelResourceLocation("diamerald:dustDiamond",
								"inventory"));

		// IC2 Integration//

		/*
		 * if (Loader.isModLoaded("IC2")) {
		 * 
		 * GrinderRecipes.smelting().addGrinderRecipe(
		 * IC2Items.getItem("tinOre"), IC2Items.getItem("tinDust"));
		 * GrinderRecipes.smelting().addGrinderRecipe(
		 * IC2Items.getItem("leadOre"), IC2Items.getItem("leadDust"));
		 * GrinderRecipes.smelting().addGrinderRecipe(
		 * IC2Items.getItem("copperOre"), IC2Items.getItem("copperDust"));
		 * DfurnaceRecipes
		 * .smelting().addDfurnaceRecipe(IC2Items.getItem("tinDust"),
		 * IC2Items.getItem("tinIngot"), 0.5f);
		 * DfurnaceRecipes.smelting().addDfurnaceRecipe
		 * (IC2Items.getItem("leadDust"), IC2Items.getItem("leadIngot"), 0.5f);
		 * DfurnaceRecipes
		 * .smelting().addDfurnaceRecipe(IC2Items.getItem("copperDust"),
		 * IC2Items.getItem("copperIngot"), 0.5f); Recipes.macerator
		 * .addRecipe(new RecipeInputItemStack(new ItemStack( oreDiamerald, 1)),
		 * null, (new ItemStack( dustDiamerald, 2)));
		 * Recipes.compressor.addRecipe(new RecipeInputItemStack( new
		 * ItemStack(dustDiamerald, 1)), null, (new ItemStack( gemDiamerald,
		 * 1))); Recipes.compressor.addRecipe(new RecipeInputItemStack( new
		 * ItemStack(dustBlackDiamerald, 1)), null, (new
		 * ItemStack(gemBlackDiamerald))); Recipes.compressor.addRecipe(new
		 * RecipeInputItemStack( new ItemStack(Items.skull, 1, 1)), null, (new
		 * ItemStack( gemBlackDiamerald, 1))); }
		 */

		// ThermalExpansion Integration//

		if (Loader.isModLoaded("ThermalExpansion")) {

			dustEmeraldTiny = GameRegistry
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
			GameRegistry.addRecipe(new ItemStack(dustEmerald, 1), new Object[] {
					"EE", "EE", 'E', Diamerald.dustEmeraldTiny });

			{
				NBTTagCompound toSend = new NBTTagCompound();

				toSend.setInteger("energy", 10000);
				toSend.setTag("primaryInput", new NBTTagCompound());
				toSend.setTag("secondaryInput", new NBTTagCompound());
				toSend.setTag("primaryOutput", new NBTTagCompound());
				toSend.setTag("secondaryOutput", new NBTTagCompound());
				new ItemStack(dustEmerald, 2).writeToNBT(toSend
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
				new ItemStack(dustEmerald, 1).writeToNBT(toSend
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
				new ItemStack(dustDiamerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryInput"));
				new ItemStack(Blocks.sand, 1).writeToNBT(toSend
						.getCompoundTag("secondaryInput"));
				new ItemStack(gemDiamerald, 2).writeToNBT(toSend
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
				new ItemStack(dustDiamerald, 2).writeToNBT(toSend
						.getCompoundTag("primaryOutput"));
				new ItemStack(dustEmeraldTiny, 1).writeToNBT(toSend
						.getCompoundTag("secondaryOutput"));
				toSend.setInteger("secondaryChance", 10);
				FMLInterModComms.sendMessage("ThermalExpansion",
						"PulverizerRecipe", toSend);
			}

		}

	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent evt) {

	}

}
