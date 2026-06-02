// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class suisouboots<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "suisouboots"), "main");
	private final ModelPart Right Foot;
	private final ModelPart Left Foot;

	public suisouboots(ModelPart root) {
		this.Right Foot = root.getChild("Right Foot");
		this.Left Foot = root.getChild("Left Foot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Right Foot = partdefinition.addOrReplaceChild("Right Foot", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -1.0F, -2.5F, 4.0F, 1.5F, 4.7F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Left Foot = partdefinition.addOrReplaceChild("Left Foot", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -1.0F, -2.5F, 4.0F, 1.5F, 4.7F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Right Foot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Left Foot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}