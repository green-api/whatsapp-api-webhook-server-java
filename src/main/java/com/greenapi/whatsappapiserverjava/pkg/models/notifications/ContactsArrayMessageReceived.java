package com.greenapi.whatsappapiserverjava.pkg.models.notifications;

import com.greenapi.whatsappapiserverjava.pkg.models.notifications.messages.ContactsArrayMessage;
import com.greenapi.whatsappapiserverjava.pkg.models.notifications.messages.InstanceData;
import com.greenapi.whatsappapiserverjava.pkg.models.notifications.messages.SenderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactsArrayMessageReceived implements NotificationBody {
    private String typeWebhook;
    private InstanceData instanceData;
    private Long timestamp;
    private String idMessage;
    private SenderData senderData;
    private ContactsArrayMessage messageData;
}
