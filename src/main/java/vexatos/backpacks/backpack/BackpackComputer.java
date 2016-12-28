package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import vexatos.backpacks.reference.Mods;

import static vexatos.backpacks.reference.Mods.*;

/**
 * @author Vexatos
 */
public class BackpackComputer extends BackpackModBase {
	public BackpackComputer() {
		super("computer", 0x333333, 0xF0F000,
			ComputerCraft, OpenComputers, Computronics, OpenPeripheralAddons,

			ComputerCraftEdu, RandomPeripherals, OpenCCSensors, CCLights2, PeripheralsPlusPlus, MoarPeripherals,
			CCTweaks, LyqydPeripherals, ChunkyPeripherals, ImmibisPeripherals, FirePeripherals, DixPeripherals, Quadracopters,

			OCLights2, OCMinecarts, OpenSecurity, OpenPrinter, OpenLights, OpenGlasses, MassSound, OpenAutomation, OpenFM
		);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return Mods.OpenComputers.equals(mod) ? "oc:materialTransistor"
			: Mods.ComputerCraft.equals(mod) ? GameRegistry.findBlock(Mods.ComputerCraft, "CC-Cable")
			: null;
	}
}
