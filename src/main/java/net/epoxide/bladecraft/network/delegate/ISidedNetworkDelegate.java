package net.epoxide.bladecraft.network.delegate;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public interface ISidedNetworkDelegate
{
    public void sendMessage(IMessage message);
}
