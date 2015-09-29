package vexatos.backpacks.backpack.buildcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.backpack.BackpackModBase;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackMachinist extends BackpackModBase {
	public BackpackMachinist() {
		super("buildcraft.machinist", 0x48BF, 0x00FFFF,
			Mods.BuildCraftCore, Mods.BuildcraftBuilders,
			Mods.BuildCraftSilicon, Mods.BuildCraftEnergy,
			Mods.BuildCraftFactory);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return "gearIron";
	}
}
