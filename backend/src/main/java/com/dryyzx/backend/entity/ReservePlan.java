package com.dryyzx.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Date;

@Data
public class ReservePlan {
    private Integer planID;
    private String planName;
    private String planType;
    private String state;

    private String accentType;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonIgnore
    private Date newTime;


}
