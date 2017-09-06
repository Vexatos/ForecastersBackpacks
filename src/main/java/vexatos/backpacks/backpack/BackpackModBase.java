package vexatos.backpacks.backpack;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vexatos.backpacks.reference.Mods;

import javax.annotation.Nullable;
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
		if(stack.isEmpty()) {
			return false;
		}
		ResourceLocation ident = stack.getItem().getRegistryName();
		return ident != null && getValidMods().stream().anyMatch((s) -> s.equalsIgnoreCase(ident.getResourceDomain()));
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

	@Nullable
	@Override
	public Object getCraftingItem() {
		for(String mod : validMods) {
			if(Mods.isLoaded(mod)) {
				Object item = getCraftingItem(mod);
				if(item != null) {
					return item;
				}
			}
		}
		return null;
	}

	@Nullable
	protected abstract Object getCraftingItem(String mod);
}
