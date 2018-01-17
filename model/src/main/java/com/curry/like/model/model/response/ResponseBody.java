package com.curry.like.model.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public  class  ResponseBody<T> implements Serializable{

    private Integer code;
    private T obj;
    private String message;
}
