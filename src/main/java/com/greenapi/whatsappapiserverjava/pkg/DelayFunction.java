package com.greenapi.whatsappapiserverjava.pkg;

import com.greenapi.pkg.api.webhook.WebhookHandler;
import com.greenapi.pkg.models.notifications.Notification;
import lombok.SneakyThrows;

public class DelayFunction implements WebhookHandler {
    @SneakyThrows
    @Override
    public void handle(Notification notification) {
        System.out.println("START " + notification);
        Thread.sleep(20000);
        System.out.println("END " + notification);
    }
}
