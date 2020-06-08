package com.randikalakmal.springreddit.service;

import com.randikalakmal.springreddit.dto.VoteDto;
import com.randikalakmal.springreddit.exception.PostNotFoundException;
import com.randikalakmal.springreddit.exception.SpringRedditException;
import com.randikalakmal.springreddit.model.Post;
import com.randikalakmal.springreddit.model.Vote;
import com.randikalakmal.springreddit.repository.PostRepository;
import com.randikalakmal.springreddit.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import static com.randikalakmal.springreddit.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {

        Post post = postRepository.findById(voteDto.getPostId()).
                orElseThrow(() -> new PostNotFoundException("Post Not Found with id " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())){
            throw new SpringRedditException("You Have already "+ voteDto.getVoteType() +" 'd for this post");

        }

        if (UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount()+1);

        }else{
            post.setVoteCount(post.getVoteCount()-1);
        }
        voteRepository.save(mapToVote(voteDto,post));
        postRepository.save(post);


    }

    // This is how we map
    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
