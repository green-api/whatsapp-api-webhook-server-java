package com.greenapi.whatsappapiserverjava.pkg.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CallButton {
    private String displayText;
    private Long phoneNumber;
}
