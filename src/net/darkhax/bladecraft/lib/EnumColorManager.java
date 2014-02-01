package net.darkhax.bladecraft.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum EnumColorManager {

	Black("Black", "#191919", new ItemStack(Item.dyePowder,1,0)),
	RED("Red", "#993333", new ItemStack(Item.dyePowder,1,1)),
	GREEN("Green", "#667F33", new ItemStack(Item.dyePowder,1,2)),
	BROWN("Brown", "#664C33", new ItemStack(Item.dyePowder,1,3)),
	BLUE("Blue", "#334CB2", new ItemStack(Item.dyePowder,1,4)),
	PURPLE("Purple", "#7F3FB2", new ItemStack(Item.dyePowder,1,5)),
	CYAN("Cyan", "#4C7F99", new ItemStack(Item.dyePowder,1,6)),
	LIGHT_GRAY("Light_Gray", "#999999", new ItemStack(Item.dyePowder,1,7)),
	GRAY("Gray", "#4C4C4C", new ItemStack(Item.dyePowder,1,8)),
	PINK("Pink", "#F27FA5", new ItemStack(Item.dyePowder,1,9)),
	LIME("Lime", "#7FCC19", new ItemStack(Item.dyePowder,1,10)),
	YELLOW("Yellow", "#E5E533", new ItemStack(Item.dyePowder,1,11)),
	LIGHT_BLUE("Light_Blue", "#6699D8", new ItemStack(Item.dyePowder,1,12)),
	MAGENTA("Magenta", "#B24CD8", new ItemStack(Item.dyePowder,1,13)),
	ORANGE("Orange", "#D87F33", new ItemStack(Item.dyePowder,1,14)),
	WHITE("White", "#FFFFFF", new ItemStack(Item.dyePowder,1,15));
	
	public static final String[] dyeColors = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
	
    private EnumColorManager(String color, String hex, ItemStack stack) {

    	this.color = color;
    	this.hex = hex;
    	this.stack = stack;
    }

    private String color;
    private String hex;
    private ItemStack stack;

    /**
     * Get an enum based on name.
     * @param color: identifier used to get an enum. uses color.
     * @return: Instance of the Enum being used.
     */
    public static EnumColorManager getEnum(String color) {

        for(EnumColorManager current : values()) {

            if(current.color.equalsIgnoreCase(color)) {

                return current;
            }
        }

        System.out.println("Failed to find " + color + ". Please report to a Bladecraft developer.");
        return null;
    }

    /**
     * Get a string version of the color name.
     * @param color: name of color to get.
     * @return: Name of color in string form.
     */
    public static String getColor(String color) {

        EnumColorManager current = getEnum(color);
        
        if(current != null) {

            return current.color;
        }
        
        System.out.println("Failed to find " + color + ". Please report to a Bladecraft developer.");
        return "No color found";
    }
    
    /**
     * Get a string version of the hex code.
     * @param color: name of color to get.
     * @return: Hex code for color in string format.
     */
    public static String getHexColor(String color) {
    	
    	EnumColorManager current = getEnum(color);
    	
    	if (current != null) {
    		
    		return current.hex;
    	}
    	
    	System.out.println("Failed to find " + color + ". Please report to a Bladecraft developer.");
    	return "No color found";
    }
    
    /**
     * Get an ItemStack associated with the color.
     * @param color: color of stack.
     * @return An ItemStack related to the color. Eg, Ink
     */
    public static ItemStack getItemStack(String color) {
    	
    	EnumColorManager current = getEnum(color);
    	
    	if (current != null) {
    		
    		return current.stack;
    	}
    	
    	System.out.println("Failed to find " + color + ". Please report to a Bladecraft developer.");
    	return null; //oh noes!
    }
}