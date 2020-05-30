package com.randikalakmal.springreddit.service;

import com.randikalakmal.springreddit.dto.SubredditDto;
import com.randikalakmal.springreddit.exception.SpringRedditException;
import com.randikalakmal.springreddit.mapper.SubredditMapper;
import com.randikalakmal.springreddit.model.Subreddit;
import com.randikalakmal.springreddit.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){

        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }
    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll().stream().map(subredditMapper::mapSubrediitToDto)
                .collect(toList());
    }


    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit= subredditRepository.findById(id)
                .orElseThrow(()-> new SpringRedditException("No Subreddit found with id"));
        return subredditMapper.mapSubrediitToDto(subreddit);
    }
}
