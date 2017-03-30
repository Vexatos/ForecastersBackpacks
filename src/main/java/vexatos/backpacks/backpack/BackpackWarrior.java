package vexatos.backpacks.backpack;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackWarrior extends BackpackModBase {
	public BackpackWarrior() {
		super("warrior", 0x151515, 0xF0F000, Mods.WeaponMod, Mods.QuiverBow);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return Items.IRON_SWORD;
	}

	@Override
	public void initialize() {
		super.initialize();
		this.addValidItem(Items.ARROW);
		this.addValidItem(Mods.IC2, "itemNanoSaber");
		this.addValidItem(Mods.IC2, "itemToolMiningLaser");
		this.addValidItem(Mods.PneumaticCraft, "minigun");
		this.addValidItem(Mods.PneumaticCraft, "gunAmmo");
	}

	@Override
	public boolean isValidItem(ItemStack stack) {
		return super.isValidItem(stack) || stack != null && (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow);
	}
}
