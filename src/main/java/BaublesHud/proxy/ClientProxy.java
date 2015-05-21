package BaublesHud.proxy;

import BaublesHud.client.GuiBaubleHud;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void registerRenders()
	{
//		MinecraftForge.EVENT_BUS.register(new GuiBaubleHud(mc));
	}

}
