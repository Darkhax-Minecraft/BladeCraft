package net.epoxide.bladecraft.client.model;

import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelForge extends ModelBase
{
    private TileEntityForge forge;
    
    ModelRenderer Shelf;
    ModelRenderer TopOne;
    ModelRenderer SideTopOne;
    ModelRenderer SideTopTwo;
    ModelRenderer SideTopThree;
    ModelRenderer SideTopFour;
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
        textureWidth = 60;
        textureHeight = 32;

        Shelf = new ModelRenderer(this, 0, 0);
        Shelf.addBox(0F, 0F, 0F, 16, 1, 16);
        Shelf.setRotationPoint(-8F, 18F, -8F);
        Shelf.setTextureSize(60, 32);
        Shelf.mirror = true;
        setRotation(Shelf, 0F, 0F, 0F);
        TopOne = new ModelRenderer(this, -16, 0);
        TopOne.addBox(0F, 0F, 0F, 16, 0, 16);
        TopOne.setRotationPoint(-8F, 8F, -8F);
        TopOne.setTextureSize(60, 32);
        TopOne.mirror = true;
        setRotation(TopOne, 0F, 0F, 0F);
        SideTopOne = new ModelRenderer(this, 16, 26);
        SideTopOne.addBox(0F, 0F, 0F, 16, 6, 0);
        SideTopOne.setRotationPoint(-8F, 8F, 8F);
        SideTopOne.setTextureSize(60, 32);
        SideTopOne.mirror = true;
        setRotation(SideTopOne, 0F, 0F, 0F);
        SideTopTwo = new ModelRenderer(this, 16, 10);
        SideTopTwo.addBox(0F, 0F, 0F, 0, 6, 16);
        SideTopTwo.setRotationPoint(-8F, 8F, -8F);
        SideTopTwo.setTextureSize(60, 32);
        SideTopTwo.mirror = true;
        setRotation(SideTopTwo, 0F, 0F, 0F);
        SideTopThree = new ModelRenderer(this, 16, 26);
        SideTopThree.addBox(0F, 0F, 0F, 16, 6, 0);
        SideTopThree.setRotationPoint(-8F, 8F, -8F);
        SideTopThree.setTextureSize(60, 32);
        SideTopThree.mirror = true;
        setRotation(SideTopThree, 0F, 0F, 0F);
        SideTopFour = new ModelRenderer(this, 16, 10);
        SideTopFour.addBox(0F, 0F, 0F, 0, 6, 16);
        SideTopFour.setRotationPoint(8F, 8F, -8F);
        SideTopFour.setTextureSize(60, 32);
        SideTopFour.mirror = true;
        setRotation(SideTopFour, 0F, 0F, 0F);
        SideBottomOne = new ModelRenderer(this, 8, 22);
        SideBottomOne.addBox(0F, 0F, 0F, 4, 10, 0);
        SideBottomOne.setRotationPoint(-8F, 14F, 8F);
        SideBottomOne.setTextureSize(60, 32);
        SideBottomOne.mirror = true;
        setRotation(SideBottomOne, 0F, 0F, 0F);
        SideBottomTwo = new ModelRenderer(this, 0, 22);
        SideBottomTwo.addBox(0F, 0F, 0F, 4, 10, 0);
        SideBottomTwo.setRotationPoint(-8F, 14F, -8F);
        SideBottomTwo.setTextureSize(60, 32);
        SideBottomTwo.mirror = true;
        setRotation(SideBottomTwo, 0F, 0F, 0F);
        SideBottomThree = new ModelRenderer(this, 8, 22);
        SideBottomThree.addBox(0F, 0F, 0F, 4, 10, 0);
        SideBottomThree.setRotationPoint(4F, 14F, -8F);
        SideBottomThree.setTextureSize(60, 32);
        SideBottomThree.mirror = true;
        setRotation(SideBottomThree, 0F, 0F, 0F);
        SideBottomFour = new ModelRenderer(this, 0, 22);
        SideBottomFour.addBox(0F, 0F, 0F, 4, 10, 0);
        SideBottomFour.setRotationPoint(4F, 14F, 8F);
        SideBottomFour.setTextureSize(60, 32);
        SideBottomFour.mirror = true;
        setRotation(SideBottomFour, 0F, 0F, 0F);
        SideBottomFive = new ModelRenderer(this, 8, 18);
        SideBottomFive.addBox(0F, 0F, 0F, 0, 10, 4);
        SideBottomFive.setRotationPoint(-8F, 14F, -8F);
        SideBottomFive.setTextureSize(60, 32);
        SideBottomFive.mirror = true;
        setRotation(SideBottomFive, 0F, 0F, 0F);
        SideBottomSix = new ModelRenderer(this, 0, 18);
        SideBottomSix.addBox(0F, 0F, 0F, 0, 10, 4);
        SideBottomSix.setRotationPoint(-8F, 14F, 4F);
        SideBottomSix.setTextureSize(60, 32);
        SideBottomSix.mirror = true;
        setRotation(SideBottomSix, 0F, 0F, 0F);
        SideBottomSeven = new ModelRenderer(this, 0, 18);
        SideBottomSeven.addBox(0F, 0F, 0F, 0, 10, 4);
        SideBottomSeven.setRotationPoint(8F, 14F, -8F);
        SideBottomSeven.setTextureSize(60, 32);
        SideBottomSeven.mirror = true;
        setRotation(SideBottomSeven, 0F, 0F, 0F);
        SideBottomEight = new ModelRenderer(this, 8, 18);
        SideBottomEight.addBox(0F, 0F, 0F, 0, 10, 4);
        SideBottomEight.setRotationPoint(8F, 14F, 4F);
        SideBottomEight.setTextureSize(60, 32);
        SideBottomEight.mirror = true;
        setRotation(SideBottomEight, 0F, 0F, 0F);
        BaseOne = new ModelRenderer(this, -4, 18);
        BaseOne.addBox(0F, 0F, 0F, 4, 0, 4);
        BaseOne.setRotationPoint(4F, 24F, 4F);
        BaseOne.setTextureSize(60, 32);
        BaseOne.mirror = true;
        setRotation(BaseOne, 0F, 0F, 0F);
        BaseTwo = new ModelRenderer(this, -4, 18);
        BaseTwo.addBox(0F, 0F, 0F, 4, 0, 4);
        BaseTwo.setRotationPoint(-8F, 24F, -8F);
        BaseTwo.setTextureSize(60, 32);
        BaseTwo.mirror = true;
        setRotation(BaseTwo, 0F, 0F, 0F);
        BaseThree = new ModelRenderer(this, -4, 18);
        BaseThree.addBox(0F, 0F, 0F, 4, 0, 4);
        BaseThree.setRotationPoint(-8F, 24F, 4F);
        BaseThree.setTextureSize(60, 32);
        BaseThree.mirror = true;
        setRotation(BaseThree, 0F, 0F, 0F);
        BaseFour = new ModelRenderer(this, -4, 18);
        BaseFour.addBox(0F, 0F, 0F, 4, 0, 4);
        BaseFour.setRotationPoint(4F, 24F, -8F);
        BaseFour.setTextureSize(60, 32);
        BaseFour.mirror = true;
        setRotation(BaseFour, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shelf.render(f5);
        TopOne.render(f5);
        SideTopOne.render(f5);
        SideTopTwo.render(f5);
        SideTopThree.render(f5);
        SideTopFour.render(f5);
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
        this.forge = te;
    }
}
