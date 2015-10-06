package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackIndustrialist extends BackpackModBase {
	public BackpackIndustrialist() {
		super("industrialist", 0xD1D9D9, 0xFFFFFF, Mods.IC2);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return GameRegistry.findItem(Mods.IC2, "itemToolbox");
	}
}
