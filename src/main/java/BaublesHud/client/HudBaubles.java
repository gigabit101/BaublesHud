package BaublesHud.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

public class HudBaubles
{
	public static ConfigBaublesHud config = ConfigBaublesHud.instance();
	public static final HudBaubles instancemain = new HudBaubles();
	private static Minecraft mc = Minecraft.getMinecraft();
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
			return;

		if (key.config.isPressed() == true)
			hudPosition = (hudPosition + 1) % 8;

		// Following Code Determines Hud Position
		// Setup Flags
		boolean isOnLeft = ((hudPosition / 2) % 2 == 0);
		boolean isOnTop = hudPosition <= 3;
		boolean isHorz = hudPosition % 2 == 0;

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
		if (isHorz) { // Horizontal Hud
			LocOffsetX = (isOnLeft ? 15 : -15);
			LocOffsetY = 0;
		} else { // Vertical Hud
			LocOffsetX = 0;
			LocOffsetY = (isOnTop ? 15 : -15);
		}

		if (mc.inGameHasFocus || (mc.currentScreen != null && mc.gameSettings.showDebugInfo))
			drawBaublesHudIcons(event.resolution);
	}

	// Draws the Hud
	public void drawBaublesHudIcons(ScaledResolution res) 
	{
		EntityPlayer player = mc.thePlayer;
		InventoryBaubles inv = PlayerHandler.getPlayerBaubles(player);

		// Renders the ItemStacks from the players baubles inventory in the correct
		//	X, Y Cordinates
		for (int i = 0; i < 4; i++)
			renderItemStack(inv.getStackInSlot(i), LocX + i * LocOffsetX,
				LocY + i * LocOffsetY);
	}
	
	// Draws ItemStack at X and Y Cordinates
	public void renderItemStack(ItemStack stack, int x, int y)
	{
		if (stack != null) {
			// Renders Item Icon.
			RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x, y);
			// Renders Item Overlay example durability bar
			RenderItem.getInstance().renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x, y);
			stack.getItem().showDurabilityBar(stack);
		}
	}
	
}