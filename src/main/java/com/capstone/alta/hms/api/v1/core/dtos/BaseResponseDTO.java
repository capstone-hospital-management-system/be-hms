package com.capstone.alta.hms.api.v1.core.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponseDTO<T> implements Serializable {
    private String code;
    private String status;
    private String message;
    private T data;
    private MetaResponseDTO meta;

    public BaseResponseDTO () { }

    public BaseResponseDTO(
        String code,
        String status,
        String message,
        T data
    ) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public BaseResponseDTO(
        String code,
        String status,
        String message,
        T data,
        MetaResponseDTO meta
    ) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
        this.meta = meta;
    }
}
