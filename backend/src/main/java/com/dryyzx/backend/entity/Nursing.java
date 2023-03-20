package com.dryyzx.backend.entity;

import lombok.Data;

@Data
public class Nursing {
    private Integer nursingID;
    private String nursingLevel;
    private String nursingName;
    private double price;
    private String introduce;
    private String status;
}
