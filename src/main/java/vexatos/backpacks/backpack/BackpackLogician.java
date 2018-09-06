package vexatos.backpacks.backpack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vexatos.backpacks.reference.Mods;

import javax.annotation.Nullable;

/**
 * @author Vexatos
 */
public class BackpackLogician extends BackpackModBase {
	public BackpackLogician() {
		super("logician", 0xE4FF00, 0x007D20, Mods.ComplexLogic);
	}

	@Nullable
	@Override
	protected Object getCraftingItem(String mod) {
		return Item.REGISTRY.getObject(new ResourceLocation(Mods.Charset, "logic_gate"));
	}

	@Override
	public void initialize() {
		super.initialize();
		addValidItem(Items.REPEATER);
		addValidItem(Items.COMPARATOR);
		addValidItem(Blocks.REDSTONE_TORCH);
		addValidItem(Blocks.REDSTONE_LAMP);
		addValidItem(Blocks.OBSERVER);
	}

	public static final String
		SimpleLogicGates = "simplelogic.gates",
		SimpleLogicWires = "simplelogic.wires";

	@Nullable
	@Override
	public Object getCraftingItem() {
		Object craftingItem = super.getCraftingItem();
		if(craftingItem == null && Mods.isLoaded(Mods.Charset)) {
			if(isModuleLoaded(SimpleLogicWires)) {
				craftingItem = Item.REGISTRY.getObject(new ResourceLocation(Mods.Charset, "logic_wire_b"));
			} else if(isModuleLoaded(SimpleLogicGates)) {
				craftingItem = "minecraft:comparator";
			}
		}
		return craftingItem;
	}

	// asie why
	private boolean isModuleLoaded(String id) {
		try {
			return (boolean) Class.forName("pl.asie.charset.ModCharset")
				.getDeclaredMethod("isModuleLoaded", String.class)
				.invoke(null, id);
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean shouldLoad() {
		if(super.shouldLoad()) {
			return true;
		}
		if(Mods.isLoaded(Mods.Charset)) {
			return isModuleLoaded(SimpleLogicGates) || isModuleLoaded(SimpleLogicWires);
		}
		return false;
	}

	@Override
	public boolean isValidItem(ItemStack stack) {
		if(stack.isEmpty()) {
			return false;
		}
		if(stack.getItem().getCreativeTab() != null && "simplelogic".equalsIgnoreCase(stack.getItem().getCreativeTab().getTabLabel())) {
			return true;
		}
		return super.isValidItem(stack);
	}
}
