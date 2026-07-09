package com.greenapi.client.pkg.models.notifications.messages;

import com.greenapi.client.pkg.models.notifications.messages.messageData.InteractiveButtonsData;
import com.greenapi.client.pkg.models.notifications.messages.quotedMessageData.QuotedMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteractiveButtonsMessage {
    private String typeMessage;
    private InteractiveButtonsData interactiveButtons;
    private QuotedMessage quotedMessage;
}
