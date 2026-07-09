package com.greenapi.client.pkg.models.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.greenapi.client.pkg.models.notifications.messages.InstanceData;
import com.greenapi.client.pkg.models.notifications.messages.messageData.QuotaData;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotaExceeded extends NotificationBody {
    private InstanceData instanceData;
    private Long timestamp;
    private QuotaData quotaData;
}
