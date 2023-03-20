package com.dryyzx.backend.controller.request;

import lombok.Data;

@Data
public class HRankPageRequest extends BaseRequest{
    public String performance;
    public String state;
}