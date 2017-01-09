package com.edinarobotics.zeppelin;

public class Controls {
    
	private static Controls instance;
	
	private Controls() {
		
		
		
	}
	
	/**
	 * Returns the proper instance of Controls. This method creates a new
	 * Controls object the first time it is called and returns that object for
	 * each subsequent call.
	 *
	 * @return The current instance of Controls.
	 */
	public static Controls getInstance() {
		if (instance == null) {
			instance = new Controls();
		}
		
		return instance;
	}
	
}

