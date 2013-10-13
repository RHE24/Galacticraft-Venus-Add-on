package spacecraft.mods.galacticraft.venus.items;

import java.util.List;

import spacecraft.mods.galacticraft.venus.GalacticraftVenus;
import spacecraft.mods.galacticraft.venus.entities.VenusEntityRocketT3;
import mekanism.api.EnumColor;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.api.entity.IRocketType.EnumRocketType;
import micdoodle8.mods.galacticraft.api.item.IHoldableItem;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import micdoodle8.mods.galacticraft.core.client.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.entities.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.core.entities.EntityTieredRocket;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VenusSpaceshipTier3 extends Item implements IHoldableItem
{
    public VenusSpaceshipTier3(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftVenus.tabVenus;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        int amountOfCorrectBlocks = 0;

        if (par3World.isRemote)
        {
            return false;
        }
        else
        {
            float centerX = -1;
            float centerY = -1;
            float centerZ = -1;

            for (int i = -1; i < 2; i++)
            {
                for (int j = -1; j < 2; j++)
                {
                    final int id = par3World.getBlockId(par4 + i, par5, par6 + j);
                    final int id2 = par3World.getBlockId(par4 + i, par5 + 1, par6 + j);

                    if (id == GalacticraftVenus.T3LaunchPad.blockID || id == GalacticraftVenus.T3LaunchPadFull.blockID || id2 == GalacticraftVenus.T3LaunchPadFull.blockID)
                    {
                        amountOfCorrectBlocks++;

                        centerX = par4 + i + 0.5F;
                        centerY = par5 - 2.2F;
                        centerZ = par6 + j + 0.5F;

                        if (id == GalacticraftVenus.T3LaunchPadFull.blockID || id2 == GalacticraftVenus.T3LaunchPadFull.blockID)
                        {
                            amountOfCorrectBlocks = 9;
                        }

                        if (id2 == GCCoreBlocks.landingPadFull.blockID)
                        {
                            centerX = par4 + i + 0.5F;
                            centerY = par5 + 1 - 2.2F;
                            centerZ = par6 + j + 0.5F;
                        }
                    }
                }
            }

            if (amountOfCorrectBlocks == 9)
            {
                EntitySpaceshipBase rocket = null;

                if (par1ItemStack.getItemDamage() < 10)
                {
                    rocket = new VenusEntityRocketT3(par3World, centerX, centerY + 0.2D, centerZ, EnumRocketType.values()[par1ItemStack.getItemDamage()]);
                }
                else
                {
//                    rocket = new VenusEntityCargoRocket(par3World, centerX, centerY + 4.2D - 1.6D, centerZ, EnumRocketType.values()[par1ItemStack.getItemDamage() - 10]);
                }

                par3World.spawnEntityInWorld(rocket);

                if (!par2EntityPlayer.capabilities.isCreativeMode)
                {
                    par2EntityPlayer.inventory.consumeInventoryItem(par1ItemStack.getItem().itemID);
                }

                if (rocket instanceof IRocketType && ((IRocketType) rocket).getType().getPreFueled())
                {
                    if (rocket instanceof EntityTieredRocket)
                    {
                        ((EntityTieredRocket) rocket).spaceshipFuelTank.fill(new FluidStack(GalacticraftCore.FUEL, rocket.getMaxFuel()), true);
                    }
//                    else if (rocket instanceof VenusEntityCargoRocket)
                    {
//                        ((VenusEntityCargoRocket) rocket).spaceshipFuelTank.fill(new FluidStack(GalacticraftCore.FUEL, rocket.getMaxFuel()), true);
                    }
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < EnumRocketType.values().length; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }

        for (int i = 11; i < 10 + EnumRocketType.values().length - 1; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par2List, boolean b)
    {
        EnumRocketType type = null;

        if (par1ItemStack.getItemDamage() < 10)
        {
            type = EnumRocketType.values()[par1ItemStack.getItemDamage()];
        }
        else
        {
            type = EnumRocketType.values()[par1ItemStack.getItemDamage() - 10];
        }

        if (!type.getTooltip().isEmpty())
        {
            par2List.add(type.getTooltip());
        }

        if (type.getPreFueled())
        {
            par2List.add(EnumColor.RED + "\u00a7o" + "Creative Only");
        }
        
        if (par1ItemStack.getItemDamage() >= 10)
        {
            par2List.add(EnumColor.AQUA + "Requires Launch Controller");
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName(par1ItemStack) + (par1ItemStack.getItemDamage() < 10 ? ".t2Rocket" : ".cargoRocket");
    }

    @Override
    public boolean shouldHoldLeftHandUp(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean shouldHoldRightHandUp(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean shouldCrouch(EntityPlayer player)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    }
}