package com.gamingcenter.model.enums;

public enum GameType {
	ARCADE("Arcade Games", 20.00),
    PC("PC Games", 50.00),
    CONSOLE("Console Games", 40.00),
    VR("Virtual Reality", 100.00),
    MOBILE("Mobile Games", 15.00);

    private final String displayName;
    private final double hourlyRate;

    GameType(String displayName, double hourlyRate) {
        this.displayName = displayName;
        this.hourlyRate = hourlyRate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

}
