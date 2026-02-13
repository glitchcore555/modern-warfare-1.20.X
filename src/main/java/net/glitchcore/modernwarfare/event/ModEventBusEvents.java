package net.glitchcore.modernwarfare.event;

import net.glitchcore.modernwarfare.ModernWarfareMod;
import net.glitchcore.modernwarfare.entity.ModEntities;
import net.glitchcore.modernwarfare.entity.client.BombModel;
import net.glitchcore.modernwarfare.entity.custom.BombEntity;
import net.glitchcore.modernwarfare.entity.layers.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModernWarfareMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.BOMB_LAYER, BombModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.BOMB.get(), BombEntity.CreateAttributes().build());
    }

}
