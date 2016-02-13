/* WARNING: The code that follows will make you cry;
 * 		a safety pig is provided below for your benefit.
 * 
 *                          _
 _._ _..._ .-',     _.._(`))
'-. `     '  /-._.-'    ',/
   )         \            '.
  / _    _    |             \
 |  a    a   /              |
 \   .-.                     ;  
  '-('' ).-'       ,'       ;
     '-;           |      .'
        \           \    /
        | 7  .__  _.-\   \
        | |  |  ``/  /`  /
       /,_|  |   /,_/   /
          /,_/      '`-'
 * 
 * Feel free to use the safety pig whenever it suits you best.
 */

package com.sapphire.handlers;

import com.sapphire.armor.FlashAbstract;
import com.sapphire.armor.FlashArmor;
import com.sapphire.main.MainRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/*
 * features list so far:
 * running speed
 * running on water
 * running on air (traditional and new way)
 * jumping higher
 * stronger
 * stronger as speed increases
 * running up walls
 * fall damage not taken
 * phasing
 * slow time
 * step helper
 * tornadoes
 * vortexes
 * rapid regeneration
 * invisibility
 * 
 * need:
 * speed digging
 * flash is a light source
 * lightning throw
 * steal speed
 * lightning trail
 * speed force dimension
 * becoming flash through barry's lab
 * cosmic treadmill to upgrade speed in survival
 * 
 */

public class SpeedForceHandler
{
	public static int upperSpeedLimit = 64;
	public static int lowerSpeedLimit = 0;

	public static float upperJumpMoveLimit = 0.64F;
	public static float lowerJumpMoveLimit = 0.04F;
	public static float gameSpeed = SlowTime.getGameSpeed();
	public static float slowMoFactor = 0.0F;
	public static float slowMoUpperLimit = 0.9F;
	public static float sloMoLowerLimit = 0.0F;

	public static boolean waterRunningUnlocked = true;
	public static boolean wallRunningUnlocked = true;
	public static boolean tornadoesAndVortexesUnlocked = true;
	public static boolean phasingUnlocked = true;
	public static boolean flyingUnlocked = true;
	public static boolean betterFlyingUnlocked = true;
	public static boolean lightningThrowUnlocked = true;
	public static boolean isFlying = false;

