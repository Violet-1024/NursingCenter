package com.dryyzx.backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Date;

@Data
public class NursingRecord {

    private Integer recordID;
    private Integer id;
    private Integer eid;
    private String status;
    private String nursingName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonIgnore
    private Date nursingTime;

}
