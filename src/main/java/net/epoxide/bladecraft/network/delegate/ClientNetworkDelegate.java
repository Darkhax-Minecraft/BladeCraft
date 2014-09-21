package net.epoxide.bladecraft.network.delegate;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ClientNetworkDelegate implements ISidedNetworkDelegate
{
    private SimpleNetworkWrapper channel;
    
    public ClientNetworkDelegate(SimpleNetworkWrapper channel)
    {
        this.channel = channel;
    }
    
    @Override
    public void sendMessage(IMessage message)
    {
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(channel.getPacketFrom(message));
    }
}
