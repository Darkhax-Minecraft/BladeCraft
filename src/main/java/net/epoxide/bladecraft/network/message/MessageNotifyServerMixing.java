package net.epoxide.bladecraft.network.message;

import io.netty.buffer.ByteBuf;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageNotifyServerMixing implements IMessage, IMessageHandler<MessageNotifyServerMixing, IMessage>
{
    public int x;
    public int y;
    public int z;
    public boolean shouldMix;

    public MessageNotifyServerMixing() {}
    
    public MessageNotifyServerMixing(TileEntityMixer mixer, boolean shouldMix)
    {
        this.x = mixer.xCoord;
        this.y = mixer.yCoord;
        this.z = mixer.zCoord;
        this.shouldMix = shouldMix;
    }
    
    @Override
    public void fromBytes(ByteBuf buffer)
    {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.shouldMix = buffer.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeBoolean(this.shouldMix);
    }

    @Override
    public IMessage onMessage(MessageNotifyServerMixing message, MessageContext ctx)
    {
        TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
        
        if(te instanceof TileEntityMixer)
        {
            ((TileEntityMixer) te).setShouldMix(this.shouldMix);
        }
        return null;
    }

}
