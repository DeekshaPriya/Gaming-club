package com.gamingcenter.model.enums;

public enum MembershipType {
	BASIC("Basic", 100.00),
    PREMIUM("Premium", 250.00),
    VIP("VIP", 500.00);

    private final String displayName;
    private final double joiningFee;

    MembershipType(String displayName, double joiningFee) {
        this.displayName = displayName;
        this.joiningFee = joiningFee;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getJoiningFee() {
        return joiningFee; 
    }

}
