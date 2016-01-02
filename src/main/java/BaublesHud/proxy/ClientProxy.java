package BaublesHud.proxy;

import BaublesHud.BaublesHud;
import BaublesHud.GuiHandler;
import BaublesHud.client.HudBaubles;
import BaublesHud.client.KeyBindings;
import BaublesHud.client.KeyInputEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
		NetworkRegistry.INSTANCE.registerGuiHandler(BaublesHud.instance, new GuiHandler());
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
	}
}
