package com.dryyzx.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Employee {
    private Integer eid;
    private String username;
    private String password;
    private String position;
    private String name;
    private String gender;
    private String phone;
    private String custIDCard;
    private String address;
    private String email;
    private String status;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonIgnore
    private Data jointime;
}
