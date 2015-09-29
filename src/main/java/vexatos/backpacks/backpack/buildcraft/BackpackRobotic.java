package vexatos.backpacks.backpack.buildcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
		Item chipset = GameRegistry.findItem(Mods.BuildCraftSilicon, "redstoneChipset");
		return chipset != null ? new ItemStack(chipset, 1, 1) : "gearGold";
	}
}
