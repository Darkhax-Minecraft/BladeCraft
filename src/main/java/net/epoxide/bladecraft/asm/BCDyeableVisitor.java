package net.epoxide.bladecraft.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

public class BCDyeableVisitor implements IClassTransformer
{
    
    
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if(name.startsWith("net.minecraft")) return bytes;
        
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);
        for(AnnotationNode annotation : cn.visibleAnnotations)
        {
            // TODO Write annotation visiting code to retrieve all instances of our API annotation.
        }
        return bytes;
    }
}
