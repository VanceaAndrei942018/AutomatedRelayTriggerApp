package com.vadelectronics.c_core.business.link;

public interface MessagingProvider {
    void sendTextMessage(String phoneNumber, String textMessage);
}
