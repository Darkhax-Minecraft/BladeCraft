package net.epoxide.bladecraft.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class BCDataCollector implements IClassTransformer 
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) 
	{
		ClassReader cr = new ClassReader(bytes);
		BCClassVisitor classVisitor = new BCClassVisitor(Opcodes.ASM4);
		return bytes;
	}
}
