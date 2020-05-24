package com.randikalakmal.springreddit.repository;

import com.randikalakmal.springreddit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
