package net.epoxide.bladecraft.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DyeableKeyValuePair
{
    String nbtKeyForIcon();
    String iconLocation();
}
