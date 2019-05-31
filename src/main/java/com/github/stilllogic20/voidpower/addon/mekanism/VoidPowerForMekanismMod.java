package com.github.stilllogic20.voidpower.addon.mekanism;

import com.github.stilllogic20.voidpower.addon.mekanism.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;

import javax.annotation.Nonnull;

@Mod(modid = VoidPowerForMekanismMod.MODID, acceptableRemoteVersions = "*", dependencies = "before:mekanism")
public final class VoidPowerForMekanismMod {

    public static final String MODID = "voidpower-mekanism";

    @SidedProxy(
        modId = MODID,
        clientSide = "ClientProxy",
        serverSide = "CommonProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void construct(@Nonnull FMLConstructionEvent event) {
        proxy.construct(event);
    }

}
