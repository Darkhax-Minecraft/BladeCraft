package net.epoxide.bladecraft.network.message;

import io.netty.buffer.ByteBuf;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateMixerValues implements IMessage, IMessageHandler<MessageUpdateMixerValues, IMessage>
{
    private int x, y, z;
    private String string;
    
    // Just for message handler instantiation
    public MessageUpdateMixerValues(){}
    
    public MessageUpdateMixerValues(TileEntityMixer mixer)
    {
        this.x = mixer.xCoord;
        this.y = mixer.yCoord;
        this.z = mixer.zCoord;
        string = mixer.getHexStr();
    }
    
    @Override
    public void fromBytes(ByteBuf buffer)
    {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        if(buffer.isReadable())
        {
            int strLen = buffer.readInt();
            this.string = new String(buffer.readBytes(strLen).array());
        }
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        if(string != null)
        {
            buffer.writeInt(string.length());
            buffer.writeBytes(string.getBytes());
        }
    }

    @Override
    public IMessage onMessage(MessageUpdateMixerValues message, MessageContext ctx)
    {
        FMLLog.severe("Is sending data!");
        TileEntity te = ctx.getServerHandler().playerEntity.getServerForPlayer().getTileEntity(message.x, message.y, message.z);
        System.err.println("Is reaching! TE: " + te);
        if(te instanceof TileEntityMixer)
        {
            System.err.println(String.format("Notifying server of String changes! String Hex: %s", message.string));
            ((TileEntityMixer) te).setHexStr(message.string);
        }
        return null;
    }
}
