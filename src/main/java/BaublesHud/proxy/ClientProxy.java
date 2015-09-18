package BaublesHud.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import BaublesHud.client.HudBaubles;
import BaublesHud.client.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerEvents() 
	{
		MinecraftForge.EVENT_BUS.register(HudBaubles.instancemain);
	}
	
	@Override
	public void registerKeybindings()
	{
		ClientRegistry.registerKeyBinding(KeyBindings.config);
	}
}
