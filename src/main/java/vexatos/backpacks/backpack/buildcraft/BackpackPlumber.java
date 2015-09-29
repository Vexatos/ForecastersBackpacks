package vexatos.backpacks.backpack.buildcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import vexatos.backpacks.backpack.BackpackModBase;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackPlumber extends BackpackModBase {
	public BackpackPlumber() {
		super("buildcraft.plumber", 0xDD4400, 0xFFFFFF, Mods.BuildCraftTransport);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return GameRegistry.findItem(Mods.BuildCraftTransport, "pipeWaterproof");
	}

	@Override
	public void initialize() {
		super.initialize();
		this.addValidItem(GameRegistry.findItem(Mods.BuildCraftCore, "wrenchItem"));
	}
}
