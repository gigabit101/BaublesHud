package BaublesHud.client;

import BaublesHud.config.ConfigBaublesHud;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiHud extends GuiScreen
{
	private boolean dragginghud;
	
	@Override
	public void initGui() 
	{
		super.initGui();
	}
	
	@Override
	public void updateScreen() 
	{
		super.updateScreen();
		String button1;
		String button2;
		if(ConfigBaublesHud.isVertical == 0)
			button1 = "VERTICAL";
		else
			button1 = "HORIZONTAL";
		
		if(ConfigBaublesHud.enable == 0)
			button2 = "DISABLE";
		else
			button2 = "ENABLE";
		
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 - 100, height / 2 + 30, button1));
//		buttonList.add(new GuiButton(2, width / 2 + 80, height / 2 + 30, 20, 20, "+"));
//		buttonList.add(new GuiButton(3, width / 2 - 100, height / 2 + 30, 20, 20, "-"));
//		buttonList.add(new GuiButton(4, width / 2 - 80, height / 2 + 30, 160, 20, "SCALE"));
		buttonList.add(new GuiButton(5, width / 2 - 100, height / 2 + 50, button2));

	}
	
	@Override
	public void drawScreen(int x, int y, float color) 
	{
		super.drawScreen(x, y, color);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int button) 
	{
		super.mouseClicked(x, y, button);
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) 
	{
		super.mouseClickMove(x, y, button, time);
		if(!dragginghud && time >= 120)
		{
			dragginghud = false;
			ConfigBaublesHud.hudPositionX = x;
			ConfigBaublesHud.hudPositionY = y;
			updateScreen();
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) 
	{
		super.actionPerformed(button);
		if(button.id == 0)
		{
			if(ConfigBaublesHud.isVertical == 0)
			{
				ConfigBaublesHud.isVertical = 1;
			}
			else if(ConfigBaublesHud.isVertical == 1)
			{
				ConfigBaublesHud.isVertical = 0;
			}
		}
//		if(button.id == 2)
//		{
//			ConfigBaublesHud.hudScale++;
//		}
//		if(button.id == 3)
//		{
//			ConfigBaublesHud.hudScale--;
//		}
		if(button.id == 5)
		{
			if(ConfigBaublesHud.enable == 0)
			{
				ConfigBaublesHud.enable = 1;
			}
			else if(ConfigBaublesHud.enable == 1)
			{
				ConfigBaublesHud.enable = 0;
			}
		}
	}
	
	@Override
	public void onGuiClosed() 
	{
		super.onGuiClosed();
		ConfigBaublesHud.config.get(ConfigBaublesHud.CATEGORY_HUD, "hud position X", 0).set(ConfigBaublesHud.hudPositionX);
		ConfigBaublesHud.config.get(ConfigBaublesHud.CATEGORY_HUD, "hud position Y", 0).set(ConfigBaublesHud.hudPositionY);
		ConfigBaublesHud.config.get(ConfigBaublesHud.CATEGORY_HUD, "hud is vertical", 0).set(ConfigBaublesHud.isVertical);
//		ConfigBaublesHud.config.get(ConfigBaublesHud.CATEGORY_HUD, "hud scale", 0).set(ConfigBaublesHud.hudScale);
		ConfigBaublesHud.config.get(ConfigBaublesHud.CATEGORY_HUD, "enable", 0).set(ConfigBaublesHud.enable);
		ConfigBaublesHud.config.save();
	}
}
