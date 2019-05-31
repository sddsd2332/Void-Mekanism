package com.github.stilllogic20.voidpower.addon.mekanism.common.patcher;

import net.minecraftforge.fml.relauncher.FMLCorePlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions({ "com.github.stilllogic20.voidpower.addon.mekanism" })
public class PatcherCorePlugin extends FMLCorePlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { "com.github.stilllogic20.voidpower.addon.mekanism.common.patcher.VariantPatcherTransformer" };
    }

    @Override
    public String getModContainerClass() {
        return "com.github.stilllogic20.voidpower.addon.mekanism.common.patcher.PatcherModContainer";
    }

}
