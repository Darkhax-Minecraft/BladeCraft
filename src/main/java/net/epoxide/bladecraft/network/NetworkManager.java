package net.epoxide.bladecraft.network;

import net.epoxide.bladecraft.network.message.MessageNotifyServerMixing;
import net.epoxide.bladecraft.network.message.MessageTileEntityForge;
import net.epoxide.bladecraft.network.message.MessageTileEntityMixer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkManager
{
    public static final SimpleNetworkWrapper CHANNELS = NetworkRegistry.INSTANCE.newSimpleChannel("BC|Network_Channel");
    
    public static void initialize()
    {
        CHANNELS.registerMessage(MessageTileEntityForge.class, MessageTileEntityForge.class, 0, Side.CLIENT);
        CHANNELS.registerMessage(MessageTileEntityMixer.class, MessageTileEntityMixer.class, 1, Side.CLIENT);
        CHANNELS.registerMessage(MessageNotifyServerMixing.class, MessageNotifyServerMixing.class, 2, Side.SERVER);
    }
}
