package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackFlamingo extends BackpackModBase {

	public BackpackFlamingo() {
		super("flamingo", 0xFF61D4, 0xFFFF00, Mods.Flamingo);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return GameRegistry.findBlock(Mods.Flamingo, "flamingo.flamingo");
	}
}
