package net.darkhax.bladecraft.lib;

public class Utils {

	/**
	 * Utility method for converting a Hexadecimal String to an RGB int array
	 * 
	 * @param String
	 *            - String to convert to hex.
	 * @return int Array containing 3 indices, Red, Green, and Blue
	 **/
	public static int[] getRGBFromHexStr(String hexStr) {

		int hex = Integer.decode(hexStr);
		int[] rgb = new int[3];
		rgb[0] = (hex >>> 16) & 0xFF;
		rgb[1] = (hex >>> 8) & 0xFF;
		rgb[2] = (hex >>> 0) & 0xFF;
		return rgb;
	}

	/**
	 * Utility method for converting an rgb int array to a Hexadecimal String
	 * 
	 * @param RGB
	 *            int array to be converting to Hexadecimal String
	 * @return String - Converted Hexadecimal String
	 **/
	public static String getHexStrFromRGB(int[] rgb) {

		if (rgb.length == 3) {
			int red = (rgb[0] << 16);
			int green = (rgb[1] << 8);
			int blue = (rgb[2] << 0);
			int hex = red + green + blue;
			return Integer.toHexString(hex);
		}
		return null;
	}
}
