package com.edinarobotics.zeppelin;

public class Components {
   
	private static Components instance;
	
	private Components() {
		
		
		
	}
	
	/**
	 * Returns the proper instance of Components. This method creates a new
	 * Components object the first time it is called and returns that object for
	 * each subsequent call.
	 *
	 * @return The current instance of Components.
	 */
	public static Components getInstance() {
		if (instance == null) {
			instance = new Components();
		}
		
		return instance;
	}
	
}
