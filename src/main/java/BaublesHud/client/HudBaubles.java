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

import BaublesHud.ConfigBaublesHud;
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
	public static int LocX;
	public static int LocY;
	public static int LocOffsetX;
	public static int LocOffsetY;
	public boolean isVertical = config.vertical;
	public boolean isHorizontal = config.horizontal;
	public boolean isTopLeft = config.topLeft;
	public boolean isTopRight = config.topRight;
	public boolean isBottomLeft = config.bottomLeft;
	public boolean isBottomRight = config.bottomRight;


	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) 
	{
		if (event.isCancelable() || event.type != ElementType.ALL) 
		{
			return;
		}

		if (isTopLeft)
		{
			if(isHorizontal)
			{
				LocX = 1;
				LocY = 1;
				LocOffsetX = LocX + 15;
			}
			else if(isVertical)
			{
				LocX = 1;
				LocX = 1;
				LocOffsetY = LocY + 15;
			}
		}
		
		else if (isTopRight)
		{
			if (isHorizontal)
			{
				LocX = event.resolution.getScaledWidth() - 60;
				LocY = 1;
				LocOffsetX =  15;
			}
			else if (isVertical)
			{
				LocX = event.resolution.getScaledWidth() - 20;
				LocY = 1;
				LocOffsetY = LocY + 15;
			}
		}
		
		else if (isBottomLeft)
		{
			if (isHorizontal)
			{
				LocX = 1;
				LocY = event.resolution.getScaledHeight() - 20;
				LocOffsetX = LocX + 15;
			}
			if (isVertical)
			{
				LocX = 1;
				LocY = event.resolution.getScaledHeight() - 60;
				LocOffsetY = 15;
			}
		}
		else if (isBottomRight) 
		{
			if (isHorizontal)
			{
				LocX = event.resolution.getScaledWidth() - 60;
				LocY = event.resolution.getScaledHeight() - 20;
				LocOffsetX = 15;
			}
			if (isVertical)
			{
				LocX = event.resolution.getScaledWidth() - 20;
				LocY = event.resolution.getScaledHeight() - 60;
				LocOffsetX = 0;
			}
			
		}
		
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
			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x, y);
		}
	}

}
