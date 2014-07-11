package net.epoxide.bladecraft.api;

import java.util.List;

/**
 * This designates a DyeableSword to BladeCraft
 * 
 * *Experimental method of dynamically registering items as dyeables* The class
 * implementing this interface does not need to register itself.
 * 
 * @author Ghostrec35
 **/
public interface IDyeableRegister
{
    /**
     * Supply your Mod's ModID. This allows us to attach bind entries to your
     * mod's ModContainer which allows us to keep track of where any errors
     * occur for debugging to be much simpler, knowing which mod was the origin
     * of the error.
     **/
    public String getModID();

    /**
     * When implementing this method, use this to register the values for your
     * now dyeable items. Adding {@link DyeableKeyValuePair}s to this list
     * causes them to be registered internally with our classes for further
     * processing. By requiring the modder to supply these KeyValuePairs for
     * each Item, we enable the ability to allow you to utilize your custom
     * textures with proper coloring as well as the ability for you to modify
     * the coloring of your items by knowing the NBT key to be used.
     **/
    public void addDyeableEntries(List<Dyeable> dyeableList);
}
