package net.epoxide.bladecraft.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

import net.epoxide.bladecraft.util.Reference;
import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class CommandDye extends CommandBase {

    @Override
    public String getCommandName() {

        return "bladecraft";
    }

    @Override
    public String getCommandUsage(ICommandSender player) {

        return "commands.bladecraft.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] pars) {

        if (sender instanceof EntityPlayer && ((EntityPlayer) sender).getHeldItem().getItem() instanceof ItemSword) {

            ItemStack stack = ((EntityPlayer) sender).getHeldItem();
            Utilities.prepareStack(stack);
            
            if (pars[0].equalsIgnoreCase("blade"))
                stack.stackTagCompound.setString(Reference.BLADE_HEX_NBT_KEY, pars[1]);
            
            else if (pars[0].equalsIgnoreCase("hilt"))
                stack.stackTagCompound.setString(Reference.HILT_HEX_NBT_KEY, pars[1]);
            
            else if (pars[0].equalsIgnoreCase("inset"))
                stack.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, pars[1]);
            
            else {
                
                stack.stackTagCompound.setString(Reference.BLADE_HEX_NBT_KEY, pars[0]);
                stack.stackTagCompound.setString(Reference.HILT_HEX_NBT_KEY, pars[1]);
                stack.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, pars[2]);
            }
        }
    }
}