package com.capstone.alta.hms.api.v1.securities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationResponseDTO<T> implements Serializable {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType = "Bearer";

    @JsonProperty("user_principal")
    private T userPrincipal;

    public AuthenticationResponseDTO(String accessToken, T userPrincipal) {
        this.accessToken = accessToken;
        this.userPrincipal = userPrincipal;
    }
}
