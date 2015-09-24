package BaublesHud.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

public class HudBaubles
{
	public static final HudBaubles instancemain = new HudBaubles();
	private static Minecraft mc = Minecraft.getMinecraft();
	public static KeyBindings key;
	public static HUDSettings hudSettings;
	private static File hudSettingsFile = new File(Minecraft.getMinecraft().mcDataDir, "BaublesHudSettings");

	public static int LocX;
	public static int LocY;
	public static int LocOffsetX;
	public static int LocOffsetY;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) 
	{		
		if (event.isCancelable() || event.type != ElementType.ALL)
			return;
		load();
		
		if (key.config.isPressed())
		{
			if(hudSettings.HUD_POS != 8)
				hudSettings.HUD_POS++;
			if(hudSettings.HUD_POS >= 8)
				hudSettings.HUD_POS = 0;
			save();
			//DEBUG
//			System.out.println(hudSettings.HUD_POS);
		}

		// Following Code Determines Hud Position
		// Setup Flags
		boolean isOnLeft = ((hudSettings.HUD_POS / 2) % 2 == 0);
		boolean isOnTop = hudSettings.HUD_POS <= 3;
		boolean isHorz = hudSettings.HUD_POS % 2 == 0;

		// Left/Right Side Check
		if (isOnLeft)
			LocX = 1;
		else
			LocX = event.resolution.getScaledWidth() - 15;

		// Top/Bottom Check
		if (isOnTop)
			LocY = 1;
		else
			LocY = event.resolution.getScaledHeight() - 15;

		// Following Code Sets Up Offsets and Determines Hud Orientation
		if (isHorz) { // Horizoantal Hud
			LocOffsetX = (isOnLeft ? 15 : -15);
			LocOffsetY = 0;
		} else { // Vertical Hud
			LocOffsetX = 0;
			LocOffsetY = (isOnTop ? 15 : -15);
		}

		if (mc.inGameHasFocus || (mc.currentScreen != null))
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
			renderItemStack(inv.getStackInSlot(i), LocX + i * LocOffsetX, LocY + i * LocOffsetY);
	}

	// Draws ItemStack at X and Y Cordinates	
	 public void renderItemStack(ItemStack stack, int x, int y) 
	 {
		if (stack != null) 
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);			
			RenderHelper.enableGUIStandardItemLighting();
			
			RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
			itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
			
			GL11.glDisable(GL11.GL_LIGHTING);
		}		
	}
	
	public static class HUDSettings
	{
		public int HUD_POS = 0;
	}
	
	// load form Json
	public static void load()
	{
		if(!hudSettingsFile.exists())
		{
			hudSettings = new HUDSettings();
		} else {
			try 
			{
				Gson gson = new Gson();
				BufferedReader reader = new BufferedReader(new FileReader(hudSettingsFile));
				hudSettings = gson.fromJson(reader, HUDSettings.class);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				hudSettings = new HUDSettings();
			}
		}
	}
	
	// Save to Json
	public static void save()
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(hudSettings);
		try 
		{
			FileWriter writer = new FileWriter(hudSettingsFile);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}