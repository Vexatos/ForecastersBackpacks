package vexatos.backpacks.backpack.buildcraft;

import vexatos.backpacks.backpack.BackpackModBase;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackMachinist extends BackpackModBase {
	public BackpackMachinist() {
		super("buildcraft.machinist", 0x0048BF, 0x00FFFF,
			Mods.BuildCraftCore, Mods.BuildcraftBuilders,
			Mods.BuildCraftSilicon, Mods.BuildCraftEnergy,
			Mods.BuildCraftFactory);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return "gearIron";
	}
}
