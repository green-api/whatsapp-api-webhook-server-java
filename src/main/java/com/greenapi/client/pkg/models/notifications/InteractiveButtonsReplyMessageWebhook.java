package com.greenapi.client.pkg.models.notifications;

import com.greenapi.client.pkg.models.notifications.messages.InteractiveButtonsReplyMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteractiveButtonsReplyMessageWebhook extends MessageWebhook {
    private InteractiveButtonsReplyMessage messageData;
}
