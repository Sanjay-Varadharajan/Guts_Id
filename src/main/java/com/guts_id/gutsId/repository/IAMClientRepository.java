package com.guts_id.gutsId.repository;

import com.guts_id.gutsId.client.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAMClientRepository extends JpaRepository<OAuthClient,Integer> {

    Optional<OAuthClient> findByClientId(String clientId);

    boolean existsByClientId(String name);

    OAuthClient findByEnabled(boolean enabled);

}