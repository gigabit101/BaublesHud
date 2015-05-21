package BaublesHud;

import net.minecraftforge.common.MinecraftForge;
import BaublesHud.client.GuiBaubleHud;
import BaublesHud.lib.ModInfo;
import BaublesHud.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = ModInfo.MOD_NAME, modid = ModInfo.MOD_ID, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCIES)
public class BaublesHud {
	
	@Instance(ModInfo.MOD_ID)
	public static BaublesHud instance;
	
	@SidedProxy(clientSide = ModInfo.MOD_CLIENT_PROXY, serverSide = ModInfo.MOD_COMMON_PROXY)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(GuiBaubleHud.instancemain);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenders();
	}
	
	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		
	}
}
