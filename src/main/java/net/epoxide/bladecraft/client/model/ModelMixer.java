package net.epoxide.bladecraft.client.model;

import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMixer extends ModelBase
{
    ModelRenderer Bottom;
    ModelRenderer Rotor;
    ModelRenderer FinOne;
    ModelRenderer FinTwo;
    ModelRenderer FinThree;
    ModelRenderer FinFour;
    ModelRenderer Dye;
    ModelRenderer TopOne;
    ModelRenderer TopTwo;
    ModelRenderer TopThree;
    ModelRenderer TopFour;
    ModelRenderer TopFive;
    ModelRenderer TopSix;
    ModelRenderer TopSeven;
    ModelRenderer TopEight;
    ModelRenderer SideOne;
    ModelRenderer SideTwo;
    ModelRenderer SideThree;
    ModelRenderer SideFour;

    public ModelMixer()
    {
        textureWidth = 106;
        textureHeight = 118;

        Bottom = new ModelRenderer(this, 0, 104);
        Bottom.addBox(0F, 0F, 0F, 14, 1, 14);
        Bottom.setRotationPoint(-7F, 23F, -7F);
        Bottom.setTextureSize(106, 118);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        Rotor = new ModelRenderer(this, 34, 0);
        Rotor.addBox(0F, 0F, 0F, 1, 14, 1);
        Rotor.setRotationPoint(-0.5F, 8.5F, -0.5F);
        Rotor.setTextureSize(106, 118);
        Rotor.mirror = true;
        setRotation(Rotor, 0F, 0F, 0F);
        FinOne = new ModelRenderer(this, 38, 0);
        FinOne.addBox(0F, 0F, 0F, 6, 6, 0);
        FinOne.setRotationPoint(-3F, 14F, 0F);
        FinOne.setTextureSize(106, 118);
        FinOne.mirror = true;
        setRotation(FinOne, 0F, 0F, 0F);
        FinTwo = new ModelRenderer(this, 38, 0);
        FinTwo.addBox(0F, 0F, 0F, 6, 6, 0);
        FinTwo.setRotationPoint(0F, 14F, 3F);
        FinTwo.setTextureSize(106, 118);
        FinTwo.mirror = true;
        setRotation(FinTwo, 0F, 1.570796F, 0F);
        FinThree = new ModelRenderer(this, 38, 6);
        FinThree.addBox(0F, 0F, 0F, 4, 0, 1);
        FinThree.setRotationPoint(-0.5F, 8.5F, 2F);
        FinThree.setTextureSize(106, 118);
        FinThree.mirror = true;
        setRotation(FinThree, 0F, 1.570796F, 0F);
        FinFour = new ModelRenderer(this, 38, 6);
        FinFour.addBox(0F, 0F, 0F, 4, 0, 1);
        FinFour.setRotationPoint(-2F, 8.5F, -0.5F);
        FinFour.setTextureSize(106, 118);
        FinFour.mirror = true;
        setRotation(FinFour, 0F, 0F, 0F);
        Dye = new ModelRenderer(this, 50, 0);
        Dye.addBox(0F, 0F, 0F, 14, 11, 14);
        Dye.setRotationPoint(-7F, 12F, -7F);
        Dye.setTextureSize(106, 118);
        Dye.mirror = true;
        setRotation(Dye, 0F, 0F, 0F);
        TopOne = new ModelRenderer(this, 0, 38);
        TopOne.addBox(0F, 0F, 0F, 5, 1, 3);
        TopOne.setRotationPoint(2F, 8F, -1.5F);
        TopOne.setTextureSize(106, 118);
        TopOne.mirror = true;
        setRotation(TopOne, 0F, 0F, 0F);
        TopTwo = new ModelRenderer(this, 0, 34);
        TopTwo.addBox(0F, 0F, 0F, 5, 1, 3);
        TopTwo.setRotationPoint(-7F, 8F, -1.5F);
        TopTwo.setTextureSize(106, 118);
        TopTwo.mirror = true;
        setRotation(TopTwo, 0F, 0F, 0F);
        TopThree = new ModelRenderer(this, 16, 34);
        TopThree.addBox(0F, 0F, 0F, 3, 1, 5);
        TopThree.setRotationPoint(-1.5F, 8F, -7F);
        TopThree.setTextureSize(106, 118);
        TopThree.mirror = true;
        setRotation(TopThree, 0F, 0F, 0F);
        TopFour = new ModelRenderer(this, 32, 34);
        TopFour.addBox(0F, 0F, 0F, 3, 1, 5);
        TopFour.setRotationPoint(-1.5F, 8F, 2F);
        TopFour.setTextureSize(106, 118);
        TopFour.mirror = true;
        setRotation(TopFour, 0F, 0F, 0F);
        TopFive = new ModelRenderer(this, 0, 42);
        TopFive.addBox(0F, 0F, 0F, 1, 1, 1);
        TopFive.setRotationPoint(-2.5F, 8F, -2.5F);
        TopFive.setTextureSize(106, 118);
        TopFive.mirror = true;
        setRotation(TopFive, 0F, 0F, 0F);
        TopSix = new ModelRenderer(this, 0, 42);
        TopSix.addBox(0F, 0F, 0F, 1, 1, 1);
        TopSix.setRotationPoint(1.5F, 8F, -2.5F);
        TopSix.setTextureSize(106, 118);
        TopSix.mirror = true;
        setRotation(TopSix, 0F, 0F, 0F);
        TopSeven = new ModelRenderer(this, 0, 42);
        TopSeven.addBox(0F, 0F, 0F, 1, 1, 1);
        TopSeven.setRotationPoint(1.5F, 8F, 1.5F);
        TopSeven.setTextureSize(106, 118);
        TopSeven.mirror = true;
        setRotation(TopSeven, 0F, 0F, 0F);
        TopEight = new ModelRenderer(this, 0, 42);
        TopEight.addBox(0F, 0F, 0F, 1, 1, 1);
        TopEight.setRotationPoint(-2.5F, 8F, 1.5F);
        TopEight.setTextureSize(106, 118);
        TopEight.mirror = true;
        setRotation(TopEight, 0F, 0F, 0F);
        SideOne = new ModelRenderer(this, 0, 17);
        SideOne.addBox(0F, 0F, 0F, 16, 16, 1);
        SideOne.setRotationPoint(-8F, 8F, -8F);
        SideOne.setTextureSize(106, 118);
        SideOne.mirror = true;
        setRotation(SideOne, 0F, 0F, 0F);
        SideTwo = new ModelRenderer(this, 0, 72);
        SideTwo.addBox(0F, 0F, 0F, 1, 16, 16);
        SideTwo.setRotationPoint(-8F, 8F, -8F);
        SideTwo.setTextureSize(106, 118);
        SideTwo.mirror = true;
        setRotation(SideTwo, 0F, 0F, 0F);
        SideThree = new ModelRenderer(this, 0, 0);
        SideThree.addBox(0F, 0F, 0F, 16, 16, 1);
        SideThree.setRotationPoint(-8F, 8F, 7F);
        SideThree.setTextureSize(106, 118);
        SideThree.mirror = true;
        setRotation(SideThree, 0F, 0F, 0F);
        SideFour = new ModelRenderer(this, 0, 40);
        SideFour.addBox(0F, 0F, 0F, 1, 16, 16);
        SideFour.setRotationPoint(7F, 8F, -8F);
        SideFour.setTextureSize(106, 118);
        SideFour.mirror = true;
        setRotation(SideFour, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Bottom.render(f5);
        Rotor.render(f5);
        FinOne.render(f5);
        FinTwo.render(f5);
        FinThree.render(f5);
        FinFour.render(f5);
        Dye.render(f5);
        TopOne.render(f5);
        TopTwo.render(f5);
        TopThree.render(f5);
        TopFour.render(f5);
        TopFive.render(f5);
        TopSix.render(f5);
        TopSeven.render(f5);
        TopEight.render(f5);
        SideOne.render(f5);
        SideTwo.render(f5);
        SideThree.render(f5);
        SideFour.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void setTileEntity(TileEntityMixer te)
    {
        
    }

}
