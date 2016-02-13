package com.sapphire.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class Abilities extends Potion
{
	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID,
			"textures/items/abilityicons.png");
	
	public Abilities(ResourceLocation par1, boolean par2, int par3)
	{
		super(par1, par2, par3);
	}
	
	public boolean bindTexture()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		return true;
	}
	
	public Potion iconIndex(int par1, int par2)
	{
		super.setIconIndex(par1, par2);
		return this;
	}
}
