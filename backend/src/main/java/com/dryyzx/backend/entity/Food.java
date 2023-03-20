package com.dryyzx.backend.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Food {
    private Integer foodid;
    private String name;
    private Double protein;
    private Double cho;
    private Double heat;
    private Double fat;
    private Double price;
}
