package com.github.stilllogic20.voidpower.addon.mekanism.client.init;

import com.github.stilllogic20.voidpower.addon.mekanism.common.util.Construct;
import mekanism.client.ClientProxy;
import mekanism.client.render.tileentity.RenderEnergyCube;
import mekanism.common.MekanismBlocks;
import mekanism.common.block.states.BlockStateBasic;
import mekanism.common.block.states.BlockStateTransmitter;
import mekanism.common.tier.BaseTier;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public final class Renders {

    private Renders() {
    }

    @Construct
    public static void construct() {
        MinecraftForge.EVENT_BUS.register(new Renders());
    }

    @SubscribeEvent
    public void registerTransmitterModels(@Nullable ModelRegistryEvent event) {
        for (BlockStateTransmitter.TransmitterType type : BlockStateTransmitter.TransmitterType.values()) {
            List<ModelResourceLocation> modelsToAdd = new ArrayList<>();
            if (type.hasTiers()) {
                BaseTier tierPointer = BaseTier.values()[BaseTier.CREATIVE.ordinal() + 1];
                String resource = "mekanism:" + type.getName() + "_" + tierPointer.getName();
                if (ClientProxy.transmitterResources.get(resource) == null) {
                    String properties = "inventory";
                    ModelResourceLocation model = new ModelResourceLocation(resource, properties);

                    ClientProxy.transmitterResources.put(resource, model);
                    modelsToAdd.add(model);
                }
            }

            ModelBakery.registerItemVariants(
                Item.getItemFromBlock(MekanismBlocks.Transmitter),
                modelsToAdd.toArray(new ModelResourceLocation[] {}));
        }
    }

    @SubscribeEvent
    public void registerBasicBlockModels(@Nullable ModelRegistryEvent event) {
        for (BlockStateBasic.BasicBlockType type : BlockStateBasic.BasicBlockType.values()) {
            List<ModelResourceLocation> modelsToAdd = new ArrayList<>();

            if (type.tiers) {
                BaseTier tierPointer = BaseTier.values()[BaseTier.CREATIVE.ordinal() + 1];
                String resource = "mekanism:" + type.getName() + "_" + tierPointer.getName();

                if (ClientProxy.basicResources.get(resource) == null) {
                    List<String> entries = new ArrayList<>();

                    if (type.hasActiveTexture()) {
                        entries.add("active=false");
                    }

                    if (type.hasRotations() || type == BlockStateBasic.BasicBlockType.THERMAL_EVAPORATION_CONTROLLER) {
                        entries.add("facing=north");
                    }

                    //TODO: Is this check against bin's needed
                    String properties = type == BlockStateBasic.BasicBlockType.BIN ? "inventory" : String.join(",", entries);

                    ModelResourceLocation model = new ModelResourceLocation(resource, properties);

                    ClientProxy.basicResources.put(resource, model);
                    modelsToAdd.add(model);
                }
            }

            ModelBakery.registerItemVariants(Item.getItemFromBlock(type.blockType.getBlock()),
                modelsToAdd.toArray(new ModelResourceLocation[] {}));
        }
    }

}
