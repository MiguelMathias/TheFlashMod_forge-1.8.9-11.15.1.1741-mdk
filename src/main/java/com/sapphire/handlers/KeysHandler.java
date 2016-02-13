package com.sapphire.handlers;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeysHandler
{
	
	public static KeyBinding UP_KEY;
	public static KeyBinding DOWN_KEY;
	public static KeyBinding R_KEY;
	public static KeyBinding F_KEY;
	public static KeyBinding C_KEY;
	public static KeyBinding X_KEY;
	public static KeyBinding Z_KEY;
	public static KeyBinding V_KEY;
	public static KeyBinding G_KEY;
	public static KeyBinding T_KEY;
	public static KeyBinding PGUP_KEY;
	public static KeyBinding PGDOWN_KEY;
	public static KeyBinding SPACEBAR;
	
	public static void mainRegistry()
	{
		
		UP_KEY = new KeyBinding("key.up", Keyboard.KEY_UP, "key.categories.sapphiremod");
		DOWN_KEY = new KeyBinding("key.down", Keyboard.KEY_DOWN, "key.categories.sapphiremod");
		R_KEY = new KeyBinding("key.r", Keyboard.KEY_R, "key.categories.sapphiremod");
		F_KEY = new KeyBinding("key.f", Keyboard.KEY_F, "key.categories.sapphiremod");
		C_KEY = new KeyBinding("key.c", Keyboard.KEY_C, "key.categories.sapphiremod");
		X_KEY = new KeyBinding("key.x", Keyboard.KEY_X, "key.categories.sapphiremod");
		Z_KEY = new KeyBinding("key.z", Keyboard.KEY_Z, "key.categories.sapphiremod");
		V_KEY = new KeyBinding("key.v", Keyboard.KEY_V, "key.categories.sapphiremod");
		G_KEY = new KeyBinding("key.g", Keyboard.KEY_G, "key.categories.sapphiremod");
		T_KEY = new KeyBinding("key.t", Keyboard.KEY_T, "key.categories.sapphiremod");
		PGUP_KEY = new KeyBinding("key.pageup", Keyboard.KEY_PRIOR, "key.categories.sapphiremod");
		PGDOWN_KEY = new KeyBinding("key.pagedown", Keyboard.KEY_NEXT, "key.categories.sapphiremod");
		SPACEBAR = new KeyBinding("key.spacebar", Keyboard.KEY_SPACE, "key.categories.sapphiremod");
		
		ClientRegistry.registerKeyBinding(UP_KEY);
		ClientRegistry.registerKeyBinding(DOWN_KEY);
		ClientRegistry.registerKeyBinding(R_KEY);
		ClientRegistry.registerKeyBinding(F_KEY);
		ClientRegistry.registerKeyBinding(C_KEY);
		ClientRegistry.registerKeyBinding(X_KEY);
		ClientRegistry.registerKeyBinding(Z_KEY);
		ClientRegistry.registerKeyBinding(V_KEY);
		ClientRegistry.registerKeyBinding(G_KEY);
		ClientRegistry.registerKeyBinding(T_KEY);
		ClientRegistry.registerKeyBinding(PGUP_KEY);
		ClientRegistry.registerKeyBinding(PGDOWN_KEY);
		ClientRegistry.registerKeyBinding(SPACEBAR);
	}
	
}
