package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class NursingPageRequest extends BaseRequest{
    private String nursingID;
    private String nursingLevel;
    private String status;
}
