package BaublesHud.proxy;

import BaublesHud.BaublesHud;
import BaublesHud.GuiHandler;
import BaublesHud.client.HudBaubles;
import BaublesHud.client.KeyBindings;
import BaublesHud.client.KeyInputEventHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
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
		NetworkRegistry.INSTANCE.registerGuiHandler(BaublesHud.instance, new GuiHandler());
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
	}
}
