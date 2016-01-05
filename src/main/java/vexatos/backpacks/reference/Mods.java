package vexatos.backpacks.reference;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionParser;

import java.util.HashMap;

/**
 * @author Vexatos
 */
public class Mods {

	//The mod itself
	public static final String
		Backpacks = "ForecastersBackpacks",
		Backpacks_NAME = "Forecaster's Backpacks";

	public static final String
		BiblioCraft = "BiblioCraft",
		Flamingo = "Flamingo",
		Forestry = "Forestry",
		IC2 = "IC2",
		PneumaticCraft = "PneumaticCraft";

	//BuildCraft
	public static final String
		BuildcraftBuilders = "BuildCraft|Builders",
		BuildCraftCore = "BuildCraft|Core",
		BuildCraftEnergy = "BuildCraft|Energy",
		BuildCraftFactory = "BuildCraft|Factory",
		BuildCraftRobotics = "BuildCraft|Robotics",
		BuildCraftSilicon = "BuildCraft|Silicon",
		BuildCraftTransport = "BuildCraft|Transport";

	// Weapon mods
	public static final String
		WeaponMod = "weaponmod",
		QuiverBow = "quiverchevsky";

	//Computer mods
	public static final String
		Computronics = "Computronics",
		OpenPeripheralAddons = "OpenPeripheral",

	ComputerCraft = "ComputerCraft",
		ComputerCraftEdu = "ComputerCraftEdu",
		RandomPeripherals = "RandomPeripherals",
		OpenCCSensors = "OCS",
		CCLights2 = "CCLights2",
		PeripheralsPlusPlus = "PeripheralsPlusPlus",
		MoarPeripherals = "MoarPeripherals",
		CCTweaks = "CCTweaks",
		LyqydPeripherals = "LyqydPeripherals",
		ChunkyPeripherals = "chunkyperipherals",
		ImmibisPeripherals = "ImmibisPeripherals",
		FirePeripherals = "FirePeripherals",
		DixPeripherals = "DixPR",
		Quadracopters = "Quadracoptors",

	OpenComputers = "OpenComputers",
		OCLights2 = "OCLights2",
		OpenSecurity = "opensecurity",
		OpenPrinter = "openprinter",
		OpenLights = "openlights",
		OpenGlasses = "openglasses",
		MassSound = "masssound",
		OpenAutomation = "OpenAutomation";

	//Other APIs
	public static class API {

		private static HashMap<String, ArtifactVersion> apiList;

		public static ArtifactVersion getVersion(String name) {
			if(apiList == null) {
				apiList = new HashMap<String, ArtifactVersion>();
				Iterable<? extends ModContainer> apis = ModAPIManager.INSTANCE.getAPIList();

				for(ModContainer api : apis) {
					apiList.put(api.getModId(), api.getProcessedVersion());
				}
			}

			if(apiList.containsKey(name)) {
				return apiList.get(name);
			}
			throw new IllegalArgumentException("API '" + name + "' does not exist!");
		}

		public static boolean hasVersion(String name, String version) {
			if(ModAPIManager.INSTANCE.hasAPI(name)) {
				ArtifactVersion v1 = VersionParser.parseVersionReference(name + "@" + version);
				ArtifactVersion v2 = getVersion(name);
				return v1.containsVersion(v2);
			}
			return false;
		}

		public static boolean hasAPI(String name) {
			return ModAPIManager.INSTANCE.hasAPI(name);
		}
	}

	public static boolean isLoaded(String name) {
		return Loader.isModLoaded(name);
	}
}
