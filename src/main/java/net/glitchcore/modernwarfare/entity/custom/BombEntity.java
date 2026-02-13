package net.glitchcore.modernwarfare.entity.custom;

import net.glitchcore.modernwarfare.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BombEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public BombEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(1,new FollowParentGoal(this,1.1d));
        this.goalSelector.addGoal(2,new WaterAvoidingRandomStrollGoal(this,1.0D));
        this.goalSelector.addGoal(3,new LookAtPlayerGoal(this, Player.class,4f));
        this.goalSelector.addGoal(4,new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder CreateAttributes(){
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH,35D)
                .add(Attributes.MOVEMENT_SPEED,0.15D)
                .add(Attributes.ARMOR_TOUGHNESS,0.1f)
                .add(Attributes.ATTACK_DAMAGE,2);
    }



    @Override
    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.BOMB.get().create(pLevel);
    }


    private void setupAnimationStates(){
        if (this.idleAnimationTimeout<=0) {
            this.idleAnimationTimeout = random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }
        else {
            --this.idleAnimationTimeout;
        }
    }

    protected void updateWalkAnimation(float v){
        float f;
        if(this.getPose()== Pose.STANDING){
            f = Math.min(v * 6.0f,1.0f);
        }
        else {
            f = 0.0f;
        }

        this.walkAnimation.update(f,0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide){
            this.setupAnimationStates();
        }
    }
}
