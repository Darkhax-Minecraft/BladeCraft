package net.darkhax.bladecraft.client.render;

import net.darkhax.bladecraft.client.event.ItemIconHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemSword implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        Icon icon = ItemIconHandler.getTextureMap().registerIcon("iron_shovel");

        if(icon != null) {

            switch(type) {

                case EQUIPPED: {

                    RenderItemHelper.drawIconIn3D(item, icon);
                    break;
                }

                case EQUIPPED_FIRST_PERSON: {

                    RenderItemHelper.drawIconIn3D(item, icon);
                    break;
                }

                case INVENTORY: {

                    RenderItemHelper.renderIconInInventory(icon, 1f, 1f, 1f);
                    break;
                }

                case ENTITY: {

                    RenderItemHelper.drawIconIn3D(item, icon, true);
                    break;
                }

                default: {

                    break;
                }
            }
        }
    }
}