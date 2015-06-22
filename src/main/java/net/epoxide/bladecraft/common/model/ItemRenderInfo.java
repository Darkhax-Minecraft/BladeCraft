package net.epoxide.bladecraft.common.model;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

public class ItemRenderInfo 
{
	public List<KeyValuePair> layerValues = new ArrayList<KeyValuePair>();
	
	public static ItemRenderInfo getRenderInfo(Item item) 
	{
		return null;
	}
	
	public static class KeyValuePair<K, V>
	{
		private final K key;
		private final V value;
		
		public KeyValuePair(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		
		public K getKey()
		{
			return key;
		}
		
		public V getValue()
		{
			return value;
		}
		
		public boolean equals(Object obj)
		{
			if(obj == null)
				return false;
			if(obj instanceof KeyValuePair)
			{
				KeyValuePair kvp = (KeyValuePair)obj;
				if(kvp.key == this.key && kvp.value == this.value)
					return true;
			}
			return false;
		}
	}
}
