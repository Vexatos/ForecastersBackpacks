package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackComputer extends BackpackModBase {
	public BackpackComputer() {
		super("computer", 0x333333, 0xF0F000, Mods.OpenComputers, Mods.ComputerCraft, Mods.Computronics);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return Mods.OpenComputers.equals(mod) ? "oc:materialTransistor"
			: Mods.ComputerCraft.equals(mod) ? GameRegistry.findBlock(Mods.ComputerCraft, "CC-Cable")
			: null;
	}
}
