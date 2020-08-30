package com.randikalakmal.springreddit.repository;


import com.randikalakmal.springreddit.model.Post;
import com.randikalakmal.springreddit.model.Subreddit;
import com.randikalakmal.springreddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post>  findByUser(User user);
}
