package com.greenapi.client.pkg.models.notifications;

import com.greenapi.client.pkg.models.notifications.messages.InteractiveButtonsMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteractiveButtonsMessageWebhook extends MessageWebhook {
    private InteractiveButtonsMessage messageData;
}
