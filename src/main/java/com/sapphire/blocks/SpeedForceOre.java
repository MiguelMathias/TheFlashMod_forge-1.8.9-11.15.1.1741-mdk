package com.sapphire.blocks;

import java.util.Random;

import com.sapphire.handlers.CreativeTabHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

public class SpeedForceOre extends Block
{

	public SpeedForceOre(Material mat)
	{
		super(mat);
		this.setCreativeTab(CreativeTabHandler.tabFlash);
		this.setHarvestLevel("pickaxe", 3);
	}

	private Random rand = new Random();
}
