package com.guts_id.gutsId.repository;

import com.guts_id.gutsId.client.IAMClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAMClientRepository extends JpaRepository<IAMClient,Integer> {

    Optional<IAMClient> findByClientId(String clientId);

    boolean existsByClientId(String name);

    IAMClient findByEnabled(boolean enabled);

}