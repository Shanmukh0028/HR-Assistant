package com.payu.hrassistant.usermanagement.repository;

import com.payu.hrassistant.usermanagement.model.SessionStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionStorageRepository extends JpaRepository<SessionStorage, Long> {
    Optional<SessionStorage> findBySessionId(String sessionId);
}