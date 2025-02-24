package com.voting.votingapp.service;

import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    //METHOD FOR CREATING A NEW POLL
    public Poll createPoll(Poll poll) {
        if (poll.getQuestion() == null || poll.getQuestion().isEmpty()) {
//            throw new IllegalArgumentException("Question cannot be empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question cannot be empty");
        }
        return pollRepository.save(poll);
    }

    // METHOD FOR GETTING ALL POLLS
    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

}
