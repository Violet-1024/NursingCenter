package com.dryyzx.backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class HRank {
    private Integer rankid;
    private String performance;
    private String describe;
    private String state;
}
