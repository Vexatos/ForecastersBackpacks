package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.reference.Mods;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vexatos
 */
public abstract class BackpackModBase extends BackpackBase {

	private final List<String> validMods;

	public BackpackModBase(String key, int primaryColor, int secondaryColor, String... validMods) {
		super(key, primaryColor, secondaryColor);
		this.validMods = Arrays.asList(validMods);
	}

	@Override
	public boolean isValidItem(ItemStack stack) {
		if(super.isValidItem(stack)) {
			return true;
		}
		if(stack == null) {
			return false;
		}
		GameRegistry.UniqueIdentifier ident = GameRegistry.findUniqueIdentifierFor(stack.getItem());
		return ident != null && getValidMods().contains(ident.modId);
	}

	public List<String> getValidMods() {
		return this.validMods;
	}

	@Override
	public boolean shouldLoad() {
		for(String mod : validMods) {
			if(Mods.isLoaded(mod)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getCraftingItem() {
		for(String mod : validMods) {
			if(Mods.isLoaded(mod)) {
				return getCraftingItem(mod);
			}
		}
		return null;
	}

	protected abstract Object getCraftingItem(String mod);
}
