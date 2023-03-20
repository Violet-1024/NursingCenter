package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class NursingRecordPageRequest extends BaseRequest{
    private Integer recordID;
    private Integer eid;
    private Integer id;
    private String status;
    private String nursingName;
}
