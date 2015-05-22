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
		if (stack0 != null) 
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			Item item0 = stack0.getItem();
			IIcon icon1 = item0.getIconFromDamage(0);

			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine,
							new ItemStack(item0), LocX , LocY);
		}

		ItemStack stack1 = inv.getStackInSlot(1);
		if (stack1 != null) 
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			Item item1 = stack1.getItem();
			IIcon icon1 = item1.getIconFromDamage(0);

			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine,
							new ItemStack(item1), LocX + LocOffsetX, LocY + LocOffsetY);
		}

		ItemStack stack2 = inv.getStackInSlot(2);
		if (stack2 != null) 
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			Item item2 = stack2.getItem();
			IIcon icon2 = item2.getIconFromDamage(0);

			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine,
							new ItemStack(item2), LocX + LocOffsetX + LocOffsetX, LocY + LocOffsetY + LocOffsetY);
		}

		ItemStack stack3 = inv.getStackInSlot(3);
		if (stack3 != null) 
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			Item item3 = stack3.getItem();
			IIcon icon3 = item3.getIconFromDamage(0);

			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine,
							new ItemStack(item3), LocX + LocOffsetX + LocOffsetX + LocOffsetX, LocY + LocOffsetY + LocOffsetY + LocOffsetY);
		}

	}

}
