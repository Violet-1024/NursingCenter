package com.dryyzx.backend.controller.request;


import lombok.Data;

import java.util.Date;

@Data
public class OutingPageRequest extends BaseRequest{
    private String name;
    private String companion;
    private String status;
}
