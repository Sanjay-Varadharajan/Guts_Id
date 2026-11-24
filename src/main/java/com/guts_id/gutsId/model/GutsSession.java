package com.guts_id.gutsId.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GutsSession {


    private String sessionId; //unique guts session id

    private String iamUserId;

    private Instant createdAt;

    private Instant expiresAt;

}
