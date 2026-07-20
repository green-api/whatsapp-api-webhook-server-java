# Verification

In a clean Maven project, add the dependency from SKILL.md and run:

    grep -RInE 'sendMessage|sendFileByUpload|sendFileByUrl|uploadFile|receiveNotification|deleteNotification|setSetting|WebhookConsumer|NotificationMapper' ~/.m2/repository/com/green-api/whatsapp-api-client-java/0.1.8
    mvn -q -DskipTests compile

Before a live send, call `greenApi.account.getStateInstance()` and proceed only for the documented authorized state. Use a consented test chat, require 2xx and non-empty `SendMessageResp.idMessage`, and keep credentials in environment variables. A successful response queues the message; use notifications or journals for delivery confirmation.
