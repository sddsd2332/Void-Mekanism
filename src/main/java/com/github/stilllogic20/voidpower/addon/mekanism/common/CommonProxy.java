package com.github.stilllogic20.voidpower.addon.mekanism.common;

import com.github.stilllogic20.voidpower.addon.mekanism.common.init.Integrations;
import com.github.stilllogic20.voidpower.addon.mekanism.common.util.Initializers;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class CommonProxy {

    protected Stream<Class<?>> initializers() {
        return Stream.of(Integrations.class);
    }

    public void construct(@Nullable FMLConstructionEvent event) {
        initializers().map(Initializers::of).forEach(initializer -> {
            Exception exception = initializer.initialize();
            if (exception != null)
                throw new InternalError(exception);
        });
    }

}
