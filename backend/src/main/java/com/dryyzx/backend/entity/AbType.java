package com.dryyzx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AbType {
    String abTypeId;
    int state;
    int status;
    String title;
    Date publishTime;
}
