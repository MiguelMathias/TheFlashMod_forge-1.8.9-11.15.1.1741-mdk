package com.sapphire.lib;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFlash extends ModelBiped
{
	ModelRenderer ear1;
	ModelRenderer ear2;
	ModelRenderer ear3;
	ModelRenderer ear4;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer cape;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer plate_1;
	ModelRenderer plate_2;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	
	public ModelFlash(float expand)
	{
		super(expand, 0.0f, 130, 40);
		this.ear1 = new ModelRenderer(this, 42, 33);
		this.ear1.addBox(-4.52f, -6.5f, 0.0f, 0, 1, 2);
		this.ear1.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.ear1.setTextureSize(130, 40);
		this.ear1.mirror = true;
		this.setRotation(this.ear1, 0.0743572f, 0.0f, 0.0f);
		this.ear2 = new ModelRenderer(this, 42, 33);
		this.ear2.addBox(-4.5f, -7.0f, 0.5f, 0, 1, 3);
		this.ear2.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.ear2.setTextureSize(130, 40);
		this.ear2.mirror = true;
		this.setRotation(this.ear2, 0.0743572f, 0.0f, 0.0f);
		this.ear3 = new ModelRenderer(this, 42, 33);
		this.ear3.addBox(4.5f, -6.5f, 0.0f, 0, 1, 2);
		this.ear3.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.ear3.setTextureSize(130, 40);
		this.ear3.mirror = true;
		this.setRotation(this.ear3, 0.0743572f, 0.0f, 0.0f);
		this.ear4 = new ModelRenderer(this, 42, 33);
		this.ear4.addBox(4.5f, -7.0f, 0.5f, 0, 1, 3);
		this.ear4.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.ear4.setTextureSize(130, 40);
		this.ear4.mirror = true;
		this.setRotation(this.ear4, 0.0743572f, 0.0f, 0.0f);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.head.setTextureSize(130, 40);
		this.head.mirror = true;
		this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
		this.body = new ModelRenderer(this, 16, 24);
		this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.body.setTextureSize(130, 40);
		this.body.mirror = true;
		this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
		this.rightarm = new ModelRenderer(this, 40, 24);
		this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		this.rightarm.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.rightarm.setTextureSize(130, 40);
		this.rightarm.mirror = true;
		this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
		this.leftarm = new ModelRenderer(this, 40, 24);
		this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		this.leftarm.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.leftarm.setTextureSize(130, 40);
		this.leftarm.mirror = true;
		this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
		this.rightleg = new ModelRenderer(this, 0, 16);
		this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		this.rightleg.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.rightleg.setTextureSize(130, 40);
		this.rightleg.mirror = true;
		this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
		this.leftleg = new ModelRenderer(this, 0, 16);
		this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		this.leftleg.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.leftleg.setTextureSize(130, 40);
		this.leftleg.mirror = true;
		this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
		this.plate_1 = new ModelRenderer(this, 42, 33);
		this.plate_1.addBox(3.5f, -6.2f, -1.0f, 1, 2, 2);
		this.plate_1.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.plate_1.setTextureSize(130, 40);
		this.plate_1.mirror = true;
		this.setRotation(this.plate_1, 0.0743572f, 0.0f, 0.0f);
		this.plate_2 = new ModelRenderer(this, 42, 33);
		this.plate_2.addBox(-4.5f, -6.2f, -1.0f, 1, 2, 2);
		this.plate_2.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.plate_2.setTextureSize(130, 40);
		this.plate_2.mirror = true;
		this.setRotation(this.plate_2, 0.0743572f, 0.0f, 0.0f);
		this.head.addChild(this.ear1);
		this.head.addChild(this.ear2);
		this.head.addChild(this.ear3);
		this.head.addChild(this.ear4);
		this.head.addChild(this.plate_1);
		this.head.addChild(this.plate_2);
		this.bipedHead.addChild(this.head);
		this.bipedRightArm.addChild(this.rightarm);
		this.bipedLeftArm.addChild(this.leftarm);
		this.bipedRightLeg.addChild(this.rightleg);
		this.bipedLeftLeg.addChild(this.leftleg);
		this.bipedBody.addChild(this.body);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
