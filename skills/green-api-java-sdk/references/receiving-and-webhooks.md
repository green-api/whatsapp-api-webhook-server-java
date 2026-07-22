# Receiving and webhooks

| SDK call | Official page | SDK behavior |
|---|---|---|
| `receiving.receiveNotification()` | [ReceiveNotification](https://green-api.com/en/docs/api/receiving/technology-http-api/ReceiveNotification/) | `ResponseEntity<String>`; body is literal `null` when queue is empty |
| `receiving.deleteNotification(Integer)` | [DeleteNotification](https://green-api.com/en/docs/api/receiving/technology-http-api/DeleteNotification/) | delete the receipt only after processing |
| `receiving.downloadFile(MessageReq)` | [DownloadFile](https://green-api.com/en/docs/api/receiving/files/DownloadFile/) | `ResponseEntity<byte[]>` |

The official [HTTP API guide](https://green-api.com/en/docs/api/receiving/technology-http-api/) requires FIFO receive → process → delete. Notifications are retained for 24 hours; repeat requests ending in HTTP 500+. Configure notification switches with `account.setSetting(InstanceSettingsReq)`.

For direct webhooks set `InstanceSettingsReq.webhookUrl` and the required switch fields, then accept POST JSON and parse it with `NotificationMapper.get(String)`. Read the official [webhook endpoint](https://green-api.com/en/docs/api/receiving/technology-webhook/) and applicable [notification format](https://green-api.com/en/docs/api/receiving/notifications-format/) page before branching by type. `WebhookConsumer` is a polling consumer, not an HTTP endpoint.
