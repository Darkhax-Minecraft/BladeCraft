package net.epoxide.bladecraft.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reference {

    public static final String MOD_ID = "bladecraft";
    public static final String MOD_NAME = "Bladecraft";
    public static final String VERSION = "2.0.0";
    public static final String PROXY_CLIENT = "net.epoxide.bladecraft.proxy.ProxyClient";
    public static final String PROXY_COMMON = "net.epoxide.bladecraft.proxy.ProxyCommon";
    public static final String FACTORY = "net.epoxide.bladecraft.gui.BladecraftGuiFactory";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static final String BLADE_HEX_NBT_KEY = "overlayBlade";
    public static final String HILT_HEX_NBT_KEY = "overlayHilt";
    public static final String INSET_HEX_NBT_KEY = "overlayInset";
    public static final String ALLOY_COLOR_TAG = "ALLOY_COLOR";
    
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    public static final int FORGE_GUI_ID = 0;
    public static final int MIXER_GUI_ID = 1;
    public static final int MIXER_COLORSELECTOR_GUI_ID = 2;
}
