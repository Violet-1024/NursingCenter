package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class BedPageRequest extends BaseRequest{
    private Integer bedID;
    private Integer roomID;
    private String status;
    private String bedLevel;
}
