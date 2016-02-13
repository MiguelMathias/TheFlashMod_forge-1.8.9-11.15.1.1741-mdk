// Sometimes, I think compiler ignores all my comments :_(

package com.sapphire.armor;

import com.sapphire.items.FlashItems;
import com.sapphire.main.ClientProxy;
import com.sapphire.main.MainRegistry;
import com.sapphire.armor.FlashAbstract;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class FutureFlashArmor extends FlashAbstract
{
	boolean isDown = Keyboard.getEventKeyState();
	private Minecraft gameController;
	private String[] armorTypes = new String[] { "futureFlashHelmet", "futureFlashChestPlate", "futureFlashLegs",
			"futureBoots" };

	public FutureFlashArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType)
	{
		super(armorMaterial, renderIndex, armorType);
	}

	@Override public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
	{
		if (stack.getItem().equals(FlashItems.helmetFutureFlash) || stack.getItem().equals(FlashItems.chestPlateFutureFlash)
				|| stack.getItem().equals(FlashItems.bootsFutureFlash))
		{
			return "sapphire:textures/armor/future_flash_1.png";
		}
		else if (stack.getItem().equals(FlashItems.legsFutureFlash))
		{
			return "sapphire:textures/armor/future_flash_2.png";
		}
		else
		{
			return null;
		}
	}

	@Override public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		ModelBiped armorModel = new ModelBiped();
		if (itemStack != null)
		{
			if (itemStack.getItem() instanceof FutureFlashArmor)
			{
				int type = ((ItemArmor) itemStack.getItem()).armorType;
				armorModel = type == 1 || type == 3 ? ClientProxy.getArmorModel(7) : ClientProxy.getArmorModel(7);
			}
			if (armorModel != null)
			{
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = ((EntityPlayer) entityLiving).getCurrentArmor(0) != null ? 1 : 0;
				armorModel.heldItemRight = ((EntityPlayer) entityLiving).getCurrentEquippedItem() != null ? 1 : 0;
				armorModel.aimedBow = false;
				if (entityLiving instanceof EntityPlayer && ((EntityPlayer) entityLiving).getItemInUseDuration() > 0)
				{
					EnumAction enumaction = ((EntityPlayer) entityLiving).getCurrentEquippedItem().getItemUseAction();
					if (enumaction == EnumAction.BLOCK)
					{
						armorModel.heldItemRight = 3;
					}
					else if (enumaction == EnumAction.BOW)
					{
						armorModel.aimedBow = true;
					}
				}
				return armorModel;
			}
		}
		return null;
	}

	@Override public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem().equals(FlashItems.helmetFutureFlash)
				&& player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem()
				.equals(FlashItems.chestPlateFutureFlash) && player.getCurrentArmor(1) != null && player.getCurrentArmor(1)
				.getItem().equals(FlashItems.legsFutureFlash) && player.getCurrentArmor(0) != null && player
				.getCurrentArmor(0).getItem().equals(FlashItems.bootsFutureFlash))
		{
			player.addPotionEffect(new PotionEffect(MainRegistry.speedForce.id, 10, FlashArmor.flashFactor * 8));

			player.fallDistance = 0.0f;
			player.jumpMovementFactor = FlashArmor.jumpFactor;

			if (FlashArmor.flashFactor >= 1)
			{
				player.capabilities.setFlySpeed((float) (FlashArmor.flashFactor * 0.0333333333333));

				player.addPotionEffect(new PotionEffect(MainRegistry.speedDigging.id, 10, FlashArmor.flashFactor * 4));
				player.addPotionEffect(new PotionEffect(MainRegistry.speedStrength.id, 10, (int) (FlashArmor.flashFactor / 2)));
			}
			else
			{
				player.capabilities.isFlying = false;
			}
		}
	}
}
