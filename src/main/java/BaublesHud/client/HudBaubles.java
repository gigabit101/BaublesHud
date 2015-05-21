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

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HudBaubles {

	public static HudBaubles instancemain = new HudBaubles();
	private static Minecraft mc = Minecraft.getMinecraft();
	public static int LocX = 1;
	public static int LocY = 1;
	private int guiLeft, guiTop;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) 
	{
		if (event.isCancelable() || event.type != ElementType.ALL) 
		{
			return;
		}

		if (mc.inGameHasFocus || (mc.currentScreen != null && mc.gameSettings.showDebugInfo)) 
		{
			drawbaublesHud(event.resolution);
		}

	}

	public void drawbaublesHud(ScaledResolution res) 
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
							new ItemStack(item0), LocX + 5, LocY + 100);
		}

		ItemStack stack1 = inv.getStackInSlot(1);
		if (stack1 != null) 
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			Item item1 = stack1.getItem();
			IIcon icon1 = item1.getIconFromDamage(0);

			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine,
							new ItemStack(item1), LocX + 5, LocY + 120);
		}

		ItemStack stack2 = inv.getStackInSlot(2);
		if (stack2 != null) 
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			Item item2 = stack2.getItem();
			IIcon icon2 = item2.getIconFromDamage(0);

			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine,
							new ItemStack(item2), LocX + 5, LocY + 140);
		}

		ItemStack stack3 = inv.getStackInSlot(3);
		if (stack3 != null) 
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			Item item3 = stack3.getItem();
			IIcon icon3 = item3.getIconFromDamage(0);

			RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine,
							new ItemStack(item3), LocX + 5, LocY + 160);
		}

	}

}
