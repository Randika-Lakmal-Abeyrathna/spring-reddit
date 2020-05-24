package com.randikalakmal.springreddit.repository;

import com.randikalakmal.springreddit.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
}
