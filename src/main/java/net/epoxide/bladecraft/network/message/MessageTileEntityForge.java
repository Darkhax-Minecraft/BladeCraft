package net.epoxide.bladecraft.network.message;

import io.netty.buffer.ByteBuf;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityForge implements IMessage, IMessageHandler<MessageTileEntityForge, IMessage>
{
    public int x;
    public int y;
    public int z;
    public int forgingTime;
    public String customName;
    
    public MessageTileEntityForge() {}
    
    public MessageTileEntityForge(TileEntityForge forge)
    {
        this.x = forge.xCoord;
        this.y = forge.yCoord;
        this.z = forge.zCoord;
        this.forgingTime = forge.getForgingTime();
        this.customName = forge.getCustomName();
    }
    
    @Override
    public void fromBytes(ByteBuf buffer)
    {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.forgingTime = buffer.readInt();
        this.customName = new String(buffer.readBytes(buffer.readInt()).array());
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.forgingTime);
        buffer.writeInt(this.customName.length());
        buffer.writeBytes(this.customName.getBytes());
    }

    @Override
    public IMessage onMessage(MessageTileEntityForge message, MessageContext ctx)
    {
        TileEntity te = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        
        if(te instanceof TileEntityForge)
        {
            ((TileEntityForge) te).setForgingTime(message.forgingTime);
            ((TileEntityForge) te).setCustomName(message.customName);
        }
        
        return null;
    }

}
