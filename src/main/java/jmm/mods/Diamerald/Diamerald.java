package jmm.mods.Diamerald;

import jmm.mods.Diamerald.blocks.BlockDirtchest;
import jmm.mods.Diamerald.blocks.oreDiamerald;
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
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Diamerald", name = "Diamerald", version = "1.7.2_1")

public class Diamerald {

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

		oreDiameraldID = config.get("oreDiamerald ID",
				Configuration.CATEGORY_GENERAL, 500).getInt();
		GSTorchID = config.get("GSTorch ID", Configuration.CATEGORY_GENERAL,
				512).getInt();
		RoughgemID = config.get("Roughgem ID", Configuration.CATEGORY_GENERAL,
				3852).getInt();
		DiameraldgemID = config.get("Diameraldgem ID",
				Configuration.CATEGORY_GENERAL, 3841).getInt();
		DiameraldswordID = config.get("Diameraldsword ID",
				Configuration.CATEGORY_GENERAL, 3842).getInt();
		DiameraldpickaxeID = config.get("Diameraldpickaxe ID",
				Configuration.CATEGORY_GENERAL, 3843).getInt();
		DiameraldaxeID = config.get("Diameraldaxe ID",
				Configuration.CATEGORY_GENERAL, 3844).getInt();
		DiameraldshovelID = config.get("Diameraldshovel ID",
				Configuration.CATEGORY_GENERAL, 3845).getInt();
		DiameraldhoeID = config.get("Diameraldhoe ID",
				Configuration.CATEGORY_GENERAL, 3846).getInt();
		DiameraldbowID = config.get("Diameraldbow ID",
				Configuration.CATEGORY_GENERAL, 3851).getInt();
		DiameraldhelmetID = config.get("Diameraldhelmet ID",
				Configuration.CATEGORY_GENERAL, 3847).getInt();
		DiameraldplateID = config.get("Diameraldplate ID",
				Configuration.CATEGORY_GENERAL, 3848).getInt();
		DiameraldlegsID = config.get("Diameraldlegs ID",
				Configuration.CATEGORY_GENERAL, 3849).getInt();
		DiameraldbootsID = config.get("Diameraldboots ID",
				Configuration.CATEGORY_GENERAL, 3850).getInt();
		DirtchestID = config.get("Dirtchest ID",
				Configuration.CATEGORY_GENERAL, 513).getInt();
		blackRoughgemID = config.get("blackRoughgem ID",
				Configuration.CATEGORY_GENERAL, 3853).getInt();
		blackDiameraldgemID = config.get("blackDiameraldgem ID",
				Configuration.CATEGORY_GENERAL, 3854).getInt();
		blackDiameraldswordID = config.get("blackDiameraldsword ID",
				Configuration.CATEGORY_GENERAL, 3855).getInt();
		blackDiameraldpickaxeID = config.get("blackDiameraldpickaxe ID",
				Configuration.CATEGORY_GENERAL, 3856).getInt();
		blackDiameraldhelmetID = config.get("blackDiameraldhelmet ID",
				Configuration.CATEGORY_GENERAL, 3857).getInt();
		DiameralddustID = config.get("Diameralddust ID",
				Configuration.CATEGORY_GENERAL, 3859).getInt();
		EmeralddustTinyID = config.get("EmeralddustTiny ID",
				Configuration.CATEGORY_GENERAL, 3860).getInt();
		EmeralddustID = config.get("Emeralddust ID",
				Configuration.CATEGORY_GENERAL, 3861).getInt();
		berylSlagID = config.get("berylSlag ID",
				Configuration.CATEGORY_GENERAL, 3862).getInt();

		config.save();
		
