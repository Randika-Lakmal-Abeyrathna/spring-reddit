package com.randikalakmal.springreddit.mapper;

import com.randikalakmal.springreddit.dto.PostRequest;
import com.randikalakmal.springreddit.dto.PostResponse;
import com.randikalakmal.springreddit.model.Post;
import com.randikalakmal.springreddit.model.Subreddit;
import com.randikalakmal.springreddit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "subreddit",source = "subreddit")
    @Mapping(target = "user",source = "user")
    @Mapping(target = "description",source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id",source = "postId")
    @Mapping(target = "postName",source = "name")
    @Mapping(target = "description",source = "description")
    @Mapping(target = "url",source = "url")
    @Mapping(target = "subredditName",source = "subreddit.name")
    @Mapping(target = "userName",source = "user.username")
    PostResponse mapToDto(Post post);
}
