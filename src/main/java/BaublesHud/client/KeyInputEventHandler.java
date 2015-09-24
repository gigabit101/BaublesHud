package BaublesHud.client;

import BaublesHud.lib.Key;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler 
{
	private static Key getPressedKeybinding()
	{
		if (KeyBindings.config.isPressed()) {
			return Key.CONFIG;
		}

		return Key.UNKNOWN;
	}

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
	{
		
	}
}
