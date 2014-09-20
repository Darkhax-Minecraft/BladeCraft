package net.epoxide.bladecraft.network;

import net.epoxide.bladecraft.network.message.MessageTileEntityForge;
import net.epoxide.bladecraft.network.message.MessageTileEntityMixer;
import net.epoxide.bladecraft.network.message.MessageUpdateForgeLayer;
import net.epoxide.bladecraft.network.message.MessageUpdateMixerValues;
import net.minecraft.network.Packet;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkManager
{
    private static final SimpleNetworkWrapper CHANNELS = NetworkRegistry.INSTANCE.newSimpleChannel("BC|Network_Channel");
    private static int nextDiscriminator = 0;
    
    public static void initialize()
    {
        addMessage(MessageTileEntityForge.class, MessageTileEntityForge.class, Side.CLIENT);
        addMessage(MessageTileEntityMixer.class, MessageTileEntityMixer.class, Side.CLIENT);
        //addMessage(MessageNotifyServerMixing.class, MessageNotifyServerMixing.class, Side.SERVER);
        addMessage(MessageUpdateMixerValues.class, MessageUpdateMixerValues.class, Side.SERVER);
        addMessage(MessageUpdateForgeLayer.class, MessageUpdateForgeLayer.class, Side.SERVER);
    }
    
    public static void sendMessage(IMessage msg)
    {
        CHANNELS.sendToAll(msg);
    }
    
    public static Packet getPacket(IMessage msg)
    {
        return CHANNELS.getPacketFrom(msg);
    }
    
    public static <REQ extends IMessage, REPLY extends IMessage> void addMessage(Class<REQ> packetClass, Class<? extends IMessageHandler<REQ, REPLY>> packetHandler, Side sideSentTo)   
    {
        if(packetClass != null && packetHandler != null)
        {
            CHANNELS.registerMessage(packetHandler, packetClass, nextDiscriminator++, sideSentTo);
        }
        else throw new InvalidNetworkRegistrationException(String.format("Attempted to register invalid Network Message: (MessageHandler: %s, Message: %s)", packetHandler.toString(), packetClass.toString()));
    }
    
    public static class InvalidNetworkRegistrationException extends RuntimeException
    {
        public InvalidNetworkRegistrationException(String exceptionMsg)
        {
            super(exceptionMsg);
        }
    }
}
