package net.epoxide.bladecraft.network.delegate;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ServerNetworkDelegate implements ISidedNetworkDelegate
{
    private SimpleNetworkWrapper channel;

    public ServerNetworkDelegate(SimpleNetworkWrapper channel)
    {
        this.channel = channel;
    }

    @Override
    public void sendMessage(IMessage message)
    {
        channel.sendToAll(message);
    }
}
