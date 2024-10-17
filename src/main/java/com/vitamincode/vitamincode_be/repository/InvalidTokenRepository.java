package com.vitamincode.vitamincode_be.repository;

import com.vitamincode.vitamincode_be.entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
    Boolean existsInvalidTokenByInvalidTokenId(String invalidTokenId);
}
