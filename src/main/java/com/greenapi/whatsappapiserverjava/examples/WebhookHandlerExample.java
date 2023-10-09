package com.greenapi.whatsappapiserverjava.examples;

import com.greenapi.pkg.api.webhook.WebhookHandler;
import com.greenapi.pkg.models.notifications.Notification;
import lombok.SneakyThrows;

//@Component(value = "whatsappWebhookHandler")
public class WebhookHandlerExample implements WebhookHandler {
    @SneakyThrows
    @Override
    public void handle(Notification notification) {
        System.out.println("START " + notification);
        Thread.sleep(20000);
        System.out.println("END " + notification);
    }
}
