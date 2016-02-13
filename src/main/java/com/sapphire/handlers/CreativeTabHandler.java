package com.sapphire.handlers;

import com.sapphire.items.FlashItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabHandler
{
	
	public static CreativeTabs tabFlash = new CreativeTabs("flashTab")
	{
		@Override
		public Item getTabIconItem()
		{
			return FlashItems.helmetFlash;
		}
	};
	
}
