package net.epoxide.bladecraft.asm;

import java.util.ArrayList;
import java.util.List;

import net.epoxide.bladecraft.api.IDyeableRegister;

import org.objectweb.asm.Type;


class BCAPIParser
{    
    private static String itfType = "net/epoxide/bladecraft/api/IDyeableRegister";
    private static String modIDMethodName = "getModID";
    private static String modIDMethodDesc = "()Ljava/lang/String;";
    private static String addDyeableMethodName = "addDyeableEntries";
    private static String addDyeableMethodSig = "()Ljava/lang/String;";
    
    private static List<String> listeners = new ArrayList<String>();
    
    private String className;
    private int classVersion;
    private Type classSuperType;
    
    private boolean validItf;
    private boolean containsProperModIDMethod;
    private boolean containsProperDyeableMethod;
    
    public void beginNewType(String name)
    {
        this.className = name;
        this.validItf = false;
        this.containsProperModIDMethod = false;
        this.containsProperDyeableMethod = false;
    }
    
    public void isValidInterface(String str)
    {
        if(itfType.equals(str))
            this.validItf = true;
    }
    
    public void isValidMethod(String desc)
    {
        if(modIDMethodDesc.equals(desc))
            this.containsProperModIDMethod = true;
        
        if(addDyeableMethodSig.equals(desc))
            this.containsProperDyeableMethod = true;
    }
    
    public void endType()
    {
        if(this.validItf && this.containsProperDyeableMethod && this.containsProperModIDMethod)
            listeners.add(className);
    }
    
    public String getModIDMethodName()
    {
        return this.modIDMethodName;
    }
    
    public String getAddDyeableMethodName()
    {
        return this.addDyeableMethodName;
    }

    public void sendDataToList(List<String> registers)
    {
        for(String listener : listeners)
        {
            registers.add(listener);
        }
        listeners.clear();
    }
}
