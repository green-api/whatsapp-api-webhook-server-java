package com.greenapi.server.examples;

import com.greenapi.client.pkg.api.webhook.WebhookHandler;
import com.greenapi.client.pkg.models.notifications.Notification;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component(value = "whatsappWebhookHandler")
class WebhookHandlerExample implements WebhookHandler {
    @SneakyThrows
    @Override
    public void handle(Notification notification) {
        System.out.println("START " + notification);
        Thread.sleep(20000);
        System.out.println("END " + notification);
    }
}
