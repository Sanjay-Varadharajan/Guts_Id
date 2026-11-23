package com.guts_id.gutsId.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IamLoginRequest {

    private String userMail;

    private String userPassword;
}
