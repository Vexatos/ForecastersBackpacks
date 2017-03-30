package vexatos.backpacks.backpack.buildcraft;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
		return Item.REGISTRY.getObject(new ResourceLocation(Mods.BuildCraftTransport, "pipeWaterproof"));
	}

	@Override
	public void initialize() {
		super.initialize();
		this.addValidItem(Mods.BuildCraftCore, "wrenchItem");
	}
}
