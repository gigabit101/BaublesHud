package BaublesHud.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BaublesHud.BaublesHud;
import BaublesHud.config.ConfigBaublesHud;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HudBaubles 
{
	public static ConfigBaublesHud config = ConfigBaublesHud.instance();
	public static final HudBaubles instancemain = new HudBaubles();
	private static Minecraft mc = Minecraft.getMinecraft();
	public static KeyBindings key;

	public static int LocX;
	public static int LocY;
	public static int isVertical;
	
	public static int LocOffsetX;
	public static int LocOffsetY;
	
	public static int scale;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) 
	{		
		if (event.isCancelable() || event.type != ElementType.ALL)
			return;
		
		LocX = ConfigBaublesHud.hudPositionX;
		LocY = ConfigBaublesHud.hudPositionY;
		isVertical = ConfigBaublesHud.isVertical;
		scale = ConfigBaublesHud.hudScale;
		if(isVertical == 0)
		{
			LocOffsetY = 15;
			LocOffsetX = 0;
		}
		if(isVertical == 1)
		{
			LocOffsetY = 0;
			LocOffsetX = 15;
		}

		if (mc.inGameHasFocus || mc.currentScreen == null || (mc.currentScreen instanceof GuiChat) || (mc.currentScreen instanceof GuiHud) && !mc.gameSettings.showDebugInfo)
		{ 
			if(!mc.gameSettings.showDebugInfo)
				drawBaublesHudIcons(event.resolution);
		}
	}

	// Draws the Hud
	public void drawBaublesHudIcons(ScaledResolution res) 
	{
		EntityPlayer player = mc.thePlayer;
		InventoryBaubles inv = PlayerHandler.getPlayerBaubles(player);

		// Renders the ItemStacks from the players baubles inventory in the
		// correct
		// X, Y Cordinates
		for (int i = 0; i < 4; i++)
		{
			renderItemStack(inv.getStackInSlot(i), LocX + i * LocOffsetX, LocY + i * LocOffsetY);
		}
	}

	// Draws ItemStack at X and Y Cordinates
	public void renderItemStack(ItemStack stack, int x, int y) 
	{
		if (stack != null) 
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);	

			RenderHelper.enableGUIStandardItemLighting();
			// Renders Item Icon.
			RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x, y);

			// Renders Item Overlay example durability bar
			RenderItem.getInstance().renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x, y);


			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
	}
}