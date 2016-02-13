/*
If this comment is removed, the program will blow up 
       ,~~.
      (  6 )-_,
 (\___ )=='-'
  \ .   ) )
   \ `-' /
~'`~'`~'`~'`~
*/

package com.sapphire.main;

import com.sapphire.blocks.FlashBlocks;
import com.sapphire.gui.SpeedGUIHandler;
import com.sapphire.handlers.CraftingHandler;
import com.sapphire.handlers.KeysHandler;
import com.sapphire.handlers.SpeedForceHandler;
import com.sapphire.items.FlashItems;
import com.sapphire.lib.Abilities;
import com.sapphire.lib.GameRules;
import com.sapphire.lib.RefStrings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.lang.reflect.Field;

@Mod(modid = RefStrings.MODID, version = RefStrings.VERSION, name = RefStrings.NAME)

public class MainRegistry
{
	@SidedProxy(clientSide = "com.sapphire.main.ClientProxy", serverSide = "com.sapphire.main.ServerProxy") public static ServerProxy proxy;
	
	// public static final SimpleNetworkWrapper NETWORK;
	
	@Mod.Metadata public static ModMetadata meta;
	
	@Mod.Instance(RefStrings.MODID) public static MainRegistry modInstance;
	public static Potion speedForce;

	// public static Achievement achievementFlash;
	public static int speedForcePotionID;
	public static Potion speedStrength;
	public static int strengthPotionID;
	public static Potion speedDigging;
	public static int speedDigID;
	private IBlockAccess lastWorld;

	public static boolean usable()
	{
		return Minecraft.getMinecraft().isSingleplayer();
	}
	
	public void initConfiguration(FMLPreInitializationEvent preEvent)
	{
		Configuration config = new Configuration(new File("config/config.cfg"));
		config.load();
		speedForcePotionID = config
				.getInt("Speed Force ID", "Abilities", 80, 0, Integer.MAX_VALUE, "Speed Force Ability ID");
		strengthPotionID = config.getInt("Strength ID", "Abilities", 84, 0, Integer.MAX_VALUE, "Strength Ability ID");
		speedDigID = config.getInt("Speed Dig ID", "Abilities", 80, 0, Integer.MAX_VALUE, "Speed Dig Ability ID");

		GameRules.isSpeedLimit = config
				.getBoolean("Speed Limit", "Game Rules", false, "Is there a speed limit on characters?");
		GameRules.isBalanced = config
				.getBoolean("Balanced", "Game Rules", true, "Are characters balanced with each other?");

		FlashItems.flashDurability = 6250;

		config.save();
	}
	
	@Mod.EventHandler public void PreLoad(FMLPreInitializationEvent preEvent)
	{
		this.initConfiguration(preEvent);

		FlashBlocks.mainRegistry();
		FlashItems.mainRegistry();
		CraftingHandler.mainRegistry();
		KeysHandler.mainRegistry();

		proxy.registerRenderThings();

		boolean modEntityID = false;
		Potion[] potionTypes = null;
		for (Field f : Potion.class.getDeclaredFields())
		{
			f.setAccessible(true);
			try
			{
				if (!f.getName().equals("potionTypes") && !f.getName().equals("field_76425_a"))
					continue;
				Field modfield = Field.class.getDeclaredField("modifiers");
				modfield.setAccessible(true);
				modfield.setInt(f, f.getModifiers() & -17);
				potionTypes = (Potion[]) f.get(null);
				Potion[] newPotionTypes = new Potion[256];
				System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
				f.set(null, newPotionTypes);
				continue;
			}
			catch (Exception e)
			{
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}

		FMLCommonHandler.instance().bus().register(new KeysHandler());
		MinecraftForge.EVENT_BUS.register(new SpeedForceHandler());
		MinecraftForge.EVENT_BUS.register(new SpeedGUIHandler());
	}
	
	@Mod.EventHandler public void load(FMLInitializationEvent event)
	{
		// proxy.registerRenderThings();

		// AchievementPage.registerAchievementPage(new AchievementPage("Flash
		// Achievements", new Achievement[]
		// { achievementFlash }));

		speedForce = (new Abilities(new ResourceLocation("speed_force"), false, 0).iconIndex(0, 0))
				.setPotionName("potion.speedForce")
				.registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed,
						"91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
		speedStrength = new Abilities(new ResourceLocation("speed_strength"), false, 0)
				.setPotionName("potion.strongPotion")
				.registerPotionAttributeModifier(SharedMonsterAttributes.attackDamage,
						"648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 3.0, 1);
		speedDigging = (new Abilities(new ResourceLocation("speed_dig"), false, 0)).setPotionName("potion.digFast");
	}
	
	@Mod.EventHandler public void PostLoad(FMLPostInitializationEvent postEvent)
	{

	}
}
