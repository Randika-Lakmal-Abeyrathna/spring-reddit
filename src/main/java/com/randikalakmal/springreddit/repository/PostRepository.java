package com.randikalakmal.springreddit.repository;


import com.randikalakmal.springreddit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
