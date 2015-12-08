package BaublesHud.client;

import BaublesHud.BaublesHud;
import BaublesHud.GuiHandler;
import BaublesHud.lib.Key;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;

public class KeyInputEventHandler 
{
	private static Key getPressedKeybinding()
	{
		if (KeyBindings.config.isPressed()) 
		{
			return Key.CONFIG;
		}

		return Key.UNKNOWN;
	}

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
	{
		if(KeyBindings.config.isPressed())
			onKeyPressed();
	}
	
	private void onKeyPressed()
	{
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.currentScreen != null)
            return;
        
        mc.thePlayer.openGui(BaublesHud.instance, GuiHandler.hudGui, mc.theWorld, 0, 0, 0);
	}
}
