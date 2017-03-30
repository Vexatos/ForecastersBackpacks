package vexatos.backpacks.proxy;

import forestry.api.core.IItemModelRegister;
import forestry.core.models.ModelManager;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vexatos
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	private final List<IItemModelRegister> itemModelRegisters = new ArrayList<>();

	@Override
	public void registerModel(Item item) {
		if(item instanceof IItemModelRegister) {
			((IItemModelRegister) item).registerModel(item, ModelManager.getInstance());
		}
	}

	@Override
	public void registerModels() {
		for(IItemModelRegister itemModelRegister : itemModelRegisters) {
			if(itemModelRegister instanceof Item) {
				itemModelRegister.registerModel((Item) itemModelRegister, ModelManager.getInstance());
			}
		}
	}
}
