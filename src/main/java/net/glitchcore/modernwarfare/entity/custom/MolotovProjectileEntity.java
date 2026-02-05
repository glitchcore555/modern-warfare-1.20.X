package net.glitchcore.modernwarfare.entity.custom;

import net.glitchcore.modernwarfare.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class MolotovProjectileEntity extends ThrowableItemProjectile {
    public MolotovProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public MolotovProjectileEntity(Level pLevel) {
        this(ModEntities.MOLOTOV_PROJECTILE.get(), pLevel);
    }
    public MolotovProjectileEntity(Level pLevel, LivingEntity livingentity) {
        super(ModEntities.MOLOTOV_PROJECTILE.get(), livingentity,pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(!this.level().isClientSide){
            this.level().broadcastEntityEvent(this,((byte)3));
            x
        }
        this.discard();
        super.onHitBlock(pResult);
    }
}
