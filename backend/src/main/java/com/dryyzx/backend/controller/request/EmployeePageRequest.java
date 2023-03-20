package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class EmployeePageRequest extends BaseRequest{
    private String name;
    private String phone;
}
