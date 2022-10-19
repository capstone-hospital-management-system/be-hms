package com.capstone.alta.hms.api.v1.core.dtos;

import java.io.Serializable;

public class MetaResponseDTO implements Serializable {
    private int page;
    private int per_page;
    private int total_page;
    private int total_data;

    public MetaResponseDTO(
        int page,
        int per_page,
        int total_page,
        int total_data
    ) {
        this.page = page;
        this.per_page = per_page;
        this.total_page = total_page;
        this.total_data = total_data;
    }
}