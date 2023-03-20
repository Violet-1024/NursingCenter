package com.dryyzx.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Evaluate {
    String evaId;
    String userId;
    String userName;
    String nurse;
    String serviceScore;

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    Date servTime;
    Date createTime;
    String professional;
    String content;
}
