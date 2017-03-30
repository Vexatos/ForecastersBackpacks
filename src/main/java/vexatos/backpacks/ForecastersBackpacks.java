package vexatos.backpacks;

import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;
import forestry.core.proxy.Proxies;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vexatos.backpacks.backpack.BackpackTypes;
import vexatos.backpacks.misc.CreativeTabBackpacks;
import vexatos.backpacks.proxy.CommonProxy;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
@Mod(modid = Mods.Backpacks, name = Mods.Backpacks_NAME, version = "@VERSION@",
	dependencies = "required-after:" + Mods.Forestry + "@[5,);after:" + Mods.BiblioCraft + "@[1.11.2,)")
public class ForecastersBackpacks {

	@Mod.Instance(Mods.Backpacks)
	public static ForecastersBackpacks instance;

	@SidedProxy(clientSide = "vexatos.backpacks.proxy.ClientProxy", serverSide = "vexatos.backpacks.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Configuration config;
	public static Logger log;

	public static CreativeTabs tab = new CreativeTabBackpacks();

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		log = LogManager.getLogger(Mods.Backpacks_NAME);

		for(BackpackTypes backpack : BackpackTypes.VALUES) {
			if(backpack.getBackpack().shouldLoad() && config.get("enable", backpack.getBackpack().getKey(), true).getBoolean(true)) {
				registerBackpack(backpack, EnumBackpackType.NORMAL);
				registerBackpack(backpack, EnumBackpackType.WOVEN);
			}
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		OreDictionary.registerOre("chestWood", Blocks.CHEST);
		proxy.registerModels();
	}

	private Item registerBackpack(BackpackTypes backpack, EnumBackpackType type) {
		return registerBackpack(backpack, type, backpack.getBackpack().getNameBase(type));
	}

	private Item registerBackpack(BackpackTypes backpack, EnumBackpackType type, String name) {
		BackpackManager.backpackInterface.registerBackpackDefinition(backpack.getBackpack().getKey(), backpack.getBackpack());
		Item item = BackpackManager.backpackInterface.createBackpack(backpack.getBackpack().getKey(), type).setCreativeTab(tab).setUnlocalizedName(name);
		GameRegistry.register(item, new ResourceLocation(Mods.Backpacks, item.getUnlocalizedName()));
		Proxies.common.registerItem(item);
		proxy.registerModel(item);
		backpack.setItem(type, item);
		return item;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		Item silk = Item.REGISTRY.getObject(new ResourceLocation(Mods.Forestry, "craftingMaterial"));

		for(BackpackTypes backpack : BackpackTypes.VALUES) {
			if(backpack.getBackpack().isLoaded()) {
				backpack.getBackpack().initialize();

				Item backpackItem1 = backpack.getItem(EnumBackpackType.NORMAL);

				if(backpackItem1 != null) {
					Object craftingItem = backpack.getBackpack().getCraftingItem();
					if(craftingItem != null) {
						GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(backpackItem1),
							"X#X", "VYV", "X#X", 'Y', "chestWood", 'X', Items.STRING, '#', Blocks.WOOL, 'V', craftingItem));
					}
					if(silk != null) {
						Item backpackItem2 = backpack.getItem(EnumBackpackType.WOVEN);
						RecipeManagers.carpenterManager.addRecipe(200, FluidRegistry.getFluidStack("water", 1000), null, new ItemStack(backpackItem2),
							"WXW", "WTW", "WWW", 'X', Items.DIAMOND, 'W',
							new ItemStack(silk, 1, 3), 'T', backpackItem1);
					}
				}
			}
		}
		config.save();
	}
}
