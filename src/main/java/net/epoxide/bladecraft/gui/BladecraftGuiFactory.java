package net.epoxide.bladecraft.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;

public class BladecraftGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {

        return BladecraftConfigGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {

        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {

        return null;
    }
}