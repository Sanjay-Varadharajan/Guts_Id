package com.guts_id.gutsId.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IamTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
