package com.guts_id.gutsId.logging;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<AuthLog,Integer> {
}
