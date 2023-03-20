package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class BedRecordPageRequest extends BedPageRequest{
    private Integer bedID;
    private Integer roomID;
    private Integer staffID;
    private String custName;

}
