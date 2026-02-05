package net.glitchcore.modernwarfare.entity;

import net.glitchcore.modernwarfare.ModernWarfareMod;
import net.glitchcore.modernwarfare.entity.custom.MolotovProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>>ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModernWarfareMod.MOD_ID);

    public static final RegistryObject<EntityType<MolotovProjectileEntity>>MOLOTOV_PROJECTILE=
            ENTITY_TYPES.register("molotov_projectile",
                    ()->EntityType.Builder.<MolotovProjectileEntity>of(MolotovProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f,0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .setCustomClientFactory((spawnEntity, level) -> new MolotovProjectileEntity(level))
                            .build("molotov_projectile"));

    public static void register(IEventBus eventBus){ENTITY_TYPES.register(eventBus);}
}
