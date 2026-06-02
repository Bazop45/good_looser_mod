// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class suisouchestplate<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "suisouchestplate"), "main");
	private final ModelPart Body;
	private final ModelPart Right Arm;
	private final ModelPart Left Arm;
	private final ModelPart podol;
	private final ModelPart podolleft;
	private final ModelPart podolright;

	public suisouchestplate(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Right Arm = root.getChild("Right Arm");
		this.Left Arm = root.getChild("Left Arm");
		this.podol = root.getChild("podol");
		this.podolleft = this.podol.getChild("podolleft");
		this.podolright = this.podol.getChild("podolright");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.5F, -2.25F, 8.0F, 12.0F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Right Arm = partdefinition.addOrReplaceChild("Right Arm", CubeListBuilder.create().texOffs(0, 17).addBox(-3.0F, -1.5F, -2.25F, 4.0F, 12.0F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Left Arm = partdefinition.addOrReplaceChild("Left Arm", CubeListBuilder.create().texOffs(18, 17).addBox(-1.0F, -1.5F, -2.25F, 4.0F, 12.0F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition podol = partdefinition.addOrReplaceChild("podol", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition podolleft = podol.addOrReplaceChild("podolleft", CubeListBuilder.create().texOffs(26, 0).addBox(1.25F, -4.25F, -2.75F, 3.0F, 2.0F, 4.8F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition podolright = podol.addOrReplaceChild("podolright", CubeListBuilder.create().texOffs(26, 7).addBox(-4.25F, -4.25F, -2.75F, 3.0F, 2.0F, 4.8F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Right Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Left Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		podol.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}