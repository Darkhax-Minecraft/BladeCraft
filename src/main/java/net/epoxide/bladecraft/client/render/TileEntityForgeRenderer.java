package net.epoxide.bladecraft.client.render;

import net.epoxide.bladecraft.client.model.ModelForge;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityForgeRenderer extends TileEntitySpecialRenderer
{
    private ModelForge model = new ModelForge();
    
    @Override
    public void renderTileEntityAt(TileEntity te, double f, double f1, double f2, float f3)
    {
        if(te instanceof TileEntityForge)
        {
            model.setTileEntity((TileEntityForge)te);
            
            model.render((Entity)null, (float)f, (float)f1, (float)f2, (float)f3, 0.0625F, 0.0625F);
        }
    }
}
