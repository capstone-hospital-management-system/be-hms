package com.capstone.alta.hms.api.v1.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MetaResponseDTO implements Serializable {
    private int page;

    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total_page")
    private int totalPage;
    @JsonProperty("total_data")
    private long totalData;

    public MetaResponseDTO(
        int page,
        int perPage,
        int totalPage,
        long totalData
    ) {
        this.page = page;
        this.perPage = perPage;
        this.totalPage = totalPage;
        this.totalData = totalData;
    }
}