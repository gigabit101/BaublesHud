package BaublesHud;

import java.io.File;

import BaublesHud.client.HudBaubles;
import BaublesHud.config.ConfigBaublesHud;
import BaublesHud.lib.ModInfo;
import BaublesHud.proxy.CommonProxy;
import BaublesHud.proxy.ClientProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = ModInfo.MOD_NAME, modid = ModInfo.MOD_ID, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCIES)
public class BaublesHud {
	public static ConfigBaublesHud config;

	@Instance(ModInfo.MOD_ID)
	public static BaublesHud instance;

	@SidedProxy(clientSide = ModInfo.MOD_CLIENT_PROXY, serverSide = ModInfo.MOD_COMMON_PROXY)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) 
	{
		instance = this;
		String path = event.getSuggestedConfigurationFile().getAbsolutePath().replace(ModInfo.MOD_ID, "BaublesHud");
		config = ConfigBaublesHud.initialize(new File(path));
		
		proxy.registerEvents();
		proxy.registerKeybindings();
	}
}