		oreDiamerald = (new oreDiamerald(oreDiameraldID))
				.func_149663_c("oreDiamerald").func_149658_d("Diameraldore");
		GSTorch = (new GSTorch(GSTorchID)).func_149663_c("GSTorch");
		Diameraldgem = (new Diameraldgem(DiameraldgemID)).setUnlocalizedName(
				"Diameraldgem");
		Roughgem = (new Roughgem(RoughgemID)).setUnlocalizedName("Roughgem");
		Diameraldsword = (new Diameraldsword(DIAMERALD))
				.setUnlocalizedName("Diameraldsword");
		Diameraldpickaxe = (new Diameraldpickaxe(DIAMERALD))
				.setUnlocalizedName("Diameraldpickaxe");
		Diameraldaxe = (new Diameraldaxe(DIAMERALD))
				.setUnlocalizedName("Diameraldaxe");
		Diameraldshovel = (new Diameraldshovel(DIAMERALD))
				.setUnlocalizedName("Diameraldshovel");
		Diameraldhoe = (new Diameraldhoe(DIAMERALD))
				.setUnlocalizedName("Diameraldhoe");
		Diameraldbow = (ItemBow) (new Diameraldbow(DIAMERALD))
				.setUnlocalizedName("Dbow");
		Diameraldhelmet = (new Diameraldhelmet(diamerald, 3, 0)
				.setUnlocalizedName("Diameraldhelmet"));
		Diameraldplate = (new Diameraldplate(diamerald, 3, 1)
				.setUnlocalizedName("Diameraldplate"));
		Diameraldlegs = (new Diameraldlegs(diamerald, 3, 2)
				.setUnlocalizedName("Diameraldlegs"));
		Diameraldboots = (new Diameraldboots(diamerald, 3, 3)
				.setUnlocalizedName("Diameraldboots"));
		BlockDirtchest = (new BlockDirtchest(DirtchestID, 0))
				.func_149663_c("Dirtchest");
		blackDiameraldgem = (new blackDiameraldgem(blackDiameraldgemID))
				.setUnlocalizedName("blackDiameraldgem");
		blackRoughgem = (new blackRoughgem(blackRoughgemID))
				.setUnlocalizedName("blackRoughgem");
		blackDiameraldsword = (new blackDiameraldsword(BLACKDIAMERALD))
				.setUnlocalizedName("blackDiameraldsword");
		blackDiameraldpickaxe = (new blackDiameraldpickaxe(BLACKDIAMERALD))
				.setUnlocalizedName("blackDiameraldpickaxe");
		blackDiameraldhelmet = (new blackDiameraldhelmet(blackdiamerald, 3, 0)
				.setUnlocalizedName("blackDiameraldhelmet"));

		// Registering things//

		GameRegistry.registerWorldGenerator(new WorldGeneratorDiamerald(), 1);
		GameRegistry.registerBlock(oreDiamerald, "oreDiamerald");
		GameRegistry.registerItem(Diameraldgem, "Diameraldgem");
		GameRegistry.registerItem(blackDiameraldgem, "blackDiameraldgem");
		GameRegistry.registerItem(Roughgem, "Roughgem");
		GameRegistry.registerItem(blackRoughgem, "blackRoughgem");
		GameRegistry.registerItem(Diameraldsword, "Diameraldsword");
		GameRegistry.registerItem(Diameraldpickaxe, "Diameraldpickaxe");
		GameRegistry.registerItem(blackDiameraldsword, "blackDiameraldsword");
		GameRegistry.registerItem(blackDiameraldpickaxe,"blackDiameraldpickaxe");
		GameRegistry.registerItem(Diameraldhoe, "Diameraldhoe");
		GameRegistry.registerItem(blackDiameraldhelmet, "blackDiameraldhelmet");
		GameRegistry.registerItem(Diameraldaxe, "Diameraldaxe");
		GameRegistry.registerItem(Diameraldshovel, "Diameraldshovel");
		GameRegistry.registerItem(Diameraldhelmet, "Diameraldhelmet");
		GameRegistry.registerItem(Diameraldplate, "Diameraldplate");
		GameRegistry.registerItem(Diameraldlegs, "Diameraldlegs");
		GameRegistry.registerItem(Diameraldboots, "Diameraldboots");
		GameRegistry.registerItem(Diameraldbow, "Diameraldbow");
		GameRegistry.registerBlock(GSTorch, "Glowstone Torch");
		GameRegistry.registerBlock(BlockDirtchest, "Dirtchest");
		GameRegistry.registerTileEntity(TileEntityChestDC.class,
				"TileEntityChestDC");

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
		GameRegistry.addRecipe(new ItemStack(blackDiameraldsword, 1), new Object[] {
						" B ", " S ", " R ", 'B', Diamerald.blackDiameraldgem,
						'S', new ItemStack(Items.skull, 1, 1), 'R',
						Items.blaze_rod });
		GameRegistry.addRecipe(new ItemStack(blackDiameraldpickaxe, 1),
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
		GameRegistry.addRecipe(new ItemStack(blackDiameraldhelmet, 1),
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
						" I ", "IRI", " I ", 'R', Diamerald.Roughgem, 'I',
						Items.dye });
		GameRegistry.addRecipe(new ItemStack(BlockDirtchest, 1), new Object[] {
				"AAA", "ACA", "AAA", 'A', Blocks.dirt, 'C', Blocks.chest });

		// Recipes for other IC2//

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

			Diameralddust = (new Diameralddust(DiameralddustID))
					.setUnlocalizedName("Diameralddust");
			EmeralddustTiny = (new EmeralddustTiny(EmeralddustTinyID))
					.setUnlocalizedName("EmeralddustTiny");
			Emeralddust = (new Emeralddust(EmeralddustID))
					.setUnlocalizedName("Emeralddust");
			berylSlag = (new berylSlag(berylSlagID))
					.setUnlocalizedName("berylSlag");
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
