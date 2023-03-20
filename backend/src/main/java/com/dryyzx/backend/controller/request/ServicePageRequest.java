package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class ServicePageRequest extends BaseRequest {
    public String name;
    public String status;
}
