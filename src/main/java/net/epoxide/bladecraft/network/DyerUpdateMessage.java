package net.epoxide.bladecraft.network;

import io.netty.buffer.ByteBuf;
import net.epoxide.bladecraft.tileentity.TileEntityDyer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**
 *  Working on implementation for Dyer updates. 
 **/
public class DyerUpdateMessage implements IMessage
{   
    public DyerUpdateMessage(TileEntityDyer dyer)
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
