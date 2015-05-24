package BaublesHud.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import BaublesHud.BaublesHud;
import BaublesHud.config.ConfigBaublesHud;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HudBaubles {

	public static ConfigBaublesHud config;
	public static HudBaubles instancemain = new HudBaubles();
	private static Minecraft mc = Minecraft.getMinecraft();
	private static ScaledResolution scaledResolution;
	public static KeyBindings key;
	public static int LocX;
	public static int LocY;
	public static int LocOffsetX;
	public static int LocOffsetY;
	public static int hudPosition = config.hudPosition;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) 
	{
		if (event.isCancelable() || event.type != ElementType.ALL) 
		{
			return;
		}
		
		if (key.config.isPressed() == true)
		{
			if(hudPosition !=7)
			{
				hudPosition += 1;
			}
			else if(hudPosition == 7)
			{
				hudPosition = 0;
			}
		}

			if(hudPosition == 0)
			{
				LocX = 1;
				LocY = 1;
				LocOffsetX = LocX + 15;
				LocOffsetY = 0;
			}
			else if(hudPosition == 1)
			{
				LocX = 1;
				LocX = 1;
				LocOffsetX = 0;
				LocOffsetY = LocY + 15;
			}

			else if (hudPosition == 2)
			{
				LocX = event.resolution.getScaledWidth() - 60;
				LocY = 1;
				LocOffsetX =  15;
				LocOffsetY = 0;
			}
			else if (hudPosition == 3)
			{
				LocX = event.resolution.getScaledWidth() - 20;
				LocY = 1;
				LocOffsetX = 0;
				LocOffsetY = LocY + 15;
			}
		
			else if (hudPosition == 4)
			{
				LocX = 1;
				LocY = event.resolution.getScaledHeight() - 20;
				LocOffsetX = LocX + 15;
				LocOffsetY = 0;
			}
			else if (hudPosition == 5)
			{
				LocX = 1;
				LocY = event.resolution.getScaledHeight() - 60;
				LocOffsetX = 0;
				LocOffsetY = 15;
			}
			else if (hudPosition == 6)
			{
				LocX = event.resolution.getScaledWidth() - 60;
				LocY = event.resolution.getScaledHeight() - 20;
				LocOffsetX = 15;
				LocOffsetY = 0;
			}
			else if (hudPosition == 7)
			{
				LocX = event.resolution.getScaledWidth() - 20;
				LocY = event.resolution.getScaledHeight() - 60;
				LocOffsetX = 0;
				LocOffsetY = 15;
			}

			config.hudPosition = hudPosition;

		if (mc.inGameHasFocus || (mc.currentScreen != null && mc.gameSettings.showDebugInfo)) 
		{
			scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			drawbaublesHudIcons(event.resolution);
		}
		
	}

	public void drawbaublesHudIcons(ScaledResolution res) 
	{
		EntityPlayer player = mc.thePlayer;
		InventoryBaubles inv = PlayerHandler.getPlayerBaubles(player);

		ItemStack stack0 = inv.getStackInSlot(0);
		ItemStack stack1 = inv.getStackInSlot(1);
		ItemStack stack2 = inv.getStackInSlot(2);
		ItemStack stack3 = inv.getStackInSlot(3);

		renderItemstack(mc, stack0, LocX , LocY);
		renderItemstack(mc, stack1, LocX + LocOffsetX, LocY + LocOffsetY);
		renderItemstack(mc, stack2, LocX + LocOffsetX + LocOffsetX, LocY + LocOffsetY + LocOffsetY);
		renderItemstack(mc, stack3, LocX + LocOffsetX + LocOffsetX + LocOffsetX, LocY + LocOffsetY + LocOffsetY + LocOffsetY);
	}
	
	public void renderItemstack(Minecraft mc, ItemStack stack, int x, int y)
	{
		if (stack != null)
		{
			RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x, y);
			RenderItem.getInstance().renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x, y);
			stack.getItem().showDurabilityBar(stack);
		}
	}
	
}
