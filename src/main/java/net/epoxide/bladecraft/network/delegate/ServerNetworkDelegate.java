package net.epoxide.bladecraft.network.delegate;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Delegate for the server. Packets must be sent to players. 
 * 
 * @author Ghosrec35
 **/
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
