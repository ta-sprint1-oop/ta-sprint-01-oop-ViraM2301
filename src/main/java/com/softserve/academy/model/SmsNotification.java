package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;

public class SmsNotification extends Notification {
    private String phoneNumber;
    private boolean isFlash;

    public SmsNotification(String recipient, String message, int priority, String phoneNumber, boolean isFlash) {
        super(recipient, message, priority);
        this.phoneNumber = phoneNumber;
        this.isFlash = isFlash;
    }

    @Override
    public boolean isDeliverable() {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.startsWith("+") &&
                phoneNumber.length() >= 10 &&
                phoneNumber.length() <= 15;
    }

    public boolean isOverLimit() {
        return message.length() > 160;
    }

    @Override
    public String getFormattedMessage() {
        if (message.length() > 160) {
            return message.substring(0, 160);
        }
        return message;
    }


    @Override
    public int estimateDeliverySeconds() {
        return 5;
    }

    @Override
    protected void performSend() {
        System.out.println("This is a very long SMS");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isFlash() {
        return isFlash;
    }
}
