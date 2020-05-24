package com.randikalakmal.springreddit.repository;

import com.randikalakmal.springreddit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
