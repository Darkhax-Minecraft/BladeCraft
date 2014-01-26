package net.darkhax.bladecraft.command;

import cpw.mods.fml.common.FMLLog;
import net.darkhax.bladecraft.lib.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class CommandSetColor extends CommandBase {

	@Override
	public String getCommandName() {

		return "setcolor";
	}

	@Override
	public int getRequiredPermissionLevel() {

		return 1;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {

		return "bccommands.setcolor.usage";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] cmdArgs) {

		if (cmdArgs.length > 0 && cmdArgs[0].length() == 1
				&& cmdArgs[1].length() == 7) {
			EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(icommandsender.getCommandSenderName());
			if (player == null) return;

			ItemStack itemstack = player.getCurrentEquippedItem();
			if (itemstack.getItem() instanceof ItemSword) {
				if (!itemstack.hasTagCompound())
					itemstack.setTagCompound(new NBTTagCompound());
				
				FMLLog.severe("Checkpoint 1");
				
				if (isValidHexColor(cmdArgs[1])) {
					FMLLog.severe("Checkpoint 2");
					if (cmdArgs[0].equals("i"))
						itemstack.getTagCompound().setString(Reference.INSET_HEX_NBT_KEY, cmdArgs[1]);
					else if (cmdArgs[0].equals("c"))
						itemstack.getTagCompound().setString(Reference.COLOR_HEX_NBT_KEY, cmdArgs[1]);
					else
						throw new WrongUsageException(Reference.SETCOLOR_COMMANDUSAGE_KEY, new Object[0]);
				}
			}
		}
		else {
			throw new WrongUsageException(Reference.SETCOLOR_COMMANDUSAGE_KEY, new Object[0]);
		}
	}

	private boolean isValidHexColor(String str) {

		boolean isValid = true;
		char[] chars = str.toCharArray();
		for (int index = 1; index < chars.length - 1; index++) {
			if (!(isInRange(chars[index], 47, 58) || isInRange(chars[index], 64, 91) || isInRange(chars[index], 96, 123))) {
				isValid = false;
			}
		}
		return isValid;
	}

	private boolean isInRange(char character, int lowerBound, int upperBound) {

		return (character > lowerBound && character < upperBound) ? true
				: false;
	}
}
