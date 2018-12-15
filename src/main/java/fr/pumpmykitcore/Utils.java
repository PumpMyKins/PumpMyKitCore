package fr.pumpmykitcore;

public class Utils {

	public void debug(Object k) {
		
		if(ConfigUtils.getConfigFile().getBoolean("debug") || ConfigUtils.getConfigFile().getBoolean("developmentMode")) {
			
			System.out.println(ConsoleColors.RED +"--DEBUG MODE ENABLE IN PMK-KIT--");
			
			System.out.println(ConsoleColors.RESET + k.toString());
			
			System.out.println(ConsoleColors.RED +"--END OF DEBUG MESSAGE--");
		}

	}
	public void error(Object k) {
		
		System.out.println(ConsoleColors.RED_BOLD +"--ERROR IN PMK-KIT--");
		
		System.out.println(ConsoleColors.RED_BACKGROUND + k.toString());
		
		System.out.println(ConsoleColors.RED_BOLD +"--END OF DEBUG MESSAGE--");
	}
}
