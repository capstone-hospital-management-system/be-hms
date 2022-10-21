package com.capstone.alta.hms.api.v1.core.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class PageBaseResponseDTO<T> implements Serializable {
    private String code;
    private HttpStatus status;
    private String message;
    private T data;
    private MetaResponseDTO meta;

    public PageBaseResponseDTO () { }

    public PageBaseResponseDTO(
        String code,
        HttpStatus status,
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