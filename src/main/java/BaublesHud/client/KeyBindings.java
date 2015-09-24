package BaublesHud.client;

import org.lwjgl.input.Keyboard;

import BaublesHud.lib.ModInfo;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings 
{
	public static KeyBinding config = new KeyBinding(ModInfo.Keys.CONFIG,
			Keyboard.KEY_NUMPAD9, ModInfo.Keys.CATEGORY);
}
