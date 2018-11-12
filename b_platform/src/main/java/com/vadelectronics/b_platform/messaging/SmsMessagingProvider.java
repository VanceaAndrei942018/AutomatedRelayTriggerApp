package com.vadelectronics.b_platform.messaging;

import android.telephony.SmsManager;
import com.vadelectronics.c_core.business.link.MessagingProvider;

public class SmsMessagingProvider implements MessagingProvider {

    private SmsManager smsManager;

    public SmsMessagingProvider() {
        smsManager = SmsManager.getDefault();
    }

    @Override
    public void sendTextMessage(String phoneNumber, String textMessage) {
        smsManager.sendTextMessage(phoneNumber, null, textMessage, null, null);
    }
}
