package BaublesHud.client;

import BaublesHud.BaublesHud;
import BaublesHud.GuiHandler;
import BaublesHud.lib.Key;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

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
        System.out.println("HELLP");
        mc.thePlayer.openGui(BaublesHud.instance, GuiHandler.hudGui, mc.theWorld, 0, 0, 0);
	}
}
