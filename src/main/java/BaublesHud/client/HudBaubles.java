package BaublesHud.client;

import BaublesHud.ConfigBaublesHud;
import BaublesHud.util.GuiUtil;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class HudBaubles
{
	public static final HudBaubles instancemain = new HudBaubles();
	private static Minecraft mc = Minecraft.getMinecraft();
	public static KeyBindings key;

	public static int LocX;
	public static int LocY;
	public static int isVertical;
	
	public static int LocOffsetX;
	public static int LocOffsetY;
	
	public static int hight;
	public static int width;
	public static double scale;
	public static float scaleF;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) 
	{		
		if (event.isCancelable() || event.getType() != ElementType.ALL)
			return;
		LocX = ConfigBaublesHud.hudPositionX;
		LocY = ConfigBaublesHud.hudPositionY;
		isVertical = ConfigBaublesHud.isVertical;
		scale = ConfigBaublesHud.hudScale;
		scaleF = (float) scale;
		if(isVertical == 0)
		{
			LocOffsetY = 15;
			LocOffsetX = 0;
			hight = 17;
			width = 62;
		}
		if(isVertical == 1)
		{
			LocOffsetY = 0;
			LocOffsetX = 15;
			hight = 62;
			width = 17;
		}

		if (mc.inGameHasFocus || mc.currentScreen == null || (mc.currentScreen instanceof GuiChat) || (mc.currentScreen instanceof GuiHud) && !mc.gameSettings.showDebugInfo)
		{ 
			if(ConfigBaublesHud.enable == 0)
			{
				drawBaublesHudIcons(event.getResolution());
			}
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
			GL11.glPushMatrix();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);			
			RenderHelper.enableGUIStandardItemLighting();

//			scale(scaleF, x, y);
			if(mc.currentScreen instanceof GuiHud || ConfigBaublesHud.showBox == 1)
				GuiUtil.drawTooltipBox(LocX - 1, LocY - 1, hight, width);

			RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
			itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();

		}		
	}
	 
//	public void scale(float f, int x, int y) 
//	{
//		GL11.glTranslatef(x, y, 10.0F);
//		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
//		GL11.glScalef(f, f, f);
//	}
}