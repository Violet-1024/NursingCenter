package com.dryyzx.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer orderID;
    private Integer foodid;
    private Integer id;
    private String status;
//    private String  custName;
//    private String  foodName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonIgnore
    private Date orderTime;
}
