package net.epoxide.bladecraft.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This designates a DyeableSword to BladeCraft
 * 
 * *Experimental method of dynamically registering Swords as dyeables*
 * 
 * @author Ghostrec35
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DyeableSword
{
    String hiltOverlay();
    String insetOverlay();
    String bladeOverlay();
}
