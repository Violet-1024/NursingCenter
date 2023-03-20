package com.dryyzx.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Bed {
    private Integer bedID;
    private Integer roomID;
    private Integer staffID;
    private String status;
    private String bedLevel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

}
