package spacecraft.mods.galacticraft.venus2.items;

import micdoodle8.mods.galacticraft.core.client.ClientProxyCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import spacecraft.mods.galacticraft.venus2.GCVenus2;
import universalelectricity.core.item.ItemElectric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GCVenus2ItemBattery extends ItemElectric
{
    public GCVenus2ItemBattery(int id, String assetName)
    {
        super(id);
        this.setUnlocalizedName(assetName);
        this.setTextureName(GCVenus2.TEXTURE_PREFIX + assetName);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return GCVenus2.galacticraftVenus2Tab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public float getMaxElectricityStored(ItemStack itemStack)
    {
        return 5000;
    }

    @Override
    public float getVoltage(ItemStack itemStack)
    {
        return 120;
    }
}