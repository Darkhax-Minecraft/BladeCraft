package net.epoxide.bladecraft.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * This designates a DyeableSword to BladeCraft
 * 
 * *Experimental method of dynamically registering items as dyeables*
 * 
 * @author Ghostrec35
 **/
public class BCAPIClassVisitor extends ClassVisitor
{
    public BCAPIParser parser;
    
    public BCAPIClassVisitor(BCAPIParser parser)
    {
        super(Opcodes.ASM4);
        this.parser = parser;
    }
    
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) 
    {
        for(String itf : interfaces)
        {   
            parser.isValidInterface(itf);
        }
    }
    
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) 
    {
        if(name.equals(parser.getModIDMethodName()))
            parser.isValidMethod(desc);
        else if(name.equals(parser.getAddDyeableMethodName()))
            parser.isValidMethod(signature);
        
        return null;
    }
    
    public void visitEnd()
    {
        parser.endType();
    }
}

