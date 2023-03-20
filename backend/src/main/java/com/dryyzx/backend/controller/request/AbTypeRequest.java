package com.dryyzx.backend.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AbTypeRequest extends BaseRequest {
    String title;
    Integer status;
}
