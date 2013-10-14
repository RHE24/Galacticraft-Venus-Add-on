package spacecraft.mods.galacticraft.venus;

import java.io.File;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import micdoodle8.mods.galacticraft.mars.GCMarsConfigManager;
import micdoodle8.mods.galacticraft.mars.items.GCMarsItemSpaceshipTier2;
import micdoodle8.mods.galacticraft.mars.items.GCMarsItems;
import micdoodle8.mods.galacticraft.moon.items.GCMoonItems;
import spacecraft.mods.galacticraft.venus.block.*;
import spacecraft.mods.galacticraft.venus.items.*;
import spacecraft.mods.galacticraft.venus.dimension.GCVenusWorldProvider;
import spacecraft.mods.galacticraft.venus.wgen.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="GalacticraftVenus", name="Galacticraft Venus", version="0.1.2A-P", dependencies = "required-after:Forge@[9.10.0.789,)")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
  public class GalacticraftVenus 
  {
	    public static final IPlanet VenusPlanet = new VenusPlanet();
	    
	    public static CreativeTabs GalacticraftVenusTab;
	    
	    public static final String CHANNEL = "GalacticraftVenus";
	    public static final String FILE_PATH = "/spacecraft/mods/galacticraft/venus/";
	    public static final String CLIENT_PATH = "client/";
	    public static final String LANGUAGE_PATH = "/assets/GalacticraftVenus/lang/";
	    public static final String CONFIG_FILE = "Spacecraft/Venus.conf";
	    private static final String[] LANGUAGES_SUPPORTED = new String[] {"en_US"};
	    
	    public static String TEXTURE_DOMAIN = "GalacticraftVenus";
	    public static String TEXTURE_PREFIX = GalacticraftVenus.TEXTURE_DOMAIN + ":";
	    public static String TEXTURE_SUFFIX;

	    @Instance("GalacticraftVenus")
        public static GalacticraftVenus instance;
		
		//Adding Creative Tab
		public static CreativeTabs tabVenus = new CreativeTabs("tabVenus") {
			public ItemStack getIconItemStack() {
				return new ItemStack(VenusDirt, 1, 0);
			}
		};
		
		//Block Venus Grass
    	public final static Block VenusGrass = new VenusGrass(610, Material.grass) .setHardness(0.4F).setStepSound(Block.soundGrassFootstep) .setUnlocalizedName("VenusGrass") .setTextureName("galacticraftvenus:venusGrass_top");

		//Block Venus Dirt
		public final static Block VenusDirt = new VenusDirt(611, Material.grass) .setHardness(0.3F).setStepSound(Block.soundGravelFootstep) .setUnlocalizedName("VenusDirt") .setTextureName("galacticraftvenus:venusDirt");
        
		//Block Venus Stone
		public final static Block VenusStone = new VenusStone(612, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusStone") .setTextureName("galacticraftvenus:venusStone");

		//Block Venus Cobblestone
		public final static Block VenusCobblestone = new VenusCobblestone(613, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusCobblestone") .setTextureName("galacticraftvenus:venusCobblestone");

		//Block Venus Gem Block
		public final static Block VenusRedGemBlock = new VenusBlock(614, Material.rock) .setHardness(2.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusGem") .setTextureName("galacticraftvenus:gemBlock");

		//Block Venus Meteor Block
		public final static Block VenusMeteorBlock = new VenusBlock(615, Material.rock) .setHardness(3.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusMeteor") .setTextureName("galacticraftvenus:meteorBlock");

		//Block Venus Brick
		public final static Block VenusBrick = new VenusBlock(616, Material.rock) .setHardness(12.0F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusBrick") .setTextureName("galacticraftvenus:venusBrick");

		//Block Venus Sulfer
		public final static Block VenusSulferBlock = new VenusOre(617, Material.rock) .setHardness(2.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusSulfer") .setTextureName("galacticraftvenus:sulferBlock");

		//Block Venus Sulfer Ore
		public final static Block VenusSulferOre = new VenusOre(618, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusSulferOre") .setTextureName("galacticraftvenus:venusSulfurOre");

		//Block Venus Meteor Ore
		public final static Block VenusMeteorOre = new VenusOre(619, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusMeteorOre") .setTextureName("galacticraftvenus:venusMeteorOre");

		//Block Venus Gem Ore
		public final static Block VenusRedGemOre = new VenusOre(620, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusGemOre") .setTextureName("galacticraftvenus:venusGemOre");

		//Block Venus Iron Ore
		public final static Block VenusIronOre = new VenusOre(621, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusIronOre") .setTextureName("galacticraftvenus:venusIronOre");

		//Block Venus Coal Ore
		public final static Block VenusCoalOre = new VenusOre(622, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusCoalOre") .setTextureName("galacticraftvenus:venusCoalOre");

		//Block Venus Iron Ore
		public final static Block VenusTinOre = new VenusOre(623, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusTinOre") .setTextureName("galacticraftvenus:venusTinOre");

		//Block Venus Coal Ore
		public final static Block VenusCopperOre = new VenusOre(624, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusCopperOre") .setTextureName("galacticraftvenus:venusCopperOre");				

		//Block Venus Basalt
		public final static Block VenusBasalt = new VenusStone(625, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusBasalt") .setTextureName("galacticraftvenus:venusBasalt");				

		//Block Venus Half Basalt
		public final static Block VenusHalfBasalt = new VenusHalfSlab(626, Material.rock) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("VenusHalfBasalt") .setTextureName("galacticraftvenus:venusBasalt");				

		//Block Evolved Blaze Egg
		public final static Block EvolvedBlazeEgg = new VenusBlazeEgg(627) .setHardness(1.5F).setStepSound(Block.soundStoneFootstep) .setUnlocalizedName("EvolvedBlazeEgg") .setTextureName("galacticraftvenus:evolvedBlazeEgg");				


		//Item Sufer Ingot
		public final static Item SulferIngot = new VenusItem(628) .setUnlocalizedName("SulferIngot") .setTextureName("galacticraftvenus:sulferIngot");

		//Item Red Gem
		public final static Item RedGem = new VenusItem(629) .setUnlocalizedName("RedGem") .setTextureName("galacticraftvenus:redGem");

		//Item Compressed Sulfer
		public final static Item CompressedSulfer = new VenusItem(630) .setUnlocalizedName("SulferPlate") .setTextureName("galacticraftvenus:compressedSulfer");

		//Item Compressed Red Gem
		public final static Item CompressedRedGem = new VenusItem(631) .setUnlocalizedName("CompressedRedGem") .setTextureName("galacticraftvenus:compressedRedGem");

		//Item Heavy Venus Plate
		public final static Item HeavyVenusPlate = new VenusHeavyPlate(732) .setUnlocalizedName("Heavy Venus Plate") .setTextureName("galacticraftvenus:HeavyVenusPlate");

		//Item Venus Plate
		public final static Item VenusPlate = new VenusItem(733) .setUnlocalizedName("VenusPlate") .setTextureName("galacticraftvenus:venusPlate");

		//Id 734-733 tooken by GalacticraftDevVenus
		
		//Where the client and server proxy is loaded.
        @SidedProxy(clientSide="spacecraft.mods.galacticraft.venus.client.ClientProxy", serverSide="spacecraft.mods.galacticraft.venus.CommonProxy")
        public static CommonProxy proxy;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) 
        {
            GalacticraftRegistry.registerTeleportType(GCVenusWorldProvider.class, new GCVenusTeleportType());
    		GalacticraftRegistry.registerCelestialBody(new VenusPlanet());
    		new GCVenusConfigManager(new File(event.getModConfigurationDirectory(), GalacticraftVenus.CONFIG_FILE));
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) 
        {
                proxy.registerRenderers();
 		        //Registering CreativeTab Name
                LanguageRegistry.instance().addStringLocalization("itemGroup.tabVenus", "en_US", "Galacticraft Venus");
                
                //Registering Blocks
                GameRegistry.registerBlock(VenusGrass, "VenusGrass");
                GameRegistry.registerBlock(VenusDirt, "VenusDirt");
				GameRegistry.registerBlock(VenusStone, "VenusStone");
				GameRegistry.registerBlock(VenusCobblestone, "VenusCobblestone");
				GameRegistry.registerBlock(VenusRedGemBlock, "VenusRedGemBlock");
				GameRegistry.registerBlock(VenusMeteorBlock, "VenusMeteorBlock");
				GameRegistry.registerBlock(VenusBrick, "VenusBrick");
				GameRegistry.registerBlock(VenusSulferBlock, "VenusSulferBlock");
				GameRegistry.registerBlock(VenusRedGemOre, "VenusRedGemOre");
				GameRegistry.registerBlock(VenusMeteorOre, "VenusMeteorOre");
				GameRegistry.registerBlock(VenusSulferOre, "VenusSulferOre");
				GameRegistry.registerBlock(VenusIronOre, "VenusIronOre");
				GameRegistry.registerBlock(VenusCoalOre, "VenusCoalOre");
				GameRegistry.registerBlock(VenusTinOre, "VenusTinOre");
				GameRegistry.registerBlock(VenusCopperOre, "VenusCopperOre");
				GameRegistry.registerBlock(VenusBasalt, "VenusBasalt");
				GameRegistry.registerBlock(VenusHalfBasalt, "VenusHalfBasalt");
				GameRegistry.registerBlock(EvolvedBlazeEgg, "EvolvedBlazeEgg");
				
				//Registering Block Names
                LanguageRegistry.addName(VenusGrass, "Venus Grass");
                LanguageRegistry.addName(VenusDirt, "Venus Dirt");
				LanguageRegistry.addName(VenusStone, "Venus Stone");
                LanguageRegistry.addName(VenusCobblestone, "Venus Cobblestone");
				LanguageRegistry.addName(VenusRedGemBlock, "Red Gem Block");
				LanguageRegistry.addName(VenusMeteorBlock, "Meteor Block");
                LanguageRegistry.addName(VenusBrick, "Venus Brick");
				LanguageRegistry.addName(VenusSulferBlock, "Sulfer Block");
				LanguageRegistry.addName(VenusRedGemOre, "Red Gem Ore");
				LanguageRegistry.addName(VenusMeteorOre, "Meteor Ore");
				LanguageRegistry.addName(VenusSulferOre, "Sulfer Ore");
				LanguageRegistry.addName(VenusIronOre, "Iron Ore");
				LanguageRegistry.addName(VenusCoalOre, "Coal Ore");	
				LanguageRegistry.addName(VenusTinOre, "Tin Ore");
				LanguageRegistry.addName(VenusCopperOre, "Copper Ore");
				LanguageRegistry.addName(VenusBasalt, "Basalt");
				LanguageRegistry.addName(VenusHalfBasalt, "Basalt Slab");
				LanguageRegistry.addName(EvolvedBlazeEgg, "Evolved Blaze Egg");

				//Registring Items Names
                LanguageRegistry.addName(SulferIngot, "Sulfer Ingot");
                LanguageRegistry.addName(RedGem, "Red Gem");
                LanguageRegistry.addName(CompressedSulfer, "Compressed Sulfer");
                LanguageRegistry.addName(CompressedRedGem, "Compressed Red Gem");
                LanguageRegistry.addName(HeavyVenusPlate, "Heavy Venus Plate");
                LanguageRegistry.addName(VenusPlate, "Venus Plate");
                
				//Adding ItemStack for recipes
                ItemStack VenusCobblestone = new ItemStack(GalacticraftVenus.VenusCobblestone);
                ItemStack VenusStone = new ItemStack(GalacticraftVenus.VenusStone);                
                ItemStack SulferIngot = new ItemStack(GalacticraftVenus.SulferIngot);
                ItemStack VenusSulferBlock = new ItemStack(GalacticraftVenus.VenusSulferBlock);
                ItemStack VenusRedGem = new ItemStack(GalacticraftVenus.RedGem);
                ItemStack VenusRedGemBlock = new ItemStack(GalacticraftVenus.VenusRedGemBlock);
                ItemStack VenusCoalOre = new ItemStack(GalacticraftVenus.VenusCoalOre);
                ItemStack VenusTinOre = new ItemStack(GalacticraftVenus.VenusTinOre);
                ItemStack VenusCopperOre = new ItemStack(GalacticraftVenus.VenusCopperOre);
                ItemStack CompressedSulfer = new ItemStack(GalacticraftVenus.CompressedSulfer);
                ItemStack CompressedRedGem = new ItemStack(GalacticraftVenus.CompressedRedGem);
                ItemStack VenusPlate = new ItemStack(GalacticraftVenus.VenusPlate);    
//              ItemStack ingotTin = new ItemStack(GCCoreItems.basicItem.ingotTin);  
//              ItemStack ingotCopper = new ItemStack(GCCoreItems.basicItem.ingotCopper);
                
                ItemStack meteoricIronRaw = new ItemStack(GCMoonItems.meteoricIronRaw);
                ItemStack furnace = new ItemStack(Block.furnaceIdle);
                ItemStack coal = new ItemStack(Item.coal);
                ItemStack ingotIron = new ItemStack(Item.ingotIron);
                
                //Register Crafting
                GameRegistry.addRecipe(VenusSulferBlock, "xxx", "xxx", "xxx", 'x', SulferIngot);
                GameRegistry.addRecipe(VenusRedGemBlock, "xxx", "xxx", "xxx", 'x', VenusRedGem);
                GameRegistry.addRecipe(furnace, "xxx", "x x", "xxx", 'x', VenusCobblestone);
                
                //Register Galacticraft Compressor Recipes
//              CompressorRecipes.recipes.add(VenusPlate, "xxx", "   ", "yyy", 'x', SulferIngot, 'y', RedGem);
//              CompressorRecipes.recipes.add(HeavyVenusPlate, "xxx", "   ", "xxx", 'x', VenusPlate);
                
                
                //Register Smelting
                GameRegistry.addSmelting(GalacticraftVenus.VenusCobblestone.blockID, VenusStone, 0.7f);
                GameRegistry.addSmelting(GalacticraftVenus.VenusSulferOre.blockID, SulferIngot, 0.7f);
                GameRegistry.addSmelting(GalacticraftVenus.VenusMeteorOre.blockID, meteoricIronRaw, 0.7f);
                GameRegistry.addSmelting(GalacticraftVenus.VenusRedGemOre.blockID, VenusRedGem, 0.7f);
                GameRegistry.addSmelting(GalacticraftVenus.VenusCoalOre.blockID, coal, 0.7f);
                GameRegistry.addSmelting(GalacticraftVenus.VenusIronOre.blockID, ingotIron, 0.7f); 
//              GameRegistry.addSmelting(GalacticraftVenus.VenusTinOre.blockID, ingotTin, 0.7f);
//              GameRegistry.addSmelting(GalacticraftVenus.VenusCopperOre.blockID, ingotCopper, 0.7f);
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) 
        {
        	
        }
}


