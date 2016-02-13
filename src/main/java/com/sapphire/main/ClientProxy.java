package com.sapphire.main;

import com.sapphire.lib.ModelFlash;

import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends ServerProxy
{
	private static final ModelBiped bipedBase = new ModelBiped(0.2f);
	private static final ModelFlash flash_armor = new ModelFlash(0.2f);
	
	@Override
	public void registerRenderThings()
	{
		//RenderingRegistry.registerEntityRenderingHandler((Class)EntitySpeedTrail.class, (Render)new RendererSpeedTrail());
	}
	
	public static ModelBiped getArmorModel(int id)
	{
		switch (id)
		{
		case 7:
		{
			return flash_armor;
		}
		}
		return bipedBase;
	}
	/*
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx);
	}
	*/
}
