package com.sxp.assign1.model;

import lombok.Data;

@Data
public class ResponseData {
    private Integer code;
    private String msg;
    private Object data;
}
