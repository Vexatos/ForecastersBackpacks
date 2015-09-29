package vexatos.backpacks.misc;

import forestry.api.storage.EnumBackpackType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.backpack.BackpackTypes;

/**
 * @author Vexatos
 */
public class CreativeTabBackpacks extends CreativeTabs {
	private ItemStack displayStack;

	public CreativeTabBackpacks() {
		super("forecastersbackpacks");
	}

	@Override
	public ItemStack getIconItemStack() {
		if(displayStack == null) {
			for(BackpackTypes value : BackpackTypes.VALUES) {
				Item item = value.getItem(EnumBackpackType.T1);
				if(item != null) {
					this.displayStack = new ItemStack(item);
					break;
				}
			}
			if(displayStack == null) {
				for(BackpackTypes value : BackpackTypes.VALUES) {
					Item item = value.getItem(EnumBackpackType.T2);
					if(item != null) {
						this.displayStack = new ItemStack(item);
						break;
					}
				}
			}
		}
		return this.displayStack;
	}

	@Override
	public Item getTabIconItem() {
		return null;
	}
}
