package net.epoxide.bladecraft.network.delegate;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**
 * Simple delegation system for networking. For now, the only difference between
 * the Client and Server is the way packets are sent, so this must be overridden.
 * 
 * @author Ghosrec35
 **/
public interface ISidedNetworkDelegate
{
    public void sendMessage(IMessage message);
}
