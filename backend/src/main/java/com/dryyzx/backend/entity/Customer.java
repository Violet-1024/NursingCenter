package com.dryyzx.backend.entity;

import lombok.Data;

@Data
public class Customer {
    private Integer id;
    private String custName;
    private String custSex;
    private Integer custAge;
    private String custLevel;
    private String phone;
    private String custIDCard;
    private String custEmail;
    private String username;
    private String password;
    private String status;
    private String nursingLevel;
    private String remark;
    private double wallet;
}
