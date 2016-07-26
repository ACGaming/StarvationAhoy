package dmfmm.StarvationAhoy.btmstuff.entity;

/**
 * Created by TeamDMFMM on 7/25/2016. Code originally written
 * for StarvationAhoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSAPlayer extends ModelBase {
    public ModelRenderer field_178736_x;
    public ModelRenderer field_178734_a;
    public ModelRenderer field_178731_d;
    public ModelRenderer field_178732_b;
    public ModelRenderer field_178720_f;
    public ModelRenderer field_178733_c;
    public ModelRenderer rightArm;
    public ModelRenderer rightLeg;
    public ModelRenderer head;//head
    public ModelRenderer body;//body
    public ModelRenderer leftArm;//left arm
    public ModelRenderer leftLeg;//leftleg
    public ModelRenderer field_178730_v;

    public ModelSAPlayer() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.rightArm = new ModelRenderer(this, 40, 16);
        this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000000149011613F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.field_178734_a = new ModelRenderer(this, 48, 48);
        this.field_178734_a.setRotationPoint(5.0F, 2.0F, -0.0F);
        this.field_178734_a.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.setRotateAngle(field_178734_a, 0.0F, 0.0F, -0.10000000149011613F);
        this.field_178732_b = new ModelRenderer(this, 40, 32);
        this.field_178732_b.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.field_178732_b.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.setRotateAngle(field_178732_b, 0.0F, 0.0F, 0.10000000149011613F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.field_178736_x = new ModelRenderer(this, 24, 0);
        this.field_178736_x.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_178736_x.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, 0.0F);
        this.field_178730_v = new ModelRenderer(this, 16, 32);
        this.field_178730_v.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_178730_v.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.setRotationPoint(-1.899999976158142F, 12.0F, 0.10000000149011612F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.field_178731_d = new ModelRenderer(this, 0, 32);
        this.field_178731_d.setRotationPoint(-1.899999976158142F, 12.0F, 0.10000000149011612F);
        this.field_178731_d.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.leftLeg = new ModelRenderer(this, 16, 48);
        this.leftLeg.setRotationPoint(1.899999976158142F, 12.0F, 0.10000000149011612F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leftArm = new ModelRenderer(this, 32, 48);
        this.leftArm.setRotationPoint(5.0F, 2.0F, -0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(leftArm, 0.0F, 0.0F, -0.10000000149011613F);
        this.field_178720_f = new ModelRenderer(this, 32, 0);
        this.field_178720_f.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_178720_f.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.field_178733_c = new ModelRenderer(this, 0, 48);
        this.field_178733_c.setRotationPoint(1.899999976158142F, 12.0F, 0.10000000149011612F);
        this.field_178733_c.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.rightArm.render(f5);
        this.body.render(f5);
        this.field_178734_a.render(f5);
        this.field_178732_b.render(f5);
        this.head.render(f5);
        this.field_178736_x.render(f5);
        this.field_178730_v.render(f5);
        this.rightLeg.render(f5);
        this.field_178731_d.render(f5);
        this.leftLeg.render(f5);
        this.leftArm.render(f5);
        this.field_178720_f.render(f5);
        this.field_178733_c.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}