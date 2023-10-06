package com.greenapi.whatsappapiserverjava.pkg.models.notifications.messages;

import com.greenapi.whatsappapiserverjava.pkg.models.notifications.messages.messageData.ContactMessageData;
import com.greenapi.whatsappapiserverjava.pkg.models.notifications.messages.messageData.QuotedMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {
    private String typeMessage;
    private ContactMessageData contactMessageData;
    private QuotedMessage quotedMessage;
}
