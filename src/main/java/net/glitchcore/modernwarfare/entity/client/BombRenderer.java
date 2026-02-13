package net.glitchcore.modernwarfare.entity.client;

import net.glitchcore.modernwarfare.ModernWarfareMod;
import net.glitchcore.modernwarfare.entity.custom.BombEntity;
import net.glitchcore.modernwarfare.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BombRenderer extends MobRenderer<BombEntity,BombModel<BombEntity>> {
    private static final ResourceLocation BOMB_LOCATION = new ResourceLocation(ModernWarfareMod.MOD_ID,"textures/entity/bomb.png");
    public BombRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BombModel<>(pContext.bakeLayer(ModModelLayers.BOMB_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(BombEntity pEntity) {
        return null;
    }
}
