package net.epoxide.bladecraft.asm;

import net.epoxide.bladecraft.api.IOverlayProvider;

import org.objectweb.asm.ClassVisitor;

public class BCClassVisitor extends ClassVisitor
{
	public BCClassVisitor(int api) 
	{
		super(api);
	}
	
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{		
		for(String str: interfaces)
		{
			System.err.println(str);
			if(str.equals("net/epoxide/bladecraft/api/IOverlayProvider"))
			{
				BCLoadingPlugin.addProvider(name);
			}
		}
	}
}
