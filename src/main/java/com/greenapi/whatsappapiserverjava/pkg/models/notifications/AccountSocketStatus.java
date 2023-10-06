package com.greenapi.whatsappapiserverjava.pkg.models.notifications;

import com.greenapi.whatsappapiserverjava.pkg.models.notifications.messages.InstanceData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSocketStatus implements NotificationBody {
    private String typeWebhook;
    private InstanceData instanceData;
    private Long timestamp;
    private String statusInstance;
}
