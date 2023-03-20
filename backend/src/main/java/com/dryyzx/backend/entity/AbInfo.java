package com.dryyzx.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AbInfo {
    String abId;
    String customerId;
    String title;
    Date startDate;
    int state;
    String remark;
    int audiStatus;
    String publisher;
}