package com.dryyzx.backend.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReservePlanPageRequest extends BaseRequest {
    private Integer planID;
    private String planName;
    private String planType;
    private String accentType;
}
