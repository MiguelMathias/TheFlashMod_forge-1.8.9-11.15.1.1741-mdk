/*
 * You may think you know what the following code does.
 * But you don't. Trust me.
 * Fiddle with it, and you'll spend many a sleepless
 * night cursing the moment you thought you'd be clever
 * enough to "optimize" the code below.
 * Now close this file and go play with something else.
 */

package com.sapphire.handlers;

import java.lang.reflect.Field;

import com.sapphire.main.MainRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

public class SlowTime
{
	public SlowTime()
	{
	}
	
	public static void setGameSpeed(float speed)
	{
		if (MainRegistry.usable())
		{
			try
			{
				Minecraft mc = Minecraft.getMinecraft();
				Field field = mc.getClass().getDeclaredField("field_71428_T");
				field.setAccessible(true);
				Timer timer = (Timer) field.get(Minecraft.getMinecraft());
				timer.timerSpeed = speed;
			}
			catch (Exception var6)
			{
				// System.out.println("Are we running in debug mode?");
				
				try
				{
					Minecraft mc2 = Minecraft.getMinecraft();
					Field field = mc2.getClass().getDeclaredField("timer");
					field.setAccessible(true);
					Timer timer = (Timer) field.get(Minecraft.getMinecraft());
					timer.timerSpeed = speed;
				}
				catch (Exception var5)
				{
					System.out.println("Something\'s wrong!");
					var6.printStackTrace();
					var5.printStackTrace();
				}
			}
		}
		else
		{
			// Minecraft.getMinecraft().ingameGUI.getChatGUI()
			// .func_146227_a(new ChatComponentText("BulletTime cannot be used
			// in a multiplayer enviroment."));
		}
		
	}
	
	public static float getGameSpeed()
	{
		float speed = 0.0F;
		
		try
		{
			Minecraft ex = Minecraft.getMinecraft();
			Field ex21 = ex.getClass().getDeclaredField("field_71428_T");
			ex21.setAccessible(true);
			Timer field1 = (Timer) ex21.get(Minecraft.getMinecraft());
			speed = field1.timerSpeed;
		}
		catch (Exception var6)
		{
			try
			{
				Minecraft ex2 = Minecraft.getMinecraft();
				Field field = ex2.getClass().getDeclaredField("timer");
				field.setAccessible(true);
				Timer timer = (Timer) field.get(Minecraft.getMinecraft());
				speed = timer.timerSpeed;
				// System.out.println(speed);
			}
			catch (Exception var5)
			{
				var6.printStackTrace();
				var5.printStackTrace();
			}
		}
		
		return speed;
	}
}
