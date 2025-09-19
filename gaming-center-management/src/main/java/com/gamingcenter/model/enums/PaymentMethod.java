package com.gamingcenter.model.enums;

public enum PaymentMethod {
	CASH("Cash Payment"),
    CARD("Card Payment"),
    UPI("UPI Payment"),
    NET_BANKING("Net Banking"),
    WALLET("Digital Wallet");
    
    private final String displayName;
    
    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