	public static double speed;
	public static Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if (event.entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP thePlayer = (EntityPlayerMP) event.entity;

			if (FlashArmor.flashFactor >= 64 && thePlayer.moveForward > 0)
			{
				if ((thePlayer.ridingEntity == null) && thePlayer.riddenByEntity == null)
				{
					if (thePlayer.timeUntilPortal > 0)
					{
						thePlayer.timeUntilPortal = 10;
					}
					else if (thePlayer.dimension != -1)
					{
						thePlayer.timeUntilPortal = 10;
						WorldServer ws = (WorldServer) thePlayer.worldObj;
						thePlayer.mcServer.getConfigurationManager()
								.transferPlayerToDimension(thePlayer, -1,
										new Teleporter(ws));
						System.out.println("true");
						// transferPlayerToDimension(thePlayer, LegendofZelda.DimID);
					}
					else
					{
						thePlayer.timeUntilPortal = 10;
						thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0);
					}
				}
			}
		}

		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;

			if (KeysHandler.SPACEBAR.isKeyDown() && player.onGround)
			{
				player.motionY = 0.42F;
				if (FlashArmor.flashFactor >= 1)
				{
					player.motionY += (double) ((float) (4 + 1) * 0.1F);
				}
				if (player.isSprinting())
				{
					float f = player.rotationYaw * 0.017453292F;
					player.motionX -= (double) (MathHelper.sin(f) * 0.2F);
					player.motionZ += (double) (MathHelper.cos(f) * 0.2F);
				}
				player.isAirBorne = true;
				ForgeHooks.onLivingJump(player);
			}
			if (KeysHandler.SPACEBAR.isKeyDown() && player.isInWater() && FlashArmor.flashFactor < 1)
			{
				player.motionY += 0.05;
			}

			if (player.isPotionActive(MainRegistry.speedForce))
			{
				// speed
				speed = player.motionY;
				if (player.moveForward > 0 && FlashArmor.flashFactor >= 1 && !player.isSneaking())
				{
					player.setSprinting(true);
				}

				World world1 = player.worldObj;
				int x1 = MathHelper.floor_double(player.posX);
				int y1 = MathHelper.floor_double(player.posY + player.eyeHeight);
				int z1 = MathHelper.floor_double(player.posZ);

				// water running
				if (FlashArmor.flashFactor >= 1)
				{
					if (world1.getBlockState(new BlockPos(x1, (int) (player.getEntityBoundingBox().minY - 1), z1))
							.getBlock().getMaterial() == Material.water
							|| player.isInWater() && SpeedForceHandler.waterRunningUnlocked)
					{
						player.motionY = 0.0D;
						player.capabilities.isFlying = true;
						player.isInWater();
						player.playSound("liquid.water", 10F, 10F);
						//player.onGround = true;

						if (player.isSneaking() && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()
								&& Minecraft.getMinecraft().currentScreen == null)
						{
							player.motionY = -0.42;
						}
						if (KeysHandler.SPACEBAR.isKeyDown() && !Minecraft.getMinecraft().ingameGUI.getChatGUI()
								.getChatOpen() && Minecraft.getMinecraft().currentScreen == null)
						{
							if(player.isInWater())
							{
								player.motionY += 0.175;
							}
							if (FlashArmor.flashFactor >= 1)
							{
								player.motionY += (double) ((float) (4 + 1) * 0.1F);
							}
							if (player.isSprinting())
							{
								float f = player.rotationYaw * 0.017453292F;
								player.motionX -= (double) (MathHelper.sin(f) * 0.2F);
								player.motionZ += (double) (MathHelper.cos(f) * 0.2F);
							}

							player.isAirBorne = true;
							ForgeHooks.onLivingJump(player);
						}
					}
					else
					{
						player.capabilities.isFlying = false;
					}
				}
				// speed fallling while in slo-mo
				if (slowMoFactor > 0 && player.motionY <= -0.000000000000001 && player.isSneaking())
				{
					player.motionY -= slowMoFactor * 1.1111111111111111111111111111111111111111111;
				}

				// rapid regeneration
				if (player.getHealth() < player.getMaxHealth() && !player.isDead)
				{
					player.heal(
							(float) (player.getActivePotionEffect(MainRegistry.speedStrength).getAmplifier() * 0.0125));
					player.hurtTime = 0;
					player.maxHurtTime = 0;
				}
				if (player.getCurrentArmor(3) != null && player.getCurrentArmor(2) != null
						&& player.getCurrentArmor(1) != null && player.getCurrentArmor(0) != null && player
						.getCurrentArmor(3).getItem() instanceof FlashAbstract && player.getCurrentArmor(2)
						.getItem() instanceof FlashAbstract && player.getCurrentArmor(1)
						.getItem() instanceof FlashAbstract && player.getCurrentArmor(0)
						.getItem() instanceof FlashAbstract)
				{
					if (player.worldObj.isRemote)
					{
						// step helper
						if (FlashArmor.flashFactor >= 1)
						{
							player.stepHeight = player.isCollidedHorizontally && player.onGround ? 1.5f : 0.5f;
						}

						// increase speed
						if (KeysHandler.UP_KEY.isKeyDown() && !Minecraft.getMinecraft().ingameGUI.getChatGUI()
								.getChatOpen() && Minecraft.getMinecraft().currentScreen == null)
						{
							if (FlashArmor.flashFactor < upperSpeedLimit)
							{
								FlashArmor.flashFactor += 1;
							}

							if (FlashArmor.jumpFactor < upperJumpMoveLimit)
							{
								FlashArmor.jumpFactor += 0.02F;
							}

							if (FlashArmor.jumpFactor >= upperJumpMoveLimit)
							{
								FlashArmor.jumpFactor = upperJumpMoveLimit;
							}

						}
						// decrease speed
						if (KeysHandler.DOWN_KEY.isKeyDown() && !Minecraft.getMinecraft().ingameGUI.getChatGUI()
								.getChatOpen() && Minecraft.getMinecraft().currentScreen == null)
						{
							if (FlashArmor.flashFactor >= lowerSpeedLimit)
							{
								FlashArmor.flashFactor -= 1;
							}
							if (FlashArmor.flashFactor <= lowerSpeedLimit || FlashArmor.flashFactor < 1
									|| FlashArmor.flashFactor == -1)
							{
								FlashArmor.flashFactor = lowerSpeedLimit;
							}

							if (FlashArmor.jumpFactor > lowerJumpMoveLimit)
							{
								FlashArmor.jumpFactor -= 0.02F;
							}

							if (FlashArmor.jumpFactor <= lowerJumpMoveLimit)
							{
								FlashArmor.jumpFactor = lowerJumpMoveLimit;
							}
						}
						// speed max
						if (KeysHandler.PGUP_KEY.isKeyDown())
						{
							FlashArmor.flashFactor = upperSpeedLimit;
							FlashArmor.jumpFactor = upperJumpMoveLimit;
						}
						// speed min
						if (KeysHandler.PGDOWN_KEY.isKeyDown())
						{
							FlashArmor.flashFactor = lowerSpeedLimit + 1;
							FlashArmor.jumpFactor = lowerJumpMoveLimit + 0.02F;
						}
						// wall running
						if (FlashArmor.flashFactor >= 1 && player.isCollidedHorizontally && KeysHandler.R_KEY
								.isKeyDown() && wallRunningUnlocked && !Minecraft.getMinecraft().ingameGUI.getChatGUI()
								.getChatOpen() && Minecraft.getMinecraft().currentScreen == null)
						{
							player.motionY += 0.175;
						}
						//extinguish all fires by vibration
						if (FlashArmor.flashFactor >= 1 && player.isBurning())
						{
							player.extinguish();
						}
					}
					// slow time perspective
					float speed = Float.valueOf(Float.toString(this.gameSpeed - this.slowMoFactor).substring(0, 3))
							.floatValue();
					if (KeysHandler.C_KEY.isKeyDown() && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()
							&& Minecraft.getMinecraft().currentScreen == null)
					{
						slowMoFactor += 0.04F;
						if (slowMoFactor >= slowMoUpperLimit)
						{
							slowMoFactor = slowMoUpperLimit;
						}
						SlowTime.setGameSpeed(speed);
						Minecraft.getMinecraft().currentScreen = null;
						player.arrowHitTimer = (int) (SlowTime.getGameSpeed() * 50.0F);
					}
					if (KeysHandler.X_KEY.isKeyDown() && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()
							&& Minecraft.getMinecraft().currentScreen == null)
					{
						slowMoFactor -= 0.04F;
						if (slowMoFactor <= sloMoLowerLimit)
						{
							slowMoFactor = sloMoLowerLimit;
						}

						SlowTime.setGameSpeed(speed);
						Minecraft.getMinecraft().currentScreen = null;
					}
					// phase
					if (KeysHandler.F_KEY.isKeyDown() && phasingUnlocked && FlashArmor.flashFactor >= 1)
					{
						player.setGameType(WorldSettings.GameType.SPECTATOR);
						player.capabilities.isFlying = true;
						if (KeysHandler.SPACEBAR.isKeyDown())
						{
							player.motionY = FlashArmor.flashFactor / 2.3809523809523809523809523809524;
						}
						if (!isFlying)
						{
							if (world1.getBlockState(new BlockPos(x1, player.getEntityBoundingBox().minY - 1, z1))
									.getBlock().getMaterial() == Material.air)
							{
								player.motionY -= 0.42;
							}
						}
					}
					else if (!KeysHandler.F_KEY.isKeyDown() || !phasingUnlocked || FlashArmor.flashFactor < 1)
					{
						player.setGameType(WorldSettings.GameType.CREATIVE);
					}
					if (KeysHandler.R_KEY.isKeyDown() && FlashArmor.flashFactor >= 1 && flyingUnlocked && !Minecraft
							.getMinecraft().ingameGUI.getChatGUI().getChatOpen()
							&& Minecraft.getMinecraft().currentScreen == null && !player.isCollidedHorizontally)
					{
						if (betterFlyingUnlocked)
						{
							player.capabilities.isFlying = true;
							isFlying = true;
							if (KeysHandler.SPACEBAR.isKeyDown())
							{
								player.motionY = FlashArmor.flashFactor / 2.3809523809523809523809523809524;
							}
						}
						else if (!player.isCollidedHorizontally && KeysHandler.SPACEBAR.isKeyDown())
						{
							player.motionY += 0.175D;
							isFlying = true;
						}
						else
						{
							isFlying = false;
						}
					}
				/*
				// steal speed needs a lot of work
				List<Entity> entities = player.worldObj.loadedEntityList;

				if (entities.size() > 0)
				{
					for (int i = 0; i < entities.size(); i++)
					{
						Entity entity = entities.get(i);

						if (KeysHandler.G_KEY.isKeyDown())
						{
							if (entity instanceof EntityPlayer)
							{

							}
							else
							{
								entity.performHurtAnimation();

								entity.motionX = 0;
								entity.motionY = 0;
								entity.motionZ = 0;

								entity.setPosition(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);

								entity.rotationYaw = entity.prevRotationYaw;
								entity.rotationPitch = entity.prevRotationPitch;
							}
						}
					}
				}
				*/
					// catching arrows
					if (KeysHandler.G_KEY.isKeyDown())
					{
						World world = player.worldObj;
						int x = 3;
						int y = 3;
						int z = 3;

						List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB
								.fromBounds(player.posX - x, player.posY - y, player.posZ - z, player.posX + x,
										player.posY + y, player.posZ + z));

						if (entityList.size() > 0)
						{
							for (int i = 0; i < entityList.size(); i++)
							{
								Entity entity = entityList.get(i);

								if (entity instanceof EntityPlayer)
								{

								}
								else if (entity instanceof EntityArrow)
								{
									entity.motionX = 0;
									entity.motionY = 0;
									entity.motionZ = 0;

									entity.setPosition(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);

									entity.rotationYaw = entity.prevRotationYaw;
									entity.rotationPitch = entity.prevRotationPitch;

									entity.setDead();
									player.inventory.addItemStackToInventory(new ItemStack(Items.arrow));
								}
								else
								{

								}
							}
						}
					}

					// tornado blowback and vortexes
					if (KeysHandler.V_KEY.isKeyDown() && FlashArmor.flashFactor >= 1 && tornadoesAndVortexesUnlocked)
					{
						World world = player.worldObj;
						int x = 16;
						int y = 16;
						int z = 16;

						List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB
								.fromBounds(player.posX - x, player.posY - y / 3, player.posZ - z, player.posX + x,
										player.posY + y, player.posZ + z));

						if (player.isSneaking())
						{
							if (entityList.size() > 0)
							{
								for (int i = 0; i < entityList.size(); i++)
								{
									Entity entity = entityList.get(i);

									double direction =
											MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
									int j = 1;
									if (entity instanceof EntityPlayer)
									{

									}
									else
									{
										if (direction >= 0 && direction < 1)
										{
											entity.motionZ = -j;
											entity.motionY = -j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
										else if (direction >= 1 && direction < 2)
										{
											entity.motionX = j;
											entity.motionY = -j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
										else if (direction >= 2 && direction < 3)
										{
											entity.motionZ = j;
											entity.motionY = -j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
										else if (direction >= 3 && direction < 4)
										{
											entity.motionX = -j;
											entity.motionY = -j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
									}
								}
							}
						}
						else
						{
							if (entityList.size() > 0)
							{
								for (int i = 0; i < entityList.size(); i++)
								{
									Entity entity = entityList.get(i);

									double direction =
											MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
									double j = 1;
									if (entity instanceof EntityPlayer)
									{

									}
									else
									{
										if (direction >= 0 && direction < 1)
										{
											entity.motionZ = j;
											entity.motionY = j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
										else if (direction >= 1 && direction < 2)
										{
											entity.motionX = -j;
											entity.motionY = j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
										else if (direction >= 2 && direction < 3)
										{
											entity.motionZ = -j;
											entity.motionY = j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
										else if (direction >= 3 && direction < 4)
										{
											entity.motionX = j;
											entity.motionY = j / 1.5;
											entity.rotationYaw = entity.prevRotationYaw;
											entity.rotationPitch = entity.prevRotationPitch;
										}
									}
								}
							}
						}
					}
					// speed force lightning throw
					if (KeysHandler.T_KEY.isKeyDown())
					{
						double range = 0.0D;
						World world = player.worldObj;
						// world.spawnEntityInWorld(new EntityLightningThrow(world,
						// player, player.posX, player.posY + 1.0D,
						// player.posZ, player.rotationYaw, player.rotationPitch,
						// range / 16.0D));
					}
				}
			}
		}
	}
}
