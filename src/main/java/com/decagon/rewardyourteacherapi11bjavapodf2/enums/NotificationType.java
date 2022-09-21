package com.decagon.rewardyourteacherapi11bjavapodf2.enums;

public enum NotificationType {
    FUND_PERSONAL_WALLET ("Fund Personal Wallet"),
    SUCCESSFUL_WITHDRAW ("Successful Withdraw"),
    TRANSFER_FUNDS ("Transfer Funds"),

    RECEIVE_FUNDS ("Receive Funds"),
    APPRECIATION("Appreciation");

    private String display;

    NotificationType(String display) {
        this.display = display;
    }
}
