package net.epoxide.bladecraft.asm;

import java.util.ArrayList;
import java.util.List;

import net.epoxide.bladecraft.api.Dyeable;
import net.epoxide.bladecraft.api.IDyeableRegister;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;

public class BCDyeableVisitor implements IClassTransformer
{
    /**
     * The static data list that contains all of the found {@link Dyeable} annotations.
     **/
    public static List<String> registers = new ArrayList<String>();

    /**
     * The parser for analyzing a class for specific annotation types.
     **/
    private BCAPIParser parser = new BCAPIParser();

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if (name.startsWith("net.minecraft"))
            return bytes;

        ClassReader cr = new ClassReader(bytes);
        BCAPIClassVisitor cv = new BCAPIClassVisitor(parser);
        cr.accept(cv, 0);
        parser.sendDataToList(registers);
        // Simply return the original bytes. We're not making any changes here,
        // just reading in the data.
        return bytes;
    }
}
