package com.capstone.alta.hms.api.v1.core.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class PageBaseResponseDTO<T> implements Serializable {
    private String message;
    private MetaResponseDTO meta;
    private T data;

    public PageBaseResponseDTO () { }

    public PageBaseResponseDTO(
        String message,
        MetaResponseDTO meta,
        T data
    ) {
        this.message = message;
        this.meta = meta;
        this.data = data;
    }
}