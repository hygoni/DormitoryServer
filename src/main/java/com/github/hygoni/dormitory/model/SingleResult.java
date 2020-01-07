package com.github.hygoni.dormitory.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleResult<T> extends CommonResult {
    private T data;
}
