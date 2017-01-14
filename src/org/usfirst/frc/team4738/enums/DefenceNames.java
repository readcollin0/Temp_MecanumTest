package org.usfirst.frc.team4738.enums;

public enum DefenceNames {
	None, Portcullis, SallyDoor, RoughTerrain, ChivalDeFris, DrawBridge, Moat, Ramparts, RockWall, LowBar; 

	public static String[] getNames(){
		String[] data = new String[DefenceNames.values().length];
		for(int i = 0; i < DefenceNames.values().length; i++){
			data[i] += DefenceNames.values()[i];
		}
		return data;
	}

}
