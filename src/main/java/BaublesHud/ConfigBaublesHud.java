package BaublesHud;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigBaublesHud {
	private static ConfigBaublesHud instance = null;
    public static String CATEGORY_HUD = "HUD";
	
	public static boolean horizontal;
	public static boolean vertical;
	
	public static boolean topLeft;
	public static boolean topRight;
	public static boolean bottomLeft;
	public static boolean bottomRight;

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
        horizontal = config.get(CATEGORY_HUD,"hud render horizontal",
        		true,"render the players bauble hud horizontally").getBoolean(true);
        vertical = config.get(CATEGORY_HUD,"hud render vertical",
                false,"render the players bauble hud vertically").getBoolean(true);
        topLeft = config.get(CATEGORY_HUD,"hud render top left",
                true,"render the players bauble hud in the top left corner of the screen").getBoolean(true);
        topRight = config.get(CATEGORY_HUD,"hud render top right",
                false,"render the players bauble hud in the top right corner of the screen").getBoolean(true);
        bottomLeft = config.get(CATEGORY_HUD,"hud render bottom left",
                false,"render the players bauble hud in the bottom left corner of the screen").getBoolean(true);
        bottomRight = config.get(CATEGORY_HUD,"hud render bottom right",
                false,"render the players bauble hud in the bottom right corner of the screen").getBoolean(true);
       
        if (config.hasChanged())
            config.save();
    }


}
