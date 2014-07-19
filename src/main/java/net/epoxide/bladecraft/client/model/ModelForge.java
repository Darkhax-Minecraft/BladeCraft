package net.epoxide.bladecraft.client.model;

import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelForge extends ModelBase
{
    ModelRenderer Top;
    ModelRenderer Shelf;
    ModelRenderer SideBottomOne;
    ModelRenderer SideBottomTwo;
    ModelRenderer SideBottomThree;
    ModelRenderer SideBottomFour;
    ModelRenderer SideBottomFive;
    ModelRenderer SideBottomSix;
    ModelRenderer SideBottomSeven;
    ModelRenderer SideBottomEight;
    ModelRenderer BaseOne;
    ModelRenderer BaseTwo;
    ModelRenderer BaseThree;
    ModelRenderer BaseFour;

    public ModelForge()
    {
        textureWidth = 64;
        textureHeight = 62;

        Top = new ModelRenderer(this, 0, 0);
        Top.addBox(0F, 0F, 0F, 16, 6, 16);
        Top.setRotationPoint(-8F, 8F, -8F);
        Top.setTextureSize(64, 62);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
        Shelf = new ModelRenderer(this, 0, 22);
        Shelf.addBox(0F, 0F, 0F, 14, 1, 14);
        Shelf.setRotationPoint(-7F, 18F, -7F);
        Shelf.setTextureSize(64, 62);
        Shelf.mirror = true;
        setRotation(Shelf, 0F, 0F, 0F);
        SideBottomOne = new ModelRenderer(this, 10, 37);
        SideBottomOne.mirror = true;
        SideBottomOne.addBox(0F, 0F, 0F, 3, 10, 1);
        SideBottomOne.setRotationPoint(-7F, 14F, 7F);
        SideBottomOne.setTextureSize(64, 62);
        SideBottomOne.mirror = true;
        setRotation(SideBottomOne, 0F, 0F, 0F);
        SideBottomOne.mirror = false;
        SideBottomTwo = new ModelRenderer(this, 0, 37);
        SideBottomTwo.addBox(0F, 0F, 0F, 4, 10, 1);
        SideBottomTwo.setRotationPoint(-8F, 14F, -8F);
        SideBottomTwo.setTextureSize(64, 62);
        SideBottomTwo.mirror = true;
        setRotation(SideBottomTwo, 0F, 0F, 0F);
        SideBottomThree = new ModelRenderer(this, 10, 37);
        SideBottomThree.addBox(0F, 0F, 0F, 3, 10, 1);
        SideBottomThree.setRotationPoint(4F, 14F, -8F);
        SideBottomThree.setTextureSize(64, 62);
        SideBottomThree.mirror = true;
        setRotation(SideBottomThree, 0F, 0F, 0F);
        SideBottomFour = new ModelRenderer(this, 0, 37);
        SideBottomFour.mirror = true;
        SideBottomFour.addBox(0F, 0F, 0F, 4, 10, 1);
        SideBottomFour.setRotationPoint(4F, 14F, 7F);
        SideBottomFour.setTextureSize(64, 62);
        SideBottomFour.mirror = true;
        setRotation(SideBottomFour, 0F, 0F, 0F);
        SideBottomFour.mirror = false;
        SideBottomFive = new ModelRenderer(this, 18, 48);
        SideBottomFive.addBox(0F, 0F, 0F, 1, 10, 3);
        SideBottomFive.setRotationPoint(-8F, 14F, -7F);
        SideBottomFive.setTextureSize(64, 62);
        SideBottomFive.mirror = true;
        setRotation(SideBottomFive, 0F, 0F, 0F);
        SideBottomSix = new ModelRenderer(this, 0, 48);
        SideBottomSix.addBox(0F, 0F, 0F, 1, 10, 4);
        SideBottomSix.setRotationPoint(-8F, 14F, 4F);
        SideBottomSix.setTextureSize(64, 62);
        SideBottomSix.mirror = true;
        setRotation(SideBottomSix, 0F, 0F, 0F);
        SideBottomSeven = new ModelRenderer(this, 26, 48);
        SideBottomSeven.addBox(0F, 0F, 0F, 1, 10, 4);
        SideBottomSeven.setRotationPoint(7F, 14F, -8F);
        SideBottomSeven.setTextureSize(64, 62);
        SideBottomSeven.mirror = true;
        setRotation(SideBottomSeven, 0F, 0F, 0F);
        SideBottomEight = new ModelRenderer(this, 10, 48);
        SideBottomEight.addBox(0F, 0F, 0F, 1, 10, 3);
        SideBottomEight.setRotationPoint(7F, 14F, 4F);
        SideBottomEight.setTextureSize(64, 62);
        SideBottomEight.mirror = true;
        setRotation(SideBottomEight, 0F, 0F, 0F);
        BaseOne = new ModelRenderer(this, 18, 37);
        BaseOne.addBox(0F, 0F, 0F, 3, 1, 3);
        BaseOne.setRotationPoint(4F, 23F, 4F);
        BaseOne.setTextureSize(64, 62);
        BaseOne.mirror = true;
        setRotation(BaseOne, 0F, 0F, 0F);
        BaseTwo = new ModelRenderer(this, 18, 37);
        BaseTwo.addBox(0F, 0F, 0F, 3, 1, 3);
        BaseTwo.setRotationPoint(-7F, 23F, -7F);
        BaseTwo.setTextureSize(64, 62);
        BaseTwo.mirror = true;
        setRotation(BaseTwo, 0F, 0F, 0F);
        BaseThree = new ModelRenderer(this, 18, 37);
        BaseThree.addBox(0F, 0F, 0F, 3, 1, 3);
        BaseThree.setRotationPoint(-7F, 23F, 4F);
        BaseThree.setTextureSize(64, 62);
        BaseThree.mirror = true;
        setRotation(BaseThree, 0F, 0F, 0F);
        BaseFour = new ModelRenderer(this, 18, 37);
        BaseFour.addBox(0F, 0F, 0F, 3, 1, 3);
        BaseFour.setRotationPoint(4F, 23F, -7F);
        BaseFour.setTextureSize(64, 62);
        BaseFour.mirror = true;
        setRotation(BaseFour, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Top.render(f5);
        Shelf.render(f5);
        SideBottomOne.render(f5);
        SideBottomTwo.render(f5);
        SideBottomThree.render(f5);
        SideBottomFour.render(f5);
        SideBottomFive.render(f5);
        SideBottomSix.render(f5);
        SideBottomSeven.render(f5);
        SideBottomEight.render(f5);
        BaseOne.render(f5);
        BaseTwo.render(f5);
        BaseThree.render(f5);
        BaseFour.render(f5);
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

    public void setTileEntity(TileEntityForge te)
    {
        
    }

}
