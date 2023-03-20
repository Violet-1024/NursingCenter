package com.dryyzx.backend.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class EvaRequest extends BaseRequest{

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    Date servTime;
    String evaId;
    String userName;
    String nurse;
}
