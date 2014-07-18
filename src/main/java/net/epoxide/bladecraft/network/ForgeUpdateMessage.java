package net.epoxide.bladecraft.network;

import io.netty.buffer.ByteBuf;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**
 *  Working on implementation for Dyer updates. 
 **/
public class ForgeUpdateMessage implements IMessage
{   
    public ForgeUpdateMessage(TileEntityForge forge)
    {
        
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        
    }
}
