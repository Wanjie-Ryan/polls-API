package com.voting.votingapp.service;

import com.voting.votingapp.model.OptionVote;
import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    //METHOD FOR GETTING A SINGLE POLL
    public Optional<Poll> getPollById(Long id) {

        Optional<Poll> getSinglePoll = pollRepository.findById(id);
        return getSinglePoll;

    }

    public void vote(Long pollId, int optionIndex) {

        // 1. get entire poll from db
        Optional<Poll> specificPoll = getPollById(pollId);
        // 2. get all options
        List<OptionVote> options = specificPoll.get().getOptions();
        // 3. if index for vote is invalid, throw exception
        if (optionIndex < 0 || optionIndex >= options.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid option index");
        }
        // 4. get selected option
        OptionVote selectedOption = options.get(optionIndex);
        // 5. increment vote count for selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        // 6. save the incremented vote option to the DB
        pollRepository.save(specificPoll.get());


    }
}
