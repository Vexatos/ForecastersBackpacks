package vexatos.backpacks.backpack;

import forestry.api.storage.EnumBackpackType;
import net.minecraft.item.Item;
import vexatos.backpacks.backpack.buildcraft.BackpackMachinist;
import vexatos.backpacks.backpack.buildcraft.BackpackPlumber;
import vexatos.backpacks.backpack.buildcraft.BackpackRobotic;

import java.util.HashMap;

/**
 * @author Vexatos
 */
public enum BackpackTypes {
	Pneumatic(new BackpackPneumatic()),
	Computer(new BackpackComputer()),
	Flamingo(new BackpackFlamingo()),
	Scholar(new BackpackScholar()),
	Industrialist(new BackpackIndustrialist()),
	//BuildCraft
	Plumber(new BackpackPlumber()),
	Robotic(new BackpackRobotic()),
	Machinist(new BackpackMachinist());

	public static final BackpackTypes[] VALUES = values();
	private final BackpackBase backpack;
	private final HashMap<EnumBackpackType, Item> backpackItems = new HashMap<EnumBackpackType, Item>();

	BackpackTypes(BackpackBase backpack) {
		this.backpack = backpack;
	}

	public BackpackBase getBackpack() {
		return this.backpack;
	}

	public Item getItem(EnumBackpackType type) {
		return this.backpackItems.get(type);
	}

	public Item setItem(EnumBackpackType type, Item item) {
		this.backpack.setLoaded(true);
		return this.backpackItems.put(type, item);
	}
}
