package net.epoxide.bladecraft.network.message;

import io.netty.buffer.ByteBuf;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateForgeLayer implements IMessage, IMessageHandler<MessageUpdateForgeLayer, IMessage>
{
    private int x, y, z;
    private int layer;
    
    // Just here for the Message Handler instantiation
    public MessageUpdateForgeLayer(){}
    
    public MessageUpdateForgeLayer(TileEntityForge forge, int layer)
    {
        this.x = forge.xCoord;
        this.y = forge.yCoord;
        this.z = forge.zCoord;
        this.layer = layer;
    }
    
    @Override
    public void fromBytes(ByteBuf buffer)
    {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.layer = buffer.readInt();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.layer);
    }

    @Override
    public IMessage onMessage(MessageUpdateForgeLayer message, MessageContext ctx)
    {
        TileEntityForge forge = (TileEntityForge) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
        
        if(forge != null)
        {
            forge.setSelectedLayer(message.layer);
        }
        
        return null;
    }
}
