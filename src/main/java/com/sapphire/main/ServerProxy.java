package com.sapphire.main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy
{
	public void registerRenderThings()
	{
	
	}
	
	public int addArmor(String Armor)
	{
		return 0;
	}
	
	public void registerHandlers()
	{
	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
	
	public void openBook()
	{
	}
}
