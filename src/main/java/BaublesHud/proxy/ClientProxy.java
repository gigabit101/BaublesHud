package BaublesHud.proxy;

import BaublesHud.client.HudBaubles;
import BaublesHud.client.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() 
	{
		MinecraftForge.EVENT_BUS.register(HudBaubles.instancemain);
	}
	
	@Override
	public void registerKeybindings()
	{
		ClientRegistry.registerKeyBinding(KeyBindings.config);
	}
}
