package com.github.stilllogic20.voidpower.addon.mekanism.common.init;

import com.github.stilllogic20.voidpower.addon.mekanism.common.util.Construct;
import mekanism.api.EnumColor;
import mekanism.common.ColourRGBA;
import mekanism.common.tier.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;

public class Integrations {

    @Construct
    public static void construct() {
        registerPaddingTier();
        registerVoidTier();
    }

    private static void registerVoidTier() {
        EnumHelper.addEnum(BaseTier.class, "VOID", new Class<?>[] { String.class, EnumColor.class }, "Void", EnumColor.DARK_GREY);
        EnumHelper.addEnum(BinTier.class, "VOID", new Class<?>[] { int.class }, Integer.MAX_VALUE);
        EnumHelper.addEnum(CableTier.class, "VOID", new Class<?>[] { int.class }, Integer.MAX_VALUE);
        EnumHelper.addEnum(ConductorTier.class, "VOID", new Class<?>[] { double.class, double.class, double.class, ColourRGBA.class }, 5.0D, 1.0D, 100000.0D, new ColourRGBA(0.1D, 0.1D, 0.1D, 1.0D));
        EnumHelper.addEnum(EnergyCubeTier.class, "VOID", new Class<?>[] { double.class, double.class }, 16384000000.0, Double.MAX_VALUE);
        EnumHelper.addEnum(FactoryTier.class, "VOID", new Class<?>[] { int.class, ResourceLocation.class }, 7, new ResourceLocation("mekanism", "gui/factory/GuiEliteFactory.png"));
        EnumHelper.addEnum(FluidTankTier.class, "VOID", new Class<?>[] { int.class, int.class }, 1073741823, 1073741823);
        EnumHelper.addEnum(GasTankTier.class, "VOID", new Class<?>[] { int.class, int.class }, 1073741823, 1073741823);
        EnumHelper.addEnum(InductionCellTier.class, "VOID", new Class<?>[] { double.class }, 32768000000000.);
        EnumHelper.addEnum(InductionProviderTier.class, "VOID", new Class<?>[] { double.class }, 65536000000000.);
        EnumHelper.addEnum(PipeTier.class, "VOID", new Class<?>[] { int.class, int.class }, 64000, 6400);
        EnumHelper.addEnum(TransporterTier.class, "VOID", new Class<?>[] { int.class, int.class }, 8192, 65536);
        EnumHelper.addEnum(TubeTier.class, "VOID", new Class<?>[] { int.class, int.class }, 8388608, 2097152);
    }

    private static void registerPaddingTier() {
        EnumHelper.addEnum(InductionCellTier.class, "PADDING_BY_BEDROCKTOOLS", new Class<?>[] { double.class }, 0);
        EnumHelper.addEnum(InductionProviderTier.class, "PADDING_BY_BEDROCKTOOLS", new Class<?>[] { double.class }, 0);
        EnumHelper.addEnum(CableTier.class, "PADDING_BY_BEDROCKTOOLS", new Class<?>[] { int.class }, 0);
        EnumHelper.addEnum(PipeTier.class, "PADDING_BY_BEDROCKTOOLS", new Class<?>[] { int.class, int.class }, 0, 0);
        EnumHelper.addEnum(TubeTier.class, "PADDING_BY_BEDROCKTOOLS", new Class<?>[] { int.class, int.class }, 0, 0);
        EnumHelper.addEnum(TransporterTier.class, "PADDING_BY_BEDROCKTOOLS", new Class<?>[] { int.class, int.class }, 0, 0);
        EnumHelper.addEnum(ConductorTier.class, "PADDING_BY_BEDROCKTOOLS", new Class<?>[] { double.class, double.class, double.class, ColourRGBA.class }, 0., 0., 0., new ColourRGBA(0., 0., 0., 1.));
    }

}
