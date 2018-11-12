package com.vadelectronics.b_platform.messaging;

import com.vadelectronics.c_core.business.link.MessagingProvider;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;

public class SmsMessagingProviderUnitTest {

    @Inject MessagingProvider messagingProvider;

    @Test
    public void testSendTextMessage() {
        //SETUP
        messagingProvider.sendTextMessage(anyString(), anyString());

        Mockito.verify(messagingProvider, Mockito.times(1)).sendTextMessage(anyString(), anyString());
    }
}
