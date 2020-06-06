package com.randikalakmal.springreddit.repository;

import com.randikalakmal.springreddit.model.Comment;
import com.randikalakmal.springreddit.model.Post;
import com.randikalakmal.springreddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
