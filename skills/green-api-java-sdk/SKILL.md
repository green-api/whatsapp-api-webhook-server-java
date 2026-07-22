---
name: green-api-java-sdk
metadata:
  version: 0.1.0
description: Implement WhatsApp integrations in Java with com.green-api:whatsapp-api-client-java 0.1.8. Use for GREEN-API sending, files, polling, webhooks, account, groups, journals, queues, marking, and service operations.
---

# GREEN-API Java SDK 0.1.8

Use only with com.green-api:whatsapp-api-client-java:0.1.8. This skill is inventory-locked: use only members and DTOs in references/sdk-inventory.md. Read the linked official GREEN-API page before every implementation; it is authoritative for semantics, parameters, limits, notification formats, and errors.

Before a live action confirm greenApi.account.getStateInstance() is authorized, keep tokens in environment variables, check ResponseEntity status and body, and run references/verification.md.

## Initialization

    GreenApi greenApi = new GreenApi(new RestTemplate(),
        "https://media.green-api.com", "https://api.green-api.com",
        System.getenv("GREEN_API_INSTANCE_ID"), System.getenv("GREEN_API_TOKEN"));

Constructor order is exactly RestTemplate, hostMedia, host, instanceId, instanceToken. Spring Boot uses green-api.host, green-api.hostMedia, green-api.instanceId, green-api.token and component scanning com.greenapi.client.

## Send text

    var response = greenApi.sending.sendMessage(OutgoingMessage.builder()
        .chatId("79990000000@c.us").message("Hello from GREEN-API").build());
    if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
        throw new IllegalStateException("sendMessage failed: " + response.getStatusCode());
    String idMessage = response.getBody().getIdMessage();

chatId and message are required. Personal chats use <digits>@c.us; groups use <group-id>@g.us. Official SendMessage limit: 20,000 characters, UTF-8 without BOM.

## Send file

    File file = new File("/absolute/path/report.pdf");
    var response = greenApi.sending.sendFileByUpload(OutgoingFileByUpload.builder()
        .chatId("79990000000@c.us").file(file).fileName(file.getName()).caption("Report").build());

For repeated delivery use uploadFile(File) then sendFileByUrl(OutgoingFileByUrl). See references/sending-and-files.md.

## Poll or webhook

receiveNotification() returns body "null" when empty. Process FIFO and delete only after successful handling:

    var json = greenApi.receiving.receiveNotification().getBody();
    if (json != null && !"null".equals(json)) {
        var notification = new NotificationMapper().get(json);
        handle(notification);
        greenApi.receiving.deleteNotification(notification.getReceiptId());
    }

Configure InstanceSettingsReq.webhookUrl and switches for direct HTTP webhooks, parse POST JSON with NotificationMapper.get(String), and never delete a queue notification in that handler. WebhookConsumer.start(WebhookHandler) is polling, not an HTTP endpoint.

## Pitfalls

- Authorization is required; queued messages may remain 24 hours.
- Set documented delaySendMessagesMilliseconds; never burst messages.
- Use ASCII @c.us and @g.us suffixes.
- Source marks sendButtons, sendTemplateButtons, and sendListMessage temporarily unavailable (HTTP 403).
