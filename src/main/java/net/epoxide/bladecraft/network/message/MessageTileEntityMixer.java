package net.epoxide.bladecraft.network.message;

import com.jcraft.jogg.Buffer;

import io.netty.buffer.ByteBuf;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityMixer implements IMessage, IMessageHandler<MessageTileEntityMixer, IMessage>
{
    public int x;
    public int y;
    public int z;
    public int splitTime;
    public int mixTime;
    public boolean shouldMix;
    public float redCompAmt;
    public float greenCompAmt;
    public float blueCompAmt;
    public String customName;
    public String hexStr;
    
    public MessageTileEntityMixer() {}
    
    public MessageTileEntityMixer(TileEntityMixer mixer)
    {
        this.x = mixer.xCoord;
        this.y = mixer.yCoord;
        this.z = mixer.zCoord;
        this.splitTime = mixer.getSplitTime();
        this.mixTime = mixer.getMixTime();
        this.shouldMix = mixer.getShouldMix();
        this.redCompAmt = mixer.getRedComponentAmt();
        this.greenCompAmt = mixer.getGreenComponentAmt();
        this.blueCompAmt = mixer.getBlueComponentAmt();
        this.customName = mixer.getCustomName();
        this.hexStr = mixer.getHexStr();
    }
    
    @Override
    public void fromBytes(ByteBuf buffer)
    {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.splitTime = buffer.readInt();
        this.mixTime = buffer.readInt();
        this.shouldMix = buffer.readBoolean();
        this.redCompAmt = buffer.readFloat();
        this.greenCompAmt = buffer.readFloat();
        this.blueCompAmt = buffer.readFloat();
        this.customName = new String(buffer.readBytes(buffer.readInt()).array());
        this.hexStr = new String(buffer.readBytes(buffer.readInt()).array());
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.splitTime);
        buffer.writeInt(this.mixTime);
        buffer.writeBoolean(this.shouldMix);
        buffer.writeFloat(this.redCompAmt);
        buffer.writeFloat(this.greenCompAmt);
        buffer.writeFloat(this.blueCompAmt);
        buffer.writeInt(this.customName.length());
        buffer.writeBytes(this.customName.getBytes());
        buffer.writeInt(this.hexStr.length());
        buffer.writeBytes(this.hexStr.getBytes());
    }

    @Override
    public IMessage onMessage(MessageTileEntityMixer message, MessageContext ctx)
    {
        TileEntity te = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        
        if(te instanceof TileEntityMixer)
        {
            ((TileEntityMixer) te).setSplitTime(message.splitTime);
            ((TileEntityMixer) te).setMixTime(message.mixTime);
            ((TileEntityMixer) te).setShouldMix(message.shouldMix);
            ((TileEntityMixer) te).setRedComponentAmt(message.redCompAmt);
            ((TileEntityMixer) te).setGreenComponentAmt(message.greenCompAmt);
            ((TileEntityMixer) te).setBlueComponentAmt(message.blueCompAmt);
            ((TileEntityMixer) te).setCustomName(message.customName);
            ((TileEntityMixer) te).setHexStr(message.hexStr);
        }
        
        return null;
    }

}
