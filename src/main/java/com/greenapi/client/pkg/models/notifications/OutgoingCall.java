package com.greenapi.client.pkg.models.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.greenapi.client.pkg.models.notifications.messages.CallParticipant;
import com.greenapi.client.pkg.models.notifications.messages.InstanceData;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutgoingCall extends NotificationBody {
    private InstanceData instanceData;
    private Long timestamp;
    private String idMessage;
    private String from;
    private Boolean isVideo;
    private Integer duration;
    private String status;
    private List<CallParticipant> participants;
}
