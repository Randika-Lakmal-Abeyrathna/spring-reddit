package com.randikalakmal.springreddit.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.randikalakmal.springreddit.dto.PostRequest;
import com.randikalakmal.springreddit.dto.PostResponse;
import com.randikalakmal.springreddit.model.Post;
import com.randikalakmal.springreddit.model.Subreddit;
import com.randikalakmal.springreddit.model.User;
import com.randikalakmal.springreddit.repository.CommentRepository;
import com.randikalakmal.springreddit.repository.VoteRepository;
import com.randikalakmal.springreddit.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "voteCount",constant = "0")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user) ;

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postName", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount",expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post){
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post){
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
