package net.epoxide.bladecraft.inventory;

import cpw.mods.fml.common.FMLLog;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerForge extends Container
{
    private InventoryPlayer playerInv;
    private TileEntityForge tileEntity;
    
    public ContainerForge(InventoryPlayer inventory, TileEntityForge te)
    {
        super();
        playerInv = inventory;
        tileEntity = te;
        this.addSlotToContainer(new SlotSword(playerInv.player, te, 0, 32, 27, false));
        this.addSlotToContainer(new SlotAlloy(playerInv.player, te, 1, 80, 27));
        this.addSlotToContainer(new SlotSword(playerInv.player, te, 2, 128, 27, true));
        FMLLog.severe("Constructing Container for Forge");

        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 4 + j * 19, 79 + i * 19));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInv, i, 4 + i * 19, 140));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileEntity.isUseableByPlayer(player);
    }
}
