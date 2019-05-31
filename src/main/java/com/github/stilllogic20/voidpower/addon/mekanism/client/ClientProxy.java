package com.github.stilllogic20.voidpower.addon.mekanism.client;

import com.github.stilllogic20.voidpower.addon.mekanism.client.init.Renders;
import com.github.stilllogic20.voidpower.addon.mekanism.common.CommonProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.stream.Stream;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    protected Stream<Class<?>> initializers() {
        return Stream.concat(super.initializers(), Stream.of(Renders.class));
    }

}
