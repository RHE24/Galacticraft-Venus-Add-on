package spacecraft.mods.galacticraft.pluto;

import java.io.File;
import java.util.logging.Level;
import micdoodle8.mods.galacticraft.core.GCCoreConfigManager;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class GCPlutoConfigManager
{
    public static boolean loaded;

    static Configuration configuration;

    public GCPlutoConfigManager(File file)
    {
        if (!GCPlutoConfigManager.loaded)
        {
            GCPlutoConfigManager.configuration = new Configuration(file);
            this.setDefaultValues();
        }
    }

    // DIMENSIONS
    public static int dimensionIDPluto;

    // BLOCKS
    public static int idBlockPluto;

    // ITEMS
    public static int idItemPlutoBasic;
    
    // ARMOR

    // TOOLS

    // ENTITIES

    // GUI

    // SCHEMATIC

    // ACHIEVEMENTS

    // GENERAL
//    public static boolean disableGemOreMercury;
//    public static boolean disableCoalOreMercury;  
//    public static boolean disableTinOreMercury;
//    public static boolean disableCopperOreMercury;
  
    public static boolean generateOtherMods;

    private void setDefaultValues()
    {
        try
        {
            GCPlutoConfigManager.configuration.load();

            GCPlutoConfigManager.dimensionIDPluto = GCPlutoConfigManager.configuration.get("Dimensions", "Venus Dimension ID", -48).getInt(-48);

            GCPlutoConfigManager.idBlockPluto = GCPlutoConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "idBlockVenus", 5390).getInt(5390);
     
            GCPlutoConfigManager.idItemPlutoBasic = GCPlutoConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "idItemvenusItemBasic", 5905).getInt(5905);

//            GCPlutoConfigManager.disableGemOreMercury = GCPlutoConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Disable Gem Ore Gen on Mercury", false).getBoolean(false);
//            GCPlutoConfigManager.disableCoalOreMercury = GCPlutoConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Disable Coal Ore Gen on Mercury", false).getBoolean(false);
//            GCPlutoConfigManager.disableTinOreMercury = GCPlutoConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Disable Tin Ore Gen on Mercury", false).getBoolean(false);
//            GCPlutoConfigManager.disableCopperOreMercury = GCPlutoConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Disable Copper Ore Gen on Mercury", false).getBoolean(false);

            GCPlutoConfigManager.generateOtherMods = GCPlutoConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Generate other mod's features on Mercury", false).getBoolean(false);        
        }
        catch (final Exception e)
        {
            FMLLog.log(Level.SEVERE, e, "Galacticraft Pluto has a problem loading it's configuration");
        }
        finally
        {
            GCPlutoConfigManager.configuration.save();
            GCPlutoConfigManager.loaded = true;
        }
    }
}
