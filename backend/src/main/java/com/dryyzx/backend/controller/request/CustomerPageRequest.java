package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class CustomerPageRequest extends BaseRequest{
    private String id;
    private String custName;
    private String custIDCard;
    private String phone;
}
