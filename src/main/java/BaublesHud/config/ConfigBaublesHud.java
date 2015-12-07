package BaublesHud.config;

import java.io.File;

import BaublesHud.BaublesHud;
import BaublesHud.client.HudBaubles;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class ConfigBaublesHud 
{
	private static ConfigBaublesHud instance = null;
    public static String CATEGORY_HUD = "HUD";
	
	public static int hudPosition;
	public static int hudPositionX;
	public static int hudPositionY;
	public static int isVertical;
	public static int hudScale;

    public static Configuration config;

    private ConfigBaublesHud(File configFile) 
    {
        config = new Configuration(configFile);
        config.load();

        ConfigBaublesHud.Configs();

        config.save();
    }
    
    public static ConfigBaublesHud initialize(File configFile) 
    {
        if (instance == null)
            instance = new ConfigBaublesHud(configFile);
        else
            throw new IllegalStateException("Cannot initialize BaublesHud Config twice");
        return instance;
    }
    
    public static ConfigBaublesHud instance() 
    {
        if (instance == null) 
        {
            throw new IllegalStateException("Instance of BaublesHud Config requested before initialization");
        }
        return instance;
    }
    
    public static void Configs() 
    {
       save();
    }
    
    public static void save()
    {
        if (config.hasChanged())
            config.save();
    }
}