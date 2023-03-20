package com.dryyzx.backend.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AbInfoRequest extends BaseRequest {
    private Date startDate;
    private String publisher;
    private String title;
}
