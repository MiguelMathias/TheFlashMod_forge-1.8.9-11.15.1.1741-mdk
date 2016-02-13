package com.sapphire.main;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

public class Sides
{
	private Sides()
	{
	}
	
	public static Side logical(World world)
	{
		return world.isRemote ? Side.CLIENT : Side.SERVER;
	}
}
