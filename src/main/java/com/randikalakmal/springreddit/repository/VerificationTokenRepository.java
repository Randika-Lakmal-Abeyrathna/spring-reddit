package com.randikalakmal.springreddit.repository;

import com.randikalakmal.springreddit.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
}
