package com.dryyzx.backend.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Date;

@Data
public class OrderPageRequest extends BaseRequest{
    private Integer orderID;
    private Integer foodid;
    private Integer id;
    private String status;
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
//    private Date orderTime;
}

