package com.dryyzx.backend.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EvaDisRequest extends BaseRequest {
    String title;
    String dishScore;
}
