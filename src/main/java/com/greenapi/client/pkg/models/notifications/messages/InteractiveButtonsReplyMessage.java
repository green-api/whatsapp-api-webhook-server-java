package com.greenapi.client.pkg.models.notifications.messages;

import com.greenapi.client.pkg.models.notifications.messages.messageData.InteractiveButtonsReplyData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteractiveButtonsReplyMessage {
    private String typeMessage;
    private InteractiveButtonsReplyData templateButtonReplyMessage;
}
