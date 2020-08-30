package com.randikalakmal.springreddit.service;

import com.randikalakmal.springreddit.exception.PostNotFoundException;
import com.randikalakmal.springreddit.mapper.CommentMapper;
import com.randikalakmal.springreddit.model.Comment;
import com.randikalakmal.springreddit.model.NotificationEmail;
import com.randikalakmal.springreddit.model.Post;
import com.randikalakmal.springreddit.model.User;
import com.randikalakmal.springreddit.repository.CommentRepository;
import com.randikalakmal.springreddit.repository.PostRepository;
import com.randikalakmal.springreddit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.randikalakmal.springreddit.dto.CommentDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL="";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentDto commentDto){

        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));

        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() +" Commented on your post."+POST_URL);
        sendCommentNotification(message,post.getUser());

    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername()+"Comment on your post",user.getEmail(),message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post).stream()
                .map(commentMapper::mapToDto).collect(Collectors.toList());

    }

    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream().map(commentMapper::mapToDto).collect(Collectors.toList());

    }
}
