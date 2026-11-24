package com.guts_id.gutsId.service;


import com.guts_id.gutsId.model.GutsSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GutsSessionService {

    private final RedisTemplate<String, GutsSession> redisTemplate;



    public GutsSession createSession(String iamUserId){

        String sessionId= UUID.randomUUID().toString();

        Instant createdAt=Instant.now();

        Instant expiresAt= createdAt.plusSeconds(1800);

        GutsSession gutsSession=new GutsSession(sessionId,iamUserId,createdAt,expiresAt);

        redisTemplate.opsForValue().set(
                "gutsid:session:" + sessionId,
                gutsSession,
                Duration.ofMinutes(30)
                );


        return gutsSession;
    }

    public GutsSession getSession(String sessionId){
        return redisTemplate.opsForValue().get("gutsid:session:" + sessionId);
    }

    private void invalidateSession(String sessionId){
        redisTemplate.delete("gutsid:session:" + sessionId);
    }

    public boolean isSessionValid(String sessionId) {
        GutsSession session = getSession(sessionId);

        return session!= null &&
                session.getExpiresAt().isAfter(Instant.now());
    }

}
