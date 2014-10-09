package net.epoxide.bladecraft.network.delegate;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Delegate for the client. Packets must be sent to the server.
 * 
 * @author Ghostrec35
 **/
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
        channel.sendToServer(message);
    }
}
