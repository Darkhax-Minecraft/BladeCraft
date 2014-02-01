package net.darkhax.bladecraft.lib;

import javax.swing.text.html.parser.Element;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public enum EnumColorManager {

	Black("Black", "#000000", new ItemStack(Item.dyePowder,1,0));

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