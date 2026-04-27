package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;

import java.util.List;

public class EmailNotification extends Notification {
    private String senderEmail;
    private String subject;
    private boolean hasAttachment;

    public EmailNotification(String recipient, String message, int priority, String senderEmail, String subject, boolean hasAttachment) {
        super(recipient, message, priority);
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.hasAttachment = hasAttachment;
    }

    @Override
    public boolean isDeliverable() {
        return recipient.contains("@") && recipient.contains(".");
    }

    public boolean isSpam() {
        // TODO: Якщо subject містить "free", "win", "click" (case insensitive)
        return subject.toLowerCase().contains("free") ||
                subject.toLowerCase().contains("win") ||
                subject.toLowerCase().contains("click")||
                subject.toLowerCase().contains("gift") ||
                subject.toLowerCase().contains("congratulations");

    }

    @Override
    public String getFormattedMessage() {
      return "Subject: " + subject + "\n" + message;
    }

    @Override
    public int estimateDeliverySeconds() {
        return 30;
    }

    @Override
    protected void performSend() {
        System.out.println("Sending email to: " + recipient);
    }

    public String getSenderEmail() { return senderEmail; }
    public String getSubject() { return subject; }
    public boolean isHasAttachment() { return hasAttachment; }
}
