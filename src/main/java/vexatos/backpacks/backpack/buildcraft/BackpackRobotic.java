package vexatos.backpacks.backpack.buildcraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vexatos.backpacks.backpack.BackpackModBase;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackRobotic extends BackpackModBase {
	public BackpackRobotic() {
		super("buildcraft.robotic", 0x770000, 0x00FFFF, Mods.BuildCraftRobotics);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		Item chipset = Item.REGISTRY.getObject(new ResourceLocation(Mods.BuildCraftSilicon, "redstoneChipset"));
		return chipset != null ? new ItemStack(chipset, 1, 1) : "gearGold";
	}
}
