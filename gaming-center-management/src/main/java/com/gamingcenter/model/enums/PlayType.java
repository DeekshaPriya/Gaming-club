package com.gamingcenter.model.enums;

public enum PlayType {
	HOURLY("Hourly Play"),
    SESSION("Session Based"),
    TOURNAMENT("Tournament Play");
    
    private final String displayName;
    
    PlayType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
