package com.greenapi.whatsappapiserverjava.pkg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenapi.pkg.api.webhook.NotificationMapper;
import com.greenapi.pkg.api.webhook.WebhookHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/green-api")
@RequiredArgsConstructor
@Slf4j
public class WebhookServer {

    private final NotificationMapper notificationMapper = new NotificationMapper(new ObjectMapper());
    private final WebhookHandler greenapiWebhookHandler = System.out::println;

    @PostMapping("/webhook")
    @ResponseStatus(HttpStatus.OK)
    public void receiveWebhook(@RequestBody String jsonString) {
        greenapiWebhookHandler.handle(notificationMapper.get(jsonString));
    }
}
