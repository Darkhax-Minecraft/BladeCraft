package net.epoxide.bladecraft.event;

import net.epoxide.bladecraft.util.Reference;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ToolTipEventHandler
{
    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event)
    {
        if(event.itemStack.getItem() instanceof ItemSword)
        {
            if(!event.itemStack.hasTagCompound())
            {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setString(Reference.BLADE_HEX_NBT_KEY, "Unset");
                nbt.setString(Reference.HILT_HEX_NBT_KEY, "Unset");
                nbt.setString(Reference.INSET_HEX_NBT_KEY, "Unset");
                event.itemStack.setTagCompound(nbt);
            }
            String bladeColor = event.itemStack.stackTagCompound.getString(Reference.BLADE_HEX_NBT_KEY);
            String hiltColor = event.itemStack.stackTagCompound.getString(Reference.HILT_HEX_NBT_KEY);
            String insetColor = event.itemStack.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY);
          
            if(insetColor.equals("Unset"))
                event.toolTip.add(1, "Inset Color: Unset");
            else
                event.toolTip.add(1, String.format("Inset Color: #%s", insetColor));
            
            if(hiltColor.equals("Unset"))
                event.toolTip.add(1, "Hilt Color: Unset");
            else
                event.toolTip.add(1, String.format("Hilt Color: #%s", hiltColor));

            if(bladeColor.equals("Unset"))
                event.toolTip.add(1, "Blade Color: Unset");
            else
                event.toolTip.add(1, String.format("Blade Color: #%s", bladeColor));
        }
    }
}
