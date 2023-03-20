package com.dryyzx.backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class HDatas {
    private Integer customerid;
    private String name;
    private String sex;
    private String age;
    private String performance;
    private double rate;
    private double sugar;
    private double oxygen;

}
