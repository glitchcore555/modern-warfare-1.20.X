package net.glitchcore.modernwarfare.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.glitchcore.modernwarfare.entity.animations.ModAnimationDefinitions;
import net.glitchcore.modernwarfare.entity.custom.BombEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BombModel<T extends BombEntity> extends HierarchicalModel<T> {
	private final ModelPart bomb;
	private final ModelPart body;
	private final ModelPart fuse;
    private final ModelPart head;

	public BombModel(ModelPart root) {
		this.bomb = root.getChild("bomb");
        this.head = this.bomb;
		this.body = this.bomb.getChild("body");
		this.fuse = this.bomb.getChild("fuse");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bomb = partdefinition.addOrReplaceChild("bomb", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = bomb.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fuse = bomb.addOrReplaceChild("fuse", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -10.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 1).addBox(-1.0F, -11.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(BombEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(entity,netHeadYaw,headPitch,ageInTicks);
        this.animateWalk(ModAnimationDefinitions.BOMB_WALK,limbSwing,limbSwingAmount,2f,2.5f);
        this.animate(entity.idleAnimationState,ModAnimationDefinitions.BOMB_IDLE,ageInTicks,1f);
	}

    private void applyHeadRotation(BombEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);
        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bomb.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

    @Override
    public ModelPart root() {
        return bomb;
    }
}