package net.epoxide.bladecraft.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraft.item.Item;

/**
 * This designates a DyeableSword to BladeCraft
 * 
 * *Experimental method of dynamically registering items as dyeables*
 * 
 * @author Ghostrec35
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dyeable
{
    boolean isMetadataSensitive() default false;
    DyeableKeyValuePair[] dyeableKeyValuePairs();
}
