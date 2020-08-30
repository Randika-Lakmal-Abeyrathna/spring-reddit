package com.randikalakmal.springreddit.repository;

import com.randikalakmal.springreddit.model.Post;
import com.randikalakmal.springreddit.model.User;
import com.randikalakmal.springreddit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
