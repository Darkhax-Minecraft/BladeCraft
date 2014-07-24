package net.epoxide.bladecraft.inventory;

import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMixer extends Container
{
    public InventoryPlayer playerInv;
    public TileEntityMixer mixer;
    
    public ContainerMixer(InventoryPlayer inv, TileEntityMixer te)
    {
        playerInv = inv;
        mixer = te;
        
        this.addSlotToContainer(new SlotDye(te, 0, 21, 18));
        this.addSlotToContainer(new SlotSword(playerInv.player, te, 1, 83, 45, false));
        this.addSlotToContainer(new SlotMixer(playerInv.player, te, 2, 134, 45));
        
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
        return mixer.isUseableByPlayer(player);
    }
}
