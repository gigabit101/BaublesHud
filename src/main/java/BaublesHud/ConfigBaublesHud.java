package BaublesHud;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigBaublesHud 
{
	private static ConfigBaublesHud instance = null;
    public static String CATEGORY_HUD = "HUD";
	
	public static int hudPositionX;
	public static int hudPositionY;
	public static int isVertical;
	public static int hudScale;
	public static int enable;
	public static int showBox;

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
    	hudPositionX = config.get(CATEGORY_HUD,"hud position X", 0).getInt();
    	hudPositionY = config.get(CATEGORY_HUD,"hud position Y", 0).getInt();
    	isVertical = config.get(CATEGORY_HUD,"hud is vertical", 0).getInt();
    	enable = config.get(CATEGORY_HUD,"enable", 0).getInt();
    	showBox = config.get(CATEGORY_HUD,"box", 0).getInt();
		
        if (config.hasChanged())
            config.save();
    }
}
