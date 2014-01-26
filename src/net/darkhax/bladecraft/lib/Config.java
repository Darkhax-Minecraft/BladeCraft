package net.darkhax.bladecraft.lib;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Config {

	private Configuration config;

	/* Configuration Properties */
	public static int coloranatorBlockID;

	public Config(File file) {

		config = new Configuration(file);
	}

	public void init() {

		config.load();
		Property clteProp = config.get(config.CATEGORY_BLOCK, "Coloranator Block ID", 3214);
		clteProp.comment = "The ID used for the Coloranator Block";
		coloranatorBlockID = clteProp.getInt();
		config.save();
	}
}
