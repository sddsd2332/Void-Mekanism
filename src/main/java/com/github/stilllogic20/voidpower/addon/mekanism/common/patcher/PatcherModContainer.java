package com.github.stilllogic20.voidpower.addon.mekanism.common.patcher;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class PatcherModContainer extends DummyModContainer {

    public PatcherModContainer() {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "voidpower-mekanism-patcher";
        meta.name = "Void Power: Mekanism Integration (Patcher)";
        meta.version = "1.0.0";
        meta.authorList = ImmutableList.of("azure");
        meta.description = "Unlimited Power for Mekanism.";
        meta.url = "https://github.com/stilllogic20/voidpower-addons/for-mekanism/";
        meta.credits = "";
        setEnabledState(true);
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}
