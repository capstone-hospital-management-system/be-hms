package com.capstone.alta.hms.api.v1.core.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponseDTO<T> implements Serializable {
    private String message;
    private T data;

    public BaseResponseDTO () { }

    public BaseResponseDTO(
        String message,
        T data
    ) {
        this.message = message;
        this.data = data;
    }
}