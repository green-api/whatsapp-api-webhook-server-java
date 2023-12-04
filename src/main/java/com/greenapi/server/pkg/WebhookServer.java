package com.greenapi.server.pkg;

import com.greenapi.client.pkg.api.webhook.NotificationMapper;
import com.greenapi.client.pkg.api.webhook.WebhookHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/whatsapp")
@RequiredArgsConstructor
@Log4j2
public class WebhookServer {

    private final NotificationMapper notificationMapper = new NotificationMapper();
    private final WebhookHandler whatsappWebhookHandler;
    @Value("${green-api.webhookToken}")
    private String webhookToken;

    @PostMapping("/async/webhook")
    public ResponseEntity<Void> receiveAsyncWebhook(@RequestBody String jsonString,
                                                    @RequestHeader(required = false) String Authorization) {
        if ((Authorization != null && Authorization.replaceAll("Bearer ", "").equals(webhookToken)) ||
            Objects.equals(webhookToken, "")) {

            CompletableFuture.runAsync(() -> whatsappWebhookHandler.handle(notificationMapper.get(jsonString)));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> receiveWebhook(@RequestBody String jsonString,
                                               @RequestHeader(required = false) String Authorization) {
        if ((Authorization != null && Authorization.replaceAll("Bearer ", "").equals(webhookToken)) ||
            Objects.equals(webhookToken, "")) {

            whatsappWebhookHandler.handle(notificationMapper.get(jsonString));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